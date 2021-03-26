package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FStockJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FStockEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
class FStockRestController {
    @Autowired
    var fStockJPARepository: FStockJPARepository? = null

    @RequestMapping(value = ["/rest/getFStockById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFStockById(@PathVariable("id") id: Int): FStockEntity {
        return fStockJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFStock"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allStockEntity: List<FStockEntity>
        get() = fStockJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFStockByFMaterial/{fmaterialBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFMaterial(@PathVariable("fmaterialBean") fmaterialBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStockEntity> {
        return fStockJPARepository!!.findAllByFMaterial(fmaterialBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouse/{fwarehouseBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouse(@PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStockEntity> {
        return fStockJPARepository!!.findAllByFWarehouse(fwarehouseBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouseOnly/{fwarehouseBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouseOnly(@PathVariable("fwarehouseBean") fwarehouseBean: Int): List<FStockEntity> {
        return fStockJPARepository!!.findAllByFWarehouse(fwarehouseBean, Date(), Date()).stream().filter { x: FStockEntity -> x.saldoAkhir > 0 }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouseAndFMaterial/{fwarehouseBean}/{fwarehouseBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouseAndFMaterial(@PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("fmaterialBean") fmaterialBean: Int): List<FStockEntity> {
        return fStockJPARepository!!.findAll(fwarehouseBean, fmaterialBean, Date(), Date()).stream().filter { x: FStockEntity -> x.saldoAkhir > 0 }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFStock/{fmaterialBean}/{fwarehouseBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStock(@PathVariable("fmaterialBean") fmaterialBean: Int, @PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStockEntity> {
        return fStockJPARepository!!.findAll(fmaterialBean, fwarehouseBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/createFStock"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFStock(@RequestBody fStockEntityNew: FStockEntity): FStockEntity {
        fStockEntityNew.refno = 0 //Memastikan ID adalah Nol
        return fStockJPARepository!!.save(fStockEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFStock/{id}"], method = [RequestMethod.PUT])
    fun updateFStockInfo(@PathVariable("id") id: Int, @RequestBody fStockEntityUpdated: FStockEntity?): FStockEntity {
        val fStock = fStockJPARepository!!.findById(id).orElse(FStockEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fStockEntityUpdated != null) {
            fStockEntityUpdated.refno = fStock.refno
            if (fStock.fmaterialBean >0) fStockEntityUpdated.fmaterialBean = fStock.fmaterialBean
            if (fStock.fwarehouseBean >0) fStockEntityUpdated.fwarehouseBean = fStock.fwarehouseBean
            fStockJPARepository!!.save(fStockEntityUpdated)
            return fStockEntityUpdated
        }
        return fStock
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFStock/{id}"], method = [RequestMethod.DELETE])
    fun deleteFStock(@PathVariable("id") id: Int): FStockEntity? {
        val fStock = fStockJPARepository!!.findById(id).orElse(FStockEntity())
        if (fStock != null) {
            fStockJPARepository!!.delete(fStock)
        }
        return fStock
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FStockRestController::class.java)
    }
}