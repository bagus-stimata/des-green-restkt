package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FRegionJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FRegionEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class FRegionRestController {
    @Autowired
    var fRegionJPARepository: FRegionJPARepository? = null

    @RequestMapping(value = ["/rest/getFRegionById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFRegionById(@PathVariable("id") id: Int): FRegionEntity {
        return fRegionJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFRegion"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allRegionEntity: List<FRegionEntity>
        get() = fRegionJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFRegionByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFRegionByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FRegionEntity> {
        return fRegionJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFRegion"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFRegion(@RequestBody fRegionEntityNew: FRegionEntity): FRegionEntity {
        fRegionEntityNew.id = 0 //Memastikan ID adalah Nol
        return fRegionJPARepository!!.save(fRegionEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFRegion/{id}"], method = [RequestMethod.PUT])
    fun updateFRegionInfo(@PathVariable("id") id: Int, @RequestBody fRegionEntityUpdated: FRegionEntity?): FRegionEntity {
        val fRegion = fRegionJPARepository!!.findById(id).orElse(FRegionEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fRegionEntityUpdated != null) {
            fRegionEntityUpdated.id = fRegion.id
            if (fRegion.fdivisionBean >0) fRegionEntityUpdated.fdivisionBean = fRegion.fdivisionBean
            fRegionJPARepository!!.save(fRegionEntityUpdated)
            return fRegionEntityUpdated
        }
        return fRegion
    }

    @RequestMapping(value = ["/rest/deleteFRegion/{id}"], method = [RequestMethod.DELETE])
    fun deleteFRegion(@PathVariable("id") id: Int): FRegionEntity? {
        val fRegion = fRegionJPARepository!!.findById(id).orElse(FRegionEntity())
        if (fRegion != null) {
            fRegionJPARepository!!.delete(fRegion)
        }
        return fRegion
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FRegionRestController::class.java)
    }
}