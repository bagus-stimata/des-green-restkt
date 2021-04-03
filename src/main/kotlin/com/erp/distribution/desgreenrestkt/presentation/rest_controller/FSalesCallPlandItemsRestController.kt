package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.domain.usecase.GetFSalesCallPlandItemsUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesCallPlandItemsRes
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class FSalesCallPlandItemsRestController @Autowired constructor(
    val getFSalesCallPlandItemsUseCase: GetFSalesCallPlandItemsUseCase
){

    @RequestMapping(value = ["/rest/getFSalesCallPlandItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable("id") id: Long): FSalesCallPlandItemsRes {
        return getFSalesCallPlandItemsUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFSalesCallPlandItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val findAll: List<FSalesCallPlandItemsRes>
        get() = getFSalesCallPlandItemsUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFSalesCallPlandItemsByParent/{fparentBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByParent(@PathVariable("fparentBean") fparentBean: Long): List<FSalesCallPlandItemsRes> {
        return getFSalesCallPlandItemsUseCase.findByParentRes(fparentBean)
    }

    @RequestMapping(value = ["/rest/getAllFSalesCallPlandItemsByListOfParent"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun findByListOfParent(@RequestBody listFtPricehAlthBean: List<Long>): List<FSalesCallPlandItemsRes> {
        return getFSalesCallPlandItemsUseCase.findByListOfParentRes(listFtPricehAlthBean)
    }



    companion object {
        private val logger = LoggerFactory.getLogger(FSalesCallPlandItemsRestController::class.java)
    }
}