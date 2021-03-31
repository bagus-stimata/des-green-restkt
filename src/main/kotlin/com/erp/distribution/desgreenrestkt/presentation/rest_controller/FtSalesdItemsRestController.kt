package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSalesdItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFtSalesdItemsUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FtSalesdItemsRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtSalesdItemsRestController @Autowired constructor(
  val getFtSalesdItemsUseCase: GetFtSalesdItemsUseCase,
  getFMaterialUseCase: GetFMaterialUseCase
){
//    @Autowired
//    var ftSalesdItemsJPARepository: FtSalesdItemsJPARepository? = null
//
//    @Autowired
//    var getFMaterialUseCase: FMaterialJPARepository? = null

    @RequestMapping(value = ["/rest/getFtSalesdItemsById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtSalesdItemsById(@PathVariable("id") id: Long): FtSalesdItemsRes {
        return getFtSalesdItemsUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesdItems"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSalesdItemEntities: List<FtSalesdItemsRes>
        get() = getFtSalesdItemsUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFtSalesdItemsByFtSalesh/{ftSaleshBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSalesdItemsByFtSalesh(@PathVariable("ftSaleshBean") ftSaleshBean: Long): List<FtSalesdItemsRes> {
        return getFtSalesdItemsUseCase.findByParentRes(ftSaleshBean)
    }

    @RequestMapping(value = ["/rest/getAllFtSalesdItemsByListParentId"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSalesdItemsByListParentId(@RequestBody listFtSaleshBean: List<Long>): List<FtSalesdItemsRes> {
        return getFtSalesdItemsUseCase.findByListOfParentRes(listFtSaleshBean)
    }

    @RequestMapping(value = ["/rest/createFtSalesdItems"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesdItems(@RequestBody ftSalesdItemsResNew: FtSalesdItemsRes): FtSalesdItemsRes {
        //Sementara TAx Dari Sini
//        FMaterial fMaterial = getFMaterialUseCase.findById(ftSalesdItemsNew.getFmaterialBean()).orElse( new FMaterial() );

//        if (fMaterial.getId() <= 0){
//            System.out.println("Tidak Ketemu bos >> " + ftSalesdItemsNew.getFmaterialBean() );
//        }else {
//            ftSalesdItemsNew.setFtaxBean(fMaterial.getFtaxBean());
//            ftSalesdItemsNew.setTaxPercent(10.0);
//            ftSalesdItemsNew.setTax(true);
//        }
        ftSalesdItemsResNew.id = 0 //Pastikan ID nya nol untuk Create Baru
        return getFtSalesdItemsUseCase.save(ftSalesdItemsResNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/updateFtSalesdItems/{id}"], method = [RequestMethod.PUT])
    fun updateFtSalesdItemsInfo(@PathVariable("id") id: Long, @RequestBody ftSalesdItemsResUpdated: FtSalesdItemsRes?): FtSalesdItemsRes {
        val ftSalesdItems = getFtSalesdItemsUseCase.findByIdRes(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSalesdItemsResUpdated != null) {
            ftSalesdItemsResUpdated.id = ftSalesdItems.id
            if (ftSalesdItems.fmaterialBean >0) ftSalesdItemsResUpdated.fmaterialBean = ftSalesdItems.fmaterialBean
            getFtSalesdItemsUseCase.save(ftSalesdItemsResUpdated.toDomain())
            return ftSalesdItemsResUpdated
        }
        return ftSalesdItems
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesdItems/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesdItems(@PathVariable("id") id: Long): FtSalesdItemsRes? {
        val ftSalesdItems = getFtSalesdItemsUseCase.findByIdRes(id)
        if (ftSalesdItems != null) {
            if (ftSalesdItems.id > 0 && ftSalesdItems.ftSaleshBean > 0) {
                getFtSalesdItemsUseCase.delete(ftSalesdItems.toDomain())
            }
        }
        return ftSalesdItems
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FtSalesdItemsRestController::class.java)
    }
}