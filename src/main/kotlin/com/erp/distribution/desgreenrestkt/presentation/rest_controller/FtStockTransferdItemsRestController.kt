package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtStockTransferdItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtStockTransferdItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtStockTransferdItemsRestController {
    @Autowired
    var ftStockTransferdItemsJPARepository: FtStockTransferdItemsJPARepository? = null

    @RequestMapping(value = ["/rest/getFtStockTransferdItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtStockTransferdItemsById(@PathVariable("id") id: Long): FtStockTransferdItemsEntity {
        return ftStockTransferdItemsJPARepository!!.findById(id).orElse(FtStockTransferdItemsEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtStockTransferdItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltStockTransferdItemEntities: List<FtStockTransferdItemsEntity>
        get() = ftStockTransferdItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtStockTransferdItemsByParent/{ftStockTransferhBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtStockTransferdItemsByParentId(@PathVariable("ftStockTransferhBean") ftStockTransferhBean: Long): List<FtStockTransferdItemsEntity> {
        return ftStockTransferdItemsJPARepository!!.findAllByParentId(ftStockTransferhBean)
    }

    @RequestMapping(value = ["/rest/getAllFtStockTransferdItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtStockTransferdItemsByListParentId(@RequestBody listFtStockTransferhBean: List<Long>): List<FtStockTransferdItemsEntity> {
        return ftStockTransferdItemsJPARepository!!.findAllByListParentId(listFtStockTransferhBean)
    }

    @RequestMapping(value = ["/rest/createFtStockTransferdItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtStockTransferdItems(@RequestBody ftStockTransferdItemsEntityNew: FtStockTransferdItemsEntity): FtStockTransferdItemsEntity {
        ftStockTransferdItemsEntityNew.id = 0 //Memastikan ID adalah Nol
        return ftStockTransferdItemsJPARepository!!.save(ftStockTransferdItemsEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtStockTransferdItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtStockTransferdItemsInfo(@PathVariable("id") id: Long, @RequestBody ftStockTransferdItemsEntityUpdated: FtStockTransferdItemsEntity?): FtStockTransferdItemsEntity {
        val ftStockTransferdItems = ftStockTransferdItemsJPARepository!!.findById(id).orElse(FtStockTransferdItemsEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftStockTransferdItemsEntityUpdated != null) {
            ftStockTransferdItemsEntityUpdated.id = ftStockTransferdItems.id
            if (ftStockTransferdItems.fmaterialBean >0) ftStockTransferdItemsEntityUpdated.fmaterialBean = ftStockTransferdItems.fmaterialBean
            //            if (ftStockTransferdItems.getFtStockTransferhBean()==null) ftStockTransferdItemsUpdated.setFtStockTransferhBean(ftStockTransferdItems.getFtStockTransferhBean());
            ftStockTransferdItemsJPARepository!!.save(ftStockTransferdItemsEntityUpdated)
            return ftStockTransferdItemsEntityUpdated
        }
        return ftStockTransferdItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtStockTransferdItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtStockTransferdItems(@PathVariable("id") id: Long): FtStockTransferdItemsEntity? {
        val ftStockTransferdItems = ftStockTransferdItemsJPARepository!!.findById(id).orElse(FtStockTransferdItemsEntity())
        if (ftStockTransferdItems != null) {
            ftStockTransferdItemsJPARepository!!.delete(ftStockTransferdItems)
        }
        return ftStockTransferdItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtStockTransferdItemsRestController::class.java)
    }
}