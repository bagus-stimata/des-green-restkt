package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FUangMukaJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FUangMuka
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
    fun getFUangMukaById(@PathVariable("id") id: Int): FUangMuka {
        return fUangMukaJPARepository!!.findById(id.toLong()).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFUangMuka"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allUangMuka: List<FUangMuka>
        get() = fUangMukaJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFUangMukaByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFUangMukaByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FUangMuka> {
        return fUangMukaJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFUangMuka"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFUangMuka(@RequestBody fUangMukaNew: FUangMuka): FUangMuka {
        fUangMukaNew.id = 0 //Memastikan ID adalah Nol
        return fUangMukaJPARepository!!.save(fUangMukaNew)
    }

    @RequestMapping(value = ["/rest/updateFUangMuka/{id}"], method = [RequestMethod.PUT])
    fun updateFUangMukaInfo(@PathVariable("id") id: Int, @RequestBody fUangMukaUpdated: FUangMuka?): FUangMuka {
        val fUangMuka: FUangMuka = fUangMukaJPARepository!!.findById(id.toLong()).get()
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fUangMukaUpdated != null) {
            fUangMukaUpdated.id = fUangMuka.id
            if (fUangMuka.fdivisionBean >0) fUangMukaUpdated.fdivisionBean = fUangMuka.fdivisionBean
            fUangMukaJPARepository!!.save(fUangMukaUpdated)
            return fUangMukaUpdated
        }
        return fUangMuka
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFUangMuka/{id}"], method = [RequestMethod.DELETE])
    fun deleteFUangMuka(@PathVariable("id") id: Int): FUangMuka? {
        val fUangMuka: FUangMuka = fUangMukaJPARepository!!.findById(id.toLong()).get()
        if (fUangMuka.id >0) {
            fUangMukaJPARepository!!.delete(fUangMuka)
        }
        return fUangMuka
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FUangMukaRestController::class.java)
    }
}