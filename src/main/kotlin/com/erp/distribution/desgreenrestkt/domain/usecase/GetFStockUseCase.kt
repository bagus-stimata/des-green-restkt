package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.FStockEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FStock
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FStockRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FStockRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetFStockUseCase @Autowired constructor(
    val fStockRepo: FStockRepo
) {

    fun findById(id: Int): FStock {
        return fStockRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FStockRes {
        return fStockRepo.findById(id).toDomain().toResponse()
    }
    fun findByFMaterial(fmaterialBean: Int): List<FStock> {
        return  fStockRepo.findByFMaterial(fmaterialBean).map {
            it.toDomain()
        }
    }
    fun findByFMaterialRes(fmaterialBean: Int): List<FStockRes> {
        return  fStockRepo.findByFMaterial(fmaterialBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByFMaterial(fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStock> {
        return  fStockRepo.findByFMaterial(fmaterialBean, stockDateFrom, stockDateTo).map {
            it.toDomain()
        }
    }
    fun findByFMaterialRes(fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockRes> {
        return  fStockRepo.findByFMaterial(fmaterialBean, stockDateFrom, stockDateTo).map {
            it.toDomain().toResponse()
        }
    }
    fun findByFWarehouse(fwarehouseBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStock> {
        return  fStockRepo.findByFWarehouse(fwarehouseBean, stockDateFrom, stockDateTo).map {
            it.toDomain()
        }
    }
    fun findByFWarehouseRes(fwarehouseBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockRes> {
        return  fStockRepo.findByFWarehouse(fwarehouseBean, stockDateFrom, stockDateTo).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(fwarehouseBean: Int, fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStock> {
        return  fStockRepo.findAll(fwarehouseBean, fmaterialBean, stockDateFrom, stockDateTo).map {
            it.toDomain()
        }
    }
    fun findAllRes(fwarehouseBean: Int, fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockRes> {
        return  fStockRepo.findAll(fwarehouseBean, fmaterialBean, stockDateFrom, stockDateTo).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fStock: FStock): FStock {
        return fStockRepo.save(fStock.toEntity()).toDomain()
    }
    fun saveAll(listFStock: List<FStock>): List<FStock> {
        return fStockRepo.saveAll(listFStock.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fStock: FStock) {
        return fStockRepo.delete(fStock.toEntity())
    }
    fun deleteInBatch(listFStock: List<FStock>) {
        return fStockRepo.deleteInBatch(listFStock.map { it.toEntity() })
    }


}