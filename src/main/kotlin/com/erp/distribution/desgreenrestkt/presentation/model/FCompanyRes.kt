package com.erp.distribution.desgreenrestkt.presentation.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FCompanyEntity
import com.erp.distribution.desgreenrestkt.domain.model.FCompany
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FCompanyRes (
    var id :Int =0,

    @Column(name = "KODE1", length = 10)
    var kode1 :String ="",

    @Column(name = "KODE2", length = 20)
    var kode2 :String ="",

    @Column(name = "DESCRIPTION", length = 100)
    var description :String ="",

    @Column(name = "SHARE_DATA_TO_BE_CLONE")
    var shareDataToBeClone :Boolean =false,

    @Column(name = "SHARE_DATA_TO_BE_CLONE_SECURITY")
    var shareDataToBeCloneSecurityCode :String ="",

    @Column(name = "STATUS_ACTIVE")
    var statusActive :Boolean =true,

    @Column(name = "WEBSERVICE_ACTIVE")
    var webServiceActive :Boolean =false,

    /*
	 * SETTING YANG BERLAKU UNTUK SEMUA DIVISI
	 * JIKA KOSONG MAKA MENGGUNAKAN PRIORITAS ATASNYA
	 * 1. Parameter System
	 * 2. Corporation
	 * 3. Division 
	 */
    /*
	 * 
	 */
    //	@Column(name="NOMOR_URUT_DOC_FOLLOW_APP") //SELALU MENGIKUTI NOMOR URUT COMPANY
    //	private boolean nomorUrutDocTransFollowApp =true,;
    /*
	 * FORMAT FAKTUR DAN ALAMAT
	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 :String ="",;
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 :String ="",;
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 :String ="",;
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 :String ="",;
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Customer, Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1 :String ="",;
    //LOG
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID

): Serializable

internal fun FCompanyRes.toDomain(): FCompany {
    return FCompany(
        id = id,

        kode1 = kode1,
        kode2 = kode2,
        description = description,

        shareDataToBeClone = shareDataToBeClone,
        shareDataToBeCloneSecurityCode = shareDataToBeCloneSecurityCode,
        statusActive = statusActive,
        webServiceActive = webServiceActive,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}