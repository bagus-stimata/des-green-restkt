package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtArPaymenthJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymenth
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtArPaymenthRestController {
    @Autowired
    var ftArPaymenthJPARepository: FtArPaymenthJPARepository? = null

    @RequestMapping(value = ["/rest/getFtArPaymenthById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtArPaymenthById(@PathVariable("id") id: Long): FtArPaymenth {
        return ftArPaymenthJPARepository!!.findById(id).orElse(FtArPaymenth())
    }

    @get:RequestMapping(value = ["/rest/getAllFtArPaymenth"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltArPaymenth: List<FtArPaymenth>
        get() = ftArPaymenthJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtArPaymenthByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtArPaymenthByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtArPaymenth> {
        return ftArPaymenthJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtArPaymenth"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtArPaymenth(@RequestBody ftArPaymenthNew: FtArPaymenth): FtArPaymenth {
        ftArPaymenthNew.refno = 0 //Memastikan ID adalah Nol
        return ftArPaymenthJPARepository!!.save(ftArPaymenthNew)
    }

    @RequestMapping(value = ["/rest/updateFtArPaymenth/{id}"], method = [RequestMethod.PUT])
    fun updateFtArPaymenthInfo(@PathVariable("id") id: Long, @RequestBody ftArPaymenthUpdated: FtArPaymenth?): FtArPaymenth {
        val ftArPaymenth = ftArPaymenthJPARepository!!.findById(id).orElse(FtArPaymenth())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftArPaymenthUpdated != null) {
            ftArPaymenthUpdated.refno = ftArPaymenth.refno
            if (ftArPaymenth.fdivisionBean >0) ftArPaymenthUpdated.fdivisionBean = ftArPaymenth.fdivisionBean
            ftArPaymenthJPARepository!!.save(ftArPaymenthUpdated)
            return ftArPaymenthUpdated
        }
        return ftArPaymenth
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtArPaymenth/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtArPaymenth(@PathVariable("id") id: Long): FtArPaymenth? {
        val ftArPaymenth = ftArPaymenthJPARepository!!.findById(id).orElse(FtArPaymenth())
        if (ftArPaymenth != null) {
            ftArPaymenthJPARepository!!.delete(ftArPaymenth)
        }
        return ftArPaymenth
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtArPaymenthRestController::class.java)
    }
}