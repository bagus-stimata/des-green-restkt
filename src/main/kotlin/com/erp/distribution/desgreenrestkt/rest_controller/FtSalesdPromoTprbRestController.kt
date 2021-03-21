package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtSalesdPromoTprbJPARepository
import com.erp.distribution.desgreenrestkt.model.FtSalesdPromoTprb
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtSalesdPromoTprbRestController {
    @Autowired
    var ftSalesdPromoTprbJPARepository: FtSalesdPromoTprbJPARepository? = null

    @RequestMapping(value = ["/rest/getFtSalesdPromoTprbById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtSalesdPromoTprbById(@PathVariable("id") id: Long): FtSalesdPromoTprb {
        return ftSalesdPromoTprbJPARepository!!.findById(id).orElse(FtSalesdPromoTprb())
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesdPromoTprb"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSalesdPromoTprb: List<FtSalesdPromoTprb>
        get() = ftSalesdPromoTprbJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/createFtSalesdPromoTprb"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesdPromoTprb(@RequestBody ftSalesdPromoTprbNew: FtSalesdPromoTprb): FtSalesdPromoTprb {
        ftSalesdPromoTprbNew.id = 0 //Memastikan ID adalah Nol
        return ftSalesdPromoTprbJPARepository!!.save(ftSalesdPromoTprbNew)
    }

    @RequestMapping(value = ["/rest/updateFtSalesdPromoTprb/{id}"], method = [RequestMethod.PUT])
    fun updateFtSalesdPromoTprbInfo(@PathVariable("id") id: Long, @RequestBody ftSalesdPromoTprbUpdated: FtSalesdPromoTprb?): FtSalesdPromoTprb {
        val ftSalesdPromoTprb = ftSalesdPromoTprbJPARepository!!.findById(id).orElse(FtSalesdPromoTprb())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSalesdPromoTprbUpdated != null) {
            ftSalesdPromoTprbUpdated.id = ftSalesdPromoTprb.id
            if (ftSalesdPromoTprb.fpromoBean >0) ftSalesdPromoTprbUpdated.fpromoBean = ftSalesdPromoTprb.fpromoBean
            if (ftSalesdPromoTprb.fmaterialBean >0) ftSalesdPromoTprbUpdated.fmaterialBean = ftSalesdPromoTprb.fmaterialBean
            if (ftSalesdPromoTprb.ftSalesdFreegood == 0L) ftSalesdPromoTprbUpdated.ftSalesdFreegood = ftSalesdPromoTprb.ftSalesdFreegood
            ftSalesdPromoTprbJPARepository!!.save(ftSalesdPromoTprbUpdated)
            return ftSalesdPromoTprbUpdated
        }
        return ftSalesdPromoTprb
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesdPromoTprb/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesdPromoTprb(@PathVariable("id") id: Long): FtSalesdPromoTprb? {
        val ftSalesdPromoTprb = ftSalesdPromoTprbJPARepository!!.findById(id).orElse(FtSalesdPromoTprb())
        if (ftSalesdPromoTprb != null) {
            ftSalesdPromoTprbJPARepository!!.delete(ftSalesdPromoTprb)
        }
        return ftSalesdPromoTprb
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtSalesdPromoTprbRestController::class.java)
    }
}