package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtArPaymentdJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymentd
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFtArPaymentdById(@PathVariable("id") id: Long): FtArPaymentd {
        return ftArPaymentdJPARepository!!.findById(id).orElse(FtArPaymentd())
    }

    @get:RequestMapping(value = ["/rest/getAllFtArPaymentd"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltArPaymentd: List<FtArPaymentd>
        get() = ftArPaymentdJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtArPaymentdByParent/{ftArPaymenthBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtArPaymentdByParentId(@PathVariable("ftArPaymenthBean") ftArPaymenthBean: Long): List<FtArPaymentd> {
        return ftArPaymentdJPARepository!!.findAllByParentId(ftArPaymenthBean)
    }

    @RequestMapping(value = ["/rest/getAllFtArPaymentdByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtArPaymentdByListParentId(@RequestBody listFtArPaymenthBean: List<Long>): List<FtArPaymentd> {
        return ftArPaymentdJPARepository!!.findAllByListParentId(listFtArPaymenthBean)
    }

    @RequestMapping(value = ["/rest/createFtArPaymentd"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtArPaymentd(@RequestBody ftArPaymentdNew: FtArPaymentd): FtArPaymentd {
        ftArPaymentdNew.id = 0 //Memastikan ID adalah Nol
        return ftArPaymentdJPARepository!!.save(ftArPaymentdNew)
    }

    @RequestMapping(value = ["/rest/updateFtArPaymentd/{id}"], method = [RequestMethod.PUT])
    fun updateFtArPaymentdInfo(@PathVariable("id") id: Long, @RequestBody ftArPaymentdUpdated: FtArPaymentd?): FtArPaymentd {
        val ftArPaymentd = ftArPaymentdJPARepository!!.findById(id).orElse(FtArPaymentd())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftArPaymentdUpdated != null) {
            ftArPaymentdUpdated.id = ftArPaymentd.id
            //            if (ftArPaymentd.getFtArPaymenthBean()==null) ftArPaymentdUpdated.setFtArPaymenthBean(ftArPaymentd.getFtArPaymenthBean());
            ftArPaymentdJPARepository!!.save(ftArPaymentdUpdated)
            return ftArPaymentdUpdated
        }
        return ftArPaymentd
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtArPaymentd/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtArPaymentd(@PathVariable("id") id: Long): FtArPaymentd? {
        val ftArPaymentd = ftArPaymentdJPARepository!!.findById(id).orElse(FtArPaymentd())
        if (ftArPaymentd != null) {
            ftArPaymentdJPARepository!!.delete(ftArPaymentd)
        }
        return ftArPaymentd
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtArPaymentdRestController::class.java)
    }
}