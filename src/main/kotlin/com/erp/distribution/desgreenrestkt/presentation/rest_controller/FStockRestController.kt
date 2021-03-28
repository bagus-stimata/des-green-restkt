package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFStockUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FStockRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
class FStockRestController @Autowired  constructor(
    val getFStockUseCase: GetFStockUseCase
){

    @RequestMapping(value = ["/rest/getFStockById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFStockById(@PathVariable("id") id: Int): FStockRes {
        return getFStockUseCase.findByIdRes(id)
    }

//    @get:RequestMapping(value = ["/rest/getAllFStock"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    val allStockEntity: List<FStockRes>
//        get() = getFStockUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFStockByFMaterial/{fmaterialBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFMaterial(@PathVariable("fmaterialBean") fmaterialBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStockRes> {
        return getFStockUseCase.findByFMaterialRes(fmaterialBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouse/{fwarehouseBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouse(@PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStockRes> {
        return getFStockUseCase.findByFWarehouseRes(fwarehouseBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouseOnly/{fwarehouseBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouseOnly(@PathVariable("fwarehouseBean") fwarehouseBean: Int): List<FStockRes> {
        return getFStockUseCase.findByFWarehouseRes(fwarehouseBean, Date(), Date()).stream().filter { x: FStockRes -> x.saldoAkhir > 0 }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouseAndFMaterial/{fwarehouseBean}/{fwarehouseBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouseAndFMaterial(@PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("fmaterialBean") fmaterialBean: Int): List<FStockRes> {
        return getFStockUseCase.findAllRes(fwarehouseBean, fmaterialBean, Date(), Date()).stream().filter { x: FStockRes -> x.saldoAkhir > 0 }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFStock/{fmaterialBean}/{fwarehouseBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStock(@PathVariable("fmaterialBean") fmaterialBean: Int, @PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStockRes> {
        return getFStockUseCase.findAllRes(fmaterialBean, fwarehouseBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/createFStock"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFStock(@RequestBody fStockEntityNew: FStockRes): FStockRes {
        fStockEntityNew.refno = 0 //Memastikan ID adalah Nol
        return getFStockUseCase.save(fStockEntityNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/updateFStock/{id}"], method = [RequestMethod.PUT])
    fun updateFStockInfo(@PathVariable("id") id: Int, @RequestBody fStockEntityUpdated: FStockRes?): FStockRes {
        val fStock = getFStockUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fStockEntityUpdated != null) {
            fStockEntityUpdated.refno = fStock.refno
            if (fStock.fmaterialBean >0) fStockEntityUpdated.fmaterialBean = fStock.fmaterialBean
            if (fStock.fwarehouseBean >0) fStockEntityUpdated.fwarehouseBean = fStock.fwarehouseBean
            getFStockUseCase.save(fStockEntityUpdated.toDomain()).toResponse()
            return fStockEntityUpdated
        }
        return fStock.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFStock/{id}"], method = [RequestMethod.DELETE])
    fun deleteFStock(@PathVariable("id") id: Int): FStockRes? {
        val fStock = getFStockUseCase.findById(id)
        if (fStock != null) {
            getFStockUseCase.delete(fStock)
        }
        return fStock.toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FStockRestController::class.java)
    }
}