package com.erp.distribution.desgreenrestkt.security_model

import javax.persistence.*

@Entity
@Table(name = "tb_user_roles")
class FUserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fuser_roles_id_seq")
    // @SequenceGenerator(name="fuser_roles_id_seq", sequenceName="fuser_roles_id_seq", allocationSize=1)
    private val id = 0
    var roleID: String? = Role.GUEST //as default

    @ManyToOne
    @JoinColumn(name = "fuserBean", referencedColumnName = "id")
    var fuserBean: FUser? = null
    override fun toString(): String {
        return "Todo [description=$roleID]"
    }



}