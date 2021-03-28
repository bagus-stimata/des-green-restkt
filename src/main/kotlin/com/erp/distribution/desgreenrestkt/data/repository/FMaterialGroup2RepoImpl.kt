package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup2Entity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialGroup2JPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialGroup2Repo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FMaterialGroup2RepoImpl @Autowired constructor(
    val fMaterialGroup2JPARepository: FMaterialGroup2JPARepository
) :FMaterialGroup2Repo {
    override fun findById(id: Int): FMaterialGroup2Entity {
        return fMaterialGroup2JPARepository.findById(id).get()
    }

    override fun findAll(): List<FMaterialGroup2Entity> {
        return fMaterialGroup2JPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FMaterialGroup2Entity> {
        return fMaterialGroup2JPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FMaterialGroup2Entity> {
        return fMaterialGroup2JPARepository.findByKode1(kode1)
    }

    override fun findByParent(fparentBean: Int): List<FMaterialGroup2Entity> {
        return fMaterialGroup2JPARepository.findAllByParentId(fparentBean)
    }

    override fun save(fMaterialGroup2Entity: FMaterialGroup2Entity): FMaterialGroup2Entity {
        return fMaterialGroup2JPARepository.save(fMaterialGroup2Entity)
    }
    override fun saveAll(listFMaterialGroup2: List<FMaterialGroup2Entity>): List<FMaterialGroup2Entity> {
        return fMaterialGroup2JPARepository.saveAll(listFMaterialGroup2)
    }


    override fun delete(fMaterialGroup2Entity: FMaterialGroup2Entity) {
        return fMaterialGroup2JPARepository.delete(fMaterialGroup2Entity)
    }
    override fun deleteInBatch(listFMaterialGroup2: List<FMaterialGroup2Entity>) {
        return fMaterialGroup2JPARepository.deleteInBatch(listFMaterialGroup2)
    }

}