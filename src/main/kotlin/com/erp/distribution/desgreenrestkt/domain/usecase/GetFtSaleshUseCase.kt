package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FtSalesh
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FtSaleshRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FtSaleshRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetFtSaleshUseCase @Autowired constructor(
    val ftSaleshRepo: FtSaleshRepo
) {

    fun findByRefno(refno: Long): FtSalesh {
        return ftSaleshRepo.findByRefno(refno).toDomain()
    }
    fun findByRefnoRes(refno: Long): FtSaleshRes {
        return ftSaleshRepo.findByRefno(refno).toDomain().toResponse()
    }
    fun findByOrderno(orderno: String): List<FtSalesh> {
        return  ftSaleshRepo.findByOrderno(orderno).map {
            it.toDomain()
        }
    }
    fun findByOrdernoRes(orderno: String): List<FtSaleshRes> {
        return  ftSaleshRepo.findByOrderno(orderno).map {
            it.toDomain().toResponse()
        }
    }
    fun findByInvoiceno(invoiceno: String): List<FtSalesh> {
        return  ftSaleshRepo.findByInvoiceno(invoiceno).map {
            it.toDomain()
        }
    }
    fun findByInvoicenoRes(invoiceno: String): List<FtSaleshRes> {
        return  ftSaleshRepo.findByInvoiceno(invoiceno).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FtSalesh> {
        return ftSaleshRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FtSaleshRes> {
        return ftSaleshRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(orderno: String, invoiceno: String): List<FtSalesh>{
        return ftSaleshRepo.findAll(orderno, invoiceno).map {
            it.toDomain()
        }
    }
    fun findAllRes(orderno: String, invoiceno: String): List<FtSaleshRes>{
        return ftSaleshRepo.findAll(orderno, invoiceno).map {
            it.toDomain().toResponse()
        }
    }


    fun findByDivision(fdivisionBean: Int): List<FtSalesh> {
        return  ftSaleshRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FtSaleshRes> {
        return  ftSaleshRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(ftSalesh: FtSalesh): FtSalesh {
        return ftSaleshRepo.save(ftSalesh.toEntity()).toDomain()
    }
    fun saveAll(listFtSalesh: List<FtSalesh>): List<FtSalesh> {
        return ftSaleshRepo.saveAll(listFtSalesh.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(ftSalesh: FtSalesh) {
        return ftSaleshRepo.delete(ftSalesh.toEntity())
    }
    fun deleteInBatch(listFtSalesh: List<FtSalesh>) {
        return ftSaleshRepo.deleteInBatch(listFtSalesh.map { it.toEntity() })
    }


    /**
     * TAMBAHAN
     */

    fun findByDivision(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSalesh> {
        return  ftSaleshRepo.findByDivision(fdivisionBean, orderDateFrom, orderDateTo, invoiceDateFrom, invoiceDateTo).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSaleshRes> {
        return  ftSaleshRepo.findByDivision(fdivisionBean, orderDateFrom, orderDateTo, invoiceDateFrom, invoiceDateTo).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndOrderDate(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date): List<FtSalesh> {
        return  ftSaleshRepo.findByDivisionAndOrderDate(fdivisionBean, orderDateFrom, orderDateTo).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndInvoiceDate(fdivisionBean: Int, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSalesh> {
        return  ftSaleshRepo.findByDivisionAndInvoiceDate(fdivisionBean, invoiceDateFrom, invoiceDateTo).map {
            it.toDomain()
        }
    }

    fun findBySourceIdAndCreated(sourceId: Long, created: Date): FtSalesh {
        return  ftSaleshRepo.findBySourceIdAndCreated(sourceId, created).toDomain()
    }


}