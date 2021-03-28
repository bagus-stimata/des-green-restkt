package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFMaterialGroup1UseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialGroup1Res
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialGroup1RestController @Autowired constructor(
    val getFMaterialGroup1UseCase: GetFMaterialGroup1UseCase
){

    @RequestMapping(value = ["/rest/getFMaterialGroup1ById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialGroup1ById(@PathVariable("id") id: Int): FMaterialGroup1Res {
        return getFMaterialGroup1UseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialGroup1"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialGroup1Entity: List<FMaterialGroup1Res>
        get() = getFMaterialGroup1UseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialGroup1Res> {
        return getFMaterialGroup1UseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByCompany/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByCompany(@PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialGroup1Res> {
        return getFMaterialGroup1UseCase.findByCompanyRes(fcompanyBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialGroup1Res> {
        return getFMaterialGroup1UseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialGroup1"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialGroup1(@RequestBody fMaterialGroup1ResNew: FMaterialGroup1Res): FMaterialGroup1Res {
        fMaterialGroup1ResNew.id = 0 //Memastikan ID adalah Nol
        return getFMaterialGroup1UseCase.save(fMaterialGroup1ResNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialGroup1/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialGroup1Info(@PathVariable("id") id: Int, @RequestBody fMaterialGroup1ResUpdated: FMaterialGroup1Res?): FMaterialGroup1Res {
        val fMaterialGroup1 = getFMaterialGroup1UseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialGroup1ResUpdated != null) {
            fMaterialGroup1ResUpdated.id = fMaterialGroup1.id
            if (fMaterialGroup1.fdivisionBean >0) fMaterialGroup1ResUpdated.fdivisionBean = fMaterialGroup1.fdivisionBean
            getFMaterialGroup1UseCase.save(fMaterialGroup1ResUpdated.toDomain())
            return fMaterialGroup1ResUpdated
        }
        return fMaterialGroup1.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialGroup1/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialGroup1(@PathVariable("id") id: Int): FMaterialGroup1Res? {
        val fMaterialGroup1 = getFMaterialGroup1UseCase.findById(id)
        if (fMaterialGroup1 != null) {
            getFMaterialGroup1UseCase.delete(fMaterialGroup1)
        }
        return fMaterialGroup1.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialGroup1RestController::class.java)
    }
}