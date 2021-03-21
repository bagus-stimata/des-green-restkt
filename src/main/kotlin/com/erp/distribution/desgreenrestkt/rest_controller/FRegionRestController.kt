package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FRegionJPARepository
import com.erp.distribution.desgreenrestkt.model.FRegion
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class FRegionRestController {
    @Autowired
    var fRegionJPARepository: FRegionJPARepository? = null

    @RequestMapping(value = ["/rest/getFRegionById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFRegionById(@PathVariable("id") id: Int): FRegion {
        return fRegionJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFRegion"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allRegion: List<FRegion>
        get() = fRegionJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFRegionByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFRegionByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FRegion> {
        return fRegionJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFRegion"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFRegion(@RequestBody fRegionNew: FRegion): FRegion {
        fRegionNew.id = 0 //Memastikan ID adalah Nol
        return fRegionJPARepository!!.save(fRegionNew)
    }

    @RequestMapping(value = ["/rest/updateFRegion/{id}"], method = [RequestMethod.PUT])
    fun updateFRegionInfo(@PathVariable("id") id: Int, @RequestBody fRegionUpdated: FRegion?): FRegion {
        val fRegion = fRegionJPARepository!!.findById(id).orElse(FRegion())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fRegionUpdated != null) {
            fRegionUpdated.id = fRegion.id
            if (fRegion.fdivisionBean >0) fRegionUpdated.fdivisionBean = fRegion.fdivisionBean
            fRegionJPARepository!!.save(fRegionUpdated)
            return fRegionUpdated
        }
        return fRegion
    }

    @RequestMapping(value = ["/rest/deleteFRegion/{id}"], method = [RequestMethod.DELETE])
    fun deleteFRegion(@PathVariable("id") id: Int): FRegion? {
        val fRegion = fRegionJPARepository!!.findById(id).orElse(FRegion())
        if (fRegion != null) {
            fRegionJPARepository!!.delete(fRegion)
        }
        return fRegion
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FRegionRestController::class.java)
    }
}