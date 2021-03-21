/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.distribution.desgreenrestkt.security_config

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * The PassEncoding class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
class PassEncoding private constructor() {
    var passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    companion object {
        private val passEncoding: PassEncoding? = PassEncoding()
        @JvmStatic
        val instance: PassEncoding?
            get() = passEncoding ?: PassEncoding()
    }

}