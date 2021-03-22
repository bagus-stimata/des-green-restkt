package com.erp.distribution.desgreenrestkt.data.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FArea
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FAreaJPARepository
import com.erp.distribution.desgreenrestkt.domain.repository.FAreaRepo
import org.springframework.beans.factory.annotation.Autowired


class FAreaRepoImpl: FAreaRepo {
    @Autowired
    lateinit var fAreaJPARepository: FAreaJPARepository

    override fun getAll(): List<FArea> {
        return fAreaJPARepository.findAll()
    }

}