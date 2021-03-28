package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FTaxEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FTaxJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FTaxRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FTaxRepoImpl @Autowired constructor(
    val fTaxJPARepository: FTaxJPARepository
) :FTaxRepo {
    override fun findById(id: Int): FTaxEntity {
        return fTaxJPARepository.findById(id).get()
    }

    override fun findAll(): List<FTaxEntity> {
        return fTaxJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FTaxEntity> {
        return fTaxJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FTaxEntity> {
        return fTaxJPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FTaxEntity> {
        return fTaxJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FTaxEntity> {
        return fTaxJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fTaxEntity: FTaxEntity): FTaxEntity {
        return fTaxJPARepository.save(fTaxEntity)
    }
    override fun saveAll(listFTax: List<FTaxEntity>): List<FTaxEntity> {
        return fTaxJPARepository.saveAll(listFTax)
    }


    override fun delete(fTaxEntity: FTaxEntity) {
        return fTaxJPARepository.delete(fTaxEntity)
    }
    override fun deleteInBatch(listFTax: List<FTaxEntity>) {
        return fTaxJPARepository.deleteInBatch(listFTax)
    }

}