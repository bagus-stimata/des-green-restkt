package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FSalesCallPlandItems
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FSalesCallPlandItemsRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesCallPlandItemsRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFSalesCallPlandItemsUseCase @Autowired constructor(
    val fSalesCallPlandItemsRepo: FSalesCallPlandItemsRepo
) {

    fun findById(id: Long): FSalesCallPlandItems {
        return fSalesCallPlandItemsRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Long): FSalesCallPlandItemsRes {
        return fSalesCallPlandItemsRepo.findById(id).toDomain().toResponse()
    }

    fun findAll(): List<FSalesCallPlandItems> {
        return fSalesCallPlandItemsRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FSalesCallPlandItemsRes> {
        return fSalesCallPlandItemsRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findByParent(fparentBean: Long): List<FSalesCallPlandItems> {
        return  fSalesCallPlandItemsRepo.findByParent(fparentBean).map {
            it.toDomain()
        }
    }
    fun findByParentRes(fparentBean: Long): List<FSalesCallPlandItemsRes> {
        return  fSalesCallPlandItemsRepo.findByParent(fparentBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByListOfParent(listFSalesCallPlanh: List<Long>): List<FSalesCallPlandItems> {
        return  fSalesCallPlandItemsRepo.findByListOfParent(listFSalesCallPlanh).map {
            it.toDomain()
        }
    }
    fun findByListOfParentRes(listFSalesCallPlanh: List<Long>): List<FSalesCallPlandItemsRes> {
        return  fSalesCallPlandItemsRepo.findByListOfParent(listFSalesCallPlanh).map {
            it.toDomain().toResponse()
        }
    }


}