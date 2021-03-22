package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.SysvarJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.Sysvar
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class SysvarRestController {
    @Autowired
    var sysvarJPARepository: SysvarJPARepository? = null

    @RequestMapping(value = ["/rest/getSysvarById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getSysvarById(@PathVariable("sysvarId") sysvarId: String?): Sysvar? {
        return sysvarJPARepository!!.findBySysvarId(sysvarId)
    }

    @get:RequestMapping(value = ["/rest/getAllSysvar"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allArea: List<Sysvar?>
        get() = sysvarJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllSysvarByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllSysvarByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<Sysvar?>? {
        return sysvarJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createSysvar"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createSysvar(@RequestBody sysvarNew: Sysvar): Sysvar {
        sysvarNew.id = "" //Memastikan ID adalah Nol
        return sysvarJPARepository!!.save(sysvarNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateSysvar/{id}"], method = [RequestMethod.PUT])
    fun updateSysvarInfo(@PathVariable("sysvarId") sysvarId: String, @RequestBody fAreaUpdated: Sysvar?): Sysvar {
        val fArea = sysvarJPARepository!!.findById(sysvarId).orElse(Sysvar())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fAreaUpdated != null) {
            fAreaUpdated.id = fArea.id
            if (fArea.fdivisionBean == null) fAreaUpdated.fdivisionBean = fArea.fdivisionBean
            sysvarJPARepository!!.save(fAreaUpdated)
            return fAreaUpdated
        }
        return fArea
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteSysvar/{id}"], method = [RequestMethod.DELETE])
    fun deleteSysvar(@PathVariable("sysvarId") sysvarId: String): Sysvar? {
        val fArea = sysvarJPARepository!!.findById(sysvarId).orElse(Sysvar())
        if (fArea != null) {
            sysvarJPARepository!!.delete(fArea)
        }
        return fArea
    }

    companion object {
        private val logger = LoggerFactory.getLogger(SysvarRestController::class.java)
    }
}