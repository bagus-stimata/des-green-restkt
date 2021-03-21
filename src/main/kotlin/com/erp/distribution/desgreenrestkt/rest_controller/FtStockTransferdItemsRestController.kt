package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FtStockTransferdItemsJPARepository
import com.erp.distribution.desgreenrestkt.model.FtStockTransferdItems
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFtStockTransferdItemsById(@PathVariable("id") id: Long): FtStockTransferdItems {
        return ftStockTransferdItemsJPARepository!!.findById(id).orElse(FtStockTransferdItems())
    }

    @get:RequestMapping(value = ["/rest/getAllFtStockTransferdItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltStockTransferdItems: List<FtStockTransferdItems>
        get() = ftStockTransferdItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtStockTransferdItemsByParent/{ftStockTransferhBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtStockTransferdItemsByParentId(@PathVariable("ftStockTransferhBean") ftStockTransferhBean: Long): List<FtStockTransferdItems> {
        return ftStockTransferdItemsJPARepository!!.findAllByParentId(ftStockTransferhBean)
    }

    @RequestMapping(value = ["/rest/getAllFtStockTransferdItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtStockTransferdItemsByListParentId(@RequestBody listFtStockTransferhBean: List<Long>): List<FtStockTransferdItems> {
        return ftStockTransferdItemsJPARepository!!.findAllByListParentId(listFtStockTransferhBean)
    }

    @RequestMapping(value = ["/rest/createFtStockTransferdItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtStockTransferdItems(@RequestBody ftStockTransferdItemsNew: FtStockTransferdItems): FtStockTransferdItems {
        ftStockTransferdItemsNew.id = 0 //Memastikan ID adalah Nol
        return ftStockTransferdItemsJPARepository!!.save(ftStockTransferdItemsNew)
    }

    @RequestMapping(value = ["/rest/updateFtStockTransferdItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtStockTransferdItemsInfo(@PathVariable("id") id: Long, @RequestBody ftStockTransferdItemsUpdated: FtStockTransferdItems?): FtStockTransferdItems {
        val ftStockTransferdItems = ftStockTransferdItemsJPARepository!!.findById(id).orElse(FtStockTransferdItems())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftStockTransferdItemsUpdated != null) {
            ftStockTransferdItemsUpdated.id = ftStockTransferdItems.id
            if (ftStockTransferdItems.fmaterialBean >0) ftStockTransferdItemsUpdated.fmaterialBean = ftStockTransferdItems.fmaterialBean
            //            if (ftStockTransferdItems.getFtStockTransferhBean()==null) ftStockTransferdItemsUpdated.setFtStockTransferhBean(ftStockTransferdItems.getFtStockTransferhBean());
            ftStockTransferdItemsJPARepository!!.save(ftStockTransferdItemsUpdated)
            return ftStockTransferdItemsUpdated
        }
        return ftStockTransferdItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtStockTransferdItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtStockTransferdItems(@PathVariable("id") id: Long): FtStockTransferdItems? {
        val ftStockTransferdItems = ftStockTransferdItemsJPARepository!!.findById(id).orElse(FtStockTransferdItems())
        if (ftStockTransferdItems != null) {
            ftStockTransferdItemsJPARepository!!.delete(ftStockTransferdItems)
        }
        return ftStockTransferdItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtStockTransferdItemsRestController::class.java)
    }
}