package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FStockEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FStockJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FStockRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FStockRepoImpl @Autowired constructor(
    val fStockJPARepository: FStockJPARepository
) :FStockRepo {
    override fun findById(id: Int): FStockEntity {
        return fStockJPARepository.findById(id).get()
    }

    override fun findAll(): List<FStockEntity> {
        return fStockJPARepository.findAll()
    }

    override fun findByFMaterial(fmaterialBean: Int): List<FStockEntity> {
        return fStockJPARepository.findAllByFMaterial(fmaterialBean)
    }

    override fun findByFMaterial(fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity> {
        return fStockJPARepository.findAllByFMaterial(fmaterialBean, stockDateFrom, stockDateTo)
    }

    override fun findByFWarehouse(fwarehouseBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity> {
        return fStockJPARepository.findAllByFWarehouse(fwarehouseBean, stockDateFrom, stockDateTo)
    }

    override fun findAll(fwarehouseBean: Int, fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity> {
        return fStockJPARepository.findAll(fwarehouseBean, fmaterialBean, stockDateFrom, stockDateTo)
    }


    override fun save(fStockEntity: FStockEntity): FStockEntity {
        return fStockJPARepository.save(fStockEntity)
    }
    override fun saveAll(listFStock: List<FStockEntity>): List<FStockEntity> {
        return fStockJPARepository.saveAll(listFStock)
    }


    override fun delete(fStockEntity: FStockEntity) {
        return fStockJPARepository.delete(fStockEntity)
    }
    override fun deleteInBatch(listFStock: List<FStockEntity>) {
        return fStockJPARepository.deleteInBatch(listFStock)
    }

}