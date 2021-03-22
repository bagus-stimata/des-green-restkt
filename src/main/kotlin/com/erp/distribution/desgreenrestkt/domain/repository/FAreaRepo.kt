package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FArea

interface FAreaRepo {
    fun getAll(): List<FArea>
}