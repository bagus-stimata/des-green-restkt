package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFAreaUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FAreaRestController  @Autowired constructor(
    val fAreaUseCase: GetFAreaUseCase
) {

    @RequestMapping(value = ["/rest/getFAreaById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFAreaById(@PathVariable("id") id: Int): FAreaRes {
        return fAreaUseCase.findByIdRes(id)
    }

    //        System.out.println("Hello Get All FArea");
    @get:RequestMapping(value = ["/rest/getAllFArea"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allAreaEntity: List<FAreaRes?>
        get() =//        System.out.println("Hello Get All FArea");
            fAreaUseCase.findAllRes()
//    @RequestMapping(value = ["/rest/getAllFArea"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun allArea(): List<FArea?>{
//        return fAreaUseCase.findAll().map {
////            System.out.println("Hello: " + it.description)
//            it
//        }
//    }

    @RequestMapping(value = ["/rest/getAllFAreaByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFAreaByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FAreaRes?>? {
        return fAreaUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/getAllFAreaByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFAreaByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FAreaRes?>? {
        return fAreaUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFArea"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFArea(@RequestBody fAreaResNew: FAreaRes): FAreaRes {
//        System.out.println("hello aku dipanggil saat create FArea");
        fAreaResNew.id = 0 //Memastikan ID adalah Nol
        return fAreaUseCase.save(fAreaResNew.toDomain()).toResponse()
        //        FArea updatedDomain = new FArea();
//        if (fAreaNew !=null) {
//            try {
//                fAreaNew.setId(0);
//                updatedDomain = fAreaUseCase.save(fAreaNew);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return updatedDomain;
    }

    @RequestMapping(value = ["/rest/updateFArea/{id}"], method = [RequestMethod.PUT])
    fun updateFAreaInfo(@PathVariable("id") id: Int, @RequestBody fAreaResUpdated: FAreaRes): FAreaRes {
        val fArea = fAreaUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
//        var updatedDomain = FArea()
            fAreaResUpdated.id = fArea.id
            if (fArea.fdivisionBean >0) fAreaResUpdated.fdivisionBean = fArea.fdivisionBean
            try {
                fAreaUseCase.save(fAreaResUpdated.toDomain())
            } catch (e: Exception) {
            }

        return fAreaResUpdated
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFArea/{id}"], method = [RequestMethod.DELETE])
    fun deleteFArea(@PathVariable("id") id: Int) {
        val fArea = fAreaUseCase.findById(id)
        if (fArea != null) {
            fAreaUseCase.delete(fArea)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FAreaRestController::class.java)
    }
}