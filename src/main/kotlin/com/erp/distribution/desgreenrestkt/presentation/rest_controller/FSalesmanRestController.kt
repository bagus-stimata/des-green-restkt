package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFSalesmanUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesmanRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FSalesmanRestController @Autowired constructor(
    val getFSalesmanUseCase: GetFSalesmanUseCase
){

    @RequestMapping(value = ["/rest/getFSalesmanById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFSalesmanById(@PathVariable("id") id: Int): FSalesmanRes {
        return getFSalesmanUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFSalesman"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allSalesmanEntity: List<FSalesmanRes>
        get() = getFSalesmanUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFSalesmanByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSalesmanByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FSalesmanRes> {
        return getFSalesmanUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFSalesmanByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSalesmanByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FSalesmanRes> {
        return getFSalesmanUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFSalesman"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFSalesman(@RequestBody fSalesmanEntityNew: FSalesmanRes): FSalesmanRes {
        fSalesmanEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFSalesmanUseCase.save(fSalesmanEntityNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFSalesman/{id}"], method = [RequestMethod.PUT])
    fun updateFSalesmanInfo(@PathVariable("id") id: Int, @RequestBody fSalesmanEntityUpdated: FSalesmanRes?): FSalesmanRes {
        val fSalesman = getFSalesmanUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fSalesmanEntityUpdated != null) {
            fSalesmanEntityUpdated.id = fSalesman.id
            if (fSalesman.fdivisionBean >0) fSalesmanEntityUpdated.fdivisionBean = fSalesman.fdivisionBean
            getFSalesmanUseCase.save(fSalesmanEntityUpdated.toDomain()).toResponse()
            return fSalesmanEntityUpdated
        }
        return fSalesman.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFSalesman/{id}"], method = [RequestMethod.DELETE])
    fun deleteFSalesman(@PathVariable("id") id: Int): FSalesmanRes? {
        val fSalesman = getFSalesmanUseCase.findById(id)
        if (fSalesman != null) {
            getFSalesmanUseCase.delete(fSalesman)
        }
        return fSalesman.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FSalesmanRestController::class.java)
    }
}