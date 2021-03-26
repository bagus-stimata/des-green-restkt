package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialGroup3JPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialGroup3RestController {
    @Autowired
    var fMaterialGroup3JPARepository: FMaterialGroup3JPARepository? = null

    @RequestMapping(value = ["/rest/getFMaterialGroup3ById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialGroup3ById(@PathVariable("id") id: Int): FMaterialGroup3Entity {
        return fMaterialGroup3JPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialGroup3"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialGroup3Entity: List<FMaterialGroup3Entity>
        get() = fMaterialGroup3JPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialGroup3ByParent/{fmaterialGroup2Bean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup3ByParentId(@PathVariable("fmaterialGroup2Bean") fmaterialGroup2Bean: Int): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository!!.findAllByParentId(fmaterialGroup2Bean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup3ByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup3ByListParentId(@RequestBody listFMaterialGroup2Bean: List<Int>): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository!!.findAllByListParentId(listFMaterialGroup2Bean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialGroup3"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialGroup3(@RequestBody fMaterialGroup3EntityNew: FMaterialGroup3Entity): FMaterialGroup3Entity {
        fMaterialGroup3EntityNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialGroup3JPARepository!!.save(fMaterialGroup3EntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialGroup3/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialGroup3Info(@PathVariable("id") id: Int, @RequestBody fMaterialGroup3EntityUpdated: FMaterialGroup3Entity?): FMaterialGroup3Entity {
        val fMaterialGroup3 = fMaterialGroup3JPARepository!!.findById(id).orElse(FMaterialGroup3Entity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialGroup3EntityUpdated != null) {
            fMaterialGroup3EntityUpdated.id = fMaterialGroup3.id
            if (fMaterialGroup3.fmaterialGroup2Bean >0) fMaterialGroup3EntityUpdated.fmaterialGroup2Bean = fMaterialGroup3.fmaterialGroup2Bean
            fMaterialGroup3JPARepository!!.save(fMaterialGroup3EntityUpdated)
            return fMaterialGroup3EntityUpdated
        }
        return fMaterialGroup3
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialGroup3/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialGroup3(@PathVariable("id") id: Int): FMaterialGroup3Entity? {
        val fMaterialGroup3 = fMaterialGroup3JPARepository!!.findById(id).orElse(FMaterialGroup3Entity())
        if (fMaterialGroup3 != null) {
            fMaterialGroup3JPARepository!!.delete(fMaterialGroup3)
        }
        return fMaterialGroup3
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialGroup3RestController::class.java)
    }
}