package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FMaterialPicJPARepository
import com.erp.distribution.desgreenrestkt.model.FMaterialPic
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFMaterialPicById(@PathVariable("id") id: Int): FMaterialPic {
        return fMaterialPicJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterialPic"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialPic: List<FMaterialPic>
        get() = fMaterialPicJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialPicByParent/{fmaterialBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialPicByParentId(@PathVariable("fmaterialBean") fmaterialBean: Int): List<FMaterialPic> {
        return fMaterialPicJPARepository!!.findAllByParentId(fmaterialBean)
    }

    @RequestMapping(value = ["/rest/getAllFMaterialPicByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialPicByListParentId(@RequestBody listFMaterialBean: List<Int>): List<FMaterialPic> {
        return fMaterialPicJPARepository!!.findAllByListParentId(listFMaterialBean)
    }

    @RequestMapping(value = ["/rest/createFMaterialPic"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterialPic(@RequestBody fMaterialPicNew: FMaterialPic): FMaterialPic {
        fMaterialPicNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialPicJPARepository!!.save(fMaterialPicNew)
    }

    @RequestMapping(value = ["/rest/updateFMaterialPic/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialPicInfo(@PathVariable("id") id: Int, @RequestBody fMaterialPicUpdated: FMaterialPic?): FMaterialPic {
        val fMaterialPic = fMaterialPicJPARepository!!.findById(id).orElse(FMaterialPic())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialPicUpdated != null) {
            fMaterialPicUpdated.id = fMaterialPic.id
            if (fMaterialPic.fmaterialBean >0) fMaterialPicUpdated.fmaterialBean = fMaterialPic.fmaterialBean
            fMaterialPicJPARepository!!.save(fMaterialPicUpdated)
            return fMaterialPicUpdated
        }
        return fMaterialPic
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterialPic/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterialPic(@PathVariable("id") id: Int): FMaterialPic? {
        val fMaterialPic = fMaterialPicJPARepository!!.findById(id).orElse(FMaterialPic())
        if (fMaterialPic != null) {
            fMaterialPicJPARepository!!.delete(fMaterialPic)
        }
        return fMaterialPic
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialPicRestController::class.java)
    }
}