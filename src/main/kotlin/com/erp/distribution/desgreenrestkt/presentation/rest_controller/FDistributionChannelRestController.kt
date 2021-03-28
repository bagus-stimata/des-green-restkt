package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFDistributionChannelUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FDistributionChannelRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FDistributionChannelRestController @Autowired constructor(
    val fDistributionChannelUseCase: GetFDistributionChannelUseCase
) {

    @RequestMapping(value = ["/rest/getFDistributionChannelById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFDistributionChannelById(@PathVariable("id") id: Int): FDistributionChannelRes {
        return fDistributionChannelUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFDistributionChannel"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allDistributionChannelEntity: List<FDistributionChannelRes>
        get() = fDistributionChannelUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFDistributionChannelByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDistributionChannelByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FDistributionChannelRes> {
        return fDistributionChannelUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFDistributionChannelByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDistributionChannelByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FDistributionChannelRes?>? {
        return fDistributionChannelUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFDistributionChannel"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFDistributionChannel(@RequestBody fDistributionChannelResNew: FDistributionChannelRes): FDistributionChannelRes {
        fDistributionChannelResNew.id = 0 //Memastikan ID adalah Nol
        return fDistributionChannelUseCase.save(fDistributionChannelResNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFDistributionChannel/{id}"], method = [RequestMethod.PUT])
    fun updateFDistributionChannelInfo(@PathVariable("id") id: Int, @RequestBody fDistributionChannelResUpdated: FDistributionChannelRes?): FDistributionChannelRes {
        val fDistributionChannel = fDistributionChannelUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fDistributionChannelResUpdated != null) {
            fDistributionChannelResUpdated.id = fDistributionChannel.id
            if (fDistributionChannel.fdivisionBean >0) fDistributionChannelResUpdated.fdivisionBean = fDistributionChannel.fdivisionBean
            fDistributionChannelUseCase.save(fDistributionChannelResUpdated.toDomain())
            return fDistributionChannelResUpdated
        }
        return fDistributionChannel.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFDistributionChannel/{id}"], method = [RequestMethod.DELETE])
    fun deleteFDistributionChannel(@PathVariable("id") id: Int): FDistributionChannelRes {
        val fDistributionChannel = fDistributionChannelUseCase.findById(id)
        if (fDistributionChannel != null) {
            fDistributionChannelUseCase.delete(fDistributionChannel)
        }
        return fDistributionChannel.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FDistributionChannelRestController::class.java)
    }
}