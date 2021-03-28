package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FTax
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FTaxRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FTaxRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFTaxUseCase @Autowired constructor(
    val fTaxRepo: FTaxRepo
) {

    fun findById(id: Int): FTax {
        return fTaxRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FTaxRes {
        return fTaxRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FTax> {
        return  fTaxRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FTaxRes> {
        return  fTaxRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FTax> {
        return fTaxRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FTaxRes> {
        return fTaxRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FTax> {
        return  fTaxRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FTaxRes> {
        return  fTaxRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FTax> {
        return  fTaxRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FTaxRes> {
        return  fTaxRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FTax> {
        return  fTaxRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FTaxRes> {
        return  fTaxRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fTax: FTax): FTax {
        return fTaxRepo.save(fTax.toEntity()).toDomain()
    }
    fun saveAll(listFTax: List<FTax>): List<FTax> {
        return fTaxRepo.saveAll(listFTax.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fTax: FTax) {
        return fTaxRepo.delete(fTax.toEntity())
    }
    fun deleteInBatch(listFTax: List<FTax>) {
        return fTaxRepo.deleteInBatch(listFTax.map { it.toEntity() })
    }


}