package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FArea
import com.erp.distribution.desgreenrestkt.domain.model.FSubArea
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FSubAreaRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import com.erp.distribution.desgreenrestkt.presentation.model.FSubAreaRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFSubAreaUseCase @Autowired constructor(
    val fSubAreaRepo: FSubAreaRepo
) {

    fun findById(id: Int): FSubArea {
        return fSubAreaRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FSubAreaRes {
        return fSubAreaRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FSubArea> {
        return  fSubAreaRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FSubAreaRes> {
        return  fSubAreaRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FSubArea> {
        return fSubAreaRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FSubAreaRes> {
        return fSubAreaRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FSubArea> {
        return  fSubAreaRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FSubAreaRes> {
        return  fSubAreaRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByParent(fparentBean: Int): List<FSubArea> {
        return  fSubAreaRepo.findByParent(fparentBean).map {
            it.toDomain()
        }
    }
    fun findByParentRes(fparentBean: Int): List<FSubAreaRes> {
        return  fSubAreaRepo.findByParent(fparentBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByListOfParent(listFArea: List<Int>): List<FSubArea> {
        return  fSubAreaRepo.findByListOfParent(listFArea).map {
            it.toDomain()
        }
    }
    fun findByListOfParentRes(listFArea: List<Int>): List<FSubAreaRes> {
        return  fSubAreaRepo.findByListOfParent(listFArea).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fSubArea: FSubArea): FSubArea {
        return fSubAreaRepo.save(fSubArea.toEntity()).toDomain()
    }
    fun saveAll(listFSubArea: List<FSubArea>): List<FSubArea> {
        return fSubAreaRepo.saveAll(listFSubArea.map { it.toEntity() }).map {
            it.toDomain()
        }
    }

    fun delete(fSubArea: FSubArea) {
        return fSubAreaRepo.delete(fSubArea.toEntity())
    }
    fun deleteInBatch(listFSubArea: List<FSubArea>) {
        return fSubAreaRepo.deleteInBatch(listFSubArea.map { it.toEntity() })
    }


}