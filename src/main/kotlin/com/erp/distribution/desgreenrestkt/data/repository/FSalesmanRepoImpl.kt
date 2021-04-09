package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesmanEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSalesmanJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesmanRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FSalesmanRepoImpl @Autowired constructor(
    val fSalesmanJPARepository: FSalesmanJPARepository
) :FSalesmanRepo {

    override fun findById(id: Int): FSalesmanEntity {
        return fSalesmanJPARepository.findById(id).get()
    }

    override fun findAll(): List<FSalesmanEntity> {
        return fSalesmanJPARepository.findAll()
    }

    override fun findAll(spcode: String, spname: String): List<FSalesmanEntity> {
        return fSalesmanJPARepository.findAll(spcode, spname)
    }

    override fun findBySpcode(kode1: String): List<FSalesmanEntity> {
        return fSalesmanJPARepository.findBySpcode(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FSalesmanEntity> {
        return fSalesmanJPARepository.findAllByDivision(fdivisionBean)
    }
    override fun findByCompany(fcompanyBean: Int): List<FSalesmanEntity> {
        return fSalesmanJPARepository.findAllByCompany(fcompanyBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesmanEntity> {
        return fSalesmanJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fSalesmanEntity: FSalesmanEntity): FSalesmanEntity {
        return fSalesmanJPARepository.save(fSalesmanEntity)
    }
    override fun saveAll(listFSalesman: List<FSalesmanEntity>): List<FSalesmanEntity> {
        return fSalesmanJPARepository.saveAll(listFSalesman)
    }


    override fun delete(fSalesmanEntity: FSalesmanEntity) {
        return fSalesmanJPARepository.delete(fSalesmanEntity)
    }
    override fun deleteInBatch(listFSalesman: List<FSalesmanEntity>) {
        return fSalesmanJPARepository.deleteInBatch(listFSalesman)
    }

}