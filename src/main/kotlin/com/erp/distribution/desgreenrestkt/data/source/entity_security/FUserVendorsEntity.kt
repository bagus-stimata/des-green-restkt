package com.erp.distribution.desgreenrestkt.data.source.entity_security

import javax.persistence.*

@Entity
@Table(name = "tb_user_vendors")
class FUserVendorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0


    var fvendorBean = 0
    @ManyToOne
    @JoinColumn(name = "fuserBean", referencedColumnName = "id")
    var fuserBean: FUserEntity? = null

//    override fun toString(): String {
//        return "FUserVendors{" +
//                "id=" + id +
//                '}'
//    }
}