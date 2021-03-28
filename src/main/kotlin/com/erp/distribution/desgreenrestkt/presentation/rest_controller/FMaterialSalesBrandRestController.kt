package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFMaterialSalesBrandUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialSalesBrandRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialSalesBrandRestController @Autowired constructor(
    val getFMaterialSalesBrandUseCase: GetFMaterialSalesBrandUseCase
){
    @RequestMapping(value = ["/rest/getFMaterialSalesBrandById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialSalesBrandById(@PathVariable("id") id: Int): FMaterialSalesBrandRes {
        return getFMaterialSalesBrandUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialSalesBrand"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialSalesBrandEntity: List<FMaterialSalesBrandRes>
        get() = getFMaterialSalesBrandUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFMaterialSalesBrandByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialSalesBrandByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialSalesBrandRes> {
        return getFMaterialSalesBrandUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialSalesBrandByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialSalesBrandByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialSalesBrandRes> {
        return getFMaterialSalesBrandUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterialSalesBrand"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialSalesBrand(@RequestBody fMaterialSalesBrandEntityNew: FMaterialSalesBrandRes): FMaterialSalesBrandRes {
        fMaterialSalesBrandEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFMaterialSalesBrandUseCase.save(fMaterialSalesBrandEntityNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterialSalesBrand/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialSalesBrandInfo(@PathVariable("id") id: Int, @RequestBody fMaterialSalesBrandEntityUpdated: FMaterialSalesBrandRes?): FMaterialSalesBrandRes {
        val fMaterialSalesBrand = getFMaterialSalesBrandUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialSalesBrandEntityUpdated != null) {
            fMaterialSalesBrandEntityUpdated.id = fMaterialSalesBrand.id
            if (fMaterialSalesBrand.fdivisionBean >0) fMaterialSalesBrandEntityUpdated.fdivisionBean = fMaterialSalesBrand.fdivisionBean
            getFMaterialSalesBrandUseCase.save(fMaterialSalesBrandEntityUpdated.toDomain())
            return fMaterialSalesBrandEntityUpdated
        }
        return fMaterialSalesBrand.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialSalesBrand/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialSalesBrand(@PathVariable("id") id: Int): FMaterialSalesBrandRes? {
        val fMaterialSalesBrand = getFMaterialSalesBrandUseCase.findById(id)
        if (fMaterialSalesBrand != null) {
            getFMaterialSalesBrandUseCase.delete(fMaterialSalesBrand)
        }
        return fMaterialSalesBrand.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialSalesBrandRestController::class.java)
    }
}