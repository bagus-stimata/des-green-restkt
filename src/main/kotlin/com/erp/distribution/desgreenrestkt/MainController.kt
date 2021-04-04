package com.erp.distribution.desgreenrestkt

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainController {
    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    @ResponseBody
    fun Root(): String {
        return "index_oke_bos"
    }

    @RequestMapping(value = ["/home"], method = [RequestMethod.GET])
    @ResponseBody
    fun homeIndex(): String {
        return "index"
    }

    // @RequestMapping(value = "/login", method = RequestMethod.GET)
    // public String login(){
    //     logger.info("#Enter Form Login");
    //     return "/login-form/login-page";
    // }
    @RequestMapping(value = ["/login"], method = [RequestMethod.GET])
    fun login(model: Model): String {
        val fuser = FUserEntity()
        fuser.username = "@gmail.com"
        fuser.password = ""
        model.addAttribute("fuser", fuser)
        return "login-form/login-page"
        // return "/login-form/login";
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MainController::class.java)
    }
}