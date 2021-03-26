package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtArPaymenthJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymenthEntity
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
    fun getFtArPaymenthById(@PathVariable("id") id: Long): FtArPaymenthEntity {
        return ftArPaymenthJPARepository!!.findById(id).orElse(FtArPaymenthEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtArPaymenth"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltArPaymenthEntity: List<FtArPaymenthEntity>
        get() = ftArPaymenthJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtArPaymenthByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtArPaymenthByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtArPaymenthEntity> {
        return ftArPaymenthJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtArPaymenth"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtArPaymenth(@RequestBody ftArPaymenthEntityNew: FtArPaymenthEntity): FtArPaymenthEntity {
        ftArPaymenthEntityNew.refno = 0 //Memastikan ID adalah Nol
        return ftArPaymenthJPARepository!!.save(ftArPaymenthEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtArPaymenth/{id}"], method = [RequestMethod.PUT])
    fun updateFtArPaymenthInfo(@PathVariable("id") id: Long, @RequestBody ftArPaymenthEntityUpdated: FtArPaymenthEntity?): FtArPaymenthEntity {
        val ftArPaymenth = ftArPaymenthJPARepository!!.findById(id).orElse(FtArPaymenthEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftArPaymenthEntityUpdated != null) {
            ftArPaymenthEntityUpdated.refno = ftArPaymenth.refno
            if (ftArPaymenth.fdivisionBean >0) ftArPaymenthEntityUpdated.fdivisionBean = ftArPaymenth.fdivisionBean
            ftArPaymenthJPARepository!!.save(ftArPaymenthEntityUpdated)
            return ftArPaymenthEntityUpdated
        }
        return ftArPaymenth
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtArPaymenth/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtArPaymenth(@PathVariable("id") id: Long): FtArPaymenthEntity? {
        val ftArPaymenth = ftArPaymenthJPARepository!!.findById(id).orElse(FtArPaymenthEntity())
        if (ftArPaymenth != null) {
            ftArPaymenthJPARepository!!.delete(ftArPaymenth)
        }
        return ftArPaymenth
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtArPaymenthRestController::class.java)
    }
}