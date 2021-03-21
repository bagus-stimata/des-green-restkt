package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FStockJPARepository
import com.erp.distribution.desgreenrestkt.model.FStock
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFStockById(@PathVariable("id") id: Int): FStock {
        return fStockJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFStock"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allStock: List<FStock>
        get() = fStockJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFStockByFMaterial/{fmaterialBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFMaterial(@PathVariable("fmaterialBean") fmaterialBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStock> {
        return fStockJPARepository!!.findAllByFMaterial(fmaterialBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouse/{fwarehouseBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouse(@PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStock> {
        return fStockJPARepository!!.findAllByFWarehouse(fwarehouseBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouseOnly/{fwarehouseBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouseOnly(@PathVariable("fwarehouseBean") fwarehouseBean: Int): List<FStock> {
        return fStockJPARepository!!.findAllByFWarehouse(fwarehouseBean, Date(), Date()).stream().filter { x: FStock -> x.saldoAkhir > 0 }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFStockByFWarehouseAndFMaterial/{fwarehouseBean}/{fwarehouseBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStockByFWarehouseAndFMaterial(@PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("fmaterialBean") fmaterialBean: Int): List<FStock> {
        return fStockJPARepository!!.findAll(fwarehouseBean, fmaterialBean, Date(), Date()).stream().filter { x: FStock -> x.saldoAkhir > 0 }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFStock/{fmaterialBean}/{fwarehouseBean}/{stockDateFrom}/{stockDateTo}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFStock(@PathVariable("fmaterialBean") fmaterialBean: Int, @PathVariable("fwarehouseBean") fwarehouseBean: Int, @PathVariable("stockDateFrom") stockDateFrom: Date?, @PathVariable("stockDateTo") stockDateTo: Date?): List<FStock> {
        return fStockJPARepository!!.findAll(fmaterialBean, fwarehouseBean, stockDateFrom!!, stockDateTo!!)
    }

    @RequestMapping(value = ["/rest/createFStock"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFStock(@RequestBody fStockNew: FStock): FStock {
        fStockNew.refno = 0 //Memastikan ID adalah Nol
        return fStockJPARepository!!.save(fStockNew)
    }

    @RequestMapping(value = ["/rest/updateFStock/{id}"], method = [RequestMethod.PUT])
    fun updateFStockInfo(@PathVariable("id") id: Int, @RequestBody fStockUpdated: FStock?): FStock {
        val fStock = fStockJPARepository!!.findById(id).orElse(FStock())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fStockUpdated != null) {
            fStockUpdated.refno = fStock.refno
            if (fStock.fmaterialBean >0) fStockUpdated.fmaterialBean = fStock.fmaterialBean
            if (fStock.fwarehouseBean >0) fStockUpdated.fwarehouseBean = fStock.fwarehouseBean
            fStockJPARepository!!.save(fStockUpdated)
            return fStockUpdated
        }
        return fStock
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFStock/{id}"], method = [RequestMethod.DELETE])
    fun deleteFStock(@PathVariable("id") id: Int): FStock? {
        val fStock = fStockJPARepository!!.findById(id).orElse(FStock())
        if (fStock != null) {
            fStockJPARepository!!.delete(fStock)
        }
        return fStock
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FStockRestController::class.java)
    }
}