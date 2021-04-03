package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FArea
import com.erp.distribution.desgreenrestkt.domain.model.FCustomer
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumCurrency
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipePajakCustomer
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTunaiKredit
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fcustomer")
data class FCustomerEntity (
    @Id
    @Column(name = "ID", length = 9)
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_master",
        allocationSize = 20,
        initialValue = 912345668
    )
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    var id :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceID :Int =0,

    @Column(name = "CUSTNO", length = 25)
    var custno :String ="",

    @Column(name = "OUTLET_ACTIVE")
    var outletActive :Boolean =false,

    @JsonIgnore
    @Column(name = "OLD_KODE1", length = 25)
    var oldKode1 :String ="",

    @JsonIgnore
    @Column(name = "FLAG_NEWITEM")
    var flagNewItem :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable =false)
    var fdivisionBean :Int =0,

    /*
	 * Fungsinya: Jika menarik data dari sistem lain dimana mempunyai kode customer yang berbeda
	 * Maka akan di konversikan menjadi kode pada sistem
	 * Pada import data: ada opsi pilih mapping customer yang mana
	 */
    @JsonIgnore
    @Column(name = "MAPPING_IN_CODE1", length = 15)
    var mappingInCode1 :String ="",

    @JsonIgnore
    @Column(name = "MAPPING_IN_CODE2", length = 15)
    var mappingInCode2 :String ="",

    @JsonIgnore
    @Column(name = "MAPPING_IN_CODE3", length = 15)
    var mappingInCode3 :String ="",

    /*
	 * Digunanakan untuk menghasilkan kode yang berbeda jika di extract
	 * Fungsinya: Untuk Integrasi dengan Sistem Lain jika ternyata kode customer berbeda
	 */
    @JsonIgnore
    @Column(name = "MAPPING_OUT_CODE1", length = 15)
    var mappingOutCode1 :String ="",

    @JsonIgnore
    @Column(name = "MAPPING_OUT_CODE2", length = 15)
    var mappingOutCode2 :String ="",

    @JsonIgnore
    @Column(name = "CUST_GP1", length = 3)
    var custGroupPromo1 :Int =0, //GROUP PROMO

    @JsonIgnore
    @Column(name = "CUST_GP2", length = 3)
    var custGroupPromo2 :Int =0, //GROUP PROMO

    @Column(name = "CUST_NAME", length = 120)
    var custname :String ="",

    @JsonIgnore
    @Column(name = "CURRENCY", length = 5)
    @Enumerated(EnumType.STRING)
    var currency :EnumCurrency = EnumCurrency.IDR,

    /*
	 * PERPAJAKAN / TAX
	 */
    @JsonIgnore
    @Column(name = "PKP")
    var pkp :Boolean =true,

    @JsonIgnore
    @Column(name = "NAMA_PRSH_FAKTURPAJAK", length = 200)
    var namaPrshFakturPajak :String ="",

    @JsonIgnore
    @Column(name = "ALAMAT_PRSH_FAKTURPAJAK", length = 200)
    var alamatPrshFakturPajak :String ="",

    @JsonIgnore
    @Column(name = "NAMA_PENGUSAHA_KENA_PAJAK", length = 120)
    var namaPengusahaKenaPajak :String ="",

    @JsonIgnore
    @Column(name = "NIK_PAJAK", length = 120)
    var nikPajak :String ="",

    @Column(name = "NPWP", length = 100)
    var npwp :String ="",

    @JsonIgnore
    @Column(name = "TANGGAL_PENGUKUHAN_PKP")
    @Temporal(TemporalType.DATE)
    var tanggalPengukuhanPkp :Date? =Date(),

    @JsonIgnore
    @Column(name = "TIPE_PAJAK_CUSTOMER")
    @Enumerated(EnumType.ORDINAL)
    var tipePajakCustomer :EnumTipePajakCustomer = EnumTipePajakCustomer.REG_01,

    @Column(name = "TUNAI_KREDIT", length = 5)
    @Enumerated(EnumType.STRING)
    var tunaikredit :EnumTunaiKredit = EnumTunaiKredit.T,

    @Column(name = "LAMA_CREDIT", length = 4)
    var lamaCredit :Int =0,

    @Column(name = "CREDIT_LIMIT")
    var creditlimit :Double =0.0,

    @Column(name = "MAX_INVOICE", length = 5)
    var maxInvoice :Int =0,

    @JsonIgnore
    @Column(name = "NAMA_PEMILIK", length = 75)
    var namaPemilik :String ="",

    @Column(name = "ADDRESS1", length = 120)
    var address1 :String ="",

    @Column(name = "ADDRESS2", length = 55)
    var address2 :String ="",

    @Column(name = "ADDRESS3", length = 55)
    var address3 :String ="",

    @Column(name = "CITY1", length = 50)
    var city1 :String ="",

    @JsonIgnore
    @Column(name = "CITY2", length = 50)
    var city2 :String ="",

    @Column(name = "STATE1", length = 50)
    var state1 :String ="",

    @Column(name = "PHONE1", length = 50)
    var phone1 :String ="",

    @JsonIgnore
    @Column(name = "PHONE2", length = 50)
    var phone2 :String ="",

    @Column(name = "POSTCODE", length = 20)
    var postcode :String ="",

    @Column(name = "EMAIL", length = 120)
    var email :String ="",

    @Column(name = "WHATSAPP", length = 50)
    var whatsApp :String ="",

    @Column(name = "STATUS_ACTIVE")
    var statusActive :Boolean =false,

    //Tidak akan dipkai: 
//    @Column(name = "HARIKUNJUNGAN", length = 3)
//    var harikunjungan :Int =0,
//    @Column(name = "PEKANKUNJUNGAN", length = 3)
//    var pekankunjungan :Int =0,

    @JsonIgnore
    @Column(name = "NOEFFCALL")
    var noeffcall :Boolean =false,

    @Column(name = "LATITUDE", precision = 11)
    var latitude :Double =0.0,

    @Column(name = "LONGITUDE", precision = 11)
    var longitude :Double =0.0,

    /*
	 * sementara belum dipakai sampai tahu principal atau SAP
	 */
    //SHIPTO BILLTO	
    //	@Column(name="SHIPTOBILLTO", length=20)
    //	private String shipToBillTo ="";
    //	@Column(name="BILLTO", length=20)
    //	private String billTo ="";
    @JsonIgnore
    var basicDisc1Barang :Double =0.0,

    @JsonIgnore
    var basicDisc1PlusBarang :Double =0.0,

    @JsonIgnore
    @Column(name = "DISC1REGMANUAL")
    var disc1RegManual :Boolean =false,

    @JsonIgnore
    @Column(name = "DISCPLUSREGMANUAL")
    var discPlusRegManual :Boolean =false,

    /*
	 * 0 = Menggunakan Harga Reguler Distributor (tidak mengenal harga bertingkat)
	 * 1 = Menggunakan harga Retail
	 * 2 = Menggunakan harga Grosir A
	 * 3 = Menggunakan harga Grosir B
	 */
    @JsonIgnore
    @Column(name = "PRICE_ALT_SWALAYAN", nullable = true)
    var priceAltSwalayan :Int? =0,

    //	@ManyToOne
    //	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup fcustomerGroupBean;
    @Column(name = "fcustomerGroupBean", nullable =false)
    var fcustomerGroupBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
    //	private FSubArea fsubAreaBean;
    @Column(name = "fsubAreaBean", nullable =false)
    var fsubAreaBean :Int =0,

    /*
	 * CLASSIFIKASI MATERIAL & SALES
	 * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
	 * jika Division = All Division maka exclusiveDivisionView tidak berlaku
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    @Column(name = "fdistributionChannelBean", nullable =false)
    var fdistributionChannelBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    @Column(name = "ftPriceAlthBean", nullable = true)
    var ftPriceAlthBean :Int? =0,

    /*
	 * reject promotion rules setting
	 */
    @Column(name = "NO_PROMOTION_RULES")
    var noPromotionRules :Boolean =false,

    /*
	 * Sales Covered
	 * dan Jadwal Kunjungan
	 */
    @JsonIgnore
    @Column(name = "EXCLUSIVE_SALESMAN")
    var exclusiveSalesman :Boolean =false,

    @JsonIgnore
    @Transient
    var notes :String ="",

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID

): Serializable

internal fun FCustomerEntity.toDomain(): FCustomer {
    return FCustomer(
        id = id,
        sourceID = sourceID,

        custno = custno,
        outletActive = outletActive,
        oldKode1 = oldKode1,
        flagNewItem = flagNewItem,
        fdivisionBean = fdivisionBean,

        mappingInCode1 = mappingInCode1,
        mappingInCode2 = mappingInCode2,
        mappingInCode3 = mappingInCode3,

        mappingOutCode1 = mappingOutCode1,
        mappingOutCode2 = mappingOutCode2,
        custGroupPromo1 = custGroupPromo1,
        custGroupPromo2 = custGroupPromo2,

        custname = custname,
        currency = currency,

        pkp = pkp,
        namaPrshFakturPajak = namaPrshFakturPajak,
        alamatPrshFakturPajak = alamatPrshFakturPajak,
        namaPengusahaKenaPajak = namaPengusahaKenaPajak,
        nikPajak = nikPajak,
        npwp = npwp,
        tanggalPengukuhanPkp = tanggalPengukuhanPkp,
        tipePajakCustomer = tipePajakCustomer,

        tunaikredit = tunaikredit,
        lamaCredit = lamaCredit,
        creditlimit = creditlimit,
        maxInvoice = maxInvoice,
        namaPemilik = namaPemilik,
        address1 = address1,
        address2 = address2,
        address3 = address3,
        city1 = city1,
        city2 = city2,
        state1 = state1,
        phone1 = phone1,
        phone2 = phone2,
        postcode = postcode,
        email = email,
        whatsApp = whatsApp,
        statusActive = statusActive,

        noeffcall = noeffcall,
        latitude = latitude,
        longitude = longitude,

        basicDisc1Barang = basicDisc1Barang,
        basicDisc1PlusBarang = basicDisc1PlusBarang,
        disc1RegManual = disc1RegManual,
        discPlusRegManual = discPlusRegManual,
        priceAltSwalayan = priceAltSwalayan,

        fcustomerGroupBean = fcustomerGroupBean,
        fsubAreaBean = fsubAreaBean,
        fdistributionChannelBean = fdistributionChannelBean,
        ftPriceAlthBean = ftPriceAlthBean,
        noPromotionRules = noPromotionRules,
        exclusiveSalesman = exclusiveSalesman,
        notes = notes,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}