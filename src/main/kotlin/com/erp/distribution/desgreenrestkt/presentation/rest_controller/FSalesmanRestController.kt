package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSalesmanJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesmanEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FSalesmanRestController {
    @Autowired
    var fSalesmanJPARepository: FSalesmanJPARepository? = null

    @RequestMapping(value = ["/rest/getFSalesmanById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFSalesmanById(@PathVariable("id") id: Int): FSalesmanEntity {
        return fSalesmanJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFSalesman"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allSalesmanEntity: List<FSalesmanEntity>
        get() = fSalesmanJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFSalesmanByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSalesmanByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FSalesmanEntity> {
        return fSalesmanJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFSalesmanByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSalesmanByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FSalesmanEntity> {
        return fSalesmanJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFSalesman"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFSalesman(@RequestBody fSalesmanEntityNew: FSalesmanEntity): FSalesmanEntity {
        fSalesmanEntityNew.id = 0 //Memastikan ID adalah Nol
        return fSalesmanJPARepository!!.save(fSalesmanEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFSalesman/{id}"], method = [RequestMethod.PUT])
    fun updateFSalesmanInfo(@PathVariable("id") id: Int, @RequestBody fSalesmanEntityUpdated: FSalesmanEntity?): FSalesmanEntity {
        val fSalesman = fSalesmanJPARepository!!.findById(id).orElse(FSalesmanEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fSalesmanEntityUpdated != null) {
            fSalesmanEntityUpdated.id = fSalesman.id
            if (fSalesman.fdivisionBean >0) fSalesmanEntityUpdated.fdivisionBean = fSalesman.fdivisionBean
            fSalesmanJPARepository!!.save(fSalesmanEntityUpdated)
            return fSalesmanEntityUpdated
        }
        return fSalesman
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFSalesman/{id}"], method = [RequestMethod.DELETE])
    fun deleteFSalesman(@PathVariable("id") id: Int): FSalesmanEntity? {
        val fSalesman = fSalesmanJPARepository!!.findById(id).orElse(FSalesmanEntity())
        if (fSalesman != null) {
            fSalesmanJPARepository!!.delete(fSalesman)
        }
        return fSalesman
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FSalesmanRestController::class.java)
    }
}