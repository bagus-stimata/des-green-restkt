package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FMaterialGroup1
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialGroup1Repo
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialGroup1Res
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFMaterialGroup1UseCase @Autowired constructor(
    val fMaterialGroup1Repo: FMaterialGroup1Repo
) {

    fun findById(id: Int): FMaterialGroup1 {
        return fMaterialGroup1Repo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FMaterialGroup1Res {
        return fMaterialGroup1Repo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FMaterialGroup1> {
        return  fMaterialGroup1Repo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FMaterialGroup1Res> {
        return  fMaterialGroup1Repo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FMaterialGroup1> {
        return fMaterialGroup1Repo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FMaterialGroup1Res> {
        return fMaterialGroup1Repo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FMaterialGroup1> {
        return  fMaterialGroup1Repo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FMaterialGroup1Res> {
        return  fMaterialGroup1Repo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FMaterialGroup1> {
        return  fMaterialGroup1Repo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FMaterialGroup1Res> {
        return  fMaterialGroup1Repo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByCompany(fcompanyBean: Int): List<FMaterialGroup1> {
        return  fMaterialGroup1Repo.findByCompany(fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByCompanyRes(fcompanyBean: Int): List<FMaterialGroup1Res> {
        return  fMaterialGroup1Repo.findByCompany(fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialGroup1> {
        return  fMaterialGroup1Repo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialGroup1Res> {
        return  fMaterialGroup1Repo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fMaterialGroup1: FMaterialGroup1): FMaterialGroup1 {
        return fMaterialGroup1Repo.save(fMaterialGroup1.toEntity()).toDomain()
    }
    fun saveAll(listFMaterialGroup1: List<FMaterialGroup1>): List<FMaterialGroup1> {
        return fMaterialGroup1Repo.saveAll(listFMaterialGroup1.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fMaterialGroup1: FMaterialGroup1) {
        return fMaterialGroup1Repo.delete(fMaterialGroup1.toEntity())
    }
    fun deleteInBatch(listFMaterialGroup1: List<FMaterialGroup1>) {
        return fMaterialGroup1Repo.deleteInBatch(listFMaterialGroup1.map { it.toEntity() })
    }


}