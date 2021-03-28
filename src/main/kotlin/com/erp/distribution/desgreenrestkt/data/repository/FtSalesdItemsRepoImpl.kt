package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FtSalesdItemsJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FtSalesdItemsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FtSalesdItemsRepoImpl @Autowired constructor(
    val ftSalesdItemsJPARepository: FtSalesdItemsJPARepository
) :FtSalesdItemsRepo {
    override fun findById(id: Long): FtSalesdItemsEntity {
        return ftSalesdItemsJPARepository.findById(id).get()
    }

    override fun findAll(): List<FtSalesdItemsEntity> {
        return ftSalesdItemsJPARepository.findAll()
    }

    override fun findByParent(fparentBean: Long): List<FtSalesdItemsEntity> {
        return ftSalesdItemsJPARepository.findAllByFtSaleshBean(fparentBean)
    }
    override fun findByListOfParent(listFParent: List<Long>): List<FtSalesdItemsEntity> {
        return ftSalesdItemsJPARepository.findAllByListFtSalesh(listFParent)
    }

    override fun save(ftSalesdItemsEntity: FtSalesdItemsEntity): FtSalesdItemsEntity {
        return ftSalesdItemsJPARepository.save(ftSalesdItemsEntity)
    }
    override fun saveAll(listFtSalesdItems: List<FtSalesdItemsEntity>): List<FtSalesdItemsEntity> {
        return ftSalesdItemsJPARepository.saveAll(listFtSalesdItems)
    }


    override fun delete(ftSalesdItemsEntity: FtSalesdItemsEntity) {
        return ftSalesdItemsJPARepository.delete(ftSalesdItemsEntity)
    }
    override fun deleteInBatch(listFtSalesdItems: List<FtSalesdItemsEntity>) {
        return ftSalesdItemsJPARepository.deleteInBatch(listFtSalesdItems)
    }

}