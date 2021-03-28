package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FMaterialGroup2
import com.erp.distribution.desgreenrestkt.domain.model.FMaterialGroup3
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialGroup3Repo
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialGroup3Res
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFMaterialGroup3UseCase @Autowired constructor(
    val fMaterialGroup3Repo: FMaterialGroup3Repo
) {

    fun findById(id: Int): FMaterialGroup3 {
        return fMaterialGroup3Repo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FMaterialGroup3Res {
        return fMaterialGroup3Repo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FMaterialGroup3> {
        return  fMaterialGroup3Repo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FMaterialGroup3Res> {
        return  fMaterialGroup3Repo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FMaterialGroup3> {
        return fMaterialGroup3Repo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FMaterialGroup3Res> {
        return fMaterialGroup3Repo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FMaterialGroup3> {
        return  fMaterialGroup3Repo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FMaterialGroup3Res> {
        return  fMaterialGroup3Repo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByParent(fparentBean: Int): List<FMaterialGroup3> {
        return  fMaterialGroup3Repo.findByParent(fparentBean).map {
            it.toDomain()
        }
    }
    fun findByParentRes(fparentBean: Int): List<FMaterialGroup3Res> {
        return  fMaterialGroup3Repo.findByParent(fparentBean).map {
            it.toDomain().toResponse()
        }
    }

    fun findByListOfParent(listFMaterialGroup2: List<Int>): List<FMaterialGroup3> {
        return  fMaterialGroup3Repo.findByListOfParent(listFMaterialGroup2).map {
            it.toDomain()
        }
    }
    fun findByListOfParentRes(listFMaterialGroup2: List<Int>): List<FMaterialGroup3Res> {
        return  fMaterialGroup3Repo.findByListOfParent(listFMaterialGroup2).map {
            it.toDomain().toResponse()
        }
    }


    fun save(fMaterialGroup3: FMaterialGroup3): FMaterialGroup3 {
        return fMaterialGroup3Repo.save(fMaterialGroup3.toEntity()).toDomain()
    }
    fun saveAll(listFMaterialGroup3: List<FMaterialGroup3>): List<FMaterialGroup3> {
        return fMaterialGroup3Repo.saveAll(listFMaterialGroup3.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fMaterialGroup3: FMaterialGroup3) {
        return fMaterialGroup3Repo.delete(fMaterialGroup3.toEntity())
    }
    fun deleteInBatch(listFMaterialGroup3: List<FMaterialGroup3>) {
        return fMaterialGroup3Repo.deleteInBatch(listFMaterialGroup3.map { it.toEntity() })
    }


}