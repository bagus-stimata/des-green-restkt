package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSalesdItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSaleshJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFtSalesdItemsUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FtSaleshRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtSaleshRestController @Autowired constructor(
    val getFtSaleshUseCase: GetFtSaleshUseCase,
    val getFtSalesdItemsUseCase: GetFtSalesdItemsUseCase
){
//    @Autowired
//    var ftSaleshJPARepository: FtSaleshJPARepository? = null
//
//    @Autowired
//    var ftSalesdItemsJPARepository: FtSalesdItemsJPARepository? = null

    @RequestMapping(value = ["/rest/getFtSaleshById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtSaleshById(@PathVariable("id") id: Long): FtSaleshRes {
        return getFtSaleshUseCase.findByRefnoRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSaleshEntities: List<FtSaleshRes>
        get() = getFtSaleshUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFtSaleshByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSaleshByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtSaleshRes> {
        return getFtSaleshUseCase.findByDivisionRes(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtSalesh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesh(@RequestBody ftSaleshEntityNew: FtSaleshRes): FtSaleshRes {
        ftSaleshEntityNew.fcustomerShipToBean = ftSaleshEntityNew.fcustomerBean

//        logger.debug(">> FtSaelsh Export: "  + ftSaleshNew.getRefno() + " >> " + ftSaleshNew.getFdivisionBean() + " >> " +
//                ftSaleshNew.getFcustomerBean() + " >> " + ftSaleshNew.getFwarehouseBean() + ftSaleshNew.getFsalesmanBean() );

//        System.out.println(">> FtSalesh Export: "  + ftSaleshNew.getRefno() + " >> " + ftSaleshNew.getFdivisionBean() + " >> " +
//                ftSaleshNew.getFcustomerBean() + " >> " + ftSaleshNew.getFwarehouseBean()  + " >> " + ftSaleshNew.getFsalesmanBean()  + " >> End");
        /**
         * 1. Check tanggal Created dan sourceID
         * 2. Jika Tidak ada maka CREATE NEW
         * 3. Jika Sama maka
         * a. Jika Sudah Terbit Nomor Order -> Maka ditolak tidak bisa update -> Mengembalikan nilai balik
         * b. Jika Belum Terbit Nomor Order maka -> Update FtSalesh -> Hapus Item -> Insert Lagi
         */
        return getFtSaleshUseCase.save(ftSaleshEntityNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/createFtSaleshFromAndroid"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshFromAndroid(@RequestBody ftSaleshEntityNew: FtSaleshRes): FtSaleshRes {
        ftSaleshEntityNew.fcustomerShipToBean = ftSaleshEntityNew.fcustomerBean

//        logger.debug(">> FtSaelsh Export: "  + ftSaleshNew.getRefno() + " >> " + ftSaleshNew.getFdivisionBean() + " >> " +
//                ftSaleshNew.getFcustomerBean() + " >> " + ftSaleshNew.getFwarehouseBean() + ftSaleshNew.getFsalesmanBean() );

//        System.out.println(">> FtSalesh Export: "  + ftSaleshNew.getRefno() + " >> " + ftSaleshNew.getFdivisionBean() + " >> " +
//                ftSaleshNew.getFcustomerBean() + " >> " + ftSaleshNew.getFwarehouseBean()  + " >> " + ftSaleshNew.getFsalesmanBean()  + " >> End");
        /**
         * 1. Check tanggal Created dan sourceID
         * 2. Jika Tidak ada maka CREATE NEW
         * 3. Jika Sama maka
         * a. Jika Sudah Terbit Nomor Order -> Maka ditolak tidak bisa update -> Mengembalikan nilai balik
         * b. Jika Belum Terbit Nomor Order maka -> Update FtSalesh -> Hapus Item -> Insert Lagi
         */
        var ftSaleshEntityResult : FtSaleshRes

        val ftSaleshExisting = getFtSaleshUseCase.findBySourceIdAndCreated(ftSaleshEntityNew.sourceId, ftSaleshEntityNew.created)
        if (ftSaleshExisting.refno == 0L) {
            ftSaleshEntityResult = getFtSaleshUseCase.save(ftSaleshEntityNew.toDomain()).toResponse()
            //            System.out.println("Masuk Kesini 1 " + ftSaleshExisting.toString());
        } else {
            if (ftSaleshExisting.orderno.trim { it <= ' ' }.toLowerCase().contains("new") || ftSaleshExisting.orderno.trim { it <= ' ' } == "") {
                if (ftSaleshExisting.refno > 0 && ftSaleshExisting.sourceId > 0) { //source menunjukkan Asal dari Source
//                System.out.println("Masuk Kesini 2");

//                ftSalesdItemsJPARepository.deleteByFtSalesh(ftSaleshExisting.getRefno());
                    getFtSalesdItemsUseCase.deleteInBatch(getFtSalesdItemsUseCase.findByParent(ftSaleshExisting.refno))
                    ftSaleshEntityNew.refno = ftSaleshExisting.refno
                    getFtSaleshUseCase.save(ftSaleshEntityNew.toDomain())
                }
                ftSaleshEntityResult = ftSaleshEntityNew
            } else {
                ftSaleshEntityResult = ftSaleshExisting.toResponse()
                //                System.out.println("Masuk Kesini 3");
            }
        }
        return ftSaleshEntityResult
    }

    @RequestMapping(value = ["/rest/createFtSaleshTest"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshTest(@RequestBody ftSaleshEntityNew: FtSaleshRes): FtSaleshRes {
        ftSaleshEntityNew.refno = 0 //Pastikan ID nya nol untuk Create Baru
        return getFtSaleshUseCase.save(ftSaleshEntityNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/updateFtSalesh/{id}"], method = [RequestMethod.PUT])
    fun updateFtSaleshInfo(@PathVariable("id") id: Long?, @RequestBody ftSaleshEntityUpdated: FtSaleshRes?): FtSaleshRes {
        val ftSalesh = getFtSaleshUseCase.findByRefno(id!!)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSaleshEntityUpdated != null) {
            ftSaleshEntityUpdated.refno = ftSalesh.refno
            if (ftSalesh.fdivisionBean > 0) ftSaleshEntityUpdated.fdivisionBean = ftSalesh.fdivisionBean
            getFtSaleshUseCase.save(ftSaleshEntityUpdated.toDomain()).toResponse()
            return ftSaleshEntityUpdated
        }
        return ftSalesh.toResponse()
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesh(@PathVariable("id") id: Long?): FtSaleshRes? {
        val ftSalesh = getFtSaleshUseCase.findByRefno(id!!)
        if (ftSalesh.refno >0) {
            getFtSaleshUseCase.delete(ftSalesh)
        }
        return ftSalesh.toResponse()
    }

    /**
     * EXTRA QUERY
     */
    companion object {
        private val logger = LoggerFactory.getLogger(FtSaleshRestController::class.java)
    }
}