package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRuleshEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FPromotionRuleshJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FPromotionRuleshRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FPromotionRuleshRepoImpl @Autowired constructor(
    val fPromotionRuleshJPARepository: FPromotionRuleshJPARepository
) :FPromotionRuleshRepo {
    override fun findById(id: Int): FPromotionRuleshEntity {
        return fPromotionRuleshJPARepository.findById(id).get()
    }

    override fun findAll(): List<FPromotionRuleshEntity> {
        return fPromotionRuleshJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FPromotionRuleshEntity> {
        return fPromotionRuleshJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FPromotionRuleshEntity> {
        return fPromotionRuleshJPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FPromotionRuleshEntity> {
        return fPromotionRuleshJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun save(fPromotionRuleshEntity: FPromotionRuleshEntity): FPromotionRuleshEntity {
        return fPromotionRuleshJPARepository.save(fPromotionRuleshEntity)
    }
    override fun saveAll(listFPromotionRulesh: List<FPromotionRuleshEntity>): List<FPromotionRuleshEntity> {
        return fPromotionRuleshJPARepository.saveAll(listFPromotionRulesh)
    }


    override fun delete(fPromotionRuleshEntity: FPromotionRuleshEntity) {
        return fPromotionRuleshJPARepository.delete(fPromotionRuleshEntity)
    }
    override fun deleteInBatch(listFPromotionRulesh: List<FPromotionRuleshEntity>) {
        return fPromotionRuleshJPARepository.deleteInBatch(listFPromotionRulesh)
    }

}