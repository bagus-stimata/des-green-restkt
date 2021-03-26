package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FVendorJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FVendorRestController {
    @Autowired
    var fVendorJPARepository: FVendorJPARepository? = null

    @RequestMapping(value = ["/rest/getFVendorById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFVendorById(@PathVariable("id") id: Int): FVendorEntity {
        return fVendorJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFVendor"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allVendorEntity: List<FVendorEntity>
        get() = fVendorJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFVendorByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFVendorByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FVendorEntity> {
        return fVendorJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFVendorByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFVendorByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FVendorEntity> {
        return fVendorJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFVendor"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFVendor(@RequestBody fVendorEntityNew: FVendorEntity): FVendorEntity {
        fVendorEntityNew.id = 0 //Memastikan ID adalah Nol
        return fVendorJPARepository!!.save(fVendorEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFVendor/{id}"], method = [RequestMethod.PUT])
    fun updateFVendorInfo(@PathVariable("id") id: Int, @RequestBody fVendorEntityUpdated: FVendorEntity?): FVendorEntity {
        val fVendor = fVendorJPARepository!!.findById(id).orElse(FVendorEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fVendorEntityUpdated != null) {
            fVendorEntityUpdated.id = fVendor.id
            if (fVendor.fdivisionBean >0) fVendorEntityUpdated.fdivisionBean = fVendor.fdivisionBean
            fVendorJPARepository!!.save(fVendorEntityUpdated)
            return fVendorEntityUpdated
        }
        return fVendor
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFVendor/{id}"], method = [RequestMethod.DELETE])
    fun deleteFVendor(@PathVariable("id") id: Int): FVendorEntity? {
        val fVendor = fVendorJPARepository!!.findById(id).orElse(FVendorEntity())
        if (fVendor != null) {
            fVendorJPARepository!!.delete(fVendor)
        }
        return fVendor
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FVendorRestController::class.java)
    }
}