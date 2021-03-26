package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FPromotionRulesdValidCustsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidCustsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FPromotionRulesdValidCustsRestController {
    @Autowired
    var fPromotionRulesdValidCustsJPARepository: FPromotionRulesdValidCustsJPARepository? = null

    @RequestMapping(value = ["/rest/getFPromotionRulesdValidCustsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFPromotionRulesdValidCustsById(@PathVariable("id") id: Int): FPromotionRulesdValidCustsEntity {
        return fPromotionRulesdValidCustsJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFPromotionRulesdValidCusts"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allPromotionRulesdValidCustEntities: List<FPromotionRulesdValidCustsEntity>
        get() = fPromotionRulesdValidCustsJPARepository!!.findAll()

//    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidCustsByParent/{fpromotionRuleshBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun getAllFPromotionRulesdValidCustsByParentId(@PathVariable("fpromotionRuleshBean") fpromotionRuleshBean: Int): List<FPromotionRulesdValidCustsEntity> {
//        return fPromotionRulesdValidCustsJPARepository!!.findAllByParentId(fpromotionRuleshBean)
//    }

//    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidCustsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    fun getAllFPromotionRulesdValidCustsByListParentId(@RequestBody listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidCustsEntity> {
//        return fPromotionRulesdValidCustsJPARepository!!.findAllByListParentId(listFpromotionRuleshBean)
//    }

    @RequestMapping(value = ["/rest/createFPromotionRulesdValidCusts"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFPromotionRulesdValidCusts(@RequestBody fPromotionRulesdValidCustsEntityNew: FPromotionRulesdValidCustsEntity): FPromotionRulesdValidCustsEntity {
        fPromotionRulesdValidCustsEntityNew.id = 0 //Memastikan ID adalah Nol
        return fPromotionRulesdValidCustsJPARepository!!.save(fPromotionRulesdValidCustsEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFPromotionRulesdValidCusts/{id}"], method = [RequestMethod.PUT])
    fun updateFPromotionRulesdValidCustsInfo(@PathVariable("id") id: Int, @RequestBody fPromotionRulesdValidCustsEntityUpdated: FPromotionRulesdValidCustsEntity?): FPromotionRulesdValidCustsEntity {
        val fPromotionRulesdValidCusts = fPromotionRulesdValidCustsJPARepository!!.findById(id).orElse(FPromotionRulesdValidCustsEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fPromotionRulesdValidCustsEntityUpdated != null) {
            fPromotionRulesdValidCustsEntityUpdated.id = fPromotionRulesdValidCusts.id
            if ( fPromotionRulesdValidCusts.fpromotionRuleshEntityBean == null) fPromotionRulesdValidCustsEntityUpdated.fpromotionRuleshEntityBean = fPromotionRulesdValidCusts.fpromotionRuleshEntityBean
            fPromotionRulesdValidCustsJPARepository!!.save(fPromotionRulesdValidCustsEntityUpdated)
            return fPromotionRulesdValidCustsEntityUpdated
        }
        return fPromotionRulesdValidCusts
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFPromotionRulesdValidCusts/{id}"], method = [RequestMethod.DELETE])
    fun deleteFPromotionRulesdValidCusts(@PathVariable("id") id: Int): FPromotionRulesdValidCustsEntity? {
        val fPromotionRulesdValidCusts = fPromotionRulesdValidCustsJPARepository!!.findById(id).orElse(FPromotionRulesdValidCustsEntity())
        if (fPromotionRulesdValidCusts != null) {
            fPromotionRulesdValidCustsJPARepository!!.delete(fPromotionRulesdValidCusts)
        }
        return fPromotionRulesdValidCusts
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FPromotionRulesdValidCustsRestController::class.java)
    }
}