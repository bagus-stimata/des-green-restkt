package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FMaterialGroup1JPARepository
import com.erp.distribution.desgreenrestkt.model.FMaterialGroup1
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFMaterialGroup1ById(@PathVariable("id") id: Int): FMaterialGroup1 {
        return fMaterialGroup1JPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialGroup1"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialGroup1: List<FMaterialGroup1>
        get() = fMaterialGroup1JPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialGroup1> {
        return fMaterialGroup1JPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByCompany/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByCompany(@PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialGroup1> {
        return fMaterialGroup1JPARepository!!.findAllByCompany(fcompanyBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup1ByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup1ByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialGroup1> {
        return fMaterialGroup1JPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialGroup1"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialGroup1(@RequestBody fMaterialGroup1New: FMaterialGroup1): FMaterialGroup1 {
        fMaterialGroup1New.id = 0 //Memastikan ID adalah Nol
        return fMaterialGroup1JPARepository!!.save(fMaterialGroup1New)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialGroup1/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialGroup1Info(@PathVariable("id") id: Int, @RequestBody fMaterialGroup1Updated: FMaterialGroup1?): FMaterialGroup1 {
        val fMaterialGroup1 = fMaterialGroup1JPARepository!!.findById(id).orElse(FMaterialGroup1())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialGroup1Updated != null) {
            fMaterialGroup1Updated.id = fMaterialGroup1.id
            if (fMaterialGroup1.fdivisionBean >0) fMaterialGroup1Updated.fdivisionBean = fMaterialGroup1.fdivisionBean
            fMaterialGroup1JPARepository!!.save(fMaterialGroup1Updated)
            return fMaterialGroup1Updated
        }
        return fMaterialGroup1
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialGroup1/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialGroup1(@PathVariable("id") id: Int): FMaterialGroup1? {
        val fMaterialGroup1 = fMaterialGroup1JPARepository!!.findById(id).orElse(FMaterialGroup1())
        if (fMaterialGroup1 != null) {
            fMaterialGroup1JPARepository!!.delete(fMaterialGroup1)
        }
        return fMaterialGroup1
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialGroup1RestController::class.java)
    }
}