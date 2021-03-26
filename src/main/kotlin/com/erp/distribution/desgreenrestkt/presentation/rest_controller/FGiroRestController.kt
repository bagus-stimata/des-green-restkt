package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FGiroJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FGiroEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
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
    fun getFGiroById(@PathVariable("id") id: Long): FGiroEntity {
        return fGiroJPARepository!!.findById(id).orElse(FGiroEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFGiro"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allGiroEntity: List<FGiroEntity>
        get() = fGiroJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFGiroByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFGiroByDivison(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FGiroEntity> {
        return fGiroJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFGiro"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFGiro(@RequestBody fGiroEntityNew: FGiroEntity): FGiroEntity {
        fGiroEntityNew.id = 0 //Memastikan ID adalah Nol
        return fGiroJPARepository!!.save(fGiroEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFGiro/{id}"], method = [RequestMethod.PUT])
    fun updateFGiroInfo(@PathVariable("id") id: Long, @RequestBody fGiroEntityUpdated: FGiroEntity?): FGiroEntity {
        val fGiro = fGiroJPARepository!!.findById(id).orElse(FGiroEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fGiroEntityUpdated != null) {
            fGiroEntityUpdated.id = fGiro.id
            if (fGiro.fdivisionBean >0) fGiroEntityUpdated.fdivisionBean = fGiro.fdivisionBean
            fGiroJPARepository!!.save(fGiroEntityUpdated)
            return fGiroEntityUpdated
        }
        return fGiro
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFGiro/{id}"], method = [RequestMethod.DELETE])
    fun deleteFGiro(@PathVariable("id") id: Long): FGiroEntity? {
        val fGiro = fGiroJPARepository!!.findById(id).orElse(FGiroEntity())
        if (fGiro != null) {
            fGiroJPARepository!!.delete(fGiro)
        }
        return fGiro
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FGiroRestController::class.java)
    }
}