package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAltdItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtPriceAltdItemsJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FtPriceAltdItemsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FtPriceAltdItemsRepoImpl @Autowired constructor(
    val ftPriceAltdItemsJPARepository: FtPriceAltdItemsJPARepository
) :FtPriceAltdItemsRepo {
    override fun findById(id: Int): FtPriceAltdItemsEntity {
        return ftPriceAltdItemsJPARepository.findById(id).get()
    }

    override fun findAll(): List<FtPriceAltdItemsEntity> {
        return ftPriceAltdItemsJPARepository.findAll()
    }

    override fun findByParent(fparentBean: Int): List<FtPriceAltdItemsEntity> {
        return ftPriceAltdItemsJPARepository.findAllByParent(fparentBean)
    }

    override fun save(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity): FtPriceAltdItemsEntity {
        return ftPriceAltdItemsJPARepository.save(ftPriceAltdItemsEntity)
    }
    override fun saveAll(listFtPriceAltdItems: List<FtPriceAltdItemsEntity>): List<FtPriceAltdItemsEntity> {
        return ftPriceAltdItemsJPARepository.saveAll(listFtPriceAltdItems)
    }


    override fun delete(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity) {
        return ftPriceAltdItemsJPARepository.delete(ftPriceAltdItemsEntity)
    }
    override fun deleteInBatch(listFtPriceAltdItems: List<FtPriceAltdItemsEntity>) {
        return ftPriceAltdItemsJPARepository.deleteInBatch(listFtPriceAltdItems)
    }

}