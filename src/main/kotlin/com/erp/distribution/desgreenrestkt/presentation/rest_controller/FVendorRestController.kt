package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.FVendor
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFVendorUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FVendorRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FVendorRestController @Autowired constructor(
    val getFVendorUseCase: GetFVendorUseCase
){

    @RequestMapping(value = ["/rest/getFVendorById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFVendorById(@PathVariable("id") id: Int): FVendorRes {
        return getFVendorUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFVendor"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allVendorEntity: List<FVendorRes>
        get() = getFVendorUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFVendorByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFVendorByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FVendorRes> {
        return getFVendorUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFVendorByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFVendorByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FVendorRes> {
        return getFVendorUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFVendor"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFVendor(@RequestBody fVendorEntityNew: FVendorRes): FVendorRes {
        fVendorEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFVendorUseCase.save(fVendorEntityNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFVendor/{id}"], method = [RequestMethod.PUT])
    fun updateFVendorInfo(@PathVariable("id") id: Int, @RequestBody fVendorEntityUpdated: FVendorRes?): FVendorRes {
        val fVendor = getFVendorUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fVendorEntityUpdated != null) {
            fVendorEntityUpdated.id = fVendor.id
            if (fVendor.fdivisionBean >0) fVendorEntityUpdated.fdivisionBean = fVendor.fdivisionBean
            getFVendorUseCase.save(fVendorEntityUpdated.toDomain())
            return fVendorEntityUpdated
        }
        return fVendor.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFVendor/{id}"], method = [RequestMethod.DELETE])
    fun deleteFVendor(@PathVariable("id") id: Int): FVendorRes? {
        val fVendor = getFVendorUseCase.findById(id)
        if (fVendor != null) {
            getFVendorUseCase.delete(fVendor)
        }
        return fVendor.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FVendorRestController::class.java)
    }
}