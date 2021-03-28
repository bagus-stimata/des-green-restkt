package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup1Entity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialGroup1JPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialGroup1Repo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FMaterialGroup1RepoImpl @Autowired constructor(
    val fMaterialGroup1JPARepository: FMaterialGroup1JPARepository
) :FMaterialGroup1Repo {
    override fun findById(id: Int): FMaterialGroup1Entity {
        return fMaterialGroup1JPARepository.findById(id).get()
    }

    override fun findAll(): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fMaterialGroup1Entity: FMaterialGroup1Entity): FMaterialGroup1Entity {
        return fMaterialGroup1JPARepository.save(fMaterialGroup1Entity)
    }
    override fun saveAll(listFMaterialGroup1: List<FMaterialGroup1Entity>): List<FMaterialGroup1Entity> {
        return fMaterialGroup1JPARepository.saveAll(listFMaterialGroup1)
    }


    override fun delete(fMaterialGroup1Entity: FMaterialGroup1Entity) {
        return fMaterialGroup1JPARepository.delete(fMaterialGroup1Entity)
    }
    override fun deleteInBatch(listFMaterialGroup1: List<FMaterialGroup1Entity>) {
        return fMaterialGroup1JPARepository.deleteInBatch(listFMaterialGroup1)
    }

}