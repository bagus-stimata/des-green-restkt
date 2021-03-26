package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialGroup1JPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup1Entity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialGroup1RestController {
    @Autowired
    var fMaterialGroup1JPARepository: FMaterialGroup1JPARepository? = null

    @RequestMapping(value = ["/rest/getFMaterialGroup1ById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialGroup1ById(@PathVariable("id") id: Int): FMaterialGroup1Entity {
        return fMaterialGroup1JPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialGroup1"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialGroup1Entity: List<FMaterialGroup1Entity>
        get() = fMaterialGroup1JPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByCompany/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByCompany(@PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository!!.findAllByCompany(fcompanyBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialGroup1"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialGroup1(@RequestBody fMaterialGroup1EntityNew: FMaterialGroup1Entity): FMaterialGroup1Entity {
        fMaterialGroup1EntityNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialGroup1JPARepository!!.save(fMaterialGroup1EntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialGroup1/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialGroup1Info(@PathVariable("id") id: Int, @RequestBody fMaterialGroup1EntityUpdated: FMaterialGroup1Entity?): FMaterialGroup1Entity {
        val fMaterialGroup1 = fMaterialGroup1JPARepository!!.findById(id).orElse(FMaterialGroup1Entity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialGroup1EntityUpdated != null) {
            fMaterialGroup1EntityUpdated.id = fMaterialGroup1.id
            if (fMaterialGroup1.fdivisionBean >0) fMaterialGroup1EntityUpdated.fdivisionBean = fMaterialGroup1.fdivisionBean
            fMaterialGroup1JPARepository!!.save(fMaterialGroup1EntityUpdated)
            return fMaterialGroup1EntityUpdated
        }
        return fMaterialGroup1
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialGroup1/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialGroup1(@PathVariable("id") id: Int): FMaterialGroup1Entity? {
        val fMaterialGroup1 = fMaterialGroup1JPARepository!!.findById(id).orElse(FMaterialGroup1Entity())
        if (fMaterialGroup1 != null) {
            fMaterialGroup1JPARepository!!.delete(fMaterialGroup1)
        }
        return fMaterialGroup1
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialGroup1RestController::class.java)
    }
}