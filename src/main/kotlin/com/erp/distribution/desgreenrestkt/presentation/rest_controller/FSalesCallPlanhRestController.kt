package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFSalesCallPlanhUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesCallPlanhRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class FSalesCallPlanhRestController @Autowired constructor(
    val getFSalesCallPlanhUseCase: GetFSalesCallPlanhUseCase
) {

    @RequestMapping(value = ["/rest/findById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable("id") id: Long): FSalesCallPlanhRes {
        return getFSalesCallPlanhUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFSalesCallPlanh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val getAllFSalesCallPlanh: List<FSalesCallPlanhRes>
        get() = getFSalesCallPlanhUseCase.findAllRes()

    @RequestMapping(value = ["/rest/findBySalesman/{fsalesmanBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBySalesman(@PathVariable("fsalesmanBean") fsalesmanBean: Int): List<FSalesCallPlanhRes> {
//        System.out.println((">> ${fdivisionBean} >> ${fcompanyBean}" ))
        return getFSalesCallPlanhUseCase.findBySalesmanRes(fsalesmanBean).stream().filter { x: FSalesCallPlanhRes -> x.statusActive == true }.collect(Collectors.toList())
    }
    @RequestMapping(value = ["/rest/findByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FSalesCallPlanhRes> {
//        System.out.println((">> ${fdivisionBean} >> ${fcompanyBean}" ))
        return getFSalesCallPlanhUseCase.findByDivisionRes(fdivisionBean).stream().filter { x: FSalesCallPlanhRes -> x.statusActive == true }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/findByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FSalesCallPlanhRes> {
//        System.out.println((">> ${fdivisionBean} >> ${fcompanyBean}" ))
        return getFSalesCallPlanhUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean).stream().filter { x: FSalesCallPlanhRes -> x.statusActive == true }.collect(Collectors.toList())
//        return listOf<FSalesCallPlanhRes>()
    }


    companion object {
        private val logger = LoggerFactory.getLogger(FSalesCallPlanhRestController::class.java)
    }
}