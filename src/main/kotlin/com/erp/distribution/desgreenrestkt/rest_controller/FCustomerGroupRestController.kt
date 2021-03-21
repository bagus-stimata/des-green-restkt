package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FCustomerGroupJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerGroup
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFCustomerGroupById(@PathVariable("id") id: Int): FCustomerGroup {
        return fCustomerGroupJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomerGroup"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomerGroup: List<FCustomerGroup?>
        get() = fCustomerGroupJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFCustomerGroupByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerGroupByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FCustomerGroup?>? {
        return fCustomerGroupJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFCustomerGroupByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerGroupByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FCustomerGroup?>? {
        return fCustomerGroupJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFCustomerGroup"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomerGroup(@RequestBody fCustomerGroupNew: FCustomerGroup): FCustomerGroup {
        fCustomerGroupNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerGroupJPARepository!!.save(fCustomerGroupNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFCustomerGroup/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerGroupInfo(@PathVariable("id") id: Int, @RequestBody fCustomerGroupUpdated: FCustomerGroup?): FCustomerGroup {
        val fCustomerGroup = fCustomerGroupJPARepository!!.findById(id).orElse(FCustomerGroup())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerGroupUpdated != null) {
            fCustomerGroupUpdated.id = fCustomerGroup.id
            if (fCustomerGroup.fdivisionBean >0) fCustomerGroupUpdated.fdivisionBean = fCustomerGroup.fdivisionBean
            fCustomerGroupUpdated.ftPriceAlthBean = fCustomerGroup.ftPriceAlthBean //bisa ada bisa enggak
            fCustomerGroupJPARepository!!.save(fCustomerGroupUpdated)
            return fCustomerGroupUpdated
        }
        return fCustomerGroup
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCustomerGroup/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomerGroup(@PathVariable("id") id: Int): FCustomerGroup? {
        val fCustomerGroup = fCustomerGroupJPARepository!!.findById(id).orElse(FCustomerGroup())
        if (fCustomerGroup != null) {
            fCustomerGroupJPARepository!!.delete(fCustomerGroup)
        }
        return fCustomerGroup
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerGroupRestController::class.java)
    }
}