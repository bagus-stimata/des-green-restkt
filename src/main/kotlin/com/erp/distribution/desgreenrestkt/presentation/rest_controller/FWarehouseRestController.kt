package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FWarehouseJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FWarehouse
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FWarehouseRestController {
    @Autowired
    var fWarehouseJPARepository: FWarehouseJPARepository? = null

    @RequestMapping(value = ["/rest/getFWarehouseById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFWarehouseById(@PathVariable("id") id: Int): FWarehouse {
        return fWarehouseJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFWarehouse"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allWarehouse: List<FWarehouse>
        get() = fWarehouseJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFWarehouseByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFWarehouseByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FWarehouse> {
        return fWarehouseJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFWarehouseByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFWarehouseByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FWarehouse> {
        return fWarehouseJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFWarehouse"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFWarehouse(@RequestBody fWarehouseNew: FWarehouse): FWarehouse {
        fWarehouseNew.id = 0 //Memastikan ID adalah Nol
        return fWarehouseJPARepository!!.save(fWarehouseNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFWarehouse/{id}"], method = [RequestMethod.PUT])
    fun updateFWarehouseInfo(@PathVariable("id") id: Int, @RequestBody fWarehouseUpdated: FWarehouse?): FWarehouse {
        val fWarehouse = fWarehouseJPARepository!!.findById(id).orElse(FWarehouse())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fWarehouseUpdated != null) {
            fWarehouseUpdated.id = fWarehouse.id
            if (fWarehouse.fdivisionBean >0) fWarehouseUpdated.fdivisionBean = fWarehouse.fdivisionBean
            fWarehouseJPARepository!!.save(fWarehouseUpdated)
            return fWarehouseUpdated
        }
        return fWarehouse
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFWarehouse/{id}"], method = [RequestMethod.DELETE])
    fun deleteFWarehouse(@PathVariable("id") id: Int): FWarehouse? {
        val fWarehouse = fWarehouseJPARepository!!.findById(id).orElse(FWarehouse())
        if (fWarehouse != null) {
            fWarehouseJPARepository!!.delete(fWarehouse)
        }
        return fWarehouse
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FWarehouseRestController::class.java)
    }
}