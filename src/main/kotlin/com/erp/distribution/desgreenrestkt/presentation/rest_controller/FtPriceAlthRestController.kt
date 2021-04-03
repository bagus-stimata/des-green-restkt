package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFtPriceAlthUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FtPriceAlthRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class FtPriceAlthRestController @Autowired constructor(
    val getFtPriceAlthUseCase: GetFtPriceAlthUseCase
) {

    @RequestMapping(value = ["/rest/getFtPriceAlthById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtPriceAlthById(@PathVariable("id") id: Int): FtPriceAlthRes {
        return getFtPriceAlthUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFtPriceAlth"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val getAllFtPriceAlth: List<FtPriceAlthRes>
        get() = getFtPriceAlthUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAlltPriceAlthByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAlltPriceAlthByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FtPriceAlthRes> {
//        System.out.println((">> ${fdivisionBean} >> ${fcompanyBean}" ))
        return getFtPriceAlthUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean).stream().filter { x: FtPriceAlthRes -> x.statusActive == true }.collect(Collectors.toList())
//        return listOf<FtPriceAlthRes>()
    }

    @RequestMapping(value = ["/rest/createFtPriceAlth"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPriceAlth(@RequestBody ftPriceAlthEntityNew: FtPriceAlthRes): FtPriceAlthRes {
        ftPriceAlthEntityNew.id = 0 //Pastikan ID nya nol untuk Create Baru
        return getFtPriceAlthUseCase.save(ftPriceAlthEntityNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/updateFtPriceAlth/{id}"], method = [RequestMethod.PUT])
    fun updateFtPriceAlthInfo(@PathVariable("id") id: Int, @RequestBody ftPriceAlthEntityUpdated: FtPriceAlthRes?): FtPriceAlthRes {
        val ftPriceAlth = getFtPriceAlthUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPriceAlthEntityUpdated != null) {
            ftPriceAlthEntityUpdated.id = ftPriceAlth.id
            if (ftPriceAlth.fdivisionBean >0) ftPriceAlthEntityUpdated.fdivisionBean = ftPriceAlth.fdivisionBean
            getFtPriceAlthUseCase.save(ftPriceAlthEntityUpdated.toDomain()).toResponse()
            return ftPriceAlthEntityUpdated
        }
        return ftPriceAlth.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPriceAlth/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPriceAlth(@PathVariable("id") id: Int): FtPriceAlthRes {
        val ftPriceAlth = getFtPriceAlthUseCase.findById(id)
//        if (ftPriceAlth != null) {
//            getFtPriceAlthUseCase.delete(ftPriceAlth)
//        }
        ftPriceAlth?.let {
            getFtPriceAlthUseCase.delete(ftPriceAlth)
        }
        getFtPriceAlthUseCase.findById(id)
        return ftPriceAlth.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPriceAlthRestController::class.java)
    }
}