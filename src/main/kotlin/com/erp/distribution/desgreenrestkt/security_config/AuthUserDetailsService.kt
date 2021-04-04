package com.erp.distribution.desgreenrestkt.security_config

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUsersJPARepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

/**
 * Ini akan disamngkan dengan spring security SystemConfiguration
 */
@Service
class AuthUserDetailsService : UserDetailsService {
    @Autowired
    private val FUsersJPARepository: FUsersJPARepository? = null
    private var springUser: User? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val enabled = true
        val accountNonExpired = true
        val credentialsNonExpired = true
        val accountNonLocked = true
        val user = getUserDetail(username)
        return if (user != null) {
            springUser = User(user.username,
                    user.password,
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user) // getAuthorities(user.getFuserRoles())
            )
            springUser!!
        } else {
            springUser = User("empty",
                    "empty",
                    false,
                    true,
                    true,
                    false,
                    getAuthorities(3)
            )
            springUser!!
        }
    }

    fun getAuthorities(role: Int): List<GrantedAuthority> {
        val authList: MutableList<GrantedAuthority> = ArrayList()
        if (role == 1) {
            authList.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        } else if (role == 2) {
            authList.add(SimpleGrantedAuthority("ROLE_USER"))
        } else if (role == 3) {
            authList.add(SimpleGrantedAuthority("ROLE_GUEST"))
        }
        return authList
    }

    fun getAuthorities(fuser: FUserEntity): List<GrantedAuthority> {
        val authList: MutableList<GrantedAuthority> = ArrayList()
        /**
         * Spring Security Selalu membutuhkan SimplleGranted Autority menggunanakn awalan ROLE_
         * sedangkan vaadin harus sama persis
         * Sehingga diambil jalan tengahnya menggunakan awalal ROLE_ untuk
         * Enumerasi Role
         */
        for (userRole in fuser.fuserRoles!!) {
            if (userRole.roleID!!.contains("ROLE_")) {
                authList.add(SimpleGrantedAuthority(userRole.roleID))
            } else {
                authList.add(SimpleGrantedAuthority("ROLE_" + userRole.roleID))
            }
        }
        return authList
    }

    private fun getUserDetail(usernameOrEmail: String): FUserEntity? {
        var user = FUsersJPARepository!!.findByUsername(usernameOrEmail)
        if (user == null) {
            user = FUsersJPARepository.findByEmail(usernameOrEmail)
        }
        if (user == null) {
            logger.warn("user '$usernameOrEmail' on null!")
        } else {
            logger.info(user.toString())
        }
        return user
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AuthUserDetailsService::class.java)
    }
}