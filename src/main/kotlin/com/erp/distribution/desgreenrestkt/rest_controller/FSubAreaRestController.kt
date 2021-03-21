package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FSubAreaJPARepository
import com.erp.distribution.desgreenrestkt.model.FSubArea
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFSubAreaById(@PathVariable("id") id: Int): FSubArea {
        return fSubAreaJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFSubArea"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allSubArea: List<FSubArea>
        get() = fSubAreaJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFSubAreaByParent/{fareaBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSubAreaByParentId(@PathVariable("fareaBean") fareaBean: Int): List<FSubArea> {
        return fSubAreaJPARepository!!.findAllByParent(fareaBean)
    }

    @RequestMapping(value = ["/rest/getAllFSubAreaByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSubAreaByListParentId(@RequestBody listFareaBean: List<Int>): List<FSubArea> {
        return fSubAreaJPARepository!!.findAllByListParent(listFareaBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFSubArea"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFSubArea(@RequestBody fSubAreaNew: FSubArea): FSubArea {
        fSubAreaNew.id = 0 //Memastikan ID adalah Nol
        return fSubAreaJPARepository!!.save(fSubAreaNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFSubArea/{id}"], method = [RequestMethod.PUT])
    fun updateFSubAreaInfo(@PathVariable("id") id: Int, @RequestBody fSubAreaUpdated: FSubArea?): FSubArea {
        val fSubArea = fSubAreaJPARepository!!.findById(id).orElse(FSubArea())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fSubAreaUpdated != null) {
            fSubAreaUpdated.id = fSubArea.id
            if (fSubArea.fareaBean >0) fSubAreaUpdated.fareaBean = fSubArea.fareaBean
            fSubAreaJPARepository!!.save(fSubAreaUpdated)
            return fSubAreaUpdated
        }
        return fSubArea
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFSubArea/{id}"], method = [RequestMethod.DELETE])
    fun deleteFSubArea(@PathVariable("id") id: Int): FSubArea? {
        val fSubArea = fSubAreaJPARepository!!.findById(id).orElse(FSubArea())
        if (fSubArea != null) {
            fSubAreaJPARepository!!.delete(fSubArea)
        }
        return fSubArea
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FSubAreaRestController::class.java)
    }
}