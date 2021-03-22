package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FAreaJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FArea
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FAreaRestController {
    @Autowired
    var fAreaJPARepository: FAreaJPARepository? = null

    @RequestMapping(value = ["/rest/getFAreaById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFAreaById(@PathVariable("id") id: Int): FArea {
        return fAreaJPARepository!!.findById(id).get()
    }

    //        System.out.println("Hello Get All FArea");
//    @get:RequestMapping(value = ["/rest/getAllFArea"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    val allArea: List<FArea?>
//        get() =//        System.out.println("Hello Get All FArea");
//            fAreaJPARepository!!.findAll()
    @RequestMapping(value = ["/rest/getAllFArea"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun allArea(): List<FArea?>{
        return fAreaJPARepository!!.findAll().map {
            System.out.println("Hello: " + it.description)
            it
        }
    }

    @RequestMapping(value = ["/rest/getAllFAreaByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFAreaByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FArea?>? {
        return fAreaJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFAreaByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFAreaByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FArea?>? {
        return fAreaJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFArea"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFArea(@RequestBody fAreaNew: FArea): FArea {
//        System.out.println("hello aku dipanggil saat create FArea");
        fAreaNew.id = 0 //Memastikan ID adalah Nol
        return fAreaJPARepository!!.save(fAreaNew)
        //        FArea updatedDomain = new FArea();
//        if (fAreaNew !=null) {
//            try {
//                fAreaNew.setId(0);
//                updatedDomain = fAreaJPARepository.save(fAreaNew);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return updatedDomain;
    }

    @RequestMapping(value = ["/rest/updateFArea/{id}"], method = [RequestMethod.PUT])
    fun updateFAreaInfo(@PathVariable("id") id: Int, @RequestBody fAreaUpdated: FArea): FArea {
        val fArea = fAreaJPARepository!!.findById(id).orElse(FArea())!!
        //Tidak Meng Update Parent: Hanya Info Saja
//        var updatedDomain = FArea()
            fAreaUpdated.id = fArea.id
            if (fArea.fdivisionBean >0) fAreaUpdated.fdivisionBean = fArea.fdivisionBean
            try {
                fAreaJPARepository!!.save(fAreaUpdated)
            } catch (e: Exception) {
            }

        return fAreaUpdated
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFArea/{id}"], method = [RequestMethod.DELETE])
    fun deleteFArea(@PathVariable("id") id: Int): FArea? {
        val fArea = fAreaJPARepository!!.findById(id).orElse(FArea())
        if (fArea != null) {
            fAreaJPARepository!!.delete(fArea)
        }
        return fArea
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FAreaRestController::class.java)
    }
}