package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FSalesTargetToCust
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesTargetToCustRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesTargetToCustRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFSalesTargetToCustUseCase @Autowired constructor(
    val fSalesTargetToCustRepo: FSalesTargetToCustRepo
) {

    fun findById(id: Long): FSalesTargetToCust {
        return fSalesTargetToCustRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Long): FSalesTargetToCustRes {
        return fSalesTargetToCustRepo.findById(id).toDomain().toResponse()
    }

    fun findAll(): List<FSalesTargetToCust> {
        return fSalesTargetToCustRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FSalesTargetToCustRes> {
        return fSalesTargetToCustRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findBySalesman(fsalesmanBean: Int): List<FSalesTargetToCust> {
        return  fSalesTargetToCustRepo.findBySalesman(fsalesmanBean).map {
            it.toDomain()
        }
    }
    fun findBySalesmanRes(fsalesmanBean: Int): List<FSalesTargetToCustRes> {
        return  fSalesTargetToCustRepo.findBySalesman(fsalesmanBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivision(fdivisionBean: Int): List<FSalesTargetToCust> {
        return  fSalesTargetToCustRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FSalesTargetToCustRes> {
        return  fSalesTargetToCustRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesTargetToCust> {
        return  fSalesTargetToCustRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FSalesTargetToCustRes> {
        return  fSalesTargetToCustRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }


}