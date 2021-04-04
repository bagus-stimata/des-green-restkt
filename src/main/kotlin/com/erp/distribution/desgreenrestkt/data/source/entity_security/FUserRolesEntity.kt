package com.erp.distribution.desgreenrestkt.data.source.entity_security

import com.erp.distribution.desgreenrestkt.domain.model_security.FUser
import com.erp.distribution.desgreenrestkt.domain.model_security.FUserRoles
import com.erp.distribution.desgreenrestkt.presentation.model_security.FUserRolesRes
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_user_roles")
data class FUserRolesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fuser_roles_id_seq")
    var id :Int =0,

    var roleID: String? = Role.GUEST, //as default

    @ManyToOne
    @JoinColumn(name = "fuserBean", referencedColumnName = "id", nullable = false)
    var fuserBean: FUserEntity = FUserEntity()


): Serializable

internal fun FUserRolesEntity.toDomain(): FUserRoles {
    return FUserRoles(
        id = id,
        roleID = roleID,
        fuserBean = FUser(fuserBean.id)
    )
}