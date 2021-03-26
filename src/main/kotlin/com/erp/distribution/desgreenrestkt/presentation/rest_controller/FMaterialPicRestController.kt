package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialPicJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialPicEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FMaterialPicRestController {
    @Autowired
    var fMaterialPicJPARepository: FMaterialPicJPARepository? = null

    @RequestMapping(value = ["/rest/getFMaterialPicById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialPicById(@PathVariable("id") id: Int): FMaterialPicEntity {
        return fMaterialPicJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialPic"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialPicEntity: List<FMaterialPicEntity>
        get() = fMaterialPicJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialPicByParent/{fmaterialBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialPicByParentId(@PathVariable("fmaterialBean") fmaterialBean: Int): List<FMaterialPicEntity> {
        return fMaterialPicJPARepository!!.findAllByParentId(fmaterialBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialPicByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialPicByListParentId(@RequestBody listFMaterialBean: List<Int>): List<FMaterialPicEntity> {
        return fMaterialPicJPARepository!!.findAllByListParentId(listFMaterialBean)
    }

    @RequestMapping(value = ["/rest/createFMaterialPic"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialPic(@RequestBody fMaterialPicEntityNew: FMaterialPicEntity): FMaterialPicEntity {
        fMaterialPicEntityNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialPicJPARepository!!.save(fMaterialPicEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFMaterialPic/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialPicInfo(@PathVariable("id") id: Int, @RequestBody fMaterialPicEntityUpdated: FMaterialPicEntity?): FMaterialPicEntity {
        val fMaterialPic = fMaterialPicJPARepository!!.findById(id).orElse(FMaterialPicEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialPicEntityUpdated != null) {
            fMaterialPicEntityUpdated.id = fMaterialPic.id
            if (fMaterialPic.fmaterialBean >0) fMaterialPicEntityUpdated.fmaterialBean = fMaterialPic.fmaterialBean
            fMaterialPicJPARepository!!.save(fMaterialPicEntityUpdated)
            return fMaterialPicEntityUpdated
        }
        return fMaterialPic
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialPic/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialPic(@PathVariable("id") id: Int): FMaterialPicEntity? {
        val fMaterialPic = fMaterialPicJPARepository!!.findById(id).orElse(FMaterialPicEntity())
        if (fMaterialPic != null) {
            fMaterialPicJPARepository!!.delete(fMaterialPic)
        }
        return fMaterialPic
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialPicRestController::class.java)
    }
}