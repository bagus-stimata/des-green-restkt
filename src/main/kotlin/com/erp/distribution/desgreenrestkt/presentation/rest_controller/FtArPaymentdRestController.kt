package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtArPaymentdJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymentdEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtArPaymentdRestController {
    @Autowired
    var ftArPaymentdJPARepository: FtArPaymentdJPARepository? = null

    @RequestMapping(value = ["/rest/getFtArPaymentdById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtArPaymentdById(@PathVariable("id") id: Long): FtArPaymentdEntity {
        return ftArPaymentdJPARepository!!.findById(id).orElse(FtArPaymentdEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtArPaymentd"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltArPaymentdEntity: List<FtArPaymentdEntity>
        get() = ftArPaymentdJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtArPaymentdByParent/{ftArPaymenthBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtArPaymentdByParentId(@PathVariable("ftArPaymenthBean") ftArPaymenthBean: Long): List<FtArPaymentdEntity> {
        return ftArPaymentdJPARepository!!.findAllByParentId(ftArPaymenthBean)
    }

    @RequestMapping(value = ["/rest/getAllFtArPaymentdByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtArPaymentdByListParentId(@RequestBody listFtArPaymenthBean: List<Long>): List<FtArPaymentdEntity> {
        return ftArPaymentdJPARepository!!.findAllByListParentId(listFtArPaymenthBean)
    }

    @RequestMapping(value = ["/rest/createFtArPaymentd"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtArPaymentd(@RequestBody ftArPaymentdEntityNew: FtArPaymentdEntity): FtArPaymentdEntity {
        ftArPaymentdEntityNew.id = 0 //Memastikan ID adalah Nol
        return ftArPaymentdJPARepository!!.save(ftArPaymentdEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtArPaymentd/{id}"], method = [RequestMethod.PUT])
    fun updateFtArPaymentdInfo(@PathVariable("id") id: Long, @RequestBody ftArPaymentdEntityUpdated: FtArPaymentdEntity?): FtArPaymentdEntity {
        val ftArPaymentd = ftArPaymentdJPARepository!!.findById(id).orElse(FtArPaymentdEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftArPaymentdEntityUpdated != null) {
            ftArPaymentdEntityUpdated.id = ftArPaymentd.id
            //            if (ftArPaymentd.getFtArPaymenthBean()==null) ftArPaymentdUpdated.setFtArPaymenthBean(ftArPaymentd.getFtArPaymenthBean());
            ftArPaymentdJPARepository!!.save(ftArPaymentdEntityUpdated)
            return ftArPaymentdEntityUpdated
        }
        return ftArPaymentd
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtArPaymentd/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtArPaymentd(@PathVariable("id") id: Long): FtArPaymentdEntity? {
        val ftArPaymentd = ftArPaymentdJPARepository!!.findById(id).orElse(FtArPaymentdEntity())
        if (ftArPaymentd != null) {
            ftArPaymentdJPARepository!!.delete(ftArPaymentd)
        }
        return ftArPaymentd
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtArPaymentdRestController::class.java)
    }
}