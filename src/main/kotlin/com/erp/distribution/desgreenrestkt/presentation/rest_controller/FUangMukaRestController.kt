package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FUangMukaJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FUangMukaEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FUangMukaRestController {
    @Autowired
    var fUangMukaJPARepository: FUangMukaJPARepository? = null

    @RequestMapping(value = ["/rest/getFUangMukaById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFUangMukaById(@PathVariable("id") id: Int): FUangMukaEntity {
        return fUangMukaJPARepository!!.findById(id.toLong()).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFUangMuka"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allUangMukaEntity: List<FUangMukaEntity>
        get() = fUangMukaJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFUangMukaByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFUangMukaByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FUangMukaEntity> {
        return fUangMukaJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFUangMuka"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFUangMuka(@RequestBody fUangMukaEntityNew: FUangMukaEntity): FUangMukaEntity {
        fUangMukaEntityNew.id = 0 //Memastikan ID adalah Nol
        return fUangMukaJPARepository!!.save(fUangMukaEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFUangMuka/{id}"], method = [RequestMethod.PUT])
    fun updateFUangMukaInfo(@PathVariable("id") id: Int, @RequestBody fUangMukaEntityUpdated: FUangMukaEntity?): FUangMukaEntity {
        val fUangMukaEntity: FUangMukaEntity = fUangMukaJPARepository!!.findById(id.toLong()).get()
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fUangMukaEntityUpdated != null) {
            fUangMukaEntityUpdated.id = fUangMukaEntity.id
            if (fUangMukaEntity.fdivisionBean >0) fUangMukaEntityUpdated.fdivisionBean = fUangMukaEntity.fdivisionBean
            fUangMukaJPARepository!!.save(fUangMukaEntityUpdated)
            return fUangMukaEntityUpdated
        }
        return fUangMukaEntity
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFUangMuka/{id}"], method = [RequestMethod.DELETE])
    fun deleteFUangMuka(@PathVariable("id") id: Int): FUangMukaEntity? {
        val fUangMukaEntity: FUangMukaEntity = fUangMukaJPARepository!!.findById(id.toLong()).get()
        if (fUangMukaEntity.id >0) {
            fUangMukaJPARepository!!.delete(fUangMukaEntity)
        }
        return fUangMukaEntity
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FUangMukaRestController::class.java)
    }
}