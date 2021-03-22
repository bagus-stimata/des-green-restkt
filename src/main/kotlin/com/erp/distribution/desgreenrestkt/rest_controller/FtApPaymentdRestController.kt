package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtApPaymentdJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtApPaymentd
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtApPaymentdRestController {
    @Autowired
    var ftApPaymentdJPARepository: FtApPaymentdJPARepository? = null

    @RequestMapping(value = ["/rest/getFtApPaymentdById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtApPaymentdById(@PathVariable("id") id: Long): FtApPaymentd {
        return ftApPaymentdJPARepository!!.findById(id).orElse(FtApPaymentd())
    }

    @get:RequestMapping(value = ["/rest/getAllFtApPaymentd"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltApPaymentd: List<FtApPaymentd>
        get() = ftApPaymentdJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtApPaymentdByParent/{ftApPaymenthBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtApPaymentdByParentId(@PathVariable("ftApPaymenthBean") ftApPaymenthBean: Long): List<FtApPaymentd> {
        return ftApPaymentdJPARepository!!.findAllByParentId(ftApPaymenthBean)
    }

    @RequestMapping(value = ["/rest/getAllFtApPaymentdByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtApPaymentdByListParentId(@RequestBody listFtApPaymenthBean: List<Long>): List<FtApPaymentd> {
        return ftApPaymentdJPARepository!!.findAllByListParentId(listFtApPaymenthBean)
    }

    @RequestMapping(value = ["/rest/createFtApPaymentd"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtApPaymentd(@RequestBody ftApPaymentdNew: FtApPaymentd): FtApPaymentd {
        ftApPaymentdNew.id = 0 //Memastikan ID adalah Nol
        return ftApPaymentdJPARepository!!.save(ftApPaymentdNew)
    }

    @RequestMapping(value = ["/rest/updateFtApPaymentd/{id}"], method = [RequestMethod.PUT])
    fun updateFtApPaymentdInfo(@PathVariable("id") id: Long, @RequestBody ftApPaymentdUpdated: FtApPaymentd?): FtApPaymentd {
        val ftApPaymentd = ftApPaymentdJPARepository!!.findById(id).orElse(FtApPaymentd())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftApPaymentdUpdated != null) {
            ftApPaymentdUpdated.id = ftApPaymentd.id
            //            if (ftApPaymentd.getFtApPaymenthBean()==null) ftApPaymentdUpdated.setFtApPaymenthBean(ftApPaymentd.getFtApPaymenthBean());
            ftApPaymentdJPARepository!!.save(ftApPaymentdUpdated)
            return ftApPaymentdUpdated
        }
        return ftApPaymentd
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtApPaymentd/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtApPaymentd(@PathVariable("id") id: Long): FtApPaymentd? {
        val ftApPaymentd = ftApPaymentdJPARepository!!.findById(id).orElse(FtApPaymentd())
        if (ftApPaymentd != null) {
            ftApPaymentdJPARepository!!.delete(ftApPaymentd)
        }
        return ftApPaymentd
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtApPaymentdRestController::class.java)
    }
}