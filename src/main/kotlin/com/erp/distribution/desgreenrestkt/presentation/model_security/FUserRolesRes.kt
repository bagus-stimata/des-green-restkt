package com.erp.distribution.desgreenrestkt.presentation.model_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.domain.model_security.FUser
import com.erp.distribution.desgreenrestkt.domain.model_security.FUserRoles
import java.io.Serializable
import javax.persistence.*

data class FUserRolesRes (
    var id :Int =0,

    var roleID: String? = Role.GUEST, //as default

//    var fuserBean: FUserRes = FUserRes()
    var fuserBean: Int = 0


): Serializable
internal fun FUserRolesRes.toDomain(): FUserRoles{
    return FUserRoles(
        id = id,
        roleID = roleID,
        fuserBean = FUser(fuserBean)
    )
}