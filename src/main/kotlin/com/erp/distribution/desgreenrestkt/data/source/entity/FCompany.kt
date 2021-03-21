package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fcompany")
class FCompany {
    @Id
    @Column(name = "ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    @Column(name = "KODE1", length = 10)
    var kode1 = ""

    @Column(name = "KODE2", length = 20)
    var kode2 = ""

    @Column(name = "DESCRIPTION", length = 100)
    var description = ""

    @Column(name = "SHARE_DATA_TO_BE_CLONE")
    var isShareDataToBeClone = false

    @Column(name = "SHARE_DATA_TO_BE_CLONE_SECURITY")
    var shareDataToBeCloneSecurityCode = ""

    @Column(name = "STATUS_ACTIVE")
    var isStatusActive = true

    @Column(name = "WEBSERVICE_ACTIVE")
    var isWebServiceActive = false

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
    //	private boolean nomorUrutDocTransFollowApp = true;
    /*
	 * FORMAT FAKTUR DAN ALAMAT
	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 = "";
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 = "";
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 = "";
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 = "";
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Customer, Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1 = "";
    //LOG
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}