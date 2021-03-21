package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FTaxJPARepository
import com.erp.distribution.desgreenrestkt.model.FTax
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FTaxRestController {
    @Autowired
    var fTaxJPARepository: FTaxJPARepository? = null

    @RequestMapping(value = ["/rest/getFTaxById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFTaxById(@PathVariable("id") id: Int): FTax {
        return fTaxJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFTax"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allTax: List<FTax>
        get() = fTaxJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFTaxByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFTaxByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FTax> {
        return fTaxJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFTaxByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFTaxByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FTax> {
        return fTaxJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFTax"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFTax(@RequestBody fTaxNew: FTax): FTax {
        fTaxNew.id = 0 //Memastikan ID adalah Nol
        return fTaxJPARepository!!.save(fTaxNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFTax/{id}"], method = [RequestMethod.PUT])
    fun updateFTaxInfo(@PathVariable("id") id: Int, @RequestBody fTaxUpdated: FTax?): FTax {
        val fTax = fTaxJPARepository!!.findById(id).orElse(FTax())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fTaxUpdated != null) {
            fTaxUpdated.id = fTax.id
            if (fTax.fdivisionBean >0) fTaxUpdated.fdivisionBean = fTax.fdivisionBean
            fTaxJPARepository!!.save(fTaxUpdated)
            return fTaxUpdated
        }
        return fTax
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFTax/{id}"], method = [RequestMethod.DELETE])
    fun deleteFTax(@PathVariable("id") id: Int): FTax? {
        val fTax = fTaxJPARepository!!.findById(id).orElse(FTax())
        if (fTax != null) {
            fTaxJPARepository!!.delete(fTax)
        }
        return fTax
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FTaxRestController::class.java)
    }
}