package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSubAreaJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FSubAreaEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FSubAreaRestController {
    @Autowired
    var fSubAreaJPARepository: FSubAreaJPARepository? = null

    @RequestMapping(value = ["/rest/getFSubAreaById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFSubAreaById(@PathVariable("id") id: Int): FSubAreaEntity {
        return fSubAreaJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFSubArea"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allSubAreaEntity: List<FSubAreaEntity>
        get() = fSubAreaJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFSubAreaByParent/{fareaBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSubAreaByParentId(@PathVariable("fareaBean") fareaBean: Int): List<FSubAreaEntity> {
        return fSubAreaJPARepository!!.findAllByParent(fareaBean)
    }

    @RequestMapping(value = ["/rest/getAllFSubAreaByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSubAreaByListParentId(@RequestBody listFareaBean: List<Int>): List<FSubAreaEntity> {
        return fSubAreaJPARepository!!.findAllByListParent(listFareaBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFSubArea"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFSubArea(@RequestBody fSubAreaEntityNew: FSubAreaEntity): FSubAreaEntity {
        fSubAreaEntityNew.id = 0 //Memastikan ID adalah Nol
        return fSubAreaJPARepository!!.save(fSubAreaEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFSubArea/{id}"], method = [RequestMethod.PUT])
    fun updateFSubAreaInfo(@PathVariable("id") id: Int, @RequestBody fSubAreaEntityUpdated: FSubAreaEntity?): FSubAreaEntity {
        val fSubArea = fSubAreaJPARepository!!.findById(id).orElse(FSubAreaEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fSubAreaEntityUpdated != null) {
            fSubAreaEntityUpdated.id = fSubArea.id
            if (fSubArea.fareaBean >0) fSubAreaEntityUpdated.fareaBean = fSubArea.fareaBean
            fSubAreaJPARepository!!.save(fSubAreaEntityUpdated)
            return fSubAreaEntityUpdated
        }
        return fSubArea
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFSubArea/{id}"], method = [RequestMethod.DELETE])
    fun deleteFSubArea(@PathVariable("id") id: Int): FSubAreaEntity? {
        val fSubArea = fSubAreaJPARepository!!.findById(id).orElse(FSubAreaEntity())
        if (fSubArea != null) {
            fSubAreaJPARepository!!.delete(fSubArea)
        }
        return fSubArea
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FSubAreaRestController::class.java)
    }
}