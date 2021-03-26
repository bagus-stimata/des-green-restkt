package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCompanyJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FDivisionJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FDivisionEntity
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
    fun getFDivisionById(@PathVariable("id") id: Int): FDivisionEntity {
        return fDivisionJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFDivision"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allDivisionEntity: List<FDivisionEntity>
        get() = fDivisionJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFDivisionByParent/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDivisionByParentId(@PathVariable("fcompanyBean") fcompanyBean: Int): List<FDivisionEntity> {
        return fDivisionJPARepository!!.findAllByParentId(fcompanyBean)
    }

    @RequestMapping(value = ["/rest/getAllFDivisionBySameCompany/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFDivisionBySameCompany(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FDivisionEntity> {
        val fDivision = fDivisionJPARepository!!.findById(fdivisionBean).get()
        return fDivisionJPARepository!!.findAllByParentId(fDivision.fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFDivision"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFDivision(@RequestBody fDivisionEntityNew: FDivisionEntity): FDivisionEntity {
        fDivisionEntityNew.id = 0 //Memastikan ID adalah Nol
        return fDivisionJPARepository!!.save(fDivisionEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFDivision/{id}"], method = [RequestMethod.PUT])
    fun updateFDivisionInfo(@PathVariable("id") id: Int, @RequestBody fDivisionEntityUpdated: FDivisionEntity?): FDivisionEntity {
        val fDivision = fDivisionJPARepository!!.findById(id).orElse(FDivisionEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fDivisionEntityUpdated != null) {
            fDivisionEntityUpdated.id = fDivision.id
            if (fDivision.fcompanyBean >0) fDivisionEntityUpdated.fcompanyBean = fDivision.fcompanyBean
            if (fDivision.accCostCenterBean >0) fDivisionEntityUpdated.accCostCenterBean = fDivision.accCostCenterBean
            fDivisionJPARepository!!.save(fDivisionEntityUpdated)
            return fDivisionEntityUpdated
        }
        return fDivision
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFDivision/{id}"], method = [RequestMethod.DELETE])
    fun deleteFDivision(@PathVariable("id") id: Int): FDivisionEntity? {
        val fDivision = fDivisionJPARepository!!.findById(id).orElse(FDivisionEntity())
        if (fDivision != null) {
            fDivisionJPARepository!!.delete(fDivision)
        }
        return fDivision
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FDivisionRestController::class.java)
    }
}