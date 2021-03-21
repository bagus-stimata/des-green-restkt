package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FParamDiskonItemJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FParamDiskonItem
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FParamDiskonItemRestController {
    @Autowired
    var fParamDiskonItemJPARepository: FParamDiskonItemJPARepository? = null

    @RequestMapping(value = ["/rest/getFParamDiskonItemById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFParamDiskonItemById(@PathVariable("id") id: Int): FParamDiskonItem {
        return fParamDiskonItemJPARepository!!.findById(id).get()
    }

    //    @RequestMapping(value = "/rest/getAllFParamDiskonItem", produces = {MediaType.APPLICATION_JSON_VALUE} )
    //    public List<FParamDiskonItem> getAllParamDiskonItem(){
    //        return fParamDiskonItemJPARepository.findAll();
    //    }
    //    @RequestMapping(value = "/rest/getAllFParamDiskonItemByDivision/{fdivisionBean}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    //    public List<FParamDiskonItem> getAllFParamDiskonItemByDivision(@PathVariable("fdivisionBean") int fdivisionBean){
    //        return fParamDiskonItemJPARepository.findAllByDivision(fdivisionBean);
    //    }
    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFParamDiskonItem"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFParamDiskonItem(@RequestBody fParamDiskonItemNew: FParamDiskonItem): FParamDiskonItem {
        fParamDiskonItemNew.id = 0 //Memastikan ID adalah Nol
        return fParamDiskonItemJPARepository!!.save(fParamDiskonItemNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFParamDiskonItem/{id}"], method = [RequestMethod.PUT])
    fun updateFParamDiskonItemInfo(@PathVariable("id") id: Int, @RequestBody fParamDiskonItemUpdated: FParamDiskonItem?): FParamDiskonItem {
        val fParamDiskonItem = fParamDiskonItemJPARepository!!.findById(id).orElse(FParamDiskonItem())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fParamDiskonItemUpdated != null) {
            fParamDiskonItemUpdated.id = fParamDiskonItem.id
            //            if (fParamDiskonItem.getFdivisionBean()==null) fParamDiskonItemUpdated.setFdivisionBean(fParamDiskonItem.getFdivisionBean());
            fParamDiskonItemJPARepository!!.save(fParamDiskonItemUpdated)
            return fParamDiskonItemUpdated
        }
        return fParamDiskonItem
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFParamDiskonItem/{id}"], method = [RequestMethod.DELETE])
    fun deleteFParamDiskonItem(@PathVariable("id") id: Int): FParamDiskonItem? {
        val fParamDiskonItem = fParamDiskonItemJPARepository!!.findById(id).orElse(FParamDiskonItem())
        if (fParamDiskonItem != null) {
            fParamDiskonItemJPARepository!!.delete(fParamDiskonItem)
        }
        return fParamDiskonItem
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FParamDiskonItemRestController::class.java)
    }
}