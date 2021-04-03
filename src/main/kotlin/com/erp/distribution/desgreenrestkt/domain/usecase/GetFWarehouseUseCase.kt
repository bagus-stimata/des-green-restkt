package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FWarehouse
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FWarehouseRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FWarehouseRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFWarehouseUseCase @Autowired constructor(
    val fWarehouseRepo: FWarehouseRepo
) {

    fun findById(id: Int): FWarehouse {
        return fWarehouseRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FWarehouseRes {
        return fWarehouseRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FWarehouse> {
        return  fWarehouseRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FWarehouseRes> {
        return  fWarehouseRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FWarehouse> {
        return fWarehouseRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FWarehouseRes> {
        return fWarehouseRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FWarehouse> {
        return  fWarehouseRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FWarehouseRes> {
        return  fWarehouseRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FWarehouse> {
        return  fWarehouseRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FWarehouseRes> {
        return  fWarehouseRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FWarehouse> {
        return  fWarehouseRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FWarehouseRes> {
        return  fWarehouseRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByCompany(fcompanyBean: Int): List<FWarehouse> {
        return  fWarehouseRepo.findByCompany(fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByCompanyRes(fcompanyBean: Int): List<FWarehouseRes> {
        return  fWarehouseRepo.findByCompany(fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fWarehouse: FWarehouse): FWarehouse {
        return fWarehouseRepo.save(fWarehouse.toEntity()).toDomain()
    }
    fun saveAll(listFWarehouse: List<FWarehouse>): List<FWarehouse> {
        return fWarehouseRepo.saveAll(listFWarehouse.map { it.toEntity() }).map {
            it.toDomain()
        }
    }

    fun delete(fWarehouse: FWarehouse) {
        return fWarehouseRepo.delete(fWarehouse.toEntity())
    }
    fun deleteInBatch(listFWarehouse: List<FWarehouse>) {
        return fWarehouseRepo.deleteInBatch(listFWarehouse.map { it.toEntity() })
    }


}