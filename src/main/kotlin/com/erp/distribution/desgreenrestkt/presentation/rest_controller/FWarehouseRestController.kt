package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFWarehouseUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FWarehouseRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FWarehouseRestController @Autowired constructor(
    val getFWarehouseUseCase: GetFWarehouseUseCase
){

    @RequestMapping(value = ["/rest/getFWarehouseById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFWarehouseById(@PathVariable("id") id: Int): FWarehouseRes {
        return getFWarehouseUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFWarehouse"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allWarehouseEntity: List<FWarehouseRes>
        get() = getFWarehouseUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFWarehouseByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFWarehouseByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FWarehouseRes> {
        return getFWarehouseUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFWarehouseByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFWarehouseByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FWarehouseRes> {
        return getFWarehouseUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFWarehouse"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFWarehouse(@RequestBody fWarehouseEntityNew: FWarehouseRes): FWarehouseRes {
        fWarehouseEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFWarehouseUseCase.save(fWarehouseEntityNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFWarehouse/{id}"], method = [RequestMethod.PUT])
    fun updateFWarehouseInfo(@PathVariable("id") id: Int, @RequestBody fWarehouseEntityUpdated: FWarehouseRes?): FWarehouseRes {
        val fWarehouse = getFWarehouseUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fWarehouseEntityUpdated != null) {
            fWarehouseEntityUpdated.id = fWarehouse.id
            if (fWarehouse.fdivisionBean >0) fWarehouseEntityUpdated.fdivisionBean = fWarehouse.fdivisionBean
            getFWarehouseUseCase.save(fWarehouseEntityUpdated.toDomain())
            return fWarehouseEntityUpdated
        }
        return fWarehouse.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFWarehouse/{id}"], method = [RequestMethod.DELETE])
    fun deleteFWarehouse(@PathVariable("id") id: Int): FWarehouseRes? {
        val fWarehouse = getFWarehouseUseCase.findById(id)
        if (fWarehouse != null) {
            getFWarehouseUseCase.delete(fWarehouse)
        }
        return fWarehouse.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FWarehouseRestController::class.java)
    }
}