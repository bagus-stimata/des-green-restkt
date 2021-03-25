package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
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

    fun findByPcode(pcode: String?): List<FMaterialEntity> {
        return  fMaterialRepo.findByPcode(pcode)
    }

    fun findAll(pcode: String, pname: String): List<FMaterialEntity> {
        return  fMaterialRepo.findAll(pcode, pname)
    }
    fun findAllRes(pcode: String, pname: String): List<FMaterialRes> {
        return  fMaterialRepo.findAll(pcode, pname).map {
            it.toDomain().toResponse()
        }
    }

    fun findAllByDivision(fdivisionBean: Int): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivision(fdivisionBean)
    }
    fun findAllByDivisionRes(fdivisionBean: Int): List<FMaterialRes> {
        return  fMaterialRepo.findAllByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }

    fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivision(fdivisionBean, pageable)
    }
    fun findAllByDivisionRes(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialRes> {
        return  fMaterialRepo.findAllByDivision(fdivisionBean, pageable).map {
            it.toDomain().toResponse()
        }
    }

    fun findAllByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivisionAndListVendor(fdivisionBean, listVendor)
    }
    fun findAllByDivisionAndListVendorRes(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialRes> {
        return  fMaterialRepo.findAllByDivisionAndListVendor(fdivisionBean, listVendor).map {
            it.toDomain().toResponse()
        }
    }

    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }
    fun findAllByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialRes> {
        return  fMaterialRepo.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun findAllByDivisionAndShareToCompanyAndListVendor(
        fdivisionBean: Int,
        fcompanyBean: Int,
        listVendor: List<Int>
    ): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean, fcompanyBean, listVendor)
    }
    fun findAllByDivisionAndShareToCompanyAndListVendorRes(
        fdivisionBean: Int,
        fcompanyBean: Int,
        listVendor: List<Int>
    ): List<FMaterialRes> {
        return  fMaterialRepo.findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean, fcompanyBean, listVendor).map {
            it.toDomain().toResponse()
        }
    }

}