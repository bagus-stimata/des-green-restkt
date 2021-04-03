package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FSalesCallPlanh
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesCallPlanhRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesCallPlanhRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFSalesCallPlanhUseCase @Autowired constructor(
    val fSalesCallPlanhRepo: FSalesCallPlanhRepo
) {

    fun findById(id: Long): FSalesCallPlanh {
        return fSalesCallPlanhRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Long): FSalesCallPlanhRes {
        return fSalesCallPlanhRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FSalesCallPlanh> {
        return  fSalesCallPlanhRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FSalesCallPlanhRes> {
        return  fSalesCallPlanhRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FSalesCallPlanh> {
        return fSalesCallPlanhRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FSalesCallPlanhRes> {
        return fSalesCallPlanhRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findBySalesman(fsalesmanBean: Int): List<FSalesCallPlanh> {
        return  fSalesCallPlanhRepo.findBySalesman(fsalesmanBean).map {
            it.toDomain()
        }
    }
    fun findBySalesmanRes(fsalesmanBean: Int): List<FSalesCallPlanhRes> {
        return  fSalesCallPlanhRepo.findBySalesman(fsalesmanBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivision(fdivisionBean: Int): List<FSalesCallPlanh> {
        return  fSalesCallPlanhRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FSalesCallPlanhRes> {
        return  fSalesCallPlanhRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesCallPlanh> {
        return  fSalesCallPlanhRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FSalesCallPlanhRes> {
        return  fSalesCallPlanhRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }


}