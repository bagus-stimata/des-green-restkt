package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FDistributionChannelJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FDistributionChannelRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FDistributionChannelRepoImpl @Autowired constructor(
    val fDistributionChannelJPARepository: FDistributionChannelJPARepository
) :FDistributionChannelRepo {
    override fun findById(id: Int): FDistributionChannelEntity {
        return fDistributionChannelJPARepository.findById(id).get()
    }

    override fun findAll(): List<FDistributionChannelEntity> {
        return fDistributionChannelJPARepository.findAll()
    }

    override fun findAll(kode1: String, description: String): List<FDistributionChannelEntity> {
        return fDistributionChannelJPARepository.findAll(kode1, description)
    }

    override fun findByKode1(kode1: String): List<FDistributionChannelEntity> {
        return fDistributionChannelJPARepository.findByKode1(kode1)
    }

    override fun findByDivision(fdivisionBean: Int): List<FDistributionChannelEntity> {
        return fDistributionChannelJPARepository.findAllByDivision(fdivisionBean)
    }

    override fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FDistributionChannelEntity> {
        return fDistributionChannelJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)
    }

    override fun save(fDistributionChannelEntity: FDistributionChannelEntity): FDistributionChannelEntity {
        return fDistributionChannelJPARepository.save(fDistributionChannelEntity)
    }
    override fun saveAll(listFDistributionChannel: List<FDistributionChannelEntity>): List<FDistributionChannelEntity> {
        return fDistributionChannelJPARepository.saveAll(listFDistributionChannel)
    }


    override fun delete(fDistributionChannelEntity: FDistributionChannelEntity) {
        return fDistributionChannelJPARepository.delete(fDistributionChannelEntity)
    }
    override fun deleteInBatch(listFDistributionChannel: List<FDistributionChannelEntity>) {
        return fDistributionChannelJPARepository.deleteInBatch(listFDistributionChannel)
    }

}