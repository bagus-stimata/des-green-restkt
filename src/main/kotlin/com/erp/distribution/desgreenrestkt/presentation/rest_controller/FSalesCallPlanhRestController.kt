package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.domain.usecase.GetFSalesCallPlanhUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesCallPlanhRes
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class FSalesCallPlanhRestController @Autowired constructor(
    val getFSalesCallPlanhUseCase: GetFSalesCallPlanhUseCase
) {

    @RequestMapping(value = ["/rest/getFSalesCallPlanhById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable("id") id: Long): FSalesCallPlanhRes {
        return getFSalesCallPlanhUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFSalesCallPlanh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val getAllFSalesCallPlanh: List<FSalesCallPlanhRes>
        get() = getFSalesCallPlanhUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFSalesCallPlanhBySalesman/{fsalesmanBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBySalesman(@PathVariable("fsalesmanBean") fsalesmanBean: Int): List<FSalesCallPlanhRes> {
//        System.out.println((">> ${fdivisionBean} >> ${fcompanyBean}" ))
        return getFSalesCallPlanhUseCase.findBySalesmanRes(fsalesmanBean).stream().filter { x: FSalesCallPlanhRes -> x.statusActive == true }.collect(Collectors.toList())
    }
    @RequestMapping(value = ["/rest/getAllFSalesCallPlanhByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FSalesCallPlanhRes> {
//        System.out.println((">> ${fdivisionBean} >> ${fcompanyBean}" ))
        return getFSalesCallPlanhUseCase.findByDivisionRes(fdivisionBean).stream().filter { x: FSalesCallPlanhRes -> x.statusActive == true }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFsalesCallPlanhByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FSalesCallPlanhRes> {
//        System.out.println((">> ${fdivisionBean} >> ${fcompanyBean}" ))
        return getFSalesCallPlanhUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean).stream().filter { x: FSalesCallPlanhRes -> x.statusActive == true }.collect(Collectors.toList())
//        return listOf<FSalesCallPlanhRes>()
    }


    companion object {
        private val logger = LoggerFactory.getLogger(FSalesCallPlanhRestController::class.java)
    }
}