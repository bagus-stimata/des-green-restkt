package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtApPaymenthJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtApPaymenthEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
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
    fun getFtApPaymenthById(@PathVariable("id") id: Long): FtApPaymenthEntity {
        return ftApPaymenthJPARepository!!.findById(id).orElse(FtApPaymenthEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtApPaymenth"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltApPaymenthEntity: List<FtApPaymenthEntity>
        get() = ftApPaymenthJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtApPaymenthByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtApPaymenthByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtApPaymenthEntity> {
        return ftApPaymenthJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtApPaymenth"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtApPaymenth(@RequestBody ftApPaymenthEntityNew: FtApPaymenthEntity): FtApPaymenthEntity {
        ftApPaymenthEntityNew.refno = 0 //Memastikan ID adalah Nol
        return ftApPaymenthJPARepository!!.save(ftApPaymenthEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtApPaymenth/{id}"], method = [RequestMethod.PUT])
    fun updateFtApPaymenthInfo(@PathVariable("id") id: Long, @RequestBody ftApPaymenthEntityUpdated: FtApPaymenthEntity?): FtApPaymenthEntity {
        val ftApPaymenth = ftApPaymenthJPARepository!!.findById(id).orElse(FtApPaymenthEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftApPaymenthEntityUpdated != null) {
            ftApPaymenthEntityUpdated.refno = ftApPaymenth.refno
            if (ftApPaymenth.fdivisionBean >0) ftApPaymenthEntityUpdated.fdivisionBean = ftApPaymenth.fdivisionBean
            ftApPaymenthJPARepository!!.save(ftApPaymenthEntityUpdated)
            return ftApPaymenthEntityUpdated
        }
        return ftApPaymenth
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtApPaymenth/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtApPaymenth(@PathVariable("id") id: Long): FtApPaymenthEntity? {
        val ftApPaymenth = ftApPaymenthJPARepository!!.findById(id).orElse(FtApPaymenthEntity())
        if (ftApPaymenth != null) {
            ftApPaymenthJPARepository!!.delete(ftApPaymenth)
        }
        return ftApPaymenth
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtApPaymenthRestController::class.java)
    }
}