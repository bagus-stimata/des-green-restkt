package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class FMaterialRepoImpl @Autowired constructor(
    val fMaterialJPARepository: FMaterialJPARepository
) : FMaterialRepo {

    override fun findById(id: Int): FMaterialEntity {
        return fMaterialJPARepository.findById(id).get()
    }

    override fun findAll(): List<FMaterialEntity> {
        return fMaterialJPARepository.findAll()
    }

    override fun findByPcode(pcode: String): List<FMaterialEntity> {
       return fMaterialJPARepository.findByPcode(pcode)
    }

    override fun findAll(pcode: String, pname: String): List<FMaterialEntity> {
       return fMaterialJPARepository.findAll(pcode, pname)
    }

    override fun findAllByDivision(fdivisionBean: Int): List<FMaterialEntity> {
        return fMaterialJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialEntity> {
        return fMaterialJPARepository.findAllByDivision(fdivisionBean, pageable)
    }

    override fun findAllByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialEntity> {
        return fMaterialJPARepository.findAllByDivisionAndListVendor(fdivisionBean, listVendor)
    }

    override fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialEntity> {
        return fMaterialJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun findAllByDivisionAndShareToCompanyAndListVendor(
        fdivisionBean: Int,
        fcompanyBean: Int,
        listVendor: List<Int>
    ): List<FMaterialEntity> {
        return fMaterialJPARepository.findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean, fcompanyBean, listVendor)
    }

}