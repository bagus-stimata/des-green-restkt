package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerGroupEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCustomerGroupJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FCustomerGroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FCustomerGroupRepoImpl @Autowired constructor(
    val fCustomerGroupJPARepository: FCustomerGroupJPARepository
) :FCustomerGroupRepo {
    override fun findById(id: Int): FCustomerGroupEntity {
        return fCustomerGroupJPARepository.findById(id).get()
    }

    override fun findAll(): List<FCustomerGroupEntity> {
        return fCustomerGroupJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FCustomerGroupEntity> {
        return fCustomerGroupJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FCustomerGroupEntity> {
        return fCustomerGroupJPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FCustomerGroupEntity> {
        return fCustomerGroupJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerGroupEntity> {
        return fCustomerGroupJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fCustomerGroupEntity: FCustomerGroupEntity): FCustomerGroupEntity {
        return fCustomerGroupJPARepository.save(fCustomerGroupEntity)
    }
    override fun saveAll(listFCustomerGroup: List<FCustomerGroupEntity>): List<FCustomerGroupEntity> {
        return fCustomerGroupJPARepository.saveAll(listFCustomerGroup)
    }


    override fun delete(fCustomerGroupEntity: FCustomerGroupEntity) {
        return fCustomerGroupJPARepository.delete(fCustomerGroupEntity)
    }
    override fun deleteInBatch(listFCustomerGroup: List<FCustomerGroupEntity>) {
        return fCustomerGroupJPARepository.deleteInBatch(listFCustomerGroup)
    }

}