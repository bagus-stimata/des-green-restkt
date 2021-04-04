package com.erp.distribution.desgreenrestkt.data.source.entity_security

import com.erp.distribution.desgreenrestkt.domain.model_security.FUser
import com.erp.distribution.desgreenrestkt.domain.model_security.FUserVendors
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_user_vendors")
data class FUserVendorsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id :Int =0,

    var fvendorBean :Int =0,

    @ManyToOne
    @JoinColumn(name = "fuserBean", referencedColumnName = "id", nullable = false)
    var fuserBean: FUserEntity = FUserEntity()

): Serializable

internal fun FUserVendorsEntity.toDomain(): FUserVendors {
    return FUserVendors(
        id = id,
        fvendorBean = fvendorBean,
        fuserBean = FUser(fuserBean.id)
    )
}