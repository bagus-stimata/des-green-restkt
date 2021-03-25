package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCustomerPicJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerPicEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class FCustomerPicRestController {
    @Autowired
    var fCustomerPicJPARepository: FCustomerPicJPARepository? = null

    @RequestMapping(value = ["/rest/getFCustomerPicById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCustomerPicById(@PathVariable("id") id: Int): FCustomerPicEntity {
        return fCustomerPicJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomerPic"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomerPicEntity: List<FCustomerPicEntity?>
        get() = fCustomerPicJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFCustomerPicByDivison/{fcustomerBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerPicByDivison(@PathVariable("fcustomerBean") fcustomerBean: Int): List<FCustomerPicEntity?>? {
        return fCustomerPicJPARepository!!.findAllByParentId(fcustomerBean)
    }

    @RequestMapping(value = ["/rest/createFCustomerPic"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomerPic(@RequestBody fCustomerPicEntityNew: FCustomerPicEntity): FCustomerPicEntity {
        fCustomerPicEntityNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerPicJPARepository!!.save(fCustomerPicEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFCustomerPic/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerPicInfo(@PathVariable("id") id: Int, @RequestBody fCustomerPicEntityUpdated: FCustomerPicEntity?): FCustomerPicEntity {
        val fCustomerPic = fCustomerPicJPARepository!!.findById(id).orElse(FCustomerPicEntity())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerPicEntityUpdated != null) {
            fCustomerPicEntityUpdated.id = fCustomerPic.id
            if (fCustomerPic.fcustomerBean >0) fCustomerPicEntityUpdated.fcustomerBean = fCustomerPic.fcustomerBean
            fCustomerPicJPARepository!!.save(fCustomerPicEntityUpdated)
            return fCustomerPicEntityUpdated
        }
        return fCustomerPic
    }

    @RequestMapping(value = ["/rest/deleteFCustomerPic/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomerPic(@PathVariable("id") id: Int): FCustomerPicEntity? {
        val fCustomerPic = fCustomerPicJPARepository!!.findById(id).orElse(FCustomerPicEntity())
        if (fCustomerPic != null) {
            fCustomerPicJPARepository!!.delete(fCustomerPic)
        }
        return fCustomerPic
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerPicRestController::class.java)
    }
}