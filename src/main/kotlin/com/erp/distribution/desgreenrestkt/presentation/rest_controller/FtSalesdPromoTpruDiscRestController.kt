package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSalesdPromoTpruDiscJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdPromoTpruDiscEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
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
    fun getFtSalesdPromoTpruDiscById(@PathVariable("id") id: Long): FtSalesdPromoTpruDiscEntity {
        return ftSalesdPromoTpruDiscJPARepository!!.findById(id).orElse(FtSalesdPromoTpruDiscEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesdPromoTpruDisc"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSalesdPromoTpruDiscEntity: List<FtSalesdPromoTpruDiscEntity>
        get() = ftSalesdPromoTpruDiscJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/createFtSalesdPromoTpruDisc"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesdPromoTpruDisc(@RequestBody ftSalesdPromoTpruDiscEntityNew: FtSalesdPromoTpruDiscEntity): FtSalesdPromoTpruDiscEntity {
        ftSalesdPromoTpruDiscEntityNew.id = 0 //Memastikan ID adalah Nol
        return ftSalesdPromoTpruDiscJPARepository!!.save(ftSalesdPromoTpruDiscEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtSalesdPromoTpruDisc/{id}"], method = [RequestMethod.PUT])
    fun updateFtSalesdPromoTpruDiscInfo(@PathVariable("id") id: Long, @RequestBody ftSalesdPromoTpruDiscEntityUpdated: FtSalesdPromoTpruDiscEntity?): FtSalesdPromoTpruDiscEntity {
        val ftSalesdPromoTpruDisc = ftSalesdPromoTpruDiscJPARepository!!.findById(id).orElse(FtSalesdPromoTpruDiscEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSalesdPromoTpruDiscEntityUpdated != null) {
            ftSalesdPromoTpruDiscEntityUpdated.id = ftSalesdPromoTpruDisc.id
            if (ftSalesdPromoTpruDisc.fPromoBean >0) ftSalesdPromoTpruDiscEntityUpdated.fPromoBean = ftSalesdPromoTpruDisc.fPromoBean
            if (ftSalesdPromoTpruDisc.ftSalesdBean >0) ftSalesdPromoTpruDiscEntityUpdated.ftSalesdBean = ftSalesdPromoTpruDisc.ftSalesdBean
            ftSalesdPromoTpruDiscJPARepository!!.save(ftSalesdPromoTpruDiscEntityUpdated)
            return ftSalesdPromoTpruDiscEntityUpdated
        }
        return ftSalesdPromoTpruDisc
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesdPromoTpruDisc/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesdPromoTpruDisc(@PathVariable("id") id: Long): FtSalesdPromoTpruDiscEntity? {
        val ftSalesdPromoTpruDisc = ftSalesdPromoTpruDiscJPARepository!!.findById(id).orElse(FtSalesdPromoTpruDiscEntity())
        if (ftSalesdPromoTpruDisc != null) {
            ftSalesdPromoTpruDiscJPARepository!!.delete(ftSalesdPromoTpruDisc)
        }
        return ftSalesdPromoTpruDisc
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtSalesdPromoTpruDiscRestController::class.java)
    }
}