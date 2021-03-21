package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FPromotionRulesdValidCustsJPARepository
import com.erp.distribution.desgreenrestkt.model.FPromotionRulesdValidCusts
import com.erp.distribution.desgreenrestkt.security_model.Role
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
    fun getFPromotionRulesdValidCustsById(@PathVariable("id") id: Int): FPromotionRulesdValidCusts {
        return fPromotionRulesdValidCustsJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFPromotionRulesdValidCusts"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allPromotionRulesdValidCusts: List<FPromotionRulesdValidCusts>
        get() = fPromotionRulesdValidCustsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidCustsByParent/{fpromotionRuleshBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFPromotionRulesdValidCustsByParentId(@PathVariable("fpromotionRuleshBean") fpromotionRuleshBean: Int): List<FPromotionRulesdValidCusts> {
        return fPromotionRulesdValidCustsJPARepository!!.findAllByParentId(fpromotionRuleshBean)
    }

    @RequestMapping(value = ["/rest/getAllFPromotionRulesdValidCustsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFPromotionRulesdValidCustsByListParentId(@RequestBody listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidCusts> {
        return fPromotionRulesdValidCustsJPARepository!!.findAllByListParentId(listFpromotionRuleshBean)
    }

    @RequestMapping(value = ["/rest/createFPromotionRulesdValidCusts"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFPromotionRulesdValidCusts(@RequestBody fPromotionRulesdValidCustsNew: FPromotionRulesdValidCusts): FPromotionRulesdValidCusts {
        fPromotionRulesdValidCustsNew.id = 0 //Memastikan ID adalah Nol
        return fPromotionRulesdValidCustsJPARepository!!.save(fPromotionRulesdValidCustsNew)
    }

    @RequestMapping(value = ["/rest/updateFPromotionRulesdValidCusts/{id}"], method = [RequestMethod.PUT])
    fun updateFPromotionRulesdValidCustsInfo(@PathVariable("id") id: Int, @RequestBody fPromotionRulesdValidCustsUpdated: FPromotionRulesdValidCusts?): FPromotionRulesdValidCusts {
        val fPromotionRulesdValidCusts = fPromotionRulesdValidCustsJPARepository!!.findById(id).orElse(FPromotionRulesdValidCusts())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fPromotionRulesdValidCustsUpdated != null) {
            fPromotionRulesdValidCustsUpdated.id = fPromotionRulesdValidCusts.id
            if (fPromotionRulesdValidCusts.fpromotionRuleshBean == null) fPromotionRulesdValidCustsUpdated.fpromotionRuleshBean = fPromotionRulesdValidCusts.fpromotionRuleshBean
            fPromotionRulesdValidCustsJPARepository!!.save(fPromotionRulesdValidCustsUpdated)
            return fPromotionRulesdValidCustsUpdated
        }
        return fPromotionRulesdValidCusts
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFPromotionRulesdValidCusts/{id}"], method = [RequestMethod.DELETE])
    fun deleteFPromotionRulesdValidCusts(@PathVariable("id") id: Int): FPromotionRulesdValidCusts? {
        val fPromotionRulesdValidCusts = fPromotionRulesdValidCustsJPARepository!!.findById(id).orElse(FPromotionRulesdValidCusts())
        if (fPromotionRulesdValidCusts != null) {
            fPromotionRulesdValidCustsJPARepository!!.delete(fPromotionRulesdValidCusts)
        }
        return fPromotionRulesdValidCusts
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FPromotionRulesdValidCustsRestController::class.java)
    }
}