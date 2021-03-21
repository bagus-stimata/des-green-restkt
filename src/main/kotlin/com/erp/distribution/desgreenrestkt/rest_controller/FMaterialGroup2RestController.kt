package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FMaterialGroup2JPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup2
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialGroup2RestController {
    @Autowired
    var fMaterialGroup2JPARepository: FMaterialGroup2JPARepository? = null

    @RequestMapping(value = ["/rest/getFMaterialGroup2ById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialGroup2ById(@PathVariable("id") id: Int): FMaterialGroup2 {
        return fMaterialGroup2JPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialGroup2"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialGroup2: List<FMaterialGroup2>
        get() = fMaterialGroup2JPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialGroup2ByParent/{fmaterialGroup1Bean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup2ByParentId(@PathVariable("fmaterialGroup1Bean") fmaterialGroup1Bean: Int): List<FMaterialGroup2> {
        return fMaterialGroup2JPARepository!!.findAllByParentId(fmaterialGroup1Bean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialGroup2ByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialGroup2ByListParentId(@RequestBody listFMaterialGroup1Bean: List<Int>): List<FMaterialGroup2> {
        return fMaterialGroup2JPARepository!!.findAllByListParentId(listFMaterialGroup1Bean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialGroup2"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialGroup2(@RequestBody fMaterialGroup2New: FMaterialGroup2): FMaterialGroup2 {
        fMaterialGroup2New.id = 0 //Memastikan ID adalah Nol
        return fMaterialGroup2JPARepository!!.save(fMaterialGroup2New)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialGroup2/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialGroup2Info(@PathVariable("id") id: Int, @RequestBody fMaterialGroup2Updated: FMaterialGroup2?): FMaterialGroup2 {
        val fMaterialGroup2 = fMaterialGroup2JPARepository!!.findById(id).orElse(FMaterialGroup2())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialGroup2Updated != null) {
            fMaterialGroup2Updated.id = fMaterialGroup2.id
            if (fMaterialGroup2.fmaterialGroup1Bean >0) fMaterialGroup2Updated.fmaterialGroup1Bean = fMaterialGroup2.fmaterialGroup1Bean
            fMaterialGroup2JPARepository!!.save(fMaterialGroup2Updated)
            return fMaterialGroup2Updated
        }
        return fMaterialGroup2
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialGroup2/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialGroup2(@PathVariable("id") id: Int): FMaterialGroup2? {
        val fMaterialGroup2 = fMaterialGroup2JPARepository!!.findById(id).orElse(FMaterialGroup2())
        if (fMaterialGroup2 != null) {
            fMaterialGroup2JPARepository!!.delete(fMaterialGroup2)
        }
        return fMaterialGroup2
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialGroup2RestController::class.java)
    }
}