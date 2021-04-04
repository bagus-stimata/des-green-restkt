package com.erp.distribution.desgreenrestkt.domain.model_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserRolesEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.presentation.model_security.FUserRes
import com.erp.distribution.desgreenrestkt.presentation.model_security.FUserRolesRes
import java.io.Serializable
import javax.persistence.*

data class FUserRoles (
    var id :Int =0,

    var roleID: String? = Role.GUEST, //as default

    var fuserBean: FUser = FUser()


): Serializable

internal fun FUserRoles.toEntity(): FUserRolesEntity{
    return FUserRolesEntity(
        id = id,
        roleID = roleID,
        fuserBean = FUserEntity(fuserBean.id)
    )
}
internal fun FUserRoles.toResponse(): FUserRolesRes{
    return FUserRolesRes(
        id = id,
        roleID = roleID,
        fuserBean = fuserBean.id
    )
}