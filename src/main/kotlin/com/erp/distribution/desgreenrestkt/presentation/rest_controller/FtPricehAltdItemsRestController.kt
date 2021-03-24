package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtPriceAltdItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAltdItems
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtPricehAltdItemsRestController {
    @Autowired
    var ftPriceAltdItemsJPARepository: FtPriceAltdItemsJPARepository? = null

    @RequestMapping(value = ["/rest/getFtPriceAltdItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtPriceAltdItemsById(@PathVariable("id") id: Int): FtPriceAltdItems {
        return ftPriceAltdItemsJPARepository!!.findById(id).orElse(FtPriceAltdItems())
    }

    @get:RequestMapping(value = ["/rest/getAllFtPriceAltdItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltPriceAltdItems: List<FtPriceAltdItems>
        get() = ftPriceAltdItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtPriceAltdItemsByParent/{ftPricehAlthBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPriceAltdItemsByParent(@PathVariable("ftPricehAlthBean") ftPricehAlthBean: Int?): List<FtPriceAltdItems> {
        return ftPriceAltdItemsJPARepository!!.findAllByParent(ftPricehAlthBean!!)
    }

    @RequestMapping(value = ["/rest/getAllFtPriceAltdItemsByListParent"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPriceAltdItemsByListParent(@RequestBody listFtPricehAlthBean: List<Int>): List<FtPriceAltdItems> {
        return ftPriceAltdItemsJPARepository!!.findAllByListParent(listFtPricehAlthBean)
    }

    @RequestMapping(value = ["/rest/createFtPriceAltdItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPriceAltdItems(@RequestBody ftPriceAltdItemsNew: FtPriceAltdItems): FtPriceAltdItems {
        ftPriceAltdItemsNew.id = 0 //Memastikan ID adalah Nol
        return ftPriceAltdItemsJPARepository!!.save(ftPriceAltdItemsNew)
    }

    @RequestMapping(value = ["/rest/updateFtPriceAltdItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtPriceAltdItemsInfo(@PathVariable("id") id: Int, @RequestBody ftPriceAltdItemsUpdated: FtPriceAltdItems?): FtPriceAltdItems {
        val ftPriceAltdItems = ftPriceAltdItemsJPARepository!!.findById(id).orElse(FtPriceAltdItems())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPriceAltdItemsUpdated != null) {
            ftPriceAltdItemsUpdated.id = ftPriceAltdItems.id
            if (ftPriceAltdItems.ftPriceAlthBean >0) ftPriceAltdItemsUpdated.ftPriceAlthBean = ftPriceAltdItems.ftPriceAlthBean
            if (ftPriceAltdItems.fmaterialBean >0) ftPriceAltdItemsUpdated.fmaterialBean = ftPriceAltdItems.fmaterialBean
            ftPriceAltdItemsJPARepository!!.save(ftPriceAltdItemsUpdated)
            return ftPriceAltdItemsUpdated
        }
        return ftPriceAltdItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPriceAltdItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPriceAltdItems(@PathVariable("id") id: Int): FtPriceAltdItems? {
        val ftPriceAltdItems = ftPriceAltdItemsJPARepository!!.findById(id).orElse(FtPriceAltdItems())
        if (ftPriceAltdItems != null) {
            ftPriceAltdItemsJPARepository!!.delete(ftPriceAltdItems)
        }
        return ftPriceAltdItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPricehAltdItemsRestController::class.java)
    }
}