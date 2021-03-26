package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtOpnamedItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtOpnamedItemsEntity
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
    fun getFtOpnamedItemsById(@PathVariable("id") id: Long): FtOpnamedItemsEntity {
        return ftOpnamedItemsJPARepository!!.findById(id).orElse(FtOpnamedItemsEntity())
    }

    @get:RequestMapping(value = ["/rest/getAllFtOpnamedItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltOpnamedItemEntities: List<FtOpnamedItemsEntity>
        get() = ftOpnamedItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtOpnamedItemsByParent/{ftOpnamehBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtOpnamedItemsByParentId(@PathVariable("fmaterialGroup1Bean") ftOpnamehBean: Long): List<FtOpnamedItemsEntity> {
        return ftOpnamedItemsJPARepository!!.findAllByParentId(ftOpnamehBean)
    }

    @RequestMapping(value = ["/rest/getAllFtOpnamedItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtOpnamedItemsByListParentId(@RequestBody listFtOpnamehBean: List<Long>): List<FtOpnamedItemsEntity> {
        return ftOpnamedItemsJPARepository!!.findAllByListParentId(listFtOpnamehBean)
    }

    @RequestMapping(value = ["/rest/createFtOpnamedItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtOpnamedItems(@RequestBody ftOpnamedItemsEntityNew: FtOpnamedItemsEntity): FtOpnamedItemsEntity {
        ftOpnamedItemsEntityNew.id = 0 //Memastikan ID adalah Nol
        return ftOpnamedItemsJPARepository!!.save(ftOpnamedItemsEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtOpnamedItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtOpnamedItemsInfo(@PathVariable("id") id: Long, @RequestBody ftOpnamedItemsEntityUpdated: FtOpnamedItemsEntity?): FtOpnamedItemsEntity {
        val ftOpnamedItems = ftOpnamedItemsJPARepository!!.findById(id).orElse(FtOpnamedItemsEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftOpnamedItemsEntityUpdated != null) {
            ftOpnamedItemsEntityUpdated.id = ftOpnamedItems.id
            //            if (ftOpnamedItems.getFtOpnamehBean()==null) ftOpnamedItemsUpdated.setFtOpnamehBean(ftOpnamedItems.getFtOpnamehBean());
            if (ftOpnamedItems.fmaterialBean >0) ftOpnamedItemsEntityUpdated.fmaterialBean = ftOpnamedItems.fmaterialBean
            ftOpnamedItemsJPARepository!!.save(ftOpnamedItemsEntityUpdated)
            return ftOpnamedItemsEntityUpdated
        }
        return ftOpnamedItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtOpnamedItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtOpnamedItems(@PathVariable("id") id: Long): FtOpnamedItemsEntity? {
        val ftOpnamedItems = ftOpnamedItemsJPARepository!!.findById(id).orElse(FtOpnamedItemsEntity())
        if (ftOpnamedItems != null) {
            ftOpnamedItemsJPARepository!!.delete(ftOpnamedItems)
        }
        return ftOpnamedItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtOpnamedItemsRestController::class.java)
    }
}