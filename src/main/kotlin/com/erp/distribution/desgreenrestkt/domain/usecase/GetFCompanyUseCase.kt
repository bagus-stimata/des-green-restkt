package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FCompany
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FCompanyRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FCompanyRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFCompanyUseCase @Autowired constructor(
    val fCompanyRepo: FCompanyRepo
) {

    fun findById(id: Int): FCompany {
        return fCompanyRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FCompanyRes {
        return fCompanyRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FCompany> {
        return  fCompanyRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FCompanyRes> {
        return  fCompanyRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FCompany> {
        return fCompanyRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FCompanyRes> {
        return fCompanyRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FCompany> {
        return  fCompanyRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FCompanyRes> {
        return  fCompanyRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }


    fun save(fCompany: FCompany): FCompany {
        return fCompanyRepo.save(fCompany.toEntity()).toDomain()
    }
    fun saveAll(listFCompany: List<FCompany>): List<FCompany> {
        return fCompanyRepo.saveAll(listFCompany.map { it.toEntity() }).map {
            it.toDomain()
        }
    }

    fun delete(fCompany: FCompany) {
        return fCompanyRepo.delete(fCompany.toEntity())
    }
    fun deleteInBatch(listFCompany: List<FCompany>) {
        return fCompanyRepo.deleteInBatch(listFCompany.map { it.toEntity() })
    }


}