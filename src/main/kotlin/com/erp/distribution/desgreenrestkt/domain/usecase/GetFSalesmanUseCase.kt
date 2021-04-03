package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FSalesman
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesmanRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesmanRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFSalesmanUseCase @Autowired constructor(
    val fSalesmanRepo: FSalesmanRepo
) {

    fun findById(id: Int): FSalesman {
        return fSalesmanRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FSalesmanRes {
        return fSalesmanRepo.findById(id).toDomain().toResponse()
    }
    fun findBySpcode(spcode: String): List<FSalesman> {
        return  fSalesmanRepo.findBySpcode(spcode).map {
            it.toDomain()
        }
    }
    fun findBySpcodeRes(spcode: String): List<FSalesmanRes> {
        return  fSalesmanRepo.findBySpcode(spcode).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FSalesman> {
        return fSalesmanRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FSalesmanRes> {
        return fSalesmanRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FSalesman> {
        return  fSalesmanRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FSalesmanRes> {
        return  fSalesmanRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByCompany(fcompanyBean: Int): List<FSalesman> {
        return  fSalesmanRepo.findByCompany(fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByCompanyRes(fcompanyBean: Int): List<FSalesmanRes> {
        return  fSalesmanRepo.findByCompany(fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivision(fdivisionBean: Int): List<FSalesman> {
        return  fSalesmanRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FSalesmanRes> {
        return  fSalesmanRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesman> {
        return  fSalesmanRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FSalesmanRes> {
        return  fSalesmanRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fSalesman: FSalesman): FSalesman {
        return fSalesmanRepo.save(fSalesman.toEntity()).toDomain()
    }
    fun saveAll(listFSalesman: List<FSalesman>): List<FSalesman> {
        return fSalesmanRepo.saveAll(listFSalesman.map { it.toEntity() }).map {
            it.toDomain()
        }
    }

    fun delete(fSalesman: FSalesman) {
        return fSalesmanRepo.delete(fSalesman.toEntity())
    }
    fun deleteInBatch(listFSalesman: List<FSalesman>) {
        return fSalesmanRepo.deleteInBatch(listFSalesman.map { it.toEntity() })
    }


}