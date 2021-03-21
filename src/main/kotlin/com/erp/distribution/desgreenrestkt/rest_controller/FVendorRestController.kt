package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FVendorJPARepository
import com.erp.distribution.desgreenrestkt.model.FVendor
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFVendorById(@PathVariable("id") id: Int): FVendor {
        return fVendorJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFVendor"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allVendor: List<FVendor>
        get() = fVendorJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFVendorByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFVendorByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FVendor> {
        return fVendorJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFVendorByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFVendorByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FVendor> {
        return fVendorJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFVendor"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFVendor(@RequestBody fVendorNew: FVendor): FVendor {
        fVendorNew.id = 0 //Memastikan ID adalah Nol
        return fVendorJPARepository!!.save(fVendorNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFVendor/{id}"], method = [RequestMethod.PUT])
    fun updateFVendorInfo(@PathVariable("id") id: Int, @RequestBody fVendorUpdated: FVendor?): FVendor {
        val fVendor = fVendorJPARepository!!.findById(id).orElse(FVendor())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fVendorUpdated != null) {
            fVendorUpdated.id = fVendor.id
            if (fVendor.fdivisionBean >0) fVendorUpdated.fdivisionBean = fVendor.fdivisionBean
            fVendorJPARepository!!.save(fVendorUpdated)
            return fVendorUpdated
        }
        return fVendor
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFVendor/{id}"], method = [RequestMethod.DELETE])
    fun deleteFVendor(@PathVariable("id") id: Int): FVendor? {
        val fVendor = fVendorJPARepository!!.findById(id).orElse(FVendor())
        if (fVendor != null) {
            fVendorJPARepository!!.delete(fVendor)
        }
        return fVendor
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FVendorRestController::class.java)
    }
}