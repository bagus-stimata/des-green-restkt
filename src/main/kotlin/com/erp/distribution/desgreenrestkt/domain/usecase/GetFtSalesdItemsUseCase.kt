package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FtSalesdItems
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FtSalesdItemsRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FtSalesdItemsRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFtSalesdItemsUseCase @Autowired constructor(
    val ftSalesdItemsRepo: FtSalesdItemsRepo
) {

    fun findById(id: Long): FtSalesdItems {
        return ftSalesdItemsRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Long): FtSalesdItemsRes {
        return ftSalesdItemsRepo.findById(id).toDomain().toResponse()
    }

    fun findAll(): List<FtSalesdItems> {
        return ftSalesdItemsRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FtSalesdItemsRes> {
        return ftSalesdItemsRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findByParent(fparentBean: Long): List<FtSalesdItems> {
        return  ftSalesdItemsRepo.findByParent(fparentBean).map {
            it.toDomain()
        }
    }
    fun findByParentRes(fparentBean: Long): List<FtSalesdItemsRes> {
        return  ftSalesdItemsRepo.findByParent(fparentBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByListOfParent(listFParent: List<Long>): List<FtSalesdItems> {
        return  ftSalesdItemsRepo.findByListOfParent(listFParent).map {
            it.toDomain()
        }
    }
    fun findByListOfParentRes(listFParent: List<Long>): List<FtSalesdItemsRes> {
        return  ftSalesdItemsRepo.findByListOfParent(listFParent).map {
            it.toDomain().toResponse()
        }
    }

    fun save(ftSalesdItems: FtSalesdItems): FtSalesdItems {
        return ftSalesdItemsRepo.save(ftSalesdItems.toEntity()).toDomain()
    }
    fun saveAll(listFtSalesdItems: List<FtSalesdItems>): List<FtSalesdItems> {
        return ftSalesdItemsRepo.saveAll(listFtSalesdItems.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(ftSalesdItems: FtSalesdItems) {
        return ftSalesdItemsRepo.delete(ftSalesdItems.toEntity())
    }
    fun deleteInBatch(listFtSalesdItems: List<FtSalesdItems>) {
        return ftSalesdItemsRepo.deleteInBatch(listFtSalesdItems.map { it.toEntity() })
    }


}