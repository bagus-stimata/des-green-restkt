package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtPriceAlthJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FtPriceAlthRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FtPriceAlthRepoImpl @Autowired constructor(
    val ftPriceAlthJPARepository: FtPriceAlthJPARepository
) :FtPriceAlthRepo {
    override fun findById(id: Int): FtPriceAlthEntity {
        return ftPriceAlthJPARepository.findById(id).get()
    }

    override fun findAll(): List<FtPriceAlthEntity> {
        return ftPriceAlthJPARepository.findAll()
    }

    override fun findByNoRek(noRek: String): List<FtPriceAlthEntity> {
        return ftPriceAlthJPARepository.findAllByNoRek(noRek)
    }

    override fun findByDivision(fdivisionBean: Int): List<FtPriceAlthEntity> {
        return ftPriceAlthJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FtPriceAlthEntity> {
        return ftPriceAlthJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(ftPriceAlthEntity: FtPriceAlthEntity): FtPriceAlthEntity {
        return ftPriceAlthJPARepository.save(ftPriceAlthEntity)
    }
    override fun saveAll(listFtPriceAlth: List<FtPriceAlthEntity>): List<FtPriceAlthEntity> {
        return ftPriceAlthJPARepository.saveAll(listFtPriceAlth)
    }


    override fun delete(ftPriceAlthEntity: FtPriceAlthEntity) {
        return ftPriceAlthJPARepository.delete(ftPriceAlthEntity)
    }
    override fun deleteInBatch(listFtPriceAlth: List<FtPriceAlthEntity>) {
        return ftPriceAlthJPARepository.deleteInBatch(listFtPriceAlth)
    }

}