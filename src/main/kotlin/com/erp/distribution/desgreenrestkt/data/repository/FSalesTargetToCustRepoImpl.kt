package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesTargetToCustEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSalesCallPlanhJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSalesTargetToCustJPARepository
import com.erp.distribution.desgreenrestkt.domain.model.FSalesTargetToCust
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesCallPlanhRepo
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesTargetToCustRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FSalesTargetToCustRepoImpl @Autowired constructor(
    val fSalesTargetToCustJPARepository: FSalesTargetToCustJPARepository
) :FSalesTargetToCustRepo {
    override fun findById(id: Long): FSalesTargetToCustEntity {
        return fSalesTargetToCustJPARepository.findById(id).get()
    }
    override fun findAll(): List<FSalesTargetToCustEntity> {
        return fSalesTargetToCustJPARepository.findAll()
    }

    override fun findBySalesman(fsalesmanBean: Int): List<FSalesTargetToCustEntity> {
        return fSalesTargetToCustJPARepository.findAllBySalesman(fsalesmanBean);
    }

    override fun findByDivision(fdivisionBean: Int): List<FSalesTargetToCustEntity> {
        return fSalesTargetToCustJPARepository.findAllByDivision(fdivisionBean);
    }

    override fun findByDivisionAndShareToCompany(
        fdivisionBean: Int,
        fcompanyBean: Int
    ): List<FSalesTargetToCustEntity> {
        return fSalesTargetToCustJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean);
    }


}