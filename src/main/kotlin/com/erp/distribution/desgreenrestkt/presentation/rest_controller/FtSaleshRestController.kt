package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model.FSalesman
import com.erp.distribution.desgreenrestkt.domain.model.FtSalesh
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumStatusPengiriman
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeFakturJual
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFSalesmanUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFtSalesdItemsUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FtSaleshRes
import com.erp.distribution.desgreenrestkt.presentation.model.toDomain
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class FtSaleshRestController @Autowired constructor(
    val getFtSaleshUseCase: GetFtSaleshUseCase,
    val getFtSalesdItemsUseCase: GetFtSalesdItemsUseCase,
    val getFSalesmanUseCase: GetFSalesmanUseCase
){


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
    fun createFtSalesh(@RequestBody ftSaleshResNew: FtSaleshRes): FtSaleshRes {
        ftSaleshResNew.fcustomerShipToBean = ftSaleshResNew.fcustomerBean

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
        return getFtSaleshUseCase.save(ftSaleshResNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/createFtSaleshFromAndroid"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshFromAndroid(@RequestBody ftSaleshResNew: FtSaleshRes): FtSaleshRes {
        ftSaleshResNew.fcustomerShipToBean = ftSaleshResNew.fcustomerBean

//        System.out.println(">> FtSalesh Export: "  + ftSaleshResNew.refno + " >> " + ftSaleshResNew.sourceId + " | " + ftSaleshResNew.fdivisionBean + " >> " +
//                ftSaleshResNew.fcustomerBean+ " >> " + ftSaleshResNew.fwarehouseBean  + " >> " + ftSaleshResNew.fsalesmanBean + " | " + ftSaleshResNew.created  + " >> End");
        /**
         * 1. Check tanggal Created dan sourceID
         * 2. Jika Tidak ada maka CREATE NEW
         * 3. Jika Sama maka
         * a. Jika Sudah Terbit Nomor Order -> Maka ditolak tidak bisa update -> Mengembalikan nilai balik
         * b. Jika Belum Terbit Nomor Order maka -> Update FtSalesh -> Hapus Item -> Insert Lagi
         */
        var ftSaleshResResult : FtSaleshRes = FtSaleshRes()

//        val ftSaleshExisting = getFtSaleshUseCase.findBySourceIdAndCreated(ftSaleshResNew.sourceId, ftSaleshResNew.created)
        var ftSaleshExisting = FtSalesh()

//        val list :List<FtSalesh> =  getFtSaleshUseCase.findBySourceId(ftSaleshResNew.sourceId)
//        if (list.size>0) ftSaleshExisting = list.get(0)


        ftSaleshExisting = getFtSaleshUseCase.findBySourceIdAndDivisionAndSalesmanAndCustomerAndWarehouse(ftSaleshResNew.sourceId, ftSaleshResNew.fdivisionBean,
                ftSaleshResNew.fsalesmanBean, ftSaleshResNew.fcustomerBean, ftSaleshResNew.fwarehouseBean)

        if (ftSaleshResNew.fexpedisiBean==0) ftSaleshResNew.fexpedisiBean = null
        if (ftSaleshResNew.driverBean==0) ftSaleshResNew.driverBean = null
        if (ftSaleshResNew.collectorBean==0) ftSaleshResNew.collectorBean = null
        if (ftSaleshResNew.fakturSOBean==0L) ftSaleshResNew.fakturSOBean = null
        if (ftSaleshResNew.fcustomerShipToBean==0) ftSaleshResNew.fcustomerShipToBean = ftSaleshResNew.fcustomerBean
        if (ftSaleshResNew.accAccountArKbBean==0) ftSaleshResNew.accAccountArKbBean = null
        if (ftSaleshResNew.accAccountFtSaleshCredit==0) ftSaleshResNew.accAccountFtSaleshCredit = null
//        if (ftSaleshResNew.fuangMuka_SOBean==0) ftSaleshResNew.fuangMuka_SOBean = null


        if (ftSaleshExisting.refno == 0L) {
//            System.out.println("Masuk Kesini 1 >> FtSalesh Export: "  + ftSaleshResNew.refno + " >> " + ftSaleshResNew.fdivisionBean + " >> " +
//                    ftSaleshResNew.fcustomerBean+ " >> " + ftSaleshResNew.fwarehouseBean  + " >> " + ftSaleshResNew.fsalesmanBean  + " >> End");

            return getFtSaleshUseCase.save(ftSaleshResNew.toDomain()).toResponse()

        } else {
            if (ftSaleshExisting.orderno.trim { it <= ' ' }.toLowerCase().contains("new") || ftSaleshExisting.orderno.trim { it <= ' ' } == "") {
                if (ftSaleshExisting.refno > 0 && ftSaleshExisting.sourceId > 0) { //source menunjukkan Asal dari Source

//                System.out.println("Masuk Kesini 2")

                    getFtSalesdItemsUseCase.deleteInBatch(getFtSalesdItemsUseCase.findByParent(ftSaleshExisting.refno))
                    ftSaleshResNew.refno = ftSaleshExisting.refno

                    getFtSaleshUseCase.save(ftSaleshResNew.toDomain())
                }
                ftSaleshResResult = ftSaleshResNew
            } else {
                ftSaleshResResult = ftSaleshExisting.toResponse()
//                System.out.println("Masuk Kesini 3")
            }
        }
        return ftSaleshResResult
    }

    @RequestMapping(value = ["/rest/createFtSaleshTest"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFtSaleshTest(@RequestBody ftSaleshResNew: FtSaleshRes): FtSaleshRes {
        ftSaleshResNew.refno = 0 //Pastikan ID nya nol untuk Create Baru
        return getFtSaleshUseCase.save(ftSaleshResNew.toDomain()).toResponse()
    }

    @RequestMapping(value = ["/rest/updateFtSalesh/{id}"], method = [RequestMethod.PUT])
    fun updateFtSaleshInfo(@PathVariable("id") id: Long?, @RequestBody ftSaleshResUpdated: FtSaleshRes?): FtSaleshRes {
        val ftSalesh = getFtSaleshUseCase.findByRefno(id!!)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (ftSaleshResUpdated != null) {
            ftSaleshResUpdated.refno = ftSalesh.refno
            if (ftSalesh.fdivisionBean > 0) ftSaleshResUpdated.fdivisionBean = ftSalesh.fdivisionBean
            getFtSaleshUseCase.save(ftSaleshResUpdated.toDomain()).toResponse()
            return ftSaleshResUpdated
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

    @RequestMapping(value = ["/rest/getAllTotalSalesByFSalesmanThisMonth/{fsalesmanBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllTotalSalesByFSalesmanThisMonth(@PathVariable("fsalesmanBean") fsalesmanBean: Int): Map<String, Double> {
        val mapResult: MutableMap<String, Double> = mutableMapOf()
        var fsalesman: FSalesman = FSalesman()
        try {
            fsalesman = getFSalesmanUseCase.findById(fsalesmanBean)
//            println(">> salemsan: ${fsalesman.spcode} ${fsalesman.spname}  ${fsalesman.targetSalesAmount} ")

            mapResult.put("targetEffCall", fsalesman.targetEffCall.toDouble())
            mapResult.put("targetSalesAmount", fsalesman.targetSalesAmount)
        }catch (ex: Exception){
            ex.printStackTrace()
        }

        val toleransiTanggal = 2;
        val calStart = Calendar.getInstance()
        calStart.time = Date()
        //Jika dibawah tangal 3 maka dianggap bulan sebelumnya
        if (calStart.get(Calendar.DATE) <= toleransiTanggal) calStart.add(Calendar.MONTH, -1)
        calStart.set(Calendar.DATE, calStart.getActualMinimum(Calendar.DATE))
        val calTo = Calendar.getInstance()
        calTo.time = calStart.time
        calTo.set(Calendar.DATE, calTo.getActualMaximum(Calendar.DATE))

        val listTipeFaktur: List<EnumTipeFakturJual> = listOf(EnumTipeFakturJual.F, EnumTipeFakturJual.SRV)
        val listTipeFakturRetur: List<EnumTipeFakturJual> = listOf(EnumTipeFakturJual.R)
        val listFsalesmanBean :List<Int> = listOf(fsalesmanBean)
        val listStatusPengiriman :List<EnumStatusPengiriman> = listOf(EnumStatusPengiriman.NOTA_OPEN, EnumStatusPengiriman.NOTA_BATAL, EnumStatusPengiriman.NOTA_PENDING, EnumStatusPengiriman.NOTA_TERKIRIM)
        val  listStatusPengirimanTerkirimSaja = listOf(EnumStatusPengiriman.NOTA_TERKIRIM)

        var salesThisMonth =  getFtSaleshUseCase.findAllTotalSales(listTipeFaktur, calStart.time , calTo.time, listFsalesmanBean, listStatusPengiriman)
        var salesThisMonthTerkirimSaja =  getFtSaleshUseCase.findAllTotalSales(listTipeFaktur, calStart.time , calTo.time, listFsalesmanBean, listStatusPengirimanTerkirimSaja)

        if (fsalesman.targetDipotongRetur){
            val returThisMonth =  getFtSaleshUseCase.findAllTotalSales(listTipeFakturRetur, calStart.time , calTo.time, listFsalesmanBean, listStatusPengiriman)
            salesThisMonth -= returThisMonth
            val returThisMonthTerkirimSaja =  getFtSaleshUseCase.findAllTotalSales(listTipeFakturRetur, calStart.time , calTo.time, listFsalesmanBean, listStatusPengiriman)
            salesThisMonthTerkirimSaja -= returThisMonthTerkirimSaja
        }

        mapResult.put("salesThisMonth", salesThisMonth)
        mapResult.put("salesThisMonthTerkirimSaja", salesThisMonthTerkirimSaja)

        try {
            val fsalesman: FSalesman = getFSalesmanUseCase.findById(fsalesmanBean)
//            println(">> salemsan: ${fsalesman.spcode} ${fsalesman.spname}  ${fsalesman.targetSalesAmount} ")

            mapResult.put("targetEffCall", fsalesman.targetEffCall.toDouble())
            mapResult.put("targetSalesAmount", fsalesman.targetSalesAmount)
        }catch (ex: Exception){
            ex.printStackTrace()
        }

        return mapResult
    }


    /**
     * EXTRA QUERY
     */
    companion object {
        private val logger = LoggerFactory.getLogger(FtSaleshRestController::class.java)
    }
}