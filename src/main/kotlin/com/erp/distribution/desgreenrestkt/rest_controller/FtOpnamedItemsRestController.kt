package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtOpnamedItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtOpnamedItems
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtOpnamedItemsRestController {
    @Autowired
    var ftOpnamedItemsJPARepository: FtOpnamedItemsJPARepository? = null

    @RequestMapping(value = ["/rest/getFtOpnamedItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtOpnamedItemsById(@PathVariable("id") id: Long): FtOpnamedItems {
        return ftOpnamedItemsJPARepository!!.findById(id).orElse(FtOpnamedItems())
    }

    @get:RequestMapping(value = ["/rest/getAllFtOpnamedItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltOpnamedItems: List<FtOpnamedItems>
        get() = ftOpnamedItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtOpnamedItemsByParent/{ftOpnamehBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtOpnamedItemsByParentId(@PathVariable("fmaterialGroup1Bean") ftOpnamehBean: Long): List<FtOpnamedItems> {
        return ftOpnamedItemsJPARepository!!.findAllByParentId(ftOpnamehBean)
    }

    @RequestMapping(value = ["/rest/getAllFtOpnamedItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtOpnamedItemsByListParentId(@RequestBody listFtOpnamehBean: List<Long>): List<FtOpnamedItems> {
        return ftOpnamedItemsJPARepository!!.findAllByListParentId(listFtOpnamehBean)
    }

    @RequestMapping(value = ["/rest/createFtOpnamedItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtOpnamedItems(@RequestBody ftOpnamedItemsNew: FtOpnamedItems): FtOpnamedItems {
        ftOpnamedItemsNew.id = 0 //Memastikan ID adalah Nol
        return ftOpnamedItemsJPARepository!!.save(ftOpnamedItemsNew)
    }

    @RequestMapping(value = ["/rest/updateFtOpnamedItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtOpnamedItemsInfo(@PathVariable("id") id: Long, @RequestBody ftOpnamedItemsUpdated: FtOpnamedItems?): FtOpnamedItems {
        val ftOpnamedItems = ftOpnamedItemsJPARepository!!.findById(id).orElse(FtOpnamedItems())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftOpnamedItemsUpdated != null) {
            ftOpnamedItemsUpdated.id = ftOpnamedItems.id
            //            if (ftOpnamedItems.getFtOpnamehBean()==null) ftOpnamedItemsUpdated.setFtOpnamehBean(ftOpnamedItems.getFtOpnamehBean());
            if (ftOpnamedItems.fmaterialBean >0) ftOpnamedItemsUpdated.fmaterialBean = ftOpnamedItems.fmaterialBean
            ftOpnamedItemsJPARepository!!.save(ftOpnamedItemsUpdated)
            return ftOpnamedItemsUpdated
        }
        return ftOpnamedItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtOpnamedItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtOpnamedItems(@PathVariable("id") id: Long): FtOpnamedItems? {
        val ftOpnamedItems = ftOpnamedItemsJPARepository!!.findById(id).orElse(FtOpnamedItems())
        if (ftOpnamedItems != null) {
            ftOpnamedItemsJPARepository!!.delete(ftOpnamedItems)
        }
        return ftOpnamedItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtOpnamedItemsRestController::class.java)
    }
}