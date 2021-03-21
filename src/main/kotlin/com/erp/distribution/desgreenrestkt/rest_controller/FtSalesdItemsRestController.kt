package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FMaterialJPARepository
import com.erp.distribution.desgreenrestkt.jpa_repository.FtSalesdItemsJPARepository
import com.erp.distribution.desgreenrestkt.model.FtSalesdItems
import com.erp.distribution.desgreenrestkt.security_model.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtSalesdItemsRestController {
    @Autowired
    var ftSalesdItemsJPARepository: FtSalesdItemsJPARepository? = null

    @Autowired
    var fMaterialJPARepository: FMaterialJPARepository? = null

    @RequestMapping(value = ["/rest/getFtSalesdItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtSalesdItemsById(@PathVariable("id") id: Long): FtSalesdItems {
        return ftSalesdItemsJPARepository!!.findById(id).orElse(FtSalesdItems())
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesdItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSalesdItems: List<FtSalesdItems>
        get() = ftSalesdItemsJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtSalesdItemsByFtSalesh/{ftSaleshBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSalesdItemsByFtSalesh(@PathVariable("ftSaleshBean") ftSaleshBean: Long): List<FtSalesdItems> {
        return ftSalesdItemsJPARepository!!.findAllByFtSaleshBean(ftSaleshBean)
    }

    @RequestMapping(value = ["/rest/getAllFtSalesdItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSalesdItemsByListParentId(@RequestBody listFtSaleshBean: List<Long>): List<FtSalesdItems> {
        return ftSalesdItemsJPARepository!!.findAllByListFtSalesh(listFtSaleshBean)
    }

    @RequestMapping(value = ["/rest/createFtSalesdItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesdItems(@RequestBody ftSalesdItemsNew: FtSalesdItems): FtSalesdItems {
        //Sementara TAx Dari Sini
//        FMaterial fMaterial = fMaterialJPARepository.findById(ftSalesdItemsNew.getFmaterialBean()).orElse( new FMaterial() );

//        if (fMaterial.getId() <= 0){
//            System.out.println("Tidak Ketemu bos >> " + ftSalesdItemsNew.getFmaterialBean() );
//        }else {
//            ftSalesdItemsNew.setFtaxBean(fMaterial.getFtaxBean());
//            ftSalesdItemsNew.setTaxPercent(10.0);
//            ftSalesdItemsNew.setTax(true);
//        }
        ftSalesdItemsNew.id = 0 //Pastikan ID nya nol untuk Create Baru
        return ftSalesdItemsJPARepository!!.save(ftSalesdItemsNew)
    }

    @RequestMapping(value = ["/rest/updateFtSalesdItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtSalesdItemsInfo(@PathVariable("id") id: Long, @RequestBody ftSalesdItemsUpdated: FtSalesdItems?): FtSalesdItems {
        val ftSalesdItems = ftSalesdItemsJPARepository!!.findById(id).orElse(FtSalesdItems())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSalesdItemsUpdated != null) {
            ftSalesdItemsUpdated.id = ftSalesdItems.id
            if (ftSalesdItems.fmaterialBean >0) ftSalesdItemsUpdated.fmaterialBean = ftSalesdItems.fmaterialBean
            ftSalesdItemsJPARepository!!.save(ftSalesdItemsUpdated)
            return ftSalesdItemsUpdated
        }
        return ftSalesdItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesdItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesdItems(@PathVariable("id") id: Long): FtSalesdItems? {
        val ftSalesdItems = ftSalesdItemsJPARepository!!.findById(id).orElse(FtSalesdItems())
        if (ftSalesdItems != null) {
            if (ftSalesdItems.id > 0 && ftSalesdItems.ftSaleshBean > 0) {
                ftSalesdItemsJPARepository!!.delete(ftSalesdItems)
            }
        }
        return ftSalesdItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtSalesdItemsRestController::class.java)
    }
}