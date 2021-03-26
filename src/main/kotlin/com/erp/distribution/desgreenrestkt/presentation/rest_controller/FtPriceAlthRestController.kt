package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtPriceAlthJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class FtPriceAlthRestController {
    @Autowired
    var ftPriceAlthJPARepository: FtPriceAlthJPARepository? = null

    @RequestMapping(value = ["/rest/getFtPriceAlthById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtPriceAlthById(@PathVariable("id") id: Int): FtPriceAlthEntity {
        return ftPriceAlthJPARepository!!.findById(id).orElse(FtPriceAlthEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtPriceAlth"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltPriceAlthEntity: List<FtPriceAlthEntity>
        get() = ftPriceAlthJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAlltPriceAlthByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAlltPriceAlthByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FtPriceAlthEntity> {
        return ftPriceAlthJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).stream().filter { x: FtPriceAlthEntity -> x.isStatusActive == true }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/createFtPriceAlth"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPriceAlth(@RequestBody ftPriceAlthEntityNew: FtPriceAlthEntity): FtPriceAlthEntity {
        ftPriceAlthEntityNew.id = 0 //Pastikan ID nya nol untuk Create Baru
        return ftPriceAlthJPARepository!!.save(ftPriceAlthEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtPriceAlth/{id}"], method = [RequestMethod.PUT])
    fun updateFtPriceAlthInfo(@PathVariable("id") id: Int, @RequestBody ftPriceAlthEntityUpdated: FtPriceAlthEntity?): FtPriceAlthEntity {
        val ftPriceAlth = ftPriceAlthJPARepository!!.findById(id).orElse(FtPriceAlthEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPriceAlthEntityUpdated != null) {
            ftPriceAlthEntityUpdated.id = ftPriceAlth.id
            if (ftPriceAlth.fdivisionBean >0) ftPriceAlthEntityUpdated.fdivisionBean = ftPriceAlth.fdivisionBean
            ftPriceAlthJPARepository!!.save(ftPriceAlthEntityUpdated)
            return ftPriceAlthEntityUpdated
        }
        return ftPriceAlth
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPriceAlth/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPriceAlth(@PathVariable("id") id: Int): FtPriceAlthEntity? {
        val ftPriceAlth = ftPriceAlthJPARepository!!.findById(id).orElse(FtPriceAlthEntity())
        if (ftPriceAlth != null) {
            ftPriceAlthJPARepository!!.delete(ftPriceAlth)
        }
        return ftPriceAlth
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPriceAlthRestController::class.java)
    }
}