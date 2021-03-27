package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FCustomerGroup
import com.erp.distribution.desgreenrestkt.domain.model.FDivision
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fdivision")
data class FDivisionEntity (
    @Id
    @Column(name = "ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id  :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId  :Int =0,

    @Column(name = "KODE1", length = 10)
    var kode1  :String ="",

    @Column(name = "KODE2", length = 20)
    var kode2  :String ="",

    @Column(name = "DESCRIPTION", length = 100)
    var description  :String ="",

    @Column(name = "DIFF_COMPANY_ACC")
    var diffCompanyAccount  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID", nullable=true)
    //    private AccCostCenter accCostCenterBean;
    @Column(name = "accCostCenterBean", nullable = false)
    var accCostCenterBean  :Int =0,

    /*
	 * Common Division Parameter
	 */
    //	@Column(name="USE_OWN_NOMOR_URUT_MASTER")
    //	private boolean useOwnNomorUrutMaster=false;
    //	@Column(name="USE_OWN_NOMOR_URUT_TRANSAKSI")
    //	private boolean useOwnNomorUrutTransaksi=false;
    @Column(name = "USE_NOMOR_URUT_MATERIAL_TO_COMPANY")
    var useNomorUrutMaterialToCompany  :Boolean =false,

    @Column(name = "USE_NOMOR_URUT_CUSTOMER_TO_COMPANY")
    var useNomorUrutCustomerToCompany  :Boolean =false,

    @Column(name = "USE_NOMOR_URUT_VENDOR_TO_COMPANY")
    var useNomorUrutVendorToCompany  :Boolean =false,

    @Column(name = "USE_NOMOR_URUT_TRANSAKSI_TO_COMPANY")
    var useNomorUrutTransaksiToCompany  :Boolean =true,

    @Column(name = "USE_NOMOR_URUT_JURNAL_TO_COMPANY")
    var useNomorUrutJurnalToCompany  :Boolean =true,

    /*
	 * Untuk Warehouse, Salesman Wajib Jadi satu dalam company
	 */
    @Column(name = "SHARE_MATERIAL_TO_COMPANY")
    var shareMaterialToCompany  :Boolean =false,

    @Column(name = "SHARE_MATERIAL_ORG_TO_COMPANY")
    var shareMaterialOrgToCompany  :Boolean =false,

    @Column(name = "SHARE_CUSTOMER_TO_COMPANY")
    var shareCustomerToCompany  :Boolean =false,

    @Column(name = "SHARE_CUSTOMER_ORG_TO_COMPANY")
    var shareCustomerOrgToCompany  :Boolean =false,

    @Column(name = "SHARE_SALESMAN_TO_COMPANY")
    var shareSalesmanToCompany  :Boolean =false,

    @Column(name = "SHARE_WAREHOUSE_TO_COMPANY")
    var shareWarehouseToCompany  :Boolean =false,

    @Column(name = "SHARE_VENDOR_TO_COMPANY")
    var shareVendorToCompany  :Boolean =false,

    /*
	 * General Ledger Accounting: Default True
	 */
    @Column(name = "SHARE_COA_TO_COMPANY")
    var shareCoaToCompany  :Boolean =true,

    @Column(name = "SHARE_COA_ORG_TO_COMPANY")
    var shareCoaOrgToCompany  :Boolean =true,

    /*
	 * TRANSAKSI: Default false
	 */
    @Column(name = "SHARE_TRANSAKSI_TO_COMPANY")
    var shareTransaksiToCompany  :Boolean =false,

    /*
	 * DISKON PROMO DAN DISKON NOTA: Menggunakan Aturan Divisi
	 * 
	 */
    @Column(name = "SHARE_PROMOTION_RULES_TO_COMPANY")
    var sharePromotionRulesToCompany  :Boolean =false,

    @Column(name = "SHARE_DISKON_NOTA_TO_COMPANY")
    var shareDiskonNotaToCompany  :Boolean =false,

    /*
	 * ACCOUNTING
	 */
    @Column(name = "ONLY_READ_HISDIV_COA_INP")
    var userOnlyRead_HisDivision_Coa_WhenInput  :Boolean =false,

    @Column(name = "NO_TAX_TRANS")
    var noTax_Trans  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="fcompanyBean", referencedColumnName="ID")
    //	private FCompany fcompanyBean;
    @Column(name = "fcompanyBean", nullable = false)
    var fcompanyBean  :Int =0,

    /*
	 * ****************************
	 */
    @Column(name = "STATUS_ACTIVE")
    var statusActive  :Boolean =true,

    @Column(name = "WEBSERVICE_ACTIVE")
    var webServiceActive  :Boolean =false,

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
    //	private boolean nomorUrutDocTransFollowCorp  :Boolean =true,;
    /*
	 * Urutan master product, material, salesman, mengikuti parameter level 2. Level Perusahaan
	 */
    var sysvarNomorUrutMasterFollowCorp  :Boolean =false,
    var sysvarFormatFakturFollowCorp  :Boolean =false,

    //	/*
    //	 * FORMAT FAKTUR DAN ALAMAT
    //	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1  :String ="",;
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1  :String ="",;
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1  :String ="",;
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1  :String ="",;
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Mengikuti Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1  :String ="",;
    //LOG
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy  :String ="" //User ID

): Serializable

internal fun FDivisionEntity.toDomain(): FDivision {
    return FDivision(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,

        diffCompanyAccount = diffCompanyAccount,
        accCostCenterBean = accCostCenterBean,

        useNomorUrutMaterialToCompany = useNomorUrutMaterialToCompany,
        useNomorUrutCustomerToCompany = useNomorUrutCustomerToCompany,
        useNomorUrutVendorToCompany = useNomorUrutVendorToCompany,
        useNomorUrutTransaksiToCompany = useNomorUrutTransaksiToCompany,
        useNomorUrutJurnalToCompany = useNomorUrutJurnalToCompany,
        shareMaterialToCompany = shareMaterialToCompany,
        shareMaterialOrgToCompany = shareMaterialOrgToCompany,
        shareCustomerToCompany = shareCustomerToCompany,
        shareCustomerOrgToCompany = shareCustomerOrgToCompany,
        shareSalesmanToCompany = shareSalesmanToCompany,
        shareWarehouseToCompany = shareWarehouseToCompany,
        shareVendorToCompany = shareVendorToCompany,

        shareCoaToCompany = shareCoaToCompany,
        shareCoaOrgToCompany = shareCoaOrgToCompany,

        shareTransaksiToCompany = shareTransaksiToCompany,

        sharePromotionRulesToCompany = sharePromotionRulesToCompany,
        shareDiskonNotaToCompany = shareDiskonNotaToCompany,

        userOnlyRead_HisDivision_Coa_WhenInput = userOnlyRead_HisDivision_Coa_WhenInput,
        noTax_Trans = noTax_Trans,


        fcompanyBean = fcompanyBean,
        webServiceActive = webServiceActive,
        sysvarNomorUrutMasterFollowCorp = sysvarNomorUrutMasterFollowCorp,
        sysvarFormatFakturFollowCorp = sysvarFormatFakturFollowCorp,

        statusActive = statusActive,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}