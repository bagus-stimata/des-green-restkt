package com.erp.distribution.desgreenrestkt.security_config

import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * The MyAuthenticationSuccessHandler class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Component
class MyAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
    private var redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()

    @Throws(IOException::class)
    override fun handle(request: HttpServletRequest,
                        response: HttpServletResponse, authentication: Authentication) {
        val targetUrl = determineTargetUrl(authentication)
        // System.out.println("handle: " + targetUrl);
        if (response.isCommitted) {
            Companion.logger.warn("Can't redirect")
            return
        }
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    protected fun determineTargetUrl(authentication: Authentication): String {
        /*
        Otomatis ambil dari database
        */
        Companion.logger.info("Cek User Name: determineTargetUrl: " + authentication.name)
        val authorities = authentication.authorities
        val roles: MutableList<String> = ArrayList()
        for (a in authorities) {
            println("######## ******** Authority: " + a.authority)
            roles.add(a.authority)
        }
        return if (isAdmin(roles)) {
            "/admin/users" //jika admin akan di redirect ke admin
        } else if (isUser(roles)) {
            "/home"
        } else {
            "/login?error"
        }
    }

    override fun setRedirectStrategy(redirectStrategy: RedirectStrategy) {
        this.redirectStrategy = redirectStrategy
    }

    override fun getRedirectStrategy(): RedirectStrategy {
        return redirectStrategy
    }

    private fun isUser(roles: List<String>): Boolean {
        return roles.contains("ROLE_USER")
    }

    private fun isAdmin(roles: List<String>): Boolean {
        return roles.contains("ROLE_ADMIN")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler::class.java)
    }
}