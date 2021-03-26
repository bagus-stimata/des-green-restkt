package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.FAreaEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FArea
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FAreaRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import jdk.jfr.Description
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    fun findByKode1(kode1: String): List<FArea> {
        return  fAreaRepo.findByKode1(kode1).map {
            it.toDomain()
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

    fun findAllByDivision(fdivisionBean: Int): List<FArea> {
        return  fAreaRepo.findAllByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findAllByDivisionRes(fdivisionBean: Int): List<FAreaRes> {
        return  fAreaRepo.findAllByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findAllByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FAreaRes> {
        return  fAreaRepo.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

}