package com.erp.distribution.desgreenrestkt.domain.model_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserVendorsEntity
import com.erp.distribution.desgreenrestkt.presentation.model_security.FUserRes
import com.erp.distribution.desgreenrestkt.presentation.model_security.FUserVendorsRes
import java.io.Serializable
import javax.persistence.*

data class FUserVendors (
    var id :Int =0,
    var fvendorBean :Int =0,
    var fuserBean: FUser = FUser()

): Serializable

internal fun FUserVendors.toEntity(): FUserVendorsEntity {
    return FUserVendorsEntity(
        id = id,
        fvendorBean = fvendorBean,
        fuserBean = FUserEntity(fuserBean.id)
    )
}
internal fun FUserVendors.toResponse(): FUserVendorsRes {
    return FUserVendorsRes(
        id = id,
        fvendorBean = fvendorBean,
        fuserBean = fuserBean.id
    )
}