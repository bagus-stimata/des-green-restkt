package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSalesCallPlanhJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesCallPlanhRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FSalesCallPlanhRepoImpl @Autowired constructor(
    val fSalesCallPlanhJPARepository: FSalesCallPlanhJPARepository
) :FSalesCallPlanhRepo {
    override fun findById(id: Long): FSalesCallPlanhEntity {
        return fSalesCallPlanhJPARepository.findById(id).get()
    }
    override fun findAll(): List<FSalesCallPlanhEntity> {
        return fSalesCallPlanhJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FSalesCallPlanhEntity> {
       return fSalesCallPlanhJPARepository.findAll(kode1, description);
    }

    override fun findByKode1(kode1: String): List<FSalesCallPlanhEntity> {
        return fSalesCallPlanhJPARepository.findByKode1(kode1);
    }

    override fun findBySalesman(fsalesmanBean: Int): List<FSalesCallPlanhEntity> {
        return fSalesCallPlanhJPARepository.findAllBySalesman(fsalesmanBean);
    }

    override fun findByDivision(fdivisionBean: Int): List<FSalesCallPlanhEntity> {
        return fSalesCallPlanhJPARepository.findAllByDivision(fdivisionBean);
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesCallPlanhEntity> {
        return fSalesCallPlanhJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean);
    }

}