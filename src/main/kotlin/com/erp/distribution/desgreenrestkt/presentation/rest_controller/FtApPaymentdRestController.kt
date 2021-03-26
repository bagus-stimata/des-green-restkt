package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtApPaymentdJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtApPaymentdEntity
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
    fun getFtApPaymentdById(@PathVariable("id") id: Long): FtApPaymentdEntity {
        return ftApPaymentdJPARepository!!.findById(id).orElse(FtApPaymentdEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtApPaymentd"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltApPaymentdEntity: List<FtApPaymentdEntity>
        get() = ftApPaymentdJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtApPaymentdByParent/{ftApPaymenthBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtApPaymentdByParentId(@PathVariable("ftApPaymenthBean") ftApPaymenthBean: Long): List<FtApPaymentdEntity> {
        return ftApPaymentdJPARepository!!.findAllByParentId(ftApPaymenthBean)
    }

    @RequestMapping(value = ["/rest/getAllFtApPaymentdByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtApPaymentdByListParentId(@RequestBody listFtApPaymenthBean: List<Long>): List<FtApPaymentdEntity> {
        return ftApPaymentdJPARepository!!.findAllByListParentId(listFtApPaymenthBean)
    }

    @RequestMapping(value = ["/rest/createFtApPaymentd"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtApPaymentd(@RequestBody ftApPaymentdEntityNew: FtApPaymentdEntity): FtApPaymentdEntity {
        ftApPaymentdEntityNew.id = 0 //Memastikan ID adalah Nol
        return ftApPaymentdJPARepository!!.save(ftApPaymentdEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtApPaymentd/{id}"], method = [RequestMethod.PUT])
    fun updateFtApPaymentdInfo(@PathVariable("id") id: Long, @RequestBody ftApPaymentdEntityUpdated: FtApPaymentdEntity?): FtApPaymentdEntity {
        val ftApPaymentd = ftApPaymentdJPARepository!!.findById(id).orElse(FtApPaymentdEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftApPaymentdEntityUpdated != null) {
            ftApPaymentdEntityUpdated.id = ftApPaymentd.id
            //            if (ftApPaymentd.getFtApPaymenthBean()==null) ftApPaymentdUpdated.setFtApPaymenthBean(ftApPaymentd.getFtApPaymenthBean());
            ftApPaymentdJPARepository!!.save(ftApPaymentdEntityUpdated)
            return ftApPaymentdEntityUpdated
        }
        return ftApPaymentd
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtApPaymentd/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtApPaymentd(@PathVariable("id") id: Long): FtApPaymentdEntity? {
        val ftApPaymentd = ftApPaymentdJPARepository!!.findById(id).orElse(FtApPaymentdEntity())
        if (ftApPaymentd != null) {
            ftApPaymentdJPARepository!!.delete(ftApPaymentd)
        }
        return ftApPaymentd
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtApPaymentdRestController::class.java)
    }
}