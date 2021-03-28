package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFCompanyUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFDivisionUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FDivisionRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FDivisionRestController @Autowired constructor(
    val fDivisionUseCase: GetFDivisionUseCase,
    val fCompanyUseCase: GetFCompanyUseCase
){
    
    @RequestMapping(value = ["/rest/getFDivisionById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFDivisionById(@PathVariable("id") id: Int): FDivisionRes {
        return fDivisionUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFDivision"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allDivisionEntity: List<FDivisionRes>
        get() = fDivisionUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFDivisionByParent/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDivisionByParentId(@PathVariable("fcompanyBean") fcompanyBean: Int): List<FDivisionRes> {
        return fDivisionUseCase.findByCompanyRes(fcompanyBean)
    }

    @RequestMapping(value = ["/rest/getAllFDivisionBySameCompany/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDivisionBySameCompany(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FDivisionRes> {
        val fDivision = fDivisionUseCase.findById(fdivisionBean)
        return fDivisionUseCase.findByCompanyRes(fDivision.fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFDivision"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFDivision(@RequestBody fDivisionResNew: FDivisionRes): FDivisionRes {
        fDivisionResNew.id = 0 //Memastikan ID adalah Nol
        return fDivisionUseCase.save(fDivisionResNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFDivision/{id}"], method = [RequestMethod.PUT])
    fun updateFDivisionInfo(@PathVariable("id") id: Int, @RequestBody fDivisionResUpdated: FDivisionRes): FDivisionRes {
        val fDivision = fDivisionUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fDivisionResUpdated != null) {
            fDivisionResUpdated.id = fDivision.id
            if (fDivision.fcompanyBean >0) fDivisionResUpdated.fcompanyBean = fDivision.fcompanyBean
            if (fDivision.accCostCenterBean >0) fDivisionResUpdated.accCostCenterBean = fDivision.accCostCenterBean
            fDivisionUseCase.save(fDivisionResUpdated.toDomain()).toResponse()
            return fDivisionResUpdated
        }
        return fDivision.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFDivision/{id}"], method = [RequestMethod.DELETE])
    fun deleteFDivision(@PathVariable("id") id: Int): FDivisionRes {
        val fDivision = fDivisionUseCase.findById(id)
        if (fDivision != null) {
            fDivisionUseCase.delete(fDivision)
        }
        return fDivision.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FDivisionRestController::class.java)
    }
}