package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FAreaEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FAreaJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialJPARepository
import com.erp.distribution.desgreenrestkt.domain.model.FArea
import com.erp.distribution.desgreenrestkt.domain.repository.FAreaRepo
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


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

    override fun findAllByDivision(fdivisionBean: Int): List<FAreaEntity> {
        return fAreaJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FAreaEntity> {
        return fAreaJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fAreaEntity: FAreaEntity): FAreaEntity? {
        return fAreaJPARepository.save(fAreaEntity)
    }
    override fun saveAll(fAreaEntity: FAreaEntity): FAreaEntity? {
        return fAreaJPARepository.save
    }


    override fun delete(fAreaEntity: FAreaEntity) {
        return fAreaJPARepository.delete(fAreaEntity)
    }
    override fun deleteInBatch(listFArea: List<FAreaEntity>) {
        return fAreaJPARepository.deleteInBatch(listFArea)
    }

//    @Autowired
//    lateinit var fAreaJPARepository: FAreaJPARepository


}