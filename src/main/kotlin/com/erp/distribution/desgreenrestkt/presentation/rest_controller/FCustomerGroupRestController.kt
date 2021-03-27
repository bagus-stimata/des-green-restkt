package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCustomerGroupJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFAreaUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFCustomerGroupUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FCustomerGroupRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FCustomerGroupRestController   @Autowired constructor(
    val fCustomerGroupUseCase: GetFCustomerGroupUseCase
) {
//    @Autowired
//    var fCustomerGroupJPARepository: FCustomerGroupJPARepository? = null

    @RequestMapping(value = ["/rest/getFCustomerGroupById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCustomerGroupById(@PathVariable("id") id: Int): FCustomerGroupRes {
        return fCustomerGroupUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomerGroup"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomerGroupEntity: List<FCustomerGroupRes?>
        get() = fCustomerGroupUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFCustomerGroupByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerGroupByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FCustomerGroupRes?>? {
        return fCustomerGroupUseCase.findAllByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFCustomerGroupByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerGroupByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FCustomerGroupRes?>? {
        return fCustomerGroupUseCase.findAllByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFCustomerGroup"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomerGroup(@RequestBody fCustomerGroupResNew: FCustomerGroupRes): FCustomerGroupRes {
        fCustomerGroupResNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerGroupUseCase.save(fCustomerGroupResNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFCustomerGroup/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerGroupInfo(@PathVariable("id") id: Int, @RequestBody fCustomerGroupResUpdated: FCustomerGroupRes?): FCustomerGroupRes {
        val fCustomerGroup = fCustomerGroupUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerGroupResUpdated != null) {
            fCustomerGroupResUpdated.id = fCustomerGroup.id
            if (fCustomerGroup.fdivisionBean >0) fCustomerGroupResUpdated.fdivisionBean = fCustomerGroup.fdivisionBean
            fCustomerGroupResUpdated.ftPriceAlthBean = fCustomerGroup.ftPriceAlthBean //bisa ada bisa enggak
            fCustomerGroupUseCase.save(fCustomerGroupResUpdated.toDomain())
            return fCustomerGroupResUpdated
        }
        return fCustomerGroup.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCustomerGroup/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomerGroup(@PathVariable("id") id: Int): FCustomerGroupRes? {
        val fCustomerGroup = fCustomerGroupUseCase.findById(id)
        if (fCustomerGroup != null) {
            fCustomerGroupUseCase.delete(fCustomerGroup)
        }
        return fCustomerGroup.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerGroupRestController::class.java)
    }
}