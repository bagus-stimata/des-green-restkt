package com.erp.distribution.desgreenrestkt.security_config

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUser
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUsersJPARepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/**
 * The GlobalController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
class SecurityUtils {
    @Autowired
    private val FUsersJPARepository: FUsersJPARepository? = null

    //    public FUser loginUser;
    //    public FUser getLoginUser() {
    //        if (loginUser == null) {
    //            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //            loginUser = FUsersJPARepository.findByUsername(auth.getName());
    //        }
    //        return loginUser;
    //    }
    val loginUser: FUser?
        get() {
            val auth = SecurityContextHolder.getContext().authentication
            return FUsersJPARepository!!.findByUsername(auth.name)
        }

    companion object {
        /**
         * user for ##### VAADIN FLOW ONLY #####
         */
        // static boolean isFrameworkInternalRequest(HttpServletRequest request) {
        // 	final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        // 	return parameterValue != null
        // 			&& Stream.of(RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameterValue));
        // }
        /**
         * Tests if some user is authenticated. As Spring Security always will create an [AnonymousAuthenticationToken]
         * we have to ignore those tokens explicitly.
         */
        val isUserLoggedIn: Boolean
            get() {
                val authentication = SecurityContextHolder.getContext().authentication
                return (authentication != null && authentication !is AnonymousAuthenticationToken
                        && authentication.isAuthenticated)
            }
    }
}