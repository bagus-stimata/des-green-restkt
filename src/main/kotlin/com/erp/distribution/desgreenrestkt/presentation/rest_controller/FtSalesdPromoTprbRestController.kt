package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSalesdPromoTprbJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdPromoTprbEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
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
    fun getFtSalesdPromoTprbById(@PathVariable("id") id: Long): FtSalesdPromoTprbEntity {
        return ftSalesdPromoTprbJPARepository!!.findById(id).orElse(FtSalesdPromoTprbEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesdPromoTprb"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSalesdPromoTprbEntity: List<FtSalesdPromoTprbEntity>
        get() = ftSalesdPromoTprbJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/createFtSalesdPromoTprb"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesdPromoTprb(@RequestBody ftSalesdPromoTprbEntityNew: FtSalesdPromoTprbEntity): FtSalesdPromoTprbEntity {
        ftSalesdPromoTprbEntityNew.id = 0 //Memastikan ID adalah Nol
        return ftSalesdPromoTprbJPARepository!!.save(ftSalesdPromoTprbEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtSalesdPromoTprb/{id}"], method = [RequestMethod.PUT])
    fun updateFtSalesdPromoTprbInfo(@PathVariable("id") id: Long, @RequestBody ftSalesdPromoTprbEntityUpdated: FtSalesdPromoTprbEntity?): FtSalesdPromoTprbEntity {
        val ftSalesdPromoTprb = ftSalesdPromoTprbJPARepository!!.findById(id).orElse(FtSalesdPromoTprbEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSalesdPromoTprbEntityUpdated != null) {
            ftSalesdPromoTprbEntityUpdated.id = ftSalesdPromoTprb.id
            if (ftSalesdPromoTprb.fpromoBean >0) ftSalesdPromoTprbEntityUpdated.fpromoBean = ftSalesdPromoTprb.fpromoBean
            if (ftSalesdPromoTprb.fmaterialBean >0) ftSalesdPromoTprbEntityUpdated.fmaterialBean = ftSalesdPromoTprb.fmaterialBean
            if (ftSalesdPromoTprb.ftSalesdFreegood == 0L) ftSalesdPromoTprbEntityUpdated.ftSalesdFreegood = ftSalesdPromoTprb.ftSalesdFreegood
            ftSalesdPromoTprbJPARepository!!.save(ftSalesdPromoTprbEntityUpdated)
            return ftSalesdPromoTprbEntityUpdated
        }
        return ftSalesdPromoTprb
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesdPromoTprb/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesdPromoTprb(@PathVariable("id") id: Long): FtSalesdPromoTprbEntity? {
        val ftSalesdPromoTprb = ftSalesdPromoTprbJPARepository!!.findById(id).orElse(FtSalesdPromoTprbEntity())
        if (ftSalesdPromoTprb != null) {
            ftSalesdPromoTprbJPARepository!!.delete(ftSalesdPromoTprb)
        }
        return ftSalesdPromoTprb
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtSalesdPromoTprbRestController::class.java)
    }
}