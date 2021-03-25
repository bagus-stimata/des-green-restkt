package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCustomerJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
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
    fun getFCustomerById(@PathVariable("id") id: Int): FCustomerEntity {
        return fCustomerJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomer"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomerEntity: List<FCustomerEntity?>
        get() = fCustomerJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFCustomerPage"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCustomer(@RequestParam("page") page: Int, @RequestParam("size") size: Int): MutableList<FCustomerEntity> {
        val pageable: Pageable = PageRequest.of(page, size)
        return fCustomerJPARepository!!.findAll(pageable).content.stream().filter(Predicate<FCustomerEntity> { x: FCustomerEntity -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FCustomerEntity> {
        return fCustomerJPARepository!!.findAllByDivision(fdivisionBean).stream().filter(Predicate<FCustomerEntity> { x: FCustomerEntity -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList()).toList()
    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): MutableList<FCustomerEntity> {
        return fCustomerJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).stream().filter(Predicate<FCustomerEntity> { x: FCustomerEntity? -> x!!.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFCustomerByDivisionPage/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFAreaByDivisionPage(@PathVariable("fdivisionBean") fdivisionBean: Int, @RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FCustomerEntity> {
        val pageable: Pageable = PageRequest.of(page, size)
        return fCustomerJPARepository!!.findAllByDivision(fdivisionBean, pageable).content.stream().filter(Predicate<FCustomerEntity> { x: FCustomerEntity -> x.statusActive == true && x.custno != "" && x.custname != "" }).collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/createFCustomer"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomer(@RequestBody fCustomerEntityNew: FCustomerEntity): FCustomerEntity {
        fCustomerEntityNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerJPARepository!!.save(fCustomerEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFCustomer/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerInfo(@PathVariable("id") id: Int, @RequestBody fCustomerEntityUpdated: FCustomerEntity?): FCustomerEntity {
        val fCustomer = fCustomerJPARepository!!.findById(id).orElse(FCustomerEntity())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerEntityUpdated != null) {
            fCustomerEntityUpdated.id = fCustomer.id
            if (fCustomer.fdivisionBean >0) fCustomerEntityUpdated.fdivisionBean = fCustomer.fdivisionBean
            if (fCustomer.fcustomerGroupBean >0) fCustomerEntityUpdated.fcustomerGroupBean = fCustomer.fcustomerGroupBean
            if (fCustomer.fdistributionChannelBean >0) fCustomerEntityUpdated.fdistributionChannelBean = fCustomer.fdistributionChannelBean
            if (fCustomer.fsubAreaBean >0) fCustomerEntityUpdated.fsubAreaBean = fCustomer.fsubAreaBean

//            fCustomerUpdated.setFregionBean(fCustomer.getFregionBean());
            fCustomerJPARepository!!.save(fCustomerEntityUpdated)
            return fCustomerEntityUpdated
        }
        return fCustomer
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFCustomer/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomer(@PathVariable("id") id: Int): FCustomerEntity? {
        val fCustomer = fCustomerJPARepository!!.findById(id).orElse(FCustomerEntity())
        if (fCustomer != null) {
            fCustomerJPARepository!!.delete(fCustomer)
        }
        return fCustomer
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerRestController::class.java)
    }
}