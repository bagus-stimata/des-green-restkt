package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtPurchasehJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchaseh
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtPurchasehRestController {
    @Autowired
    var ftPurchasehJPARepository: FtPurchasehJPARepository? = null

    @RequestMapping(value = ["/rest/getFtPurchasehById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtPurchasehById(@PathVariable("id") id: Long?): FtPurchaseh {
        return ftPurchasehJPARepository!!.findByRefno(id!!)
    }

    @get:RequestMapping(value = ["/rest/getAllFtPurchaseh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltPurchaseh: List<FtPurchaseh>
        get() = ftPurchasehJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtPurchasehByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPurchasehByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtPurchaseh> {
        return ftPurchasehJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtPurchaseh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPurchaseh(@RequestBody ftPurchasehNew: FtPurchaseh): FtPurchaseh {
        ftPurchasehNew.refno = 0 //Memastikan ID adalah Nol
        return ftPurchasehJPARepository!!.save(ftPurchasehNew)
    }

    @RequestMapping(value = ["/rest/updateFtPurchaseh/{id}"], method = [RequestMethod.PUT])
    fun updateFtPurchasehInfo(@PathVariable("id") id: Long?, @RequestBody ftPurchasehUpdated: FtPurchaseh?): FtPurchaseh {
        val ftPurchaseh = ftPurchasehJPARepository!!.findByRefno(id!!)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPurchasehUpdated != null) {
            ftPurchasehUpdated.refno = ftPurchaseh.refno
            if (ftPurchaseh.fdivisionBean >0) ftPurchasehUpdated.fdivisionBean = ftPurchaseh.fdivisionBean
            ftPurchasehJPARepository!!.save(ftPurchasehUpdated)
            return ftPurchasehUpdated
        }
        return ftPurchaseh
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPurchaseh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPurchaseh(@PathVariable("id") id: Long?): FtPurchaseh? {
        val ftPurchaseh = ftPurchasehJPARepository!!.findByRefno(id!!)
        if (ftPurchaseh.refno >0) {
            ftPurchasehJPARepository!!.delete(ftPurchaseh)
        }
        return ftPurchaseh
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPurchasehRestController::class.java)
    }
}