package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFMaterialGroup2UseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialGroup2Res
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialGroup2RestController @Autowired constructor(
    val getFMaterialGroup2UseCase: GetFMaterialGroup2UseCase
) {

    @RequestMapping(value = ["/rest/getFMaterialGroup2ById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialGroup2ById(@PathVariable("id") id: Int): FMaterialGroup2Res {
        return getFMaterialGroup2UseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialGroup2"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialGroup2Entity: List<FMaterialGroup2Res>
        get() = getFMaterialGroup2UseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFMaterialGroup2ByParent/{fmaterialGroup1Bean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup2ByParentId(@PathVariable("fmaterialGroup1Bean") fmaterialGroup1Bean: Int): List<FMaterialGroup2Res> {
        return getFMaterialGroup2UseCase.findByParentRes(fmaterialGroup1Bean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup2ByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup2ByListParentId(@RequestBody listFMaterialGroup1Bean: List<Int>): List<FMaterialGroup2Res> {
        return getFMaterialGroup2UseCase.findByListOfParentRes(listFMaterialGroup1Bean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialGroup2"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialGroup2(@RequestBody fMaterialGroup2ResNew: FMaterialGroup2Res): FMaterialGroup2Res {
        fMaterialGroup2ResNew.id = 0 //Memastikan ID adalah Nol
        return getFMaterialGroup2UseCase.save(fMaterialGroup2ResNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialGroup2/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialGroup2Info(@PathVariable("id") id: Int, @RequestBody fMaterialGroup2ResUpdated: FMaterialGroup2Res?): FMaterialGroup2Res {
        val fMaterialGroup2 = getFMaterialGroup2UseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialGroup2ResUpdated != null) {
            fMaterialGroup2ResUpdated.id = fMaterialGroup2.id
            if (fMaterialGroup2.fmaterialGroup1Bean >0) fMaterialGroup2ResUpdated.fmaterialGroup1Bean = fMaterialGroup2.fmaterialGroup1Bean
            getFMaterialGroup2UseCase.save(fMaterialGroup2ResUpdated.toDomain())
            return fMaterialGroup2ResUpdated
        }
        return fMaterialGroup2.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialGroup2/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialGroup2(@PathVariable("id") id: Int): FMaterialGroup2Res? {
        val fMaterialGroup2 = getFMaterialGroup2UseCase.findById(id)
        if (fMaterialGroup2 != null) {
            getFMaterialGroup2UseCase.delete(fMaterialGroup2)
        }
        return fMaterialGroup2.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialGroup2RestController::class.java)
    }
}