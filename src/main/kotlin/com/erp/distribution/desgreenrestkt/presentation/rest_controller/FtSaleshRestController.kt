package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSalesdItemsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSaleshJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesh
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
    fun getFtSaleshById(@PathVariable("id") id: Long): FtSalesh {
        return ftSaleshJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFtSalesh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val alltSalesh: List<FtSalesh>
        get() = ftSaleshJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFtSaleshByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFtSaleshByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FtSalesh> {
        return ftSaleshJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFtSalesh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSalesh(@RequestBody ftSaleshNew: FtSalesh): FtSalesh {
        ftSaleshNew.fcustomerShipToBean = ftSaleshNew.fcustomerBean

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
        return ftSaleshJPARepository!!.save(ftSaleshNew)
    }

    @RequestMapping(value = ["/rest/createFtSaleshFromAndroid"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshFromAndroid(@RequestBody ftSaleshNew: FtSalesh): FtSalesh {
        ftSaleshNew.fcustomerShipToBean = ftSaleshNew.fcustomerBean

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
        var ftSaleshResult : FtSalesh

        val ftSaleshExisting = ftSaleshJPARepository!!.findBySourceIdAndCreated(ftSaleshNew.sourceId, ftSaleshNew.created).get()
        if (ftSaleshExisting.refno == 0L) {
            ftSaleshResult = ftSaleshJPARepository!!.save(ftSaleshNew)
            //            System.out.println("Masuk Kesini 1 " + ftSaleshExisting.toString());
        } else {
            if (ftSaleshExisting.orderno.trim { it <= ' ' }.toLowerCase().contains("new") || ftSaleshExisting.orderno.trim { it <= ' ' } == "") {
                if (ftSaleshExisting.refno > 0 && ftSaleshExisting.sourceId > 0) { //source menunjukkan Asal dari Source
//                System.out.println("Masuk Kesini 2");

//                ftSalesdItemsJPARepository.deleteByFtSalesh(ftSaleshExisting.getRefno());
                    ftSalesdItemsJPARepository!!.deleteInBatch(ftSalesdItemsJPARepository!!.findAllByFtSaleshBean(ftSaleshExisting.refno))
                    ftSaleshNew.refno = ftSaleshExisting.refno
                    ftSaleshJPARepository!!.save(ftSaleshNew)
                }
                ftSaleshResult = ftSaleshNew
            } else {
                ftSaleshResult = ftSaleshExisting
                //                System.out.println("Masuk Kesini 3");
            }
        }
        return ftSaleshResult
    }

    @RequestMapping(value = ["/rest/createFtSaleshTest"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshTest(@RequestBody ftSaleshNew: FtSalesh): FtSalesh {
        ftSaleshNew.refno = 0 //Pastikan ID nya nol untuk Create Baru
        return ftSaleshJPARepository!!.save(ftSaleshNew)
    }

    @RequestMapping(value = ["/rest/updateFtSalesh/{id}"], method = [RequestMethod.PUT])
    fun updateFtSaleshInfo(@PathVariable("id") id: Long?, @RequestBody ftSaleshUpdated: FtSalesh?): FtSalesh {
        val ftSalesh = ftSaleshJPARepository!!.findByRefno(id!!)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSaleshUpdated != null) {
            ftSaleshUpdated.refno = ftSalesh.refno
            if (ftSalesh.fdivisionBean > 0) ftSaleshUpdated.fdivisionBean = ftSalesh.fdivisionBean
            ftSaleshJPARepository!!.save(ftSaleshUpdated)
            return ftSaleshUpdated
        }
        return ftSalesh
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFtSalesh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFtSalesh(@PathVariable("id") id: Long?): FtSalesh? {
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