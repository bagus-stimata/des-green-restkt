package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FMaterial
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetFMaterialUseCase @Autowired constructor(
    val fMaterialRepo: FMaterialRepo
) {

    fun findById(id: Int): FMaterialEntity {
        return fMaterialRepo.findById(id)
    }
    fun findByIdRes(id: Int): FMaterialRes {
        return fMaterialRepo.findById(id).toDomain().toResponse()
    }
    fun findAll(): List<FMaterialEntity> {
        return fMaterialRepo.findAll()
    }
    fun findAllRes(): List<FMaterialRes> {
        return fMaterialRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findByPcode(pcode: String): List<FMaterial> {
        return  fMaterialRepo.findByPcode(pcode).map {
            it.toDomain()
        }
    }
    fun findByPcodeRes(pcode: String): List<FMaterialRes> {
        return  fMaterialRepo.findByPcode(pcode).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(pcode: String, pname: String): List<FMaterial> {
        return  fMaterialRepo.findAll(pcode, pname).map {
            it.toDomain()
        }
    }
    fun findAllRes(pcode: String, pname: String): List<FMaterialRes> {
        return  fMaterialRepo.findAll(pcode, pname).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FMaterialEntity> {
        return  fMaterialRepo.findByDivision(fdivisionBean)
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FMaterialRes> {
        return  fMaterialRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialEntity> {
        return  fMaterialRepo.findByDivision(fdivisionBean, pageable)
    }
    fun findByDivisionRes(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialRes> {
        return  fMaterialRepo.findByDivision(fdivisionBean, pageable).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialEntity> {
        return  fMaterialRepo.findByDivisionAndListVendor(fdivisionBean, listVendor)
    }
    fun findByDivisionAndListVendorRes(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialRes> {
        return  fMaterialRepo.findByDivisionAndListVendor(fdivisionBean, listVendor).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialEntity> {
        return  fMaterialRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialRes> {
        return  fMaterialRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivisionAndShareToCompanyAndListVendor(
        fdivisionBean: Int,
        fcompanyBean: Int,
        listVendor: List<Int>
    ): List<FMaterialEntity> {
        return  fMaterialRepo.findByDivisionAndShareToCompanyAndListVendor(fdivisionBean, fcompanyBean, listVendor)
    }
    fun findAllByDivisionAndShareToCompanyAndListVendorRes(
        fdivisionBean: Int,
        fcompanyBean: Int,
        listVendor: List<Int>
    ): List<FMaterialRes> {
        return  fMaterialRepo.findByDivisionAndShareToCompanyAndListVendor(fdivisionBean, fcompanyBean, listVendor).map {
            it.toDomain().toResponse()
        }
    }

}