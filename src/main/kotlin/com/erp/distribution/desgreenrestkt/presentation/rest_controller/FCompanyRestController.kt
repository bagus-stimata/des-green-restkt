package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCompanyJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFAreaUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFCompanyUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FCompanyRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FCompanyRestController   @Autowired constructor(
    val fCompanyUseCase: GetFCompanyUseCase
) {

    @RequestMapping(value = ["/rest/getFCompanyById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCompanyById(@PathVariable("id") id: Int): FCompanyRes {
        return fCompanyUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFCompany"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCompanyEntity: List<FCompanyRes?>
        get() = fCompanyUseCase.findAllRes()

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFCompany"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCompany(@RequestBody fCompanyResNew: FCompanyRes): FCompanyRes {
        var updatedDomain = FCompanyRes()
        try {
            updatedDomain = fCompanyUseCase.save(fCompanyResNew.toDomain()).toResponse()
        } catch (e: Exception) {
        }
        return updatedDomain
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFCompany/{id}"], method = [RequestMethod.PUT])
    fun updateFCompanyInfo(@PathVariable("id") id: Int, @RequestBody fCompanyResUpdated: FCompanyRes?): FCompanyRes {
        val fCompany = fCompanyUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        var updatedDomain = FCompanyRes()
        if (fCompanyResUpdated != null) {
            fCompanyResUpdated.id = fCompany.id
            //            fCompanyUpdated.setFdivisionBean(fCompany.getFdivisionBean());
//            fCompanyUpdated.setFregionBean(fCompany.getFregionBean());
            try {
                updatedDomain = fCompanyUseCase.save(fCompanyResUpdated.toDomain()).toResponse()
            } catch (e: Exception) {
            }
            return updatedDomain
        }
        return fCompany.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCompany/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCompany(@PathVariable("id") id: Int): FCompanyRes? {
        val fCompany = fCompanyUseCase.findById(id)
        if (fCompany != null) {
            fCompanyUseCase.delete(fCompany)
        }
        return fCompany.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCompanyRestController::class.java)
    }
}