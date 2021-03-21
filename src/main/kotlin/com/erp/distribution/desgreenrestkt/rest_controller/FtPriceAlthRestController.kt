package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtPriceAlthJPARepository
import com.erp.distribution.desgreenrestkt.model.FtPriceAlth
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class FtPriceAlthRestController {
    @Autowired
    var ftPriceAlthJPARepository: FtPriceAlthJPARepository? = null

    @RequestMapping(value = ["/rest/getFtPriceAlthById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtPriceAlthById(@PathVariable("id") id: Int): FtPriceAlth {
        return ftPriceAlthJPARepository!!.findById(id).orElse(FtPriceAlth())
    }

    @get:RequestMapping(value = ["/rest/getAllFtPriceAlth"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltPriceAlth: List<FtPriceAlth>
        get() = ftPriceAlthJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAlltPriceAlthByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAlltPriceAlthByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FtPriceAlth> {
        return ftPriceAlthJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).stream().filter { x: FtPriceAlth -> x.isStatusActive == true }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/createFtPriceAlth"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPriceAlth(@RequestBody ftPriceAlthNew: FtPriceAlth): FtPriceAlth {
        ftPriceAlthNew.id = 0 //Pastikan ID nya nol untuk Create Baru
        return ftPriceAlthJPARepository!!.save(ftPriceAlthNew)
    }

    @RequestMapping(value = ["/rest/updateFtPriceAlth/{id}"], method = [RequestMethod.PUT])
    fun updateFtPriceAlthInfo(@PathVariable("id") id: Int, @RequestBody ftPriceAlthUpdated: FtPriceAlth?): FtPriceAlth {
        val ftPriceAlth = ftPriceAlthJPARepository!!.findById(id).orElse(FtPriceAlth())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPriceAlthUpdated != null) {
            ftPriceAlthUpdated.id = ftPriceAlth.id
            if (ftPriceAlth.fdivisionBean >0) ftPriceAlthUpdated.fdivisionBean = ftPriceAlth.fdivisionBean
            ftPriceAlthJPARepository!!.save(ftPriceAlthUpdated)
            return ftPriceAlthUpdated
        }
        return ftPriceAlth
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPriceAlth/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPriceAlth(@PathVariable("id") id: Int): FtPriceAlth? {
        val ftPriceAlth = ftPriceAlthJPARepository!!.findById(id).orElse(FtPriceAlth())
        if (ftPriceAlth != null) {
            ftPriceAlthJPARepository!!.delete(ftPriceAlth)
        }
        return ftPriceAlth
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPriceAlthRestController::class.java)
    }
}