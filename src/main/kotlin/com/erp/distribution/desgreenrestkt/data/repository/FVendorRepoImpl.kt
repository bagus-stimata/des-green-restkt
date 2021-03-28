package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FVendorJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FVendorRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FVendorRepoImpl @Autowired constructor(
    val fVendorJPARepository: FVendorJPARepository
) :FVendorRepo {
    override fun findById(id: Int): FVendorEntity {
        return fVendorJPARepository.findById(id).get()
    }

    override fun findAll(): List<FVendorEntity> {
        return fVendorJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FVendorEntity> {
        return fVendorJPARepository.findAll(kode1, description)
    }

    override fun findByVcode(vcode: String): List<FVendorEntity> {
        return fVendorJPARepository.findByVcode(vcode)
    }

    override fun findByDivision(fdivisionBean: Int): List<FVendorEntity> {
        return fVendorJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FVendorEntity> {
        return fVendorJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fVendorEntity: FVendorEntity): FVendorEntity {
        return fVendorJPARepository.save(fVendorEntity)
    }
    override fun saveAll(listFVendor: List<FVendorEntity>): List<FVendorEntity> {
        return fVendorJPARepository.saveAll(listFVendor)
    }


    override fun delete(fVendorEntity: FVendorEntity) {
        return fVendorJPARepository.delete(fVendorEntity)
    }
    override fun deleteInBatch(listFVendor: List<FVendorEntity>) {
        return fVendorJPARepository.deleteInBatch(listFVendor)
    }

}