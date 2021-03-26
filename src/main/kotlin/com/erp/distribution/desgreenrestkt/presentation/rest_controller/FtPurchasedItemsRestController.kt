package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtPurchasedItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchasedItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtPurchasedItemsRestController {
    @Autowired
    var ftPurchasedItemsJPARepository: FtPurchasedItemsJPARepository? = null

    @RequestMapping(value = ["/rest/getFtPurchasedItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtPurchasedItemsById(@PathVariable("id") id: Long): FtPurchasedItemsEntity {
        return ftPurchasedItemsJPARepository!!.findById(id).orElse(FtPurchasedItemsEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtPurchasedItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltPurchasedItemEntities: List<FtPurchasedItemsEntity>
        get() = ftPurchasedItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtPurchasedItemsByParent/{ftPurchasehBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPurchasedItemsByParentId(@PathVariable("ftPurchasehBean") ftPurchasehBean: Long): List<FtPurchasedItemsEntity> {
        return ftPurchasedItemsJPARepository!!.findAllByParentId(ftPurchasehBean)
    }

    @RequestMapping(value = ["/rest/getAllFtPurchasedItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPurchasedItemsByListParentId(@RequestBody listFtPurchasehBean: List<Long>): List<FtPurchasedItemsEntity> {
        return ftPurchasedItemsJPARepository!!.findAllByListParentId(listFtPurchasehBean)
    }

    @RequestMapping(value = ["/rest/createFtPurchasedItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPurchasedItems(@RequestBody ftPurchasedItemsEntityNew: FtPurchasedItemsEntity): FtPurchasedItemsEntity {
        ftPurchasedItemsEntityNew.id = 0 //Memastikan ID adalah Nol
        return ftPurchasedItemsJPARepository!!.save(ftPurchasedItemsEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtPurchasedItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtPurchasedItemsInfo(@PathVariable("id") id: Long, @RequestBody ftPurchasedItemsEntityUpdated: FtPurchasedItemsEntity?): FtPurchasedItemsEntity {
        val ftPurchasedItems = ftPurchasedItemsJPARepository!!.findById(id).orElse(FtPurchasedItemsEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPurchasedItemsEntityUpdated != null) {
            ftPurchasedItemsEntityUpdated.id = ftPurchasedItems.id
            //            if (ftPurchasedItems.getFtPurchasehBean()==null) ftPurchasedItemsUpdated.setFtPurchasehBean(ftPurchasedItems.getFtPurchasehBean());
            if (ftPurchasedItems.fmaterialBean >0) ftPurchasedItemsEntityUpdated.fmaterialBean = ftPurchasedItems.fmaterialBean
            ftPurchasedItemsJPARepository!!.save(ftPurchasedItemsEntityUpdated)
            return ftPurchasedItemsEntityUpdated
        }
        return ftPurchasedItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPurchasedItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPurchasedItems(@PathVariable("id") id: Long): FtPurchasedItemsEntity? {
        val ftPurchasedItems = ftPurchasedItemsJPARepository!!.findById(id).orElse(FtPurchasedItemsEntity())
        if (ftPurchasedItems != null) {
            ftPurchasedItemsJPARepository!!.delete(ftPurchasedItems)
        }
        return ftPurchasedItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPurchasedItemsRestController::class.java)
    }
}