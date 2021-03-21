package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtPurchasedItemsJPARepository
import com.erp.distribution.desgreenrestkt.model.FtPurchasedItems
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFtPurchasedItemsById(@PathVariable("id") id: Long): FtPurchasedItems {
        return ftPurchasedItemsJPARepository!!.findById(id).orElse(FtPurchasedItems())
    }

    @get:RequestMapping(value = ["/rest/getAllFtPurchasedItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltPurchasedItems: List<FtPurchasedItems>
        get() = ftPurchasedItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtPurchasedItemsByParent/{ftPurchasehBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPurchasedItemsByParentId(@PathVariable("ftPurchasehBean") ftPurchasehBean: Long): List<FtPurchasedItems> {
        return ftPurchasedItemsJPARepository!!.findAllByParentId(ftPurchasehBean)
    }

    @RequestMapping(value = ["/rest/getAllFtPurchasedItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPurchasedItemsByListParentId(@RequestBody listFtPurchasehBean: List<Long>): List<FtPurchasedItems> {
        return ftPurchasedItemsJPARepository!!.findAllByListParentId(listFtPurchasehBean)
    }

    @RequestMapping(value = ["/rest/createFtPurchasedItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPurchasedItems(@RequestBody ftPurchasedItemsNew: FtPurchasedItems): FtPurchasedItems {
        ftPurchasedItemsNew.id = 0 //Memastikan ID adalah Nol
        return ftPurchasedItemsJPARepository!!.save(ftPurchasedItemsNew)
    }

    @RequestMapping(value = ["/rest/updateFtPurchasedItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtPurchasedItemsInfo(@PathVariable("id") id: Long, @RequestBody ftPurchasedItemsUpdated: FtPurchasedItems?): FtPurchasedItems {
        val ftPurchasedItems = ftPurchasedItemsJPARepository!!.findById(id).orElse(FtPurchasedItems())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPurchasedItemsUpdated != null) {
            ftPurchasedItemsUpdated.id = ftPurchasedItems.id
            //            if (ftPurchasedItems.getFtPurchasehBean()==null) ftPurchasedItemsUpdated.setFtPurchasehBean(ftPurchasedItems.getFtPurchasehBean());
            if (ftPurchasedItems.fmaterialBean >0) ftPurchasedItemsUpdated.fmaterialBean = ftPurchasedItems.fmaterialBean
            ftPurchasedItemsJPARepository!!.save(ftPurchasedItemsUpdated)
            return ftPurchasedItemsUpdated
        }
        return ftPurchasedItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPurchasedItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPurchasedItems(@PathVariable("id") id: Long): FtPurchasedItems? {
        val ftPurchasedItems = ftPurchasedItemsJPARepository!!.findById(id).orElse(FtPurchasedItems())
        if (ftPurchasedItems != null) {
            ftPurchasedItemsJPARepository!!.delete(ftPurchasedItems)
        }
        return ftPurchasedItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPurchasedItemsRestController::class.java)
    }
}