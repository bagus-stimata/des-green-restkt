package com.erp.distribution.desgreenrestkt.presentation.model_security

import com.erp.distribution.desgreenrestkt.domain.model_security.FUser
import com.erp.distribution.desgreenrestkt.domain.model_security.FUserVendors
import java.io.Serializable
import javax.persistence.*

data class FUserVendorsRes (
    var id :Int =0,
    var fvendorBean :Int =0,
//    var fuserBean: FUserRes = FUserRes()
    var fuserBean: Int = 0

): Serializable

internal fun FUserVendorsRes.toDomain(): FUserVendors {
    return FUserVendors(
        id = id,
        fvendorBean = fvendorBean,
        fuserBean = FUser(fuserBean)
    )
}