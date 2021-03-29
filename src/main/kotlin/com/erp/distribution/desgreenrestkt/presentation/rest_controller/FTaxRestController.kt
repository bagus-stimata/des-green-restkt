package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFTaxUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FTaxRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FTaxRestController @Autowired constructor(
    val getFTaxUseCase: GetFTaxUseCase
) {

    @RequestMapping(value = ["/rest/getFTaxById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFTaxById(@PathVariable("id") id: Int): FTaxRes {
        return getFTaxUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFTax"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allTaxEntities: List<FTaxRes>
        get() = getFTaxUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFTaxByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFTaxByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FTaxRes> {
        return getFTaxUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFTaxByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFTaxByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FTaxRes> {
        return getFTaxUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFTax"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFTax(@RequestBody fTaxEntityNew: FTaxRes): FTaxRes {
        fTaxEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFTaxUseCase.save(fTaxEntityNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFTax/{id}"], method = [RequestMethod.PUT])
    fun updateFTaxInfo(@PathVariable("id") id: Int, @RequestBody fTaxEntityUpdated: FTaxRes?): FTaxRes {
        val fTax = getFTaxUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fTaxEntityUpdated != null) {
            fTaxEntityUpdated.id = fTax.id
            if (fTax.fdivisionBean >0) fTaxEntityUpdated.fdivisionBean = fTax.fdivisionBean
            getFTaxUseCase.save(fTaxEntityUpdated.toDomain())
            return fTaxEntityUpdated
        }
        return fTax.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFTax/{id}"], method = [RequestMethod.DELETE])
    fun deleteFTax(@PathVariable("id") id: Int): FTaxRes? {
        val fTax = getFTaxUseCase.findById(id)
//        if (fTax != null) {
//            getFTaxUseCase.delete(fTax)
//        }
        fTax?.let {
            getFTaxUseCase.delete(it)
        }
        return fTax.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FTaxRestController::class.java)
    }
}