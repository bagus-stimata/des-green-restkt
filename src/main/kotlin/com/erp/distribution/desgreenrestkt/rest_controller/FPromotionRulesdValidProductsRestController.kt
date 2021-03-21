package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FPromotionRulesdValidProductsJPARepository
import com.erp.distribution.desgreenrestkt.model.FPromotionRulesdValidProducts
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FPromotionRulesdValidProductsRestController {
    @Autowired
    var fPromotionRulesdValidProductsJPARepository: FPromotionRulesdValidProductsJPARepository? = null

    @RequestMapping(value = ["/rest/getFPromotionRulesdValidProductsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFPromotionRulesdValidProductsById(@PathVariable("id") id: Int): FPromotionRulesdValidProducts {
        return fPromotionRulesdValidProductsJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFPromotionRulesdValidProducts"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allPromotionRulesdValidProducts: List<FPromotionRulesdValidProducts>
        get() = fPromotionRulesdValidProductsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidProductsByParent/{fpromotionRuleshBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFPromotionRulesdValidProductsByParentId(@PathVariable("fpromotionRuleshBean") fpromotionRuleshBean: Int): List<FPromotionRulesdValidProducts> {
        return fPromotionRulesdValidProductsJPARepository!!.findAllByParentId(fpromotionRuleshBean)
    }

    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidProductsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFPromotionRulesdValidProductsByListParentId(@RequestBody listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidProducts> {
        return fPromotionRulesdValidProductsJPARepository!!.findAllByListParentId(listFpromotionRuleshBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFPromotionRulesdValidProducts"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFPromotionRulesdValidProducts(@RequestBody fPromotionRulesdValidProductsNew: FPromotionRulesdValidProducts): FPromotionRulesdValidProducts {
        fPromotionRulesdValidProductsNew.id = 0 //Memastikan ID adalah Nol
        return fPromotionRulesdValidProductsJPARepository!!.save(fPromotionRulesdValidProductsNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFPromotionRulesdValidProducts/{id}"], method = [RequestMethod.PUT])
    fun updateFPromotionRulesdValidProductsInfo(@PathVariable("id") id: Int, @RequestBody fPromotionRulesdValidProductsUpdated: FPromotionRulesdValidProducts?): FPromotionRulesdValidProducts {
        val fPromotionRulesdValidProducts = fPromotionRulesdValidProductsJPARepository!!.findById(id).orElse(FPromotionRulesdValidProducts())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fPromotionRulesdValidProductsUpdated != null) {
            fPromotionRulesdValidProductsUpdated.id = fPromotionRulesdValidProducts.id
            if (fPromotionRulesdValidProducts.fpromotionRuleshBean == null) fPromotionRulesdValidProductsUpdated.fpromotionRuleshBean = fPromotionRulesdValidProducts.fpromotionRuleshBean
            fPromotionRulesdValidProductsJPARepository!!.save(fPromotionRulesdValidProductsUpdated)
            return fPromotionRulesdValidProductsUpdated
        }
        return fPromotionRulesdValidProducts
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFPromotionRulesdValidProducts/{id}"], method = [RequestMethod.DELETE])
    fun deleteFPromotionRulesdValidProducts(@PathVariable("id") id: Int): FPromotionRulesdValidProducts? {
        val fPromotionRulesdValidProducts = fPromotionRulesdValidProductsJPARepository!!.findById(id).orElse(FPromotionRulesdValidProducts())
        if (fPromotionRulesdValidProducts != null) {
            fPromotionRulesdValidProductsJPARepository!!.delete(fPromotionRulesdValidProducts)
        }
        return fPromotionRulesdValidProducts
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FPromotionRulesdValidProductsRestController::class.java)
    }
}