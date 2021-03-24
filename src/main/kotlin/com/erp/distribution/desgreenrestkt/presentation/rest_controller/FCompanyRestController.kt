package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCompanyJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FCompany
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FCompanyRestController {
    @Autowired
    var fCompanyJPARepository: FCompanyJPARepository? = null

    @RequestMapping(value = ["/rest/getFCompanyById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCompanyById(@PathVariable("id") id: Int): FCompany {
        return fCompanyJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFCompany"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCompany: List<FCompany?>
        get() = fCompanyJPARepository!!.findAll()

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFCompany"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCompany(@RequestBody fCompanyNew: FCompany): FCompany {
        var updatedDomain = FCompany()
        try {
            updatedDomain = fCompanyJPARepository!!.save(fCompanyNew)
        } catch (e: Exception) {
        }
        return updatedDomain
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFCompany/{id}"], method = [RequestMethod.PUT])
    fun updateFCompanyInfo(@PathVariable("id") id: Int, @RequestBody fCompanyUpdated: FCompany?): FCompany {
        val fCompany = fCompanyJPARepository!!.findById(id).orElse(FCompany())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        var updatedDomain = FCompany()
        if (fCompanyUpdated != null) {
            fCompanyUpdated.id = fCompany.id
            //            fCompanyUpdated.setFdivisionBean(fCompany.getFdivisionBean());
//            fCompanyUpdated.setFregionBean(fCompany.getFregionBean());
            try {
                updatedDomain = fCompanyJPARepository!!.save(fCompanyUpdated)
            } catch (e: Exception) {
            }
            return updatedDomain
        }
        return fCompany
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCompany/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCompany(@PathVariable("id") id: Int): FCompany? {
        val fCompany = fCompanyJPARepository!!.findById(id).orElse(FCompany())
        if (fCompany != null) {
            fCompanyJPARepository!!.delete(fCompany)
        }
        return fCompany
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCompanyRestController::class.java)
    }
}