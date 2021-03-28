package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCompanyEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCompanyJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FCompanyRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FCompanyRepoImpl @Autowired constructor(
    val fCompanyJPARepository: FCompanyJPARepository
) :FCompanyRepo {
    override fun findById(id: Int): FCompanyEntity {
        return fCompanyJPARepository.findById(id).get()
    }

    override fun findAll(): List<FCompanyEntity> {
        return fCompanyJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FCompanyEntity> {
        return fCompanyJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FCompanyEntity> {
        return fCompanyJPARepository.findByKode1(kode1)
    }

    override fun save(fCompanyEntity: FCompanyEntity): FCompanyEntity {
        return fCompanyJPARepository.save(fCompanyEntity)
    }
    override fun saveAll(listFCompany: List<FCompanyEntity>): List<FCompanyEntity> {
        return fCompanyJPARepository.saveAll(listFCompany)
    }


    override fun delete(fCompanyEntity: FCompanyEntity) {
        return fCompanyJPARepository.delete(fCompanyEntity)
    }
    override fun deleteInBatch(listFCompany: List<FCompanyEntity>) {
        return fCompanyJPARepository.deleteInBatch(listFCompany)
    }

}