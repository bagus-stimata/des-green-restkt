package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSaleshJPARepository
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumStatusPengiriman
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeFakturJual
import com.erp.distribution.desgreenrestkt.domain.repository.FtSaleshRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*


@Service
class FtSaleshRepoImpl @Autowired constructor(
    val ftSaleshJPARepository: FtSaleshJPARepository
) :FtSaleshRepo {
    override fun findByRefno(refno: Long): FtSaleshEntity {
        return ftSaleshJPARepository.findByRefno(refno)
    }

    override fun findAll(): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findAll()
    }

    override fun findAll(orderno: String, invoiceno: String): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findAll(orderno, invoiceno)
    }

    override fun findByOrderno(orderno: String): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findByOrderno(orderno)
    }

    override fun findByInvoiceno(invoiceno: String): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findByInvoiceno(invoiceno)
    }

    override fun findByDivision(fdivisionBean: Int): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findAllByDivision(fdivisionBean)
    }
    override fun findByDivision(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findAllByDivision(fdivisionBean, orderDateFrom, orderDateTo, invoiceDateFrom, invoiceDateTo)
    }
    override fun findByDivisionAndOrderDate(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findAllByDivisionAndOrderDate(fdivisionBean, orderDateFrom, orderDateTo)
    }
    override fun findByDivisionAndInvoiceDate(fdivisionBean: Int, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findAllByDivisionAndInvoiceDate(fdivisionBean, invoiceDateFrom, invoiceDateTo)
    }

    override fun findBySourceIdAndCreated(sourceId: Long, created: Date): FtSaleshEntity {
        return ftSaleshJPARepository.findBySourceIdAndCreated(sourceId, created).orElse(FtSaleshEntity())
    }

    override fun findBySourceId(sourceId: Long): List<FtSaleshEntity> {
        return ftSaleshJPARepository.findBySourceId(sourceId)
    }

    override fun findBySourceIdAndDivisionAndSalesmanAndCustomerAndWarehouse(
        sourceId: Long,
        fdivisionBean: Int,
        fsalesmanBean: Int,
        fcustomerBean: Int,
        fwarehouseBean: Int
    ): FtSaleshEntity {
        return ftSaleshJPARepository.findBySourceIdAndDivisionAndSalesmanAndCustomerAndWarehouse(sourceId, fdivisionBean, fsalesmanBean, fcustomerBean, fwarehouseBean).orElse(FtSaleshEntity())
    }


    override fun save(ftSaleshEntity: FtSaleshEntity): FtSaleshEntity {
        return ftSaleshJPARepository.save(ftSaleshEntity)
    }
    override fun saveAll(listFtSalesh: List<FtSaleshEntity>): List<FtSaleshEntity> {
        return ftSaleshJPARepository.saveAll(listFtSalesh)
    }


    override fun delete(ftSaleshEntity: FtSaleshEntity) {
        return ftSaleshJPARepository.delete(ftSaleshEntity)
    }
    override fun deleteInBatch(listFtSalesh: List<FtSaleshEntity>) {
        return ftSaleshJPARepository.deleteInBatch(listFtSalesh)
    }

    override fun findAllTotalSales(
        listTipeFaktur: List<EnumTipeFakturJual>,
        dateFrom: Date,
        dateTo: Date,
        listFsalesmanBean: List<Int>,
        listStatusPengiriman: List<EnumStatusPengiriman>
    ): Double {

//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        val strDateFrom = sdf.format(dateFrom)
//        val strDateTo = sdf.format(dateTo)
//        println("hello >> ${sdf.format(dateFrom)} and ${sdf.format(dateTo)} | ${sdf.parse(strDateFrom)} and ${sdf.parse(strDateFrom)} ")
//
//        val cal = Calendar.getInstance()
//        cal.time = dateFrom
//        cal.add(Calendar.MONTH, -1)

        return ftSaleshJPARepository.findAllTotalSales( listTipeFaktur, dateFrom, dateTo, listFsalesmanBean, listStatusPengiriman).orElse(0.0)



    }

}