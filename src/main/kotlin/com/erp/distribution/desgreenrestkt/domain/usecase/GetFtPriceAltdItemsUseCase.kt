package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FtPriceAltdItems
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FtPriceAltdItemsRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FtPriceAltdItemsRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFtPriceAltdItemsUseCase @Autowired constructor(
    val ftPriceAltdItemsRepo: FtPriceAltdItemsRepo
) {

    fun findById(id: Int): FtPriceAltdItems {
        return ftPriceAltdItemsRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FtPriceAltdItemsRes {
        return ftPriceAltdItemsRepo.findById(id).toDomain().toResponse()
    }

    fun findAll(): List<FtPriceAltdItems> {
        return ftPriceAltdItemsRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FtPriceAltdItemsRes> {
        return ftPriceAltdItemsRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findByParent(fparentBean: Int): List<FtPriceAltdItems> {
        return  ftPriceAltdItemsRepo.findByParent(fparentBean).map {
            it.toDomain()
        }
    }
    fun findByParentRes(fparentBean: Int): List<FtPriceAltdItemsRes> {
        return  ftPriceAltdItemsRepo.findByParent(fparentBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByListOfParent(listFParent: List<Int>): List<FtPriceAltdItems> {
        return  ftPriceAltdItemsRepo.findByListOfParent(listFParent).map {
            it.toDomain()
        }
    }
    fun findByListOfParentRes(listFParent: List<Int>): List<FtPriceAltdItemsRes> {
        return  ftPriceAltdItemsRepo.findByListOfParent(listFParent).map {
            it.toDomain().toResponse()
        }
    }

    fun save(ftPriceAltdItems: FtPriceAltdItems): FtPriceAltdItems {
        return ftPriceAltdItemsRepo.save(ftPriceAltdItems.toEntity()).toDomain()
    }
    fun saveAll(listFtPriceAltdItems: List<FtPriceAltdItems>): List<FtPriceAltdItems> {
        return ftPriceAltdItemsRepo.saveAll(listFtPriceAltdItems.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(ftPriceAltdItems: FtPriceAltdItems) {
        return ftPriceAltdItemsRepo.delete(ftPriceAltdItems.toEntity())
    }
    fun deleteInBatch(listFtPriceAltdItems: List<FtPriceAltdItems>) {
        return ftPriceAltdItemsRepo.deleteInBatch(listFtPriceAltdItems.map { it.toEntity() })
    }


}