package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtSalesdPromoTpruDiscJPARepository
import com.erp.distribution.desgreenrestkt.model.FtSalesdPromoTpruDisc
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtSalesdPromoTpruDiscRestController {
    @Autowired
    var ftSalesdPromoTpruDiscJPARepository: FtSalesdPromoTpruDiscJPARepository? = null

    @RequestMapping(value = ["/rest/getFtSalesdPromoTpruDiscById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtSalesdPromoTpruDiscById(@PathVariable("id") id: Long): FtSalesdPromoTpruDisc {
        return ftSalesdPromoTpruDiscJPARepository!!.findById(id).orElse(FtSalesdPromoTpruDisc())
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesdPromoTpruDisc"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSalesdPromoTpruDisc: List<FtSalesdPromoTpruDisc>
        get() = ftSalesdPromoTpruDiscJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/createFtSalesdPromoTpruDisc"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesdPromoTpruDisc(@RequestBody ftSalesdPromoTpruDiscNew: FtSalesdPromoTpruDisc): FtSalesdPromoTpruDisc {
        ftSalesdPromoTpruDiscNew.id = 0 //Memastikan ID adalah Nol
        return ftSalesdPromoTpruDiscJPARepository!!.save(ftSalesdPromoTpruDiscNew)
    }

    @RequestMapping(value = ["/rest/updateFtSalesdPromoTpruDisc/{id}"], method = [RequestMethod.PUT])
    fun updateFtSalesdPromoTpruDiscInfo(@PathVariable("id") id: Long, @RequestBody ftSalesdPromoTpruDiscUpdated: FtSalesdPromoTpruDisc?): FtSalesdPromoTpruDisc {
        val ftSalesdPromoTpruDisc = ftSalesdPromoTpruDiscJPARepository!!.findById(id).orElse(FtSalesdPromoTpruDisc())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSalesdPromoTpruDiscUpdated != null) {
            ftSalesdPromoTpruDiscUpdated.id = ftSalesdPromoTpruDisc.id
            if (ftSalesdPromoTpruDisc.fPromoBean >0) ftSalesdPromoTpruDiscUpdated.fPromoBean = ftSalesdPromoTpruDisc.fPromoBean
            if (ftSalesdPromoTpruDisc.ftSalesdBean >0) ftSalesdPromoTpruDiscUpdated.ftSalesdBean = ftSalesdPromoTpruDisc.ftSalesdBean
            ftSalesdPromoTpruDiscJPARepository!!.save(ftSalesdPromoTpruDiscUpdated)
            return ftSalesdPromoTpruDiscUpdated
        }
        return ftSalesdPromoTpruDisc
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesdPromoTpruDisc/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesdPromoTpruDisc(@PathVariable("id") id: Long): FtSalesdPromoTpruDisc? {
        val ftSalesdPromoTpruDisc = ftSalesdPromoTpruDiscJPARepository!!.findById(id).orElse(FtSalesdPromoTpruDisc())
        if (ftSalesdPromoTpruDisc != null) {
            ftSalesdPromoTpruDiscJPARepository!!.delete(ftSalesdPromoTpruDisc)
        }
        return ftSalesdPromoTpruDisc
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtSalesdPromoTpruDiscRestController::class.java)
    }
}