package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFCustomerUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FCustomerRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.function.Predicate
import java.util.stream.Collectors

@RestController
class FCustomerRestController @Autowired constructor(
    val fCustomerUseCase: GetFCustomerUseCase
) {
//    @Autowired
//    var fCustomerJPARepository: FCustomerJPARepository? = null

    @RequestMapping(value = ["/rest/getFCustomerById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCustomerById(@PathVariable("id") id: Int): FCustomerRes {
        return fCustomerUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomer"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomerEntity: List<FCustomerRes?>
        get() = fCustomerUseCase.findAllRes()

//    @RequestMapping(value = ["/rest/getAllFCustomerPage"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun getAllCustomer(@RequestParam("page") page: Int, @RequestParam("size") size: Int): MutableList<FCustomerRes> {
//        val pageable: Pageable = PageRequest.of(page, size)
//        return fCustomerUseCase.findAllRes(pageable).content.stream().filter(Predicate<FCustomerRes> { x: FCustomerRes -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
//    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FCustomerRes> {
        return fCustomerUseCase.findAllByDivisionRes(fdivisionBean).stream().filter(Predicate<FCustomerRes> { x: FCustomerRes -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList()).toList()
    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): MutableList<FCustomerRes> {
        return fCustomerUseCase.findAllByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean).stream().filter(Predicate<FCustomerRes> { x: FCustomerRes? -> x!!.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
    }

//    @RequestMapping(value = ["/rest/getAllFCustomerByDivisionPage/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun getAllFAreaByDivisionPage(@PathVariable("fdivisionBean") fdivisionBean: Int, @RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FCustomerRes> {
//        val pageable: Pageable = PageRequest.of(page, size)
//        return fCustomerUseCase.findAllByDivision(fdivisionBean, pageable).content.stream().filter(Predicate<FCustomerRes> { x: FCustomerRes -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
//    }

    @RequestMapping(value = ["/rest/createFCustomer"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomer(@RequestBody fCustomerResNew: FCustomerRes): FCustomerRes {
        fCustomerResNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerUseCase.save(fCustomerResNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/updateFCustomer/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerInfo(@PathVariable("id") id: Int, @RequestBody fCustomerResUpdated: FCustomerRes?): FCustomerRes {
        val fCustomer = fCustomerUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerResUpdated != null) {
            fCustomerResUpdated.id = fCustomer.id
            if (fCustomer.fdivisionBean >0) fCustomerResUpdated.fdivisionBean = fCustomer.fdivisionBean
            if (fCustomer.fcustomerGroupBean >0) fCustomerResUpdated.fcustomerGroupBean = fCustomer.fcustomerGroupBean
            if (fCustomer.fdistributionChannelBean >0) fCustomerResUpdated.fdistributionChannelBean = fCustomer.fdistributionChannelBean
            if (fCustomer.fsubAreaBean >0) fCustomerResUpdated.fsubAreaBean = fCustomer.fsubAreaBean

//            fCustomerUpdated.setFregionBean(fCustomer.getFregionBean());
            fCustomerUseCase.save(fCustomerResUpdated.toDomain())
            return fCustomerResUpdated
        }
        return fCustomer.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCustomer/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomer(@PathVariable("id") id: Int): FCustomerRes? {
        val fCustomer = fCustomerUseCase.findById(id)
        if (fCustomer != null) {
            fCustomerUseCase.delete(fCustomer)
        }
        return fCustomer.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerRestController::class.java)
    }
}