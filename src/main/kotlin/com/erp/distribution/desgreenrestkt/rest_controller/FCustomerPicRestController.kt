package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FCustomerPicJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerPic
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class FCustomerPicRestController {
    @Autowired
    var fCustomerPicJPARepository: FCustomerPicJPARepository? = null

    @RequestMapping(value = ["/rest/getFCustomerPicById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFCustomerPicById(@PathVariable("id") id: Int): FCustomerPic {
        return fCustomerPicJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFCustomerPic"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomerPic: List<FCustomerPic?>
        get() = fCustomerPicJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFCustomerPicByDivison/{fcustomerBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFCustomerPicByDivison(@PathVariable("fcustomerBean") fcustomerBean: Int): List<FCustomerPic?>? {
        return fCustomerPicJPARepository!!.findAllByParentId(fcustomerBean)
    }

    @RequestMapping(value = ["/rest/createFCustomerPic"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFCustomerPic(@RequestBody fCustomerPicNew: FCustomerPic): FCustomerPic {
        fCustomerPicNew.id = 0 //Memastikan ID adalah Nol
        return fCustomerPicJPARepository!!.save(fCustomerPicNew)
    }

    @RequestMapping(value = ["/rest/updateFCustomerPic/{id}"], method = [RequestMethod.PUT])
    fun updateFCustomerPicInfo(@PathVariable("id") id: Int, @RequestBody fCustomerPicUpdated: FCustomerPic?): FCustomerPic {
        val fCustomerPic = fCustomerPicJPARepository!!.findById(id).orElse(FCustomerPic())!!
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fCustomerPicUpdated != null) {
            fCustomerPicUpdated.id = fCustomerPic.id
            if (fCustomerPic.fcustomerBean >0) fCustomerPicUpdated.fcustomerBean = fCustomerPic.fcustomerBean
            fCustomerPicJPARepository!!.save(fCustomerPicUpdated)
            return fCustomerPicUpdated
        }
        return fCustomerPic
    }

    @RequestMapping(value = ["/rest/deleteFCustomerPic/{id}"], method = [RequestMethod.DELETE])
    fun deleteFCustomerPic(@PathVariable("id") id: Int): FCustomerPic? {
        val fCustomerPic = fCustomerPicJPARepository!!.findById(id).orElse(FCustomerPic())
        if (fCustomerPic != null) {
            fCustomerPicJPARepository!!.delete(fCustomerPic)
        }
        return fCustomerPic
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FCustomerPicRestController::class.java)
    }
}