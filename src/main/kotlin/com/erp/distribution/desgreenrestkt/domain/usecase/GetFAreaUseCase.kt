package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FArea
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FAreaRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFAreaUseCase @Autowired constructor(
    val fAreaRepo: FAreaRepo
) {

    fun findById(id: Int): FArea {
        return fAreaRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FAreaRes {
        return fAreaRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FArea> {
        return  fAreaRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FAreaRes> {
        return  fAreaRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FArea> {
        return fAreaRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FAreaRes> {
        return fAreaRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FArea> {
        return  fAreaRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FAreaRes> {
        return  fAreaRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FArea> {
        return  fAreaRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FAreaRes> {
        return  fAreaRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FArea> {
        return  fAreaRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FAreaRes> {
        return  fAreaRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fArea: FArea): FArea {
        return fAreaRepo.save(fArea.toEntity()).toDomain()
    }
    fun saveAll(listFArea: List<FArea>): List<FArea> {
        return fAreaRepo.saveAll(listFArea.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fArea: FArea) {
        return fAreaRepo.delete(fArea.toEntity())
    }
    fun deleteInBatch(listFArea: List<FArea>) {
        return fAreaRepo.deleteInBatch(listFArea.map { it.toEntity() })
    }


}