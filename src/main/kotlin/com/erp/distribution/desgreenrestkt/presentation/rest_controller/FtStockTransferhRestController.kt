package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtStockTransferhJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtStockTransferhEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtStockTransferhRestController {
    @Autowired
    var ftStockTransferhJPARepository: FtStockTransferhJPARepository? = null

    @RequestMapping(value = ["/rest/getFtStockTransferhById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtStockTransferhById(@PathVariable("id") id: Long?): FtStockTransferhEntity {
        return ftStockTransferhJPARepository!!.findByRefno(id!!)
    }

    @get:RequestMapping(value = ["/rest/getAllFtStockTransferh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltStockTransferhEntity: List<FtStockTransferhEntity>
        get() = ftStockTransferhJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtStockTransferhByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtStockTransferhByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtStockTransferhEntity> {
        return ftStockTransferhJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtStockTransferh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtStockTransferh(@RequestBody ftStockTransferhEntityNew: FtStockTransferhEntity): FtStockTransferhEntity {
        ftStockTransferhEntityNew.refno = 0 //Memastikan ID adalah Nol
        return ftStockTransferhJPARepository!!.save(ftStockTransferhEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtStockTransferh/{id}"], method = [RequestMethod.PUT])
    fun updateFtStockTransferhInfo(@PathVariable("id") id: Long?, @RequestBody ftStockTransferhEntityUpdated: FtStockTransferhEntity?): FtStockTransferhEntity {
        val ftStockTransferh = ftStockTransferhJPARepository!!.findByRefno(id!!)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftStockTransferhEntityUpdated != null) {
            ftStockTransferhEntityUpdated.refno = ftStockTransferh.refno
            if (ftStockTransferh.fdivisionBean >0) ftStockTransferhEntityUpdated.fdivisionBean = ftStockTransferh.fdivisionBean
            ftStockTransferhJPARepository!!.save(ftStockTransferhEntityUpdated)
            return ftStockTransferhEntityUpdated
        }
        return ftStockTransferh
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtStockTransferh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtStockTransferh(@PathVariable("id") id: Long?): FtStockTransferhEntity? {
        val ftStockTransferh = ftStockTransferhJPARepository!!.findByRefno(id!!)
        if (ftStockTransferh.refno >0) {
            ftStockTransferhJPARepository!!.delete(ftStockTransferh)
        }
        return ftStockTransferh
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtStockTransferhRestController::class.java)
    }
}