package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSubAreaEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSubAreaJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FSubAreaRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FSubAreaRepoImpl @Autowired constructor(
    val fSubAreaJPARepository: FSubAreaJPARepository
) :FSubAreaRepo {
    override fun findById(id: Int): FSubAreaEntity {
        return fSubAreaJPARepository.findById(id).get()
    }

    override fun findAll(): List<FSubAreaEntity> {
        return fSubAreaJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FSubAreaEntity> {
        return fSubAreaJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FSubAreaEntity> {
        return fSubAreaJPARepository.findByKode1(kode1)
    }

    override fun findByParent(fparentBean: Int): List<FSubAreaEntity> {
        return fSubAreaJPARepository.findAllByParent(fparentBean)
    }
    override fun findByListOfParent(listFArea: List<Int>): List<FSubAreaEntity> {
        return fSubAreaJPARepository.findAllByListParent(listFArea)
    }

    override fun save(fSubAreaEntity: FSubAreaEntity): FSubAreaEntity {
        return fSubAreaJPARepository.save(fSubAreaEntity)
    }
    override fun saveAll(listFSubArea: List<FSubAreaEntity>): List<FSubAreaEntity> {
        return fSubAreaJPARepository.saveAll(listFSubArea)
    }


    override fun delete(fSubAreaEntity: FSubAreaEntity) {
        return fSubAreaJPARepository.delete(fSubAreaEntity)
    }
    override fun deleteInBatch(listFSubArea: List<FSubAreaEntity>) {
        return fSubAreaJPARepository.deleteInBatch(listFSubArea)
    }

}