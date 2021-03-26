package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FPromotionRulesdValidProductsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidProductsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
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
    fun getFPromotionRulesdValidProductsById(@PathVariable("id") id: Int): FPromotionRulesdValidProductsEntity {
        return fPromotionRulesdValidProductsJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFPromotionRulesdValidProducts"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allPromotionRulesdValidProductEntities: List<FPromotionRulesdValidProductsEntity>
        get() = fPromotionRulesdValidProductsJPARepository!!.findAll()

//    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidProductsByParent/{fpromotionRuleshBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun getAllFPromotionRulesdValidProductsByParentId(@PathVariable("fpromotionRuleshBean") fpromotionRuleshBean: Int): List<FPromotionRulesdValidProductsEntity> {
//        return fPromotionRulesdValidProductsJPARepository!!.findAllByParentId(fpromotionRuleshBean)
//    }
//
//    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidProductsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    fun getAllFPromotionRulesdValidProductsByListParentId(@RequestBody listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidProductsEntity> {
//        return fPromotionRulesdValidProductsJPARepository!!.findAllByListParentId(listFpromotionRuleshBean)
//    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFPromotionRulesdValidProducts"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFPromotionRulesdValidProducts(@RequestBody fPromotionRulesdValidProductsEntityNew: FPromotionRulesdValidProductsEntity): FPromotionRulesdValidProductsEntity {
        fPromotionRulesdValidProductsEntityNew.id = 0 //Memastikan ID adalah Nol
        return fPromotionRulesdValidProductsJPARepository!!.save(fPromotionRulesdValidProductsEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFPromotionRulesdValidProducts/{id}"], method = [RequestMethod.PUT])
    fun updateFPromotionRulesdValidProductsInfo(@PathVariable("id") id: Int, @RequestBody fPromotionRulesdValidProductsEntityUpdated: FPromotionRulesdValidProductsEntity?): FPromotionRulesdValidProductsEntity {
        val fPromotionRulesdValidProducts = fPromotionRulesdValidProductsJPARepository!!.findById(id).orElse(FPromotionRulesdValidProductsEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fPromotionRulesdValidProductsEntityUpdated != null) {
            fPromotionRulesdValidProductsEntityUpdated.id = fPromotionRulesdValidProducts.id
            if (fPromotionRulesdValidProducts.fpromotionRuleshEntityBean == null) fPromotionRulesdValidProductsEntityUpdated.fpromotionRuleshEntityBean = fPromotionRulesdValidProducts.fpromotionRuleshEntityBean
            fPromotionRulesdValidProductsJPARepository!!.save(fPromotionRulesdValidProductsEntityUpdated)
            return fPromotionRulesdValidProductsEntityUpdated
        }
        return fPromotionRulesdValidProducts
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFPromotionRulesdValidProducts/{id}"], method = [RequestMethod.DELETE])
    fun deleteFPromotionRulesdValidProducts(@PathVariable("id") id: Int): FPromotionRulesdValidProductsEntity? {
        val fPromotionRulesdValidProducts = fPromotionRulesdValidProductsJPARepository!!.findById(id).orElse(FPromotionRulesdValidProductsEntity())
        if (fPromotionRulesdValidProducts != null) {
            fPromotionRulesdValidProductsJPARepository!!.delete(fPromotionRulesdValidProducts)
        }
        return fPromotionRulesdValidProducts
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FPromotionRulesdValidProductsRestController::class.java)
    }
}