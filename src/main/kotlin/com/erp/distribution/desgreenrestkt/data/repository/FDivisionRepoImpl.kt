package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FDivisionEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FDivisionJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FDivisionRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FDivisionRepoImpl @Autowired constructor(
    val fDivisionJPARepository: FDivisionJPARepository
) :FDivisionRepo {
    override fun findById(id: Int): FDivisionEntity {
        return fDivisionJPARepository.findById(id).get()
    }

    override fun findAll(): List<FDivisionEntity> {
        return fDivisionJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FDivisionEntity> {
        return fDivisionJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FDivisionEntity> {
        return fDivisionJPARepository.findByKode1(kode1)
    }

    override fun findByCompany(fcompanyBean: Int): List<FDivisionEntity> {
        return fDivisionJPARepository.findAllByCompanyId(fcompanyBean)
    }

    override fun save(fDivisionEntity: FDivisionEntity): FDivisionEntity {
        return fDivisionJPARepository.save(fDivisionEntity)
    }
    override fun saveAll(listFDivision: List<FDivisionEntity>): List<FDivisionEntity> {
        return fDivisionJPARepository.saveAll(listFDivision)
    }


    override fun delete(fDivisionEntity: FDivisionEntity) {
        return fDivisionJPARepository.delete(fDivisionEntity)
    }
    override fun deleteInBatch(listFDivision: List<FDivisionEntity>) {
        return fDivisionJPARepository.deleteInBatch(listFDivision)
    }

}