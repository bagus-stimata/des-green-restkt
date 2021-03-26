package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FDistributionChannelJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FDistributionChannelRestController {
    @Autowired
    var fDistributionChannelJPARepository: FDistributionChannelJPARepository? = null

    @RequestMapping(value = ["/rest/getFDistributionChannelById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFDistributionChannelById(@PathVariable("id") id: Int): FDistributionChannelEntity {
        return fDistributionChannelJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFDistributionChannel"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allDistributionChannelEntity: List<FDistributionChannelEntity?>
        get() = fDistributionChannelJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFDistributionChannelByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDistributionChannelByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FDistributionChannelEntity?>? {
        return fDistributionChannelJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFDistributionChannelByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDistributionChannelByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FDistributionChannelEntity?>? {
        return fDistributionChannelJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFDistributionChannel"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFDistributionChannel(@RequestBody fDistributionChannelEntityNew: FDistributionChannelEntity): FDistributionChannelEntity {
        fDistributionChannelEntityNew.id = 0 //Memastikan ID adalah Nol
        return fDistributionChannelJPARepository!!.save(fDistributionChannelEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFDistributionChannel/{id}"], method = [RequestMethod.PUT])
    fun updateFDistributionChannelInfo(@PathVariable("id") id: Int, @RequestBody fDistributionChannelEntityUpdated: FDistributionChannelEntity?): FDistributionChannelEntity {
        val fDistributionChannel = fDistributionChannelJPARepository!!.findById(id).orElse(FDistributionChannelEntity())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fDistributionChannelEntityUpdated != null) {
            fDistributionChannelEntityUpdated.id = fDistributionChannel.id
            if (fDistributionChannel.fdivisionBean >0) fDistributionChannelEntityUpdated.fdivisionBean = fDistributionChannel.fdivisionBean
            fDistributionChannelJPARepository!!.save(fDistributionChannelEntityUpdated)
            return fDistributionChannelEntityUpdated
        }
        return fDistributionChannel
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFDistributionChannel/{id}"], method = [RequestMethod.DELETE])
    fun deleteFDistributionChannel(@PathVariable("id") id: Int): FDistributionChannelEntity? {
        val fDistributionChannel = fDistributionChannelJPARepository!!.findById(id).orElse(FDistributionChannelEntity())
        if (fDistributionChannel != null) {
            fDistributionChannelJPARepository!!.delete(fDistributionChannel)
        }
        return fDistributionChannel
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FDistributionChannelRestController::class.java)
    }
}