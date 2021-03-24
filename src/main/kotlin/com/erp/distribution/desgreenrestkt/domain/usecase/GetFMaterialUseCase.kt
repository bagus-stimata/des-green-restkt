package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.FArea
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class GetFMaterialUseCase(
) {
    @Autowired
    lateinit var fMaterialRepo: FMaterialRepo

    fun findAll(): List<FMaterialEntity> {
        return fMaterialRepo.findAll()
    }

    fun findByPcode(pcode: String?): List<FMaterialEntity> {
        return  fMaterialRepo.findByPcode(pcode)
    }

    fun findAll(pcode: String, pname: String): List<FMaterialEntity> {
        return  fMaterialRepo.findAll(pcode, pname)
    }

    fun findAllByDivision(fdivisionBean: Int): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivision(fdivisionBean)
    }

    fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivision(fdivisionBean, pageable)
    }

    fun findAllByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivisionAndListVendor(fdivisionBean, listVendor)
    }

    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    fun findAllByDivisionAndShareToCompanyAndListVendor(
        fdivisionBean: Int,
        fcompanyBean: Int,
        listVendor: List<Int>
    ): List<FMaterialEntity> {
        return  fMaterialRepo.findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean, fcompanyBean, listVendor)
    }
}