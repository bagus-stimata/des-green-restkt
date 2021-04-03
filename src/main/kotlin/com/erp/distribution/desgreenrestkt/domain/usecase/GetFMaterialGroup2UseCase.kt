package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FMaterialGroup2
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialGroup2Repo
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialGroup2Res
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFMaterialGroup2UseCase @Autowired constructor(
    val fMaterialGroup2Repo: FMaterialGroup2Repo
) {

    fun findById(id: Int): FMaterialGroup2 {
        return fMaterialGroup2Repo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FMaterialGroup2Res {
        return fMaterialGroup2Repo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FMaterialGroup2> {
        return  fMaterialGroup2Repo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FMaterialGroup2Res> {
        return  fMaterialGroup2Repo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FMaterialGroup2> {
        return fMaterialGroup2Repo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FMaterialGroup2Res> {
        return fMaterialGroup2Repo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FMaterialGroup2> {
        return  fMaterialGroup2Repo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FMaterialGroup2Res> {
        return  fMaterialGroup2Repo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByParent(fparentBean: Int): List<FMaterialGroup2> {
        return  fMaterialGroup2Repo.findByParent(fparentBean).map {
            it.toDomain()
        }
    }
    fun findByParentRes(fparentBean: Int): List<FMaterialGroup2Res> {
        return  fMaterialGroup2Repo.findByParent(fparentBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByListOfParent(listFMaterialGroup1Bean: List<Int>): List<FMaterialGroup2> {
        return  fMaterialGroup2Repo.findByListOfParent(listFMaterialGroup1Bean).map {
            it.toDomain()
        }
    }
    fun findByListOfParentRes(listFMaterialGroup1Bean: List<Int>): List<FMaterialGroup2Res> {
        return  fMaterialGroup2Repo.findByListOfParent(listFMaterialGroup1Bean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fMaterialGroup2: FMaterialGroup2): FMaterialGroup2 {
        return fMaterialGroup2Repo.save(fMaterialGroup2.toEntity()).toDomain()
    }
    fun saveAll(listFMaterialGroup2: List<FMaterialGroup2>): List<FMaterialGroup2> {
        return fMaterialGroup2Repo.saveAll(listFMaterialGroup2.map { it.toEntity() }).map {
            it.toDomain()
        }
    }

    fun delete(fMaterialGroup2: FMaterialGroup2) {
        return fMaterialGroup2Repo.delete(fMaterialGroup2.toEntity())
    }
    fun deleteInBatch(listFMaterialGroup2: List<FMaterialGroup2>) {
        return fMaterialGroup2Repo.deleteInBatch(listFMaterialGroup2.map { it.toEntity() })
    }


}