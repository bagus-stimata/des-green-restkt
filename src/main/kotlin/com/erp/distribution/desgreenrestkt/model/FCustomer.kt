package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumCurrency
import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumTipePajakCustomer
import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumTunaiKredit
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fcustomer")
class FCustomer {
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
    var sourceID = 0

    @Column(name = "CUSTNO", length = 25)
    var custno = ""

    @Column(name = "OUTLET_ACTIVE")
    var isOutletActive = false

    @JsonIgnore
    @Column(name = "OLD_KODE1", length = 25)
    var oldKode1 = ""

    @JsonIgnore
    @Column(name = "FLAG_NEWITEM")
    var isFlagNewItem = false

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    /*
	 * Fungsinya: Jika menarik data dari sistem lain dimana mempunyai kode customer yang berbeda
	 * Maka akan di konversikan menjadi kode pada sistem
	 * Pada import data: ada opsi pilih mapping customer yang mana
	 */
    @JsonIgnore
    @Column(name = "MAPPING_IN_CODE1", length = 15)
    var mappingInCode1 = ""

    @JsonIgnore
    @Column(name = "MAPPING_IN_CODE2", length = 15)
    var mappingInCode2 = ""

    @JsonIgnore
    @Column(name = "MAPPING_IN_CODE3", length = 15)
    var mappingInCode3 = ""

    /*
	 * Digunanakan untuk menghasilkan kode yang berbeda jika di extract
	 * Fungsinya: Untuk Integrasi dengan Sistem Lain jika ternyata kode customer berbeda
	 */
    @JsonIgnore
    @Column(name = "MAPPING_OUT_CODE1", length = 15)
    var mappingOutCode1 = ""

    @JsonIgnore
    @Column(name = "MAPPING_OUT_CODE2", length = 15)
    var mappingOutCode2 = ""

    @JsonIgnore
    @Column(name = "CUST_GP1", length = 3)
    var custGroupPromo1 = 0 //GROUP PROMO

    @JsonIgnore
    @Column(name = "CUST_GP2", length = 3)
    var custGroupPromo2 = 0 //GROUP PROMO

    @Column(name = "CUST_NAME", length = 120)
    var custname = ""

    @JsonIgnore
    @Column(name = "CURRENCY", length = 5)
    @Enumerated(EnumType.STRING)
    var currency = EnumCurrency.IDR

    /*
	 * PERPAJAKAN / TAX
	 */
    @JsonIgnore
    @Column(name = "PKP")
    var isPkp = true

    @JsonIgnore
    @Column(name = "NAMA_PRSH_FAKTURPAJAK", length = 200)
    var namaPrshFakturPajak = ""

    @JsonIgnore
    @Column(name = "ALAMAT_PRSH_FAKTURPAJAK", length = 200)
    var alamatPrshFakturPajak = ""

    @JsonIgnore
    @Column(name = "NAMA_PENGUSAHA_KENA_PAJAK", length = 120)
    var namaPengusahaKenaPajak = ""

    @JsonIgnore
    @Column(name = "NIK_PAJAK", length = 120)
    var nikPajak = ""

    @Column(name = "NPWP", length = 100)
    var npwp = ""

    @JsonIgnore
    @Column(name = "TANGGAL_PENGUKUHAN_PKP")
    @Temporal(TemporalType.DATE)
    var tanggalPengukuhanPkp = Date()

    @JsonIgnore
    @Column(name = "TIPE_PAJAK_CUSTOMER")
    @Enumerated(EnumType.ORDINAL)
    var tipePajakCustomer = EnumTipePajakCustomer.REG_01

    @Column(name = "TUNAI_KREDIT", length = 5)
    @Enumerated(EnumType.STRING)
    var tunaikredit = EnumTunaiKredit.T

    @Column(name = "LAMA_CREDIT", length = 4)
    var lamaCredit = 0

    @Column(name = "CREDIT_LIMIT")
    var creditlimit = 0.0

    @Column(name = "MAX_INVOICE", length = 5)
    var maxInvoice = 0

    @JsonIgnore
    @Column(name = "NAMA_PEMILIK", length = 75)
    var namaPemilik = ""

    @Column(name = "ADDRESS1", length = 120)
    var address1 = ""

    @Column(name = "ADDRESS2", length = 55)
    var address2 = ""

    @Column(name = "ADDRESS3", length = 55)
    var address3 = ""

    @Column(name = "CITY1", length = 50)
    var city1 = ""

    @JsonIgnore
    @Column(name = "CITY2", length = 50)
    var city2 = ""

    @Column(name = "STATE1", length = 50)
    var state1 = ""

    @Column(name = "PHONE1", length = 50)
    var phone1 = ""

    @JsonIgnore
    @Column(name = "PHONE2", length = 50)
    var phone2 = ""

    @Column(name = "POSTCODE", length = 20)
    var postcode = ""

    @Column(name = "EMAIL", length = 120)
    var email = ""

    @Column(name = "WHATSAPP", length = 50)
    var whatsApp = ""

    @Column(name = "STATUS_ACTIVE")
    var statusActive: Boolean = false

    //Tidak akan dipkai: 
    @Column(name = "HARIKUNJUNGAN", length = 3)
    var harikunjungan = 0

    @Column(name = "PEKANKUNJUNGAN", length = 3)
    var pekankunjungan = 0

    @JsonIgnore
    @Column(name = "NOEFFCALL")
    var isNoeffcall = false

    @Column(name = "LATITUDE", precision = 11)
    var latitude = 0.0

    @Column(name = "LONGITUDE", precision = 11)
    var longitude = 0.0

    /*
	 * sementara belum dipakai sampai tahu principal atau SAP
	 */
    //SHIPTO BILLTO	
    //	@Column(name="SHIPTOBILLTO", length=20)
    //	private String shipToBillTo ="";
    //	@Column(name="BILLTO", length=20)
    //	private String billTo ="";
    @JsonIgnore
    var basicDisc1Barang = 0.0

    @JsonIgnore
    var basicDisc1PlusBarang = 0.0

    @JsonIgnore
    @Column(name = "DISC1REGMANUAL")
    var isDisc1RegManual = false

    @JsonIgnore
    @Column(name = "DISCPLUSREGMANUAL")
    var isDiscPlusRegManual = false

    /*
	 * 0 = Menggunakan Harga Reguler Distributor (tidak mengenal harga bertingkat)
	 * 1 = Menggunakan harga Retail
	 * 2 = Menggunakan harga Grosir A
	 * 3 = Menggunakan harga Grosir B
	 */
    @JsonIgnore
    @Column(name = "PRICE_ALT_SWALAYAN", length = 2)
    var priceAltSwalayan = 0

    //	@ManyToOne
    //	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup fcustomerGroupBean;
    @Column(name = "fcustomerGroupBean", nullable = false)
    var fcustomerGroupBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
    //	private FSubArea fsubAreaBean;
    @Column(name = "fsubAreaBean", nullable = false)
    var fsubAreaBean = 0

    /*
	 * CLASSIFIKASI MATERIAL & SALES
	 * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
	 * jika Division = All Division maka exclusiveDivisionView tidak berlaku
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    @Column(name = "fdistributionChannelBean", nullable = false)
    var fdistributionChannelBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    @Column(name = "ftPriceAlthBean")
    var ftPriceAlthBean = 0

    /*
	 * reject promotion rules setting
	 */
    @Column(name = "NO_PROMOTION_RULES")
    var isNoPromotionRules = false

    /*
	 * Sales Covered
	 * dan Jadwal Kunjungan
	 */
    @JsonIgnore
    @Column(name = "EXCLUSIVE_SALESMAN")
    var isExclusiveSalesman = false

    @JsonIgnore
    @Transient
    var notes = ""

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}