package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FPromotionRulesh
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FPromotionRuleshRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFPromotionRuleshUseCase @Autowired constructor(
    val fPromotionRuleshRepo: FPromotionRuleshRepo
) {

    fun findById(id: Int): FPromotionRulesh {
        return fPromotionRuleshRepo.findById(id).toDomain()
    }
//    fun findByIdRes(id: Int): FPromotionRuleshRes {
//        return fPromotionRuleshRepo.findById(id).toDomain().toResponse()
//    }
    fun findByKode1(kode1: String): List<FPromotionRulesh> {
        return  fPromotionRuleshRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }

    fun findAll(): List<FPromotionRulesh> {
        return fPromotionRuleshRepo.findAll().map {
            it.toDomain()
        }
    }
//    fun findAllRes(): List<FPromotionRuleshRes> {
//        return fPromotionRuleshRepo.findAll().map {
//            it.toDomain().toResponse()
//        }
//    }
    fun findAll(kode1: String, description: String): List<FPromotionRulesh> {
        return  fPromotionRuleshRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
//    fun findAllRes(kode1: String, description: String): List<FPromotionRuleshRes> {
//        return  fPromotionRuleshRepo.findAll(kode1, description).map {
//            it.toDomain().toResponse()
//        }
//    }

    fun findByDivision(fdivisionBean: Int): List<FPromotionRulesh> {
        return  fPromotionRuleshRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
//    fun findByDivisionRes(fdivisionBean: Int): List<FPromotionRuleshRes> {
//        return  fPromotionRuleshRepo.findByDivision(fdivisionBean).map {
//            it.toDomain().toResponse()
//        }
//    }
//    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FPromotionRulesh> {
//        return  fPromotionRuleshRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
//            it.toDomain()
//        }
//    }
//    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FPromotionRuleshRes> {
//        return  fPromotionRuleshRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
//            it.toDomain().toResponse()
//        }
//    }

    fun save(fPromotionRulesh: FPromotionRulesh): FPromotionRulesh {
        return fPromotionRuleshRepo.save(fPromotionRulesh.toEntity()).toDomain()
    }
    fun saveAll(listFPromotionRulesh: List<FPromotionRulesh>): List<FPromotionRulesh> {
        return fPromotionRuleshRepo.saveAll(listFPromotionRulesh.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fPromotionRulesh: FPromotionRulesh) {
        return fPromotionRuleshRepo.delete(fPromotionRulesh.toEntity())
    }
    fun deleteInBatch(listFPromotionRulesh: List<FPromotionRulesh>) {
        return fPromotionRuleshRepo.deleteInBatch(listFPromotionRulesh.map { it.toEntity() })
    }


}