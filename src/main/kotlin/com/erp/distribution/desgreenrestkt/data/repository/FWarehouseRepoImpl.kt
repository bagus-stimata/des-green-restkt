package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FWarehouseEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FWarehouseJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FWarehouseRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FWarehouseRepoImpl @Autowired constructor(
    val fWarehouseJPARepository: FWarehouseJPARepository
) :FWarehouseRepo {
    override fun findById(id: Int): FWarehouseEntity {
        return fWarehouseJPARepository.findById(id).get()
    }

    override fun findAll(): List<FWarehouseEntity> {
        return fWarehouseJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FWarehouseEntity> {
        return fWarehouseJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FWarehouseEntity> {
        return fWarehouseJPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FWarehouseEntity> {
        return fWarehouseJPARepository.findAllByDivision(fdivisionBean)
    }
    override fun findByCompany(fcompanyBean: Int): List<FWarehouseEntity> {
        return fWarehouseJPARepository.findAllByCompany(fcompanyBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FWarehouseEntity> {
        return fWarehouseJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fWarehouseEntity: FWarehouseEntity): FWarehouseEntity {
        return fWarehouseJPARepository.save(fWarehouseEntity)
    }
    override fun saveAll(listFWarehouse: List<FWarehouseEntity>): List<FWarehouseEntity> {
        return fWarehouseJPARepository.saveAll(listFWarehouse)
    }


    override fun delete(fWarehouseEntity: FWarehouseEntity) {
        return fWarehouseJPARepository.delete(fWarehouseEntity)
    }
    override fun deleteInBatch(listFWarehouse: List<FWarehouseEntity>) {
        return fWarehouseJPARepository.deleteInBatch(listFWarehouse)
    }

}