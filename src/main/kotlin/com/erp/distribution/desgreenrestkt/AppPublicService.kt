package com.erp.distribution.desgreenrestkt

import org.slf4j.LoggerFactory

object AppPublicService {
    private val logger = LoggerFactory.getLogger(AppPublicService::class.java)
    const val APP_NAME = "SIAPP"
    const val APP_DESC1 = "DES Green RestApi Kotlin"
    const val APP_DESC2 = "Green Soft Innovation"
    const val APP_DESC3 = "Jawa Timur"

    //	public static final String FILE_PATH = "/Users/yhawin/AppSourceCode/siapp_file/";
    val FILE_PATH = System.getProperty("user.home") + "/des_files/"
    val REPORT_PATH = System.getProperty("user.home") + "/des_reports/"

    /**
     * Harus disamakan dengan yang ada di application.properties
     */
    const val PUBLIC_HOST_EMAIL = "helpdesk1@des-green.com"
    const val PUBLIC_HOST_EMAIL_PASSWORD = "Welcome123456789"
}