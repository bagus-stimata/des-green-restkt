package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFSubAreaUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FSubAreaRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FSubAreaRestController @Autowired constructor(
    val getFSubAreaUseCase: GetFSubAreaUseCase
) {

    @RequestMapping(value = ["/rest/getFSubAreaById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFSubAreaById(@PathVariable("id") id: Int): FSubAreaRes {
        return getFSubAreaUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFSubArea"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allSubAreaEntity: List<FSubAreaRes>
        get() = getFSubAreaUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFSubAreaByParent/{fareaBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSubAreaByParentId(@PathVariable("fareaBean") fareaBean: Int): List<FSubAreaRes> {
        return getFSubAreaUseCase.findByParentRes(fareaBean).ifEmpty {
            listOf()
        }
    }

    @RequestMapping(value = ["/rest/getAllFSubAreaByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFSubAreaByListParentId(@RequestBody listFareaBean: List<Int>): List<FSubAreaRes> {
        return getFSubAreaUseCase.findByListOfParentRes(listFareaBean).ifEmpty {
            listOf()
        }
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFSubArea"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFSubArea(@RequestBody fSubAreaEntityNew: FSubAreaRes): FSubAreaRes {
        fSubAreaEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFSubAreaUseCase.save(fSubAreaEntityNew.toDomain()).toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFSubArea/{id}"], method = [RequestMethod.PUT])
    fun updateFSubAreaInfo(@PathVariable("id") id: Int, @RequestBody fSubAreaEntityUpdated: FSubAreaRes?): FSubAreaRes {
        val fSubArea = getFSubAreaUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fSubAreaEntityUpdated != null) {
            fSubAreaEntityUpdated.id = fSubArea.id
            if (fSubArea.fareaBean.id >0) fSubAreaEntityUpdated.fareaBean = fSubArea.fareaBean.id
            getFSubAreaUseCase.save(fSubAreaEntityUpdated.toDomain()).toResponse()
            return fSubAreaEntityUpdated
        }
        return fSubArea.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFSubArea/{id}"], method = [RequestMethod.DELETE])
    fun deleteFSubArea(@PathVariable("id") id: Int): FSubAreaRes? {
        val fSubArea = getFSubAreaUseCase.findById(id)
//        if (fSubArea != null) {
//            getFSubAreaUseCase.delete(fSubArea)
//        }
        fSubArea?.let {
            getFSubAreaUseCase.delete(it)
        }
        return fSubArea.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FSubAreaRestController::class.java)
    }
}