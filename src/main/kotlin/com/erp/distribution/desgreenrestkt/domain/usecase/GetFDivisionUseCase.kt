package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FDivision
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FDivisionRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FDivisionRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFDivisionUseCase @Autowired constructor(
    val fDivisionRepo: FDivisionRepo
) {

    fun findById(id: Int): FDivision {
        return fDivisionRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FDivisionRes {
        return fDivisionRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FDivision> {
        return  fDivisionRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FDivisionRes> {
        return  fDivisionRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FDivision> {
        return fDivisionRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FDivisionRes> {
        return fDivisionRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FDivision> {
        return  fDivisionRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FDivisionRes> {
        return  fDivisionRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByCompany(fcompanyBean: Int): List<FDivision> {
        return  fDivisionRepo.findByCompany(fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByCompanyRes(fcompanyBean: Int): List<FDivisionRes> {
        return  fDivisionRepo.findByCompany(fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }
    fun save(fDivision: FDivision): FDivision {
        return fDivisionRepo.save(fDivision.toEntity()).toDomain()
    }
    fun saveAll(listFDivision: List<FDivision>): List<FDivision> {
        return fDivisionRepo.saveAll(listFDivision.map { it.toEntity() }).map {
            it.toDomain()
        }
    }

    fun delete(fDivision: FDivision) {
        return fDivisionRepo.delete(fDivision.toEntity())
    }
    fun deleteInBatch(listFDivision: List<FDivision>) {
        return fDivisionRepo.deleteInBatch(listFDivision.map { it.toEntity() })
    }


}