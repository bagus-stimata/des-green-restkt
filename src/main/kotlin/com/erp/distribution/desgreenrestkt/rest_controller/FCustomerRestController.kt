package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FCustomerJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomer
import com.erp.distribution.desgreenrestkt.security_model.Role
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
class FCustomerRestController {
    @Autowired
    var fCustomerJPARepository: FCustomerJPARepository? = null

    @RequestMapping(value = ["/rest/getFCustomerById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCustomerById(@PathVariable("id") id: Int): FCustomer {
        return fCustomerJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomer"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomer: List<FCustomer?>
        get() = fCustomerJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFCustomerPage"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCustomer(@RequestParam("page") page: Int, @RequestParam("size") size: Int): MutableList<FCustomer> {
        val pageable: Pageable = PageRequest.of(page, size)
        return fCustomerJPARepository!!.findAll(pageable).content.stream().filter(Predicate<FCustomer> { x: FCustomer -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FCustomer> {
        return fCustomerJPARepository!!.findAllByDivision(fdivisionBean).stream().filter(Predicate<FCustomer> { x: FCustomer -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList()).toList()
    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): MutableList<FCustomer> {
        return fCustomerJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).stream().filter(Predicate<FCustomer> { x: FCustomer? -> x!!.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivisionPage/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFAreaByDivisionPage(@PathVariable("fdivisionBean") fdivisionBean: Int, @RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FCustomer> {
        val pageable: Pageable = PageRequest.of(page, size)
        return fCustomerJPARepository!!.findAllByDivision(fdivisionBean, pageable).content.stream().filter(Predicate<FCustomer> { x: FCustomer -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/createFCustomer"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomer(@RequestBody fCustomerNew: FCustomer): FCustomer {
        fCustomerNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerJPARepository!!.save(fCustomerNew)
    }

    @RequestMapping(value = ["/rest/updateFCustomer/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerInfo(@PathVariable("id") id: Int, @RequestBody fCustomerUpdated: FCustomer?): FCustomer {
        val fCustomer = fCustomerJPARepository!!.findById(id).orElse(FCustomer())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerUpdated != null) {
            fCustomerUpdated.id = fCustomer.id
            if (fCustomer.fdivisionBean >0) fCustomerUpdated.fdivisionBean = fCustomer.fdivisionBean
            if (fCustomer.fcustomerGroupBean >0) fCustomerUpdated.fcustomerGroupBean = fCustomer.fcustomerGroupBean
            if (fCustomer.fdistributionChannelBean >0) fCustomerUpdated.fdistributionChannelBean = fCustomer.fdistributionChannelBean
            if (fCustomer.fsubAreaBean >0) fCustomerUpdated.fsubAreaBean = fCustomer.fsubAreaBean

//            fCustomerUpdated.setFregionBean(fCustomer.getFregionBean());
            fCustomerJPARepository!!.save(fCustomerUpdated)
            return fCustomerUpdated
        }
        return fCustomer
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCustomer/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomer(@PathVariable("id") id: Int): FCustomer? {
        val fCustomer = fCustomerJPARepository!!.findById(id).orElse(FCustomer())
        if (fCustomer != null) {
            fCustomerJPARepository!!.delete(fCustomer)
        }
        return fCustomer
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerRestController::class.java)
    }
}