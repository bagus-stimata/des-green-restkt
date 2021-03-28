package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FVendor
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FVendorRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FVendorRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFVendorUseCase @Autowired constructor(
    val fVendorRepo: FVendorRepo
) {

    fun findById(id: Int): FVendor {
        return fVendorRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FVendorRes {
        return fVendorRepo.findById(id).toDomain().toResponse()
    }
    fun findByVcode(vcode: String): List<FVendor> {
        return  fVendorRepo.findByVcode(vcode).map {
            it.toDomain()
        }
    }
    fun findByVcodeRes(kode1: String): List<FVendorRes> {
        return  fVendorRepo.findByVcode(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FVendor> {
        return fVendorRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FVendorRes> {
        return fVendorRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FVendor> {
        return  fVendorRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FVendorRes> {
        return  fVendorRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByCompany(fcompanyBean: Int): List<FVendor> {
        return  fVendorRepo.findByCompany(fcompanyBean).map {
            it.toDomain()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FVendor> {
        return  fVendorRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FVendorRes> {
        return  fVendorRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FVendor> {
        return  fVendorRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FVendorRes> {
        return  fVendorRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fVendor: FVendor): FVendor {
        return fVendorRepo.save(fVendor.toEntity()).toDomain()
    }
    fun saveAll(listFVendor: List<FVendor>): List<FVendor> {
        return fVendorRepo.saveAll(listFVendor.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fVendor: FVendor) {
        return fVendorRepo.delete(fVendor.toEntity())
    }
    fun deleteInBatch(listFVendor: List<FVendor>) {
        return fVendorRepo.deleteInBatch(listFVendor.map { it.toEntity() })
    }


}