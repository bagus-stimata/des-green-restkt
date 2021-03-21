package com.erp.distribution.desgreenrestkt.security_model

import javax.persistence.*

@Entity
@Table(name = "tb_user_vendors")
class FUserVendors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0
    var fvendorBean = 0

    @ManyToOne
    @JoinColumn(name = "fuserBean", referencedColumnName = "id")
    var fuserBean: FUser? = null

    override fun toString(): String {
        return "FUserVendors{" +
                "id=" + id +
                '}'
    }
}