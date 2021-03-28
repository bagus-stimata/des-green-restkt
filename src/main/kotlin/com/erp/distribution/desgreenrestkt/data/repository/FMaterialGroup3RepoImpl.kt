package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialGroup3JPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialGroup3Repo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FMaterialGroup3RepoImpl @Autowired constructor(
    val fMaterialGroup3JPARepository: FMaterialGroup3JPARepository
) :FMaterialGroup3Repo {
    override fun findById(id: Int): FMaterialGroup3Entity {
        return fMaterialGroup3JPARepository.findById(id).get()
    }

    override fun findAll(): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository.findByKode1(kode1)
    }

    override fun findByParent(fparentBean: Int): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository.findAllByParentId(fparentBean)
    }
    override fun findByListOfParent(listFMaterialGroup2: List<Int>): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository.findAllByListOfParent(listFMaterialGroup2)
    }

    override fun save(fMaterialGroup3Entity: FMaterialGroup3Entity): FMaterialGroup3Entity {
        return fMaterialGroup3JPARepository.save(fMaterialGroup3Entity)
    }
    override fun saveAll(listFMaterialGroup3: List<FMaterialGroup3Entity>): List<FMaterialGroup3Entity> {
        return fMaterialGroup3JPARepository.saveAll(listFMaterialGroup3)
    }


    override fun delete(fMaterialGroup3Entity: FMaterialGroup3Entity) {
        return fMaterialGroup3JPARepository.delete(fMaterialGroup3Entity)
    }
    override fun deleteInBatch(listFMaterialGroup3: List<FMaterialGroup3Entity>) {
        return fMaterialGroup3JPARepository.deleteInBatch(listFMaterialGroup3)
    }

}