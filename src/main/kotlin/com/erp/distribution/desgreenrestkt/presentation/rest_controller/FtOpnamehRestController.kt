package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtOpnamehJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtOpnameh
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtOpnamehRestController {
    @Autowired
    var ftOpnamehJPARepository: FtOpnamehJPARepository? = null

    @RequestMapping(value = ["/rest/getFtOpnamehById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtOpnamehById(@PathVariable("id") id: Long?): FtOpnameh {
        return ftOpnamehJPARepository!!.findByRefno(id!!)
    }

    @get:RequestMapping(value = ["/rest/getAllFtOpnameh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltOpnameh: List<FtOpnameh>
        get() = ftOpnamehJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/createFtOpnameh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtOpnameh(@RequestBody ftOpnamehNew: FtOpnameh): FtOpnameh {
        ftOpnamehNew.refno = 0 //Memastikan ID adalah Nol
        return ftOpnamehJPARepository!!.save(ftOpnamehNew)
    }

    @RequestMapping(value = ["/rest/updateFtOpnameh/{id}"], method = [RequestMethod.PUT])
    fun updateFtOpnamehInfo(@PathVariable("id") id: Long?, @RequestBody ftOpnamehUpdated: FtOpnameh?): FtOpnameh {
        val ftOpnameh = ftOpnamehJPARepository!!.findByRefno(id!!)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftOpnamehUpdated != null) {
            ftOpnamehUpdated.refno = ftOpnameh.refno
            if (ftOpnameh.fdivisionBean >0) ftOpnamehUpdated.fdivisionBean = ftOpnameh.fdivisionBean
            ftOpnamehJPARepository!!.save(ftOpnamehUpdated)
            return ftOpnamehUpdated
        }
        return ftOpnameh
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtOpnameh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtOpnameh(@PathVariable("id") id: Long?): FtOpnameh? {
        val ftOpnameh = ftOpnamehJPARepository!!.findByRefno(id!!)
        if (ftOpnameh.refno >0) {
            ftOpnamehJPARepository!!.delete(ftOpnameh)
        }
        return ftOpnameh
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtOpnamehRestController::class.java)
    }
}