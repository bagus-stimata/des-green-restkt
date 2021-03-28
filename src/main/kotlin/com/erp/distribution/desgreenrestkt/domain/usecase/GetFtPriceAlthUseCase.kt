package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FtPriceAlth
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FtPriceAlthRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FtPriceAlthRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFtPriceAlthUseCase @Autowired constructor(
    val ftPriceAlthRepo: FtPriceAlthRepo
) {

    fun findById(id: Int): FtPriceAlth {
        return ftPriceAlthRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FtPriceAlthRes {
        return ftPriceAlthRepo.findById(id).toDomain().toResponse()
    }
    fun findByNoRek(noRek: String): List<FtPriceAlth> {
        return  ftPriceAlthRepo.findByNoRek(noRek).map {
            it.toDomain()
        }
    }
    fun findByNoRekRes(noRek: String): List<FtPriceAlthRes> {
        return  ftPriceAlthRepo.findByNoRek(noRek).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FtPriceAlth> {
        return ftPriceAlthRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FtPriceAlthRes> {
        return ftPriceAlthRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FtPriceAlth> {
        return  ftPriceAlthRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FtPriceAlthRes> {
        return  ftPriceAlthRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FtPriceAlth> {
        return  ftPriceAlthRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FtPriceAlthRes> {
        return  ftPriceAlthRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(ftPriceAlth: FtPriceAlth): FtPriceAlth {
        return ftPriceAlthRepo.save(ftPriceAlth.toEntity()).toDomain()
    }
    fun saveAll(listFtPriceAlth: List<FtPriceAlth>): List<FtPriceAlth> {
        return ftPriceAlthRepo.saveAll(listFtPriceAlth.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(ftPriceAlth: FtPriceAlth) {
        return ftPriceAlthRepo.delete(ftPriceAlth.toEntity())
    }
    fun deleteInBatch(listFtPriceAlth: List<FtPriceAlth>) {
        return ftPriceAlthRepo.deleteInBatch(listFtPriceAlth.map { it.toEntity() })
    }


}