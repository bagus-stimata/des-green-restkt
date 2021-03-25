package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FAreaEntity

interface FAreaRepo {
    fun getAll(): List<FAreaEntity>
}