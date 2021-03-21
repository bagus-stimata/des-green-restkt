package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FGiroJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FGiro
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FGiroRestController {
    @Autowired
    var fGiroJPARepository: FGiroJPARepository? = null

    @RequestMapping(value = ["/rest/getFGiroById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFGiroById(@PathVariable("id") id: Long): FGiro {
        return fGiroJPARepository!!.findById(id).orElse(FGiro())
    }

    @get:RequestMapping(value = ["/rest/getAllFGiro"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allGiro: List<FGiro>
        get() = fGiroJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFGiroByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFGiroByDivison(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FGiro> {
        return fGiroJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFGiro"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFGiro(@RequestBody fGiroNew: FGiro): FGiro {
        fGiroNew.id = 0 //Memastikan ID adalah Nol
        return fGiroJPARepository!!.save(fGiroNew)
    }

    @RequestMapping(value = ["/rest/updateFGiro/{id}"], method = [RequestMethod.PUT])
    fun updateFGiroInfo(@PathVariable("id") id: Long, @RequestBody fGiroUpdated: FGiro?): FGiro {
        val fGiro = fGiroJPARepository!!.findById(id).orElse(FGiro())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fGiroUpdated != null) {
            fGiroUpdated.id = fGiro.id
            if (fGiro.fdivisionBean >0) fGiroUpdated.fdivisionBean = fGiro.fdivisionBean
            fGiroJPARepository!!.save(fGiroUpdated)
            return fGiroUpdated
        }
        return fGiro
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFGiro/{id}"], method = [RequestMethod.DELETE])
    fun deleteFGiro(@PathVariable("id") id: Long): FGiro? {
        val fGiro = fGiroJPARepository!!.findById(id).orElse(FGiro())
        if (fGiro != null) {
            fGiroJPARepository!!.delete(fGiro)
        }
        return fGiro
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FGiroRestController::class.java)
    }
}