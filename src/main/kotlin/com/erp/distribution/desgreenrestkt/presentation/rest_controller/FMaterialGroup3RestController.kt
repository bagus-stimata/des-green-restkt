package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFMaterialGroup3UseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialGroup3Res
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialGroup3RestController @Autowired constructor(
    val getFMaterialGroup3UseCase: GetFMaterialGroup3UseCase
){

    @RequestMapping(value = ["/rest/getFMaterialGroup3ById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialGroup3ById(@PathVariable("id") id: Int): FMaterialGroup3Res {
        return getFMaterialGroup3UseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialGroup3"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialGroup3Entity: List<FMaterialGroup3Res>
        get() = getFMaterialGroup3UseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFMaterialGroup3ByParent/{fmaterialGroup2Bean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup3ByParentId(@PathVariable("fmaterialGroup2Bean") fmaterialGroup2Bean: Int): List<FMaterialGroup3Res> {
        return getFMaterialGroup3UseCase.findByParentRes(fmaterialGroup2Bean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup3ByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup3ByListParentId(@RequestBody listFMaterialGroup2Bean: List<Int>): List<FMaterialGroup3Res> {
        return getFMaterialGroup3UseCase.findByListOfParentRes(listFMaterialGroup2Bean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialGroup3"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialGroup3(@RequestBody fMaterialGroup3ResNew: FMaterialGroup3Res): FMaterialGroup3Res {
        fMaterialGroup3ResNew.id = 0 //Memastikan ID adalah Nol
        return getFMaterialGroup3UseCase.save(fMaterialGroup3ResNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialGroup3/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialGroup3Info(@PathVariable("id") id: Int, @RequestBody fMaterialGroup3ResUpdated: FMaterialGroup3Res): FMaterialGroup3Res {
        val fMaterialGroup3 = getFMaterialGroup3UseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialGroup3ResUpdated != null) {
            fMaterialGroup3ResUpdated.id = fMaterialGroup3.id
            if (fMaterialGroup3.fmaterialGroup2Bean >0) fMaterialGroup3ResUpdated.fmaterialGroup2Bean = fMaterialGroup3.fmaterialGroup2Bean
            getFMaterialGroup3UseCase.save(fMaterialGroup3ResUpdated.toDomain())
            return fMaterialGroup3ResUpdated
        }
        return fMaterialGroup3.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialGroup3/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialGroup3(@PathVariable("id") id: Int): FMaterialGroup3Res? {
        val fMaterialGroup3 = getFMaterialGroup3UseCase.findById(id)
        if (fMaterialGroup3 != null) {
            getFMaterialGroup3UseCase.delete(fMaterialGroup3)
        }
        return fMaterialGroup3.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialGroup3RestController::class.java)
    }
}