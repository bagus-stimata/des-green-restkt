package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSaleshHistorySJJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshHistorySJ
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtSaleshHistorySJRestController {
    @Autowired
    var ftSaleshHistorySJJPARepository: FtSaleshHistorySJJPARepository? = null

    @RequestMapping(value = ["/rest/getFtSaleshHistorySJById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtSaleshHistorySJById(@PathVariable("id") id: Long): FtSaleshHistorySJ {
        return ftSaleshHistorySJJPARepository!!.findById(id).orElse(FtSaleshHistorySJ())
    }

    @get:RequestMapping(value = ["/rest/getAllFtSaleshHistorySJ"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSaleshHistorySJ: List<FtSaleshHistorySJ>
        get() = ftSaleshHistorySJJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtSaleshHistorySJByParent/{ftSaleshBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSaleshHistorySJByParentId(@PathVariable("ftSaleshBean") ftSaleshBean: Long): List<FtSaleshHistorySJ> {
        return ftSaleshHistorySJJPARepository!!.findAllByParentId(ftSaleshBean)
    }

    @RequestMapping(value = ["/rest/getAllFtSaleshHistorySJByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSaleshHistorySJByListParentId(@RequestBody listFtSaleshBean: List<Long>): List<FtSaleshHistorySJ> {
        return ftSaleshHistorySJJPARepository!!.findAllByListParentId(listFtSaleshBean)
    }

    @RequestMapping(value = ["/rest/createFtSaleshHistorySJ"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshHistorySJ(@RequestBody ftSaleshHistorySJNew: FtSaleshHistorySJ): FtSaleshHistorySJ {
        ftSaleshHistorySJNew.id = 0 //Memastikan ID adalah Nol
        return ftSaleshHistorySJJPARepository!!.save(ftSaleshHistorySJNew)
    }

    @RequestMapping(value = ["/rest/updateFtSaleshHistorySJ/{id}"], method = [RequestMethod.PUT])
    fun updateFtSaleshHistorySJInfo(@PathVariable("id") id: Long, @RequestBody ftSaleshHistorySJUpdated: FtSaleshHistorySJ?): FtSaleshHistorySJ {
        val ftSaleshHistorySJ = ftSaleshHistorySJJPARepository!!.findById(id).orElse(FtSaleshHistorySJ())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSaleshHistorySJUpdated != null) {
            ftSaleshHistorySJUpdated.id = ftSaleshHistorySJ.id
            if (ftSaleshHistorySJ.ftSaleshBean == 0L) ftSaleshHistorySJUpdated.ftSaleshBean = ftSaleshHistorySJ.ftSaleshBean
            ftSaleshHistorySJJPARepository!!.save(ftSaleshHistorySJUpdated)
            return ftSaleshHistorySJUpdated
        }
        return ftSaleshHistorySJ
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSaleshHistorySJ/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSaleshHistorySJ(@PathVariable("id") id: Long): FtSaleshHistorySJ? {
        val ftSaleshHistorySJ = ftSaleshHistorySJJPARepository!!.findById(id).orElse(FtSaleshHistorySJ())
        if (ftSaleshHistorySJ != null) {
            ftSaleshHistorySJJPARepository!!.delete(ftSaleshHistorySJ)
        }
        return ftSaleshHistorySJ
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtSaleshHistorySJRestController::class.java)
    }
}