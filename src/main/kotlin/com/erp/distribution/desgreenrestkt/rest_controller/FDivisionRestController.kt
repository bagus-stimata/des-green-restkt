package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCompanyJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FDivisionJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FDivision
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FDivisionRestController {
    @Autowired
    var fDivisionJPARepository: FDivisionJPARepository? = null

    @Autowired
    var fCompanyJPARepository: FCompanyJPARepository? = null

    @RequestMapping(value = ["/rest/getFDivisionById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFDivisionById(@PathVariable("id") id: Int): FDivision {
        return fDivisionJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFDivision"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allDivision: List<FDivision>
        get() = fDivisionJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFDivisionByParent/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDivisionByParentId(@PathVariable("fcompanyBean") fcompanyBean: Int): List<FDivision> {
        return fDivisionJPARepository!!.findAllByParentId(fcompanyBean)
    }

    @RequestMapping(value = ["/rest/getAllFDivisionBySameCompany/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDivisionBySameCompany(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FDivision> {
        val fDivision = fDivisionJPARepository!!.findById(fdivisionBean).get()
        return fDivisionJPARepository!!.findAllByParentId(fDivision.fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFDivision"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFDivision(@RequestBody fDivisionNew: FDivision): FDivision {
        fDivisionNew.id = 0 //Memastikan ID adalah Nol
        return fDivisionJPARepository!!.save(fDivisionNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFDivision/{id}"], method = [RequestMethod.PUT])
    fun updateFDivisionInfo(@PathVariable("id") id: Int, @RequestBody fDivisionUpdated: FDivision?): FDivision {
        val fDivision = fDivisionJPARepository!!.findById(id).orElse(FDivision())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fDivisionUpdated != null) {
            fDivisionUpdated.id = fDivision.id
            if (fDivision.fcompanyBean >0) fDivisionUpdated.fcompanyBean = fDivision.fcompanyBean
            if (fDivision.accCostCenterBean >0) fDivisionUpdated.accCostCenterBean = fDivision.accCostCenterBean
            fDivisionJPARepository!!.save(fDivisionUpdated)
            return fDivisionUpdated
        }
        return fDivision
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFDivision/{id}"], method = [RequestMethod.DELETE])
    fun deleteFDivision(@PathVariable("id") id: Int): FDivision? {
        val fDivision = fDivisionJPARepository!!.findById(id).orElse(FDivision())
        if (fDivision != null) {
            fDivisionJPARepository!!.delete(fDivision)
        }
        return fDivision
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FDivisionRestController::class.java)
    }
}