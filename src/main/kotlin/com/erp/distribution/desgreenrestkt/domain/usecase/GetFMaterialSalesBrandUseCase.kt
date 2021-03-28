package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FMaterialSalesBrand
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialSalesBrandRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialSalesBrandRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFMaterialSalesBrandUseCase @Autowired constructor(
    val fMaterialSalesBrandRepo: FMaterialSalesBrandRepo
) {

    fun findById(id: Int): FMaterialSalesBrand {
        return fMaterialSalesBrandRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FMaterialSalesBrandRes {
        return fMaterialSalesBrandRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FMaterialSalesBrand> {
        return  fMaterialSalesBrandRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FMaterialSalesBrandRes> {
        return  fMaterialSalesBrandRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FMaterialSalesBrand> {
        return fMaterialSalesBrandRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FMaterialSalesBrandRes> {
        return fMaterialSalesBrandRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FMaterialSalesBrand> {
        return  fMaterialSalesBrandRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FMaterialSalesBrandRes> {
        return  fMaterialSalesBrandRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FMaterialSalesBrand> {
        return  fMaterialSalesBrandRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FMaterialSalesBrandRes> {
        return  fMaterialSalesBrandRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialSalesBrand> {
        return  fMaterialSalesBrandRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialSalesBrandRes> {
        return  fMaterialSalesBrandRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fMaterialSalesBrand: FMaterialSalesBrand): FMaterialSalesBrand {
        return fMaterialSalesBrandRepo.save(fMaterialSalesBrand.toEntity()).toDomain()
    }
    fun saveAll(listFMaterialSalesBrand: List<FMaterialSalesBrand>): List<FMaterialSalesBrand> {
        return fMaterialSalesBrandRepo.saveAll(listFMaterialSalesBrand.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fMaterialSalesBrand: FMaterialSalesBrand) {
        return fMaterialSalesBrandRepo.delete(fMaterialSalesBrand.toEntity())
    }
    fun deleteInBatch(listFMaterialSalesBrand: List<FMaterialSalesBrand>) {
        return fMaterialSalesBrandRepo.deleteInBatch(listFMaterialSalesBrand.map { it.toEntity() })
    }


}