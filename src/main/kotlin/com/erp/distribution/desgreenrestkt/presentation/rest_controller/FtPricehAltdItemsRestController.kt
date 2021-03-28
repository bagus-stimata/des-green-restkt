package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity.toResponse
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFtPriceAltdItemsUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FtPriceAltdItemsRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtPricehAltdItemsRestController @Autowired constructor(
    val getFtPriceAltdItemsUseCase: GetFtPriceAltdItemsUseCase
){

    @RequestMapping(value = ["/rest/getFtPriceAltdItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtPriceAltdItemsById(@PathVariable("id") id: Int): FtPriceAltdItemsRes {
        return getFtPriceAltdItemsUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFtPriceAltdItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltPriceAltdItemEntities: List<FtPriceAltdItemsRes>
        get() = getFtPriceAltdItemsUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFtPriceAltdItemsByParent/{ftPricehAlthBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPriceAltdItemsByParent(@PathVariable("ftPricehAlthBean") ftPricehAlthBean: Int): List<FtPriceAltdItemsRes> {
        return getFtPriceAltdItemsUseCase.findByParentRes(ftPricehAlthBean)
    }

    @RequestMapping(value = ["/rest/getAllFtPriceAltdItemsByListParent"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtPriceAltdItemsByListParent(@RequestBody listFtPricehAlthBean: List<Int>): List<FtPriceAltdItemsRes> {
        return getFtPriceAltdItemsUseCase.findByListOfParentRes(listFtPricehAlthBean)
    }

    @RequestMapping(value = ["/rest/createFtPriceAltdItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtPriceAltdItems(@RequestBody ftPriceAltdItemsEntityNew: FtPriceAltdItemsRes): FtPriceAltdItemsRes {
        ftPriceAltdItemsEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFtPriceAltdItemsUseCase.save(ftPriceAltdItemsEntityNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/updateFtPriceAltdItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtPriceAltdItemsInfo(@PathVariable("id") id: Int, @RequestBody ftPriceAltdItemsEntityUpdated: FtPriceAltdItemsRes?): FtPriceAltdItemsRes {
        val ftPriceAltdItems = getFtPriceAltdItemsUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftPriceAltdItemsEntityUpdated != null) {
            ftPriceAltdItemsEntityUpdated.id = ftPriceAltdItems.id
            if (ftPriceAltdItems.ftPriceAlthBean.id >0) ftPriceAltdItemsEntityUpdated.ftPriceAlthBean = ftPriceAltdItems.ftPriceAlthBean.id
            if (ftPriceAltdItems.fmaterialBean >0) ftPriceAltdItemsEntityUpdated.fmaterialBean = ftPriceAltdItems.fmaterialBean
            getFtPriceAltdItemsUseCase.save(ftPriceAltdItemsEntityUpdated.toDomain())
            return ftPriceAltdItemsEntityUpdated
        }
        return ftPriceAltdItems.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtPriceAltdItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtPriceAltdItems(@PathVariable("id") id: Int): FtPriceAltdItemsRes? {
        val ftPriceAltdItems = getFtPriceAltdItemsUseCase.findById(id)
        if (ftPriceAltdItems != null) {
            getFtPriceAltdItemsUseCase.delete(ftPriceAltdItems)
        }
        return ftPriceAltdItems.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtPricehAltdItemsRestController::class.java)
    }
}