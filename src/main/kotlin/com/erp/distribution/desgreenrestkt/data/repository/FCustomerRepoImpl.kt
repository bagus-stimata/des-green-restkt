package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FCustomerJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FCustomerRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FCustomerRepoImpl @Autowired constructor(
    val fCustomerJPARepository: FCustomerJPARepository
) :FCustomerRepo {
    override fun findById(id: Int): FCustomerEntity {
        return fCustomerJPARepository.findById(id).get()
    }

    override fun findAll(): List<FCustomerEntity> {
        return fCustomerJPARepository.findAll()
    }

    override fun findAll(custno: String, custname: String): List<FCustomerEntity> {
        return fCustomerJPARepository.findAll(custno, custname)
    }

    override fun findByKode1(custno: String): List<FCustomerEntity> {
        return fCustomerJPARepository.findByCustno(custno)
    }

    override fun findAllByDivision(fdivisionBean: Int): List<FCustomerEntity> {
        return fCustomerJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerEntity> {
        return fCustomerJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fCustomerEntity: FCustomerEntity): FCustomerEntity {
        return fCustomerJPARepository.save(fCustomerEntity)
    }
    override fun saveAll(listFCustomer: List<FCustomerEntity>): List<FCustomerEntity> {
        return fCustomerJPARepository.saveAll(listFCustomer)
    }


    override fun delete(fCustomerEntity: FCustomerEntity) {
        return fCustomerJPARepository.delete(fCustomerEntity)
    }
    override fun deleteInBatch(listFCustomer: List<FCustomerEntity>) {
        return fCustomerJPARepository.deleteInBatch(listFCustomer)
    }

}