package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialSalesBrandEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialSalesBrandJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialSalesBrandRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FMaterialSalesBrandRepoImpl @Autowired constructor(
    val fMaterialSalesBrandJPARepository: FMaterialSalesBrandJPARepository
) :FMaterialSalesBrandRepo {
    override fun findById(id: Int): FMaterialSalesBrandEntity {
        return fMaterialSalesBrandJPARepository.findById(id).get()
    }

    override fun findAll(): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity): FMaterialSalesBrandEntity {
        return fMaterialSalesBrandJPARepository.save(fMaterialSalesBrandEntity)
    }
    override fun saveAll(listFMaterialSalesBrand: List<FMaterialSalesBrandEntity>): List<FMaterialSalesBrandEntity> {
        return fMaterialSalesBrandJPARepository.saveAll(listFMaterialSalesBrand)
    }


    override fun delete(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity) {
        return fMaterialSalesBrandJPARepository.delete(fMaterialSalesBrandEntity)
    }
    override fun deleteInBatch(listFMaterialSalesBrand: List<FMaterialSalesBrandEntity>) {
        return fMaterialSalesBrandJPARepository.deleteInBatch(listFMaterialSalesBrand)
    }

}