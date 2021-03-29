package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FAreaEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FAreaJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FAreaRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FAreaRepoImpl @Autowired constructor(
    val fAreaJPARepository: FAreaJPARepository
) :FAreaRepo {
    override fun findById(id: Int): FAreaEntity {
        return fAreaJPARepository.findById(id).get()
    }

    override fun findAll(): List<FAreaEntity> {
        return fAreaJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FAreaEntity> {
        return fAreaJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FAreaEntity> {
        return fAreaJPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FAreaEntity> {
        return fAreaJPARepository.findAllByDivision(fdivisionBean).orEmpty()
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FAreaEntity> {
        return fAreaJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fAreaEntity: FAreaEntity): FAreaEntity {
        return fAreaJPARepository.save(fAreaEntity)
    }
    override fun saveAll(listFArea: List<FAreaEntity>): List<FAreaEntity> {
        return fAreaJPARepository.saveAll(listFArea)
    }


    override fun delete(fAreaEntity: FAreaEntity) {
        return fAreaJPARepository.delete(fAreaEntity)
    }
    override fun deleteInBatch(listFArea: List<FAreaEntity>) {
        return fAreaJPARepository.deleteInBatch(listFArea)
    }

}