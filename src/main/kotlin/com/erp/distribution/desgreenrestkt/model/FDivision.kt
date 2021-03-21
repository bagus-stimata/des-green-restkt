package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fdivision")
class FDivision {
    @Id
    @Column(name = "ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId = 0

    @Column(name = "KODE1", length = 10)
    var kode1 = ""

    @Column(name = "KODE2", length = 20)
    var kode2 = ""

    @Column(name = "DESCRIPTION", length = 100)
    var description = ""

    @Column(name = "DIFF_COMPANY_ACC")
    var diffCompanyAccount = false

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID", nullable=true)
    //    private AccCostCenter accCostCenterBean;
    @Column(name = "accCostCenterBean", nullable = false)
    var accCostCenterBean = 0

    /*
	 * Common Division Parameter
	 */
    //	@Column(name="USE_OWN_NOMOR_URUT_MASTER")
    //	private boolean useOwnNomorUrutMaster=false;
    //	@Column(name="USE_OWN_NOMOR_URUT_TRANSAKSI")
    //	private boolean useOwnNomorUrutTransaksi=false;
    @Column(name = "USE_NOMOR_URUT_MATERIAL_TO_COMPANY")
    var useNomorUrutMaterialToCompany = false

    @Column(name = "USE_NOMOR_URUT_CUSTOMER_TO_COMPANY")
    var useNomorUrutCustomerToCompany = false

    @Column(name = "USE_NOMOR_URUT_VENDOR_TO_COMPANY")
    var useNomorUrutVendorToCompany = false

    @Column(name = "USE_NOMOR_URUT_TRANSAKSI_TO_COMPANY")
    var useNomorUrutTransaksiToCompany = true

    @Column(name = "USE_NOMOR_URUT_JURNAL_TO_COMPANY")
    var useNomorUrutJurnalToCompany = true

    /*
	 * Untuk Warehouse, Salesman Wajib Jadi satu dalam company
	 */
    @Column(name = "SHARE_MATERIAL_TO_COMPANY")
    var shareMaterialToCompany = false

    @Column(name = "SHARE_MATERIAL_ORG_TO_COMPANY")
    var shareMaterialOrgToCompany = false

    @Column(name = "SHARE_CUSTOMER_TO_COMPANY")
    var shareCustomerToCompany = false

    @Column(name = "SHARE_CUSTOMER_ORG_TO_COMPANY")
    var shareCustomerOrgToCompany = false

    @Column(name = "SHARE_SALESMAN_TO_COMPANY")
    var shareSalesmanToCompany = false

    @Column(name = "SHARE_WAREHOUSE_TO_COMPANY")
    var shareWarehouseToCompany = false

    @Column(name = "SHARE_VENDOR_TO_COMPANY")
    var shareVendorToCompany = false

    /*
	 * General Ledger Accounting: Default True
	 */
    @Column(name = "SHARE_COA_TO_COMPANY")
    var shareCoaToCompany = true

    @Column(name = "SHARE_COA_ORG_TO_COMPANY")
    var shareCoaOrgToCompany = true

    /*
	 * TRANSAKSI: Default false
	 */
    @Column(name = "SHARE_TRANSAKSI_TO_COMPANY")
    var shareTransaksiToCompany = false

    /*
	 * DISKON PROMO DAN DISKON NOTA: Menggunakan Aturan Divisi
	 * 
	 */
    @Column(name = "SHARE_PROMOTION_RULES_TO_COMPANY")
    var sharePromotionRulesToCompany = false

    @Column(name = "SHARE_DISKON_NOTA_TO_COMPANY")
    var shareDiskonNotaToCompany = false

    /*
	 * ACCOUNTING
	 */
    @Column(name = "ONLY_READ_HISDIV_COA_INP")
    var userOnlyRead_HisDivision_Coa_WhenInput = false

    @Column(name = "NO_TAX_TRANS")
    var noTax_Trans = false

    //	@ManyToOne
    //	@JoinColumn(name="fcompanyBean", referencedColumnName="ID")
    //	private FCompany fcompanyBean;
    @Column(name = "fcompanyBean", nullable = false)
    var fcompanyBean = 0

    /*
	 * ****************************
	 */
    @Column(name = "STATUS_ACTIVE")
    var statusActive = true

    @Column(name = "WEBSERVICE_ACTIVE")
    var webServiceActive = false

    /*
	 * SETTING YANG BERLAKU UNTUK SEMUA DIVISI
	 * JIKA KOSONG MAKA MENGGUNAKAN PRIORITAS ATASNYA
	 * 1. Parameter System
	 * 2. Corporation
	 * 3. Division 
	 */
    /*
	 * Urutan nomor dalam satu divisi biasanya mengikuti perusahaan, karena mempunyai nomor urut faktur pajak yang sama
	 * Tidak digunakan. Karena urutan Dokumen Transaksi  mengikuti Company
	 */
    //	@Column(name="NOMOR_URUT_DOC_FOLLOW_CORP") //Tidak boleh diubah-ubah
    //	private boolean nomorUrutDocTransFollowCorp = true;
    /*
	 * Urutan master product, material, salesman, mengikuti parameter level 2. Level Perusahaan
	 */
    var sysvarNomorUrutMasterFollowCorp = false
    var sysvarFormatFakturFollowCorp = false

    //	/*
    //	 * FORMAT FAKTUR DAN ALAMAT
    //	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 = "";
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 = "";
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 = "";
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 = "";
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Mengikuti Mengikuti Corporation	
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