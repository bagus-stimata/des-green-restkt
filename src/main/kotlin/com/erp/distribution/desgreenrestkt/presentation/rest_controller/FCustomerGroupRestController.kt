package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCustomerGroupJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerGroupEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FCustomerGroupRestController {
    @Autowired
    var fCustomerGroupJPARepository: FCustomerGroupJPARepository? = null

    @RequestMapping(value = ["/rest/getFCustomerGroupById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCustomerGroupById(@PathVariable("id") id: Int): FCustomerGroupEntity {
        return fCustomerGroupJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomerGroup"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomerGroupEntity: List<FCustomerGroupEntity?>
        get() = fCustomerGroupJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFCustomerGroupByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerGroupByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FCustomerGroupEntity?>? {
        return fCustomerGroupJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFCustomerGroupByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerGroupByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FCustomerGroupEntity?>? {
        return fCustomerGroupJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFCustomerGroup"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomerGroup(@RequestBody fCustomerGroupEntityNew: FCustomerGroupEntity): FCustomerGroupEntity {
        fCustomerGroupEntityNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerGroupJPARepository!!.save(fCustomerGroupEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFCustomerGroup/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerGroupInfo(@PathVariable("id") id: Int, @RequestBody fCustomerGroupEntityUpdated: FCustomerGroupEntity?): FCustomerGroupEntity {
        val fCustomerGroup = fCustomerGroupJPARepository!!.findById(id).orElse(FCustomerGroupEntity())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerGroupEntityUpdated != null) {
            fCustomerGroupEntityUpdated.id = fCustomerGroup.id
            if (fCustomerGroup.fdivisionBean >0) fCustomerGroupEntityUpdated.fdivisionBean = fCustomerGroup.fdivisionBean
            fCustomerGroupEntityUpdated.ftPriceAlthBean = fCustomerGroup.ftPriceAlthBean //bisa ada bisa enggak
            fCustomerGroupJPARepository!!.save(fCustomerGroupEntityUpdated)
            return fCustomerGroupEntityUpdated
        }
        return fCustomerGroup
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCustomerGroup/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomerGroup(@PathVariable("id") id: Int): FCustomerGroupEntity? {
        val fCustomerGroup = fCustomerGroupJPARepository!!.findById(id).orElse(FCustomerGroupEntity())
        if (fCustomerGroup != null) {
            fCustomerGroupJPARepository!!.delete(fCustomerGroup)
        }
        return fCustomerGroup
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerGroupRestController::class.java)
    }
}