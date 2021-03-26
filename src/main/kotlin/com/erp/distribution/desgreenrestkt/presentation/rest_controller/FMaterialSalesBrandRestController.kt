package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialSalesBrandJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialSalesBrandEntity
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
    fun getFMaterialSalesBrandById(@PathVariable("id") id: Int): FMaterialSalesBrandEntity {
        return fMaterialSalesBrandJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialSalesBrand"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialSalesBrandEntity: List<FMaterialSalesBrandEntity>
        get() = fMaterialSalesBrandJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialSalesBrandByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialSalesBrandByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialSalesBrandByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialSalesBrandByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialSalesBrand"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialSalesBrand(@RequestBody fMaterialSalesBrandEntityNew: FMaterialSalesBrandEntity): FMaterialSalesBrandEntity {
        fMaterialSalesBrandEntityNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialSalesBrandJPARepository!!.save(fMaterialSalesBrandEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialSalesBrand/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialSalesBrandInfo(@PathVariable("id") id: Int, @RequestBody fMaterialSalesBrandEntityUpdated: FMaterialSalesBrandEntity?): FMaterialSalesBrandEntity {
        val fMaterialSalesBrand = fMaterialSalesBrandJPARepository!!.findById(id).orElse(FMaterialSalesBrandEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialSalesBrandEntityUpdated != null) {
            fMaterialSalesBrandEntityUpdated.id = fMaterialSalesBrand.id
            if (fMaterialSalesBrand.fdivisionBean >0) fMaterialSalesBrandEntityUpdated.fdivisionBean = fMaterialSalesBrand.fdivisionBean
            fMaterialSalesBrandJPARepository!!.save(fMaterialSalesBrandEntityUpdated)
            return fMaterialSalesBrandEntityUpdated
        }
        return fMaterialSalesBrand
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialSalesBrand/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialSalesBrand(@PathVariable("id") id: Int): FMaterialSalesBrandEntity? {
        val fMaterialSalesBrand = fMaterialSalesBrandJPARepository!!.findById(id).orElse(FMaterialSalesBrandEntity())
        if (fMaterialSalesBrand != null) {
            fMaterialSalesBrandJPARepository!!.delete(fMaterialSalesBrand)
        }
        return fMaterialSalesBrand
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialSalesBrandRestController::class.java)
    }
}