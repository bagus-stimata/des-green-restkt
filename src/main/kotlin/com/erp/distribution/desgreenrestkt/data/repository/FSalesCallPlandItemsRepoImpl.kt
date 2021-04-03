package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FSalesCallPlandItemsJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesCallPlandItemsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FSalesCallPlandItemsRepoImpl @Autowired constructor(
    val fSalesCallPlandItemsJPARepository: FSalesCallPlandItemsJPARepository
) :FSalesCallPlandItemsRepo {
    override fun findById(id: Long): FSalesCallPlandItemsEntity {
        return fSalesCallPlandItemsJPARepository.findById(id).get()
    }
    override fun findAll(): List<FSalesCallPlandItemsEntity> {
        return fSalesCallPlandItemsJPARepository.findAll()
    }

    override fun findByParent(fparentBean: Long): List<FSalesCallPlandItemsEntity> {
        return fSalesCallPlandItemsJPARepository.findAllByParent(fparentBean)
    }
    override fun findByListOfParent(listFParent: List<Long>): List<FSalesCallPlandItemsEntity> {
        return fSalesCallPlandItemsJPARepository.findAllByListParent(listFParent)
    }

}