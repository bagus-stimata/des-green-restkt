package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialSalesBrandJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialSalesBrand
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialSalesBrandRestController {
    @Autowired
    var fMaterialSalesBrandJPARepository: FMaterialSalesBrandJPARepository? = null

    @RequestMapping(value = ["/rest/getFMaterialSalesBrandById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialSalesBrandById(@PathVariable("id") id: Int): FMaterialSalesBrand {
        return fMaterialSalesBrandJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialSalesBrand"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialSalesBrand: List<FMaterialSalesBrand>
        get() = fMaterialSalesBrandJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialSalesBrandByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialSalesBrandByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialSalesBrand> {
        return fMaterialSalesBrandJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialSalesBrandByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialSalesBrandByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialSalesBrand> {
        return fMaterialSalesBrandJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialSalesBrand"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialSalesBrand(@RequestBody fMaterialSalesBrandNew: FMaterialSalesBrand): FMaterialSalesBrand {
        fMaterialSalesBrandNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialSalesBrandJPARepository!!.save(fMaterialSalesBrandNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialSalesBrand/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialSalesBrandInfo(@PathVariable("id") id: Int, @RequestBody fMaterialSalesBrandUpdated: FMaterialSalesBrand?): FMaterialSalesBrand {
        val fMaterialSalesBrand = fMaterialSalesBrandJPARepository!!.findById(id).orElse(FMaterialSalesBrand())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialSalesBrandUpdated != null) {
            fMaterialSalesBrandUpdated.id = fMaterialSalesBrand.id
            if (fMaterialSalesBrand.fdivisionBean >0) fMaterialSalesBrandUpdated.fdivisionBean = fMaterialSalesBrand.fdivisionBean
            fMaterialSalesBrandJPARepository!!.save(fMaterialSalesBrandUpdated)
            return fMaterialSalesBrandUpdated
        }
        return fMaterialSalesBrand
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialSalesBrand/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialSalesBrand(@PathVariable("id") id: Int): FMaterialSalesBrand? {
        val fMaterialSalesBrand = fMaterialSalesBrandJPARepository!!.findById(id).orElse(FMaterialSalesBrand())
        if (fMaterialSalesBrand != null) {
            fMaterialSalesBrandJPARepository!!.delete(fMaterialSalesBrand)
        }
        return fMaterialSalesBrand
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialSalesBrandRestController::class.java)
    }
}