package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FTaxJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FTaxEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
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
    fun getFTaxById(@PathVariable("id") id: Int): FTaxEntity {
        return fTaxJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFTax"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allTaxEntities: List<FTaxEntity>
        get() = fTaxJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFTaxByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFTaxByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FTaxEntity> {
        return fTaxJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFTaxByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFTaxByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FTaxEntity> {
        return fTaxJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFTax"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFTax(@RequestBody fTaxEntityNew: FTaxEntity): FTaxEntity {
        fTaxEntityNew.id = 0 //Memastikan ID adalah Nol
        return fTaxJPARepository!!.save(fTaxEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFTax/{id}"], method = [RequestMethod.PUT])
    fun updateFTaxInfo(@PathVariable("id") id: Int, @RequestBody fTaxEntityUpdated: FTaxEntity?): FTaxEntity {
        val fTax = fTaxJPARepository!!.findById(id).orElse(FTaxEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fTaxEntityUpdated != null) {
            fTaxEntityUpdated.id = fTax.id
            if (fTax.fdivisionBean >0) fTaxEntityUpdated.fdivisionBean = fTax.fdivisionBean
            fTaxJPARepository!!.save(fTaxEntityUpdated)
            return fTaxEntityUpdated
        }
        return fTax
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFTax/{id}"], method = [RequestMethod.DELETE])
    fun deleteFTax(@PathVariable("id") id: Int): FTaxEntity? {
        val fTax = fTaxJPARepository!!.findById(id).orElse(FTaxEntity())
        if (fTax != null) {
            fTaxJPARepository!!.delete(fTax)
        }
        return fTax
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FTaxRestController::class.java)
    }
}