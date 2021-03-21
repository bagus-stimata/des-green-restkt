package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtApPaymenthJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtApPaymenth
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtApPaymenthRestController {
    @Autowired
    var ftApPaymenthJPARepository: FtApPaymenthJPARepository? = null

    @RequestMapping(value = ["/rest/getFtApPaymenthById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtApPaymenthById(@PathVariable("id") id: Long): FtApPaymenth {
        return ftApPaymenthJPARepository!!.findById(id).orElse(FtApPaymenth())
    }

    @get:RequestMapping(value = ["/rest/getAllFtApPaymenth"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltApPaymenth: List<FtApPaymenth>
        get() = ftApPaymenthJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtApPaymenthByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtApPaymenthByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtApPaymenth> {
        return ftApPaymenthJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtApPaymenth"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtApPaymenth(@RequestBody ftApPaymenthNew: FtApPaymenth): FtApPaymenth {
        ftApPaymenthNew.refno = 0 //Memastikan ID adalah Nol
        return ftApPaymenthJPARepository!!.save(ftApPaymenthNew)
    }

    @RequestMapping(value = ["/rest/updateFtApPaymenth/{id}"], method = [RequestMethod.PUT])
    fun updateFtApPaymenthInfo(@PathVariable("id") id: Long, @RequestBody ftApPaymenthUpdated: FtApPaymenth?): FtApPaymenth {
        val ftApPaymenth = ftApPaymenthJPARepository!!.findById(id).orElse(FtApPaymenth())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftApPaymenthUpdated != null) {
            ftApPaymenthUpdated.refno = ftApPaymenth.refno
            if (ftApPaymenth.fdivisionBean >0) ftApPaymenthUpdated.fdivisionBean = ftApPaymenth.fdivisionBean
            ftApPaymenthJPARepository!!.save(ftApPaymenthUpdated)
            return ftApPaymenthUpdated
        }
        return ftApPaymenth
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtApPaymenth/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtApPaymenth(@PathVariable("id") id: Long): FtApPaymenth? {
        val ftApPaymenth = ftApPaymenthJPARepository!!.findById(id).orElse(FtApPaymenth())
        if (ftApPaymenth != null) {
            ftApPaymenthJPARepository!!.delete(ftApPaymenth)
        }
        return ftApPaymenth
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtApPaymenthRestController::class.java)
    }
}