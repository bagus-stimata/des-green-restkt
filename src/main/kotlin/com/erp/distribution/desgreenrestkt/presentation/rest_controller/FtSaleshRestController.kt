package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSalesdItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSaleshJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FtSaleshRestController {
    @Autowired
    var ftSaleshJPARepository: FtSaleshJPARepository? = null

    @Autowired
    var ftSalesdItemsJPARepository: FtSalesdItemsJPARepository? = null

    @RequestMapping(value = ["/rest/getFtSaleshById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFtSaleshById(@PathVariable("id") id: Long): FtSaleshEntity {
        return ftSaleshJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSaleshEntities: List<FtSaleshEntity>
        get() = ftSaleshJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtSaleshByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSaleshByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtSaleshEntity> {
        return ftSaleshJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtSalesh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesh(@RequestBody ftSaleshEntityNew: FtSaleshEntity): FtSaleshEntity {
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
        return ftSaleshJPARepository!!.save(ftSaleshEntityNew)
    }

    @RequestMapping(value = ["/rest/createFtSaleshFromAndroid"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshFromAndroid(@RequestBody ftSaleshEntityNew: FtSaleshEntity): FtSaleshEntity {
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
        var ftSaleshEntityResult : FtSaleshEntity

        val ftSaleshExisting = ftSaleshJPARepository!!.findBySourceIdAndCreated(ftSaleshEntityNew.sourceId, ftSaleshEntityNew.created).get()
        if (ftSaleshExisting.refno == 0L) {
            ftSaleshEntityResult = ftSaleshJPARepository!!.save(ftSaleshEntityNew)
            //            System.out.println("Masuk Kesini 1 " + ftSaleshExisting.toString());
        } else {
            if (ftSaleshExisting.orderno.trim { it <= ' ' }.toLowerCase().contains("new") || ftSaleshExisting.orderno.trim { it <= ' ' } == "") {
                if (ftSaleshExisting.refno > 0 && ftSaleshExisting.sourceId > 0) { //source menunjukkan Asal dari Source
//                System.out.println("Masuk Kesini 2");

//                ftSalesdItemsJPARepository.deleteByFtSalesh(ftSaleshExisting.getRefno());
                    ftSalesdItemsJPARepository!!.deleteInBatch(ftSalesdItemsJPARepository!!.findAllByFtSaleshBean(ftSaleshExisting.refno))
                    ftSaleshEntityNew.refno = ftSaleshExisting.refno
                    ftSaleshJPARepository!!.save(ftSaleshEntityNew)
                }
                ftSaleshEntityResult = ftSaleshEntityNew
            } else {
                ftSaleshEntityResult = ftSaleshExisting
                //                System.out.println("Masuk Kesini 3");
            }
        }
        return ftSaleshEntityResult
    }

    @RequestMapping(value = ["/rest/createFtSaleshTest"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshTest(@RequestBody ftSaleshEntityNew: FtSaleshEntity): FtSaleshEntity {
        ftSaleshEntityNew.refno = 0 //Pastikan ID nya nol untuk Create Baru
        return ftSaleshJPARepository!!.save(ftSaleshEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFtSalesh/{id}"], method = [RequestMethod.PUT])
    fun updateFtSaleshInfo(@PathVariable("id") id: Long?, @RequestBody ftSaleshEntityUpdated: FtSaleshEntity?): FtSaleshEntity {
        val ftSalesh = ftSaleshJPARepository!!.findByRefno(id!!)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSaleshEntityUpdated != null) {
            ftSaleshEntityUpdated.refno = ftSalesh.refno
            if (ftSalesh.fdivisionBean > 0) ftSaleshEntityUpdated.fdivisionBean = ftSalesh.fdivisionBean
            ftSaleshJPARepository!!.save(ftSaleshEntityUpdated)
            return ftSaleshEntityUpdated
        }
        return ftSalesh
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesh(@PathVariable("id") id: Long?): FtSaleshEntity? {
        val ftSalesh = ftSaleshJPARepository!!.findByRefno(id!!)
        if (ftSalesh.refno >0) {
            ftSaleshJPARepository!!.delete(ftSalesh)
        }
        return ftSalesh
    }

    /**
     * EXTRA QUERY
     */
    companion object {
        private val logger = LoggerFactory.getLogger(FtSaleshRestController::class.java)
    }
}