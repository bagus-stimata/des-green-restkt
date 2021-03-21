package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FDistributionChannelJPARepository
import com.erp.distribution.desgreenrestkt.model.FDistributionChannel
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFDistributionChannelById(@PathVariable("id") id: Int): FDistributionChannel {
        return fDistributionChannelJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFDistributionChannel"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allDistributionChannel: List<FDistributionChannel?>
        get() = fDistributionChannelJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFDistributionChannelByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDistributionChannelByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FDistributionChannel?>? {
        return fDistributionChannelJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFDistributionChannelByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDistributionChannelByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FDistributionChannel?>? {
        return fDistributionChannelJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFDistributionChannel"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFDistributionChannel(@RequestBody fDistributionChannelNew: FDistributionChannel): FDistributionChannel {
        fDistributionChannelNew.id = 0 //Memastikan ID adalah Nol
        return fDistributionChannelJPARepository!!.save(fDistributionChannelNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFDistributionChannel/{id}"], method = [RequestMethod.PUT])
    fun updateFDistributionChannelInfo(@PathVariable("id") id: Int, @RequestBody fDistributionChannelUpdated: FDistributionChannel?): FDistributionChannel {
        val fDistributionChannel = fDistributionChannelJPARepository!!.findById(id).orElse(FDistributionChannel())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fDistributionChannelUpdated != null) {
            fDistributionChannelUpdated.id = fDistributionChannel.id
            if (fDistributionChannel.fdivisionBean >0) fDistributionChannelUpdated.fdivisionBean = fDistributionChannel.fdivisionBean
            fDistributionChannelJPARepository!!.save(fDistributionChannelUpdated)
            return fDistributionChannelUpdated
        }
        return fDistributionChannel
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFDistributionChannel/{id}"], method = [RequestMethod.DELETE])
    fun deleteFDistributionChannel(@PathVariable("id") id: Int): FDistributionChannel? {
        val fDistributionChannel = fDistributionChannelJPARepository!!.findById(id).orElse(FDistributionChannel())
        if (fDistributionChannel != null) {
            fDistributionChannelJPARepository!!.delete(fDistributionChannel)
        }
        return fDistributionChannel
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FDistributionChannelRestController::class.java)
    }
}