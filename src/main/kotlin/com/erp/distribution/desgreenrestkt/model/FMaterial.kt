package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumMaterialType
import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumUom
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "fmaterial")
class FMaterial : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 */
    @JsonIgnore
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId = 0

    @JsonIgnore
    @Column(name = "NO_URUT", length = 6)
    var noUrut = 0

    @Column(name = "PCODE", length = 15)
    var pcode = ""

    @Column(name = "BARCODE", length = 50)
    var barcode = ""

    @Column(name = "PNAME", length = 120)
    var pname = ""

    @Lob
    @Type(type = "text")
    @Column(name = "DESCRIPTION")
    var description = ""

    @JsonIgnore
    @Column(name = "OLD_KODE1", length = 15)
    var oldKode1 = ""

    @JsonIgnore
    @Column(name = "VARIAN_NAME", length = 25)
    var varianName = ""

    @Column(name = "FREE_GOOD")
    var isFreeGood = false

    @JsonIgnore
    @Column(name = "SHORTNAME", length = 30)
    var shortname = ""

    @Column(name = "STATUS_ACTIVE")
    var isStatusActive = true

    /*
	 * KLASIFIKASI: BASIC
	 */
    /* 
	 * exclusiveDivisionTransaction: Input Penjualan dan Pembelian akan menolak jika item product berlainan Divisi
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 * jika Division = All Division maka exclusiveDivisionView tidak berlaku
	 * 
	 * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
	 * 
	 * 	 Dalam satu Divisi biasanya terdapat beberapa Vendor
	 */
    @JsonIgnore
    @Column(name = "EXCLUSIVE_DIVISION_TRANSACTION")
    var isExclusiveDivisionTransaction = false

    @JsonIgnore
    @Column(name = "EXCLUSIVE_DIVISION_VIEW")
    var isExclusiveDivisionView = false

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    /*
	 * TAX
	 */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    @Column(name = "ftaxBean")
    var ftaxBean = 0

    @Column(name = "TAXABLE")
    var isTaxable = true

    /*
	 * Adalah Vendor Utama Produk Tersebut
	 * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
	 * 
	 * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 */
    @JsonIgnore
    @Column(name = "EXCLUSIVE_VENDOR_TRANSACTION")
    var isExclusiveVendorTransaction = false

    @ManyToOne
    @JoinColumn(name = "fvendorBean", referencedColumnName = "ID")
    var fvendorBean: FVendor? = null

    //	@Column(name="fvendorBean", nullable = false)
    //	private Integer fvendorBean = 0;
    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
    @JsonIgnore
    @Column(name = "fwarehouseBean_Utm", nullable = false)
    var fwarehouseBean_Utm = 0

    @JsonIgnore
    @Column(name = "MATERIAL_TYPE", length = 5)
    @Enumerated(EnumType.STRING)
    var materialType: EnumMaterialType? = null

    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    @Column(name = "fdistributionChannelBean", nullable = false)
    var fdistributionChannelBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    @Column(name = "fmaterialGroup3Bean", nullable = false)
    var fmaterialGroup3Bean = 0

    /*
	 * KLASIFIKASI: SALES
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    @Column(name = "fmaterialSalesBrandBean", nullable = false)
    var fmaterialSalesBrandBean = 0

    //BATCH CODE --> Berhubungan dengan Stockist atau Gudang
    //PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
    @JsonIgnore
    @Column(name = "PRINCIPAL_CODE", length = 25)
    var principalCode = ""

    @JsonIgnore
    @Column(name = "BATCH_CODE", length = 30)
    var batchCode = ""

    @JsonIgnore
    @Column(name = "PRODUCTION_CODE", length = 30)
    var productionCode = ""

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "PRODUCTION_DATE")
    var productionDate = Date()

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRED_DATE")
    var expiredDate = Date()

    @JsonIgnore
    @Column(name = "PRODCLASS", length = 3)
    var prodclass = 0

    @Column(name = "UOM1", length = 10)
    var uom1 = ""

    @Column(name = "UOM2", length = 10)
    var uom2 = ""

    @Column(name = "UOM3", length = 10)
    var uom3 = ""

    @Column(name = "UOM4", length = 10) //UOM4 Paling Kecil
    var uom4 = ""

    @Column(name = "CONVFACT1", length = 6)
    var convfact1 = 1 //uom1 to uom4

    @Column(name = "CONVFACT2", length = 5)
    var convfact2 = 1 //uom2 to uom4

    @Column(name = "CONVFACT3", length = 4)
    var convfact3 = 1 //uom3 to uom4

    /*
	 * PRICE yang muncul pada faktur dengan menggunakan UOM
	 * 0. dan 1 adalah default
	 * 2. uom 2
	 * 3. uom 3
	 * 4.uom 4
	 * 
	 */
    @JsonIgnore
    @Column(name = "PRICE_UOM", length = 1)
    @Enumerated(EnumType.ORDINAL)
    var priceUom = EnumUom.UOM1

    //	@Transient
    //	private Integer temp_QtySaldo = 0; //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur
    @JsonIgnore
    @Transient
    var temp_QtySaldo = 0.0 //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur

    /*
	 * Harga Beli berbeda dengan HPP
	 */
    @Transient
    @JsonIgnore
    var hargaBeliUOM4NetAfterPpn = 0.0

    /*
	 * HPP disimpan Perdivisi pada Tabel AccBalanceHpp
	 * HPP adalah Harga Net Per Barang SEBELUM PPN
	 */
    @JsonIgnore
    @Column(name = "HPP_AWAL")
    var hppAwalPprice2 = 0.0 //Jika tidak ada HPP Awal maka menggunakan Harga Barang Net Sebelum PPN

    @Transient
    @JsonIgnore
    var hppLifo = 0.0

    @Transient
    @JsonIgnore
    var hppLifoTotalAmount = 0.0

    @Transient
    @JsonIgnore
    var hppAverage = 0.0

    @Transient
    @JsonIgnore
    var hppAverageTotalAmount = 0.0

    @Transient
    @JsonIgnore
    var hppFifo = 0.0

    @Transient
    @JsonIgnore
    var hppFifoTotalAmount = 0.0

    //PPRICE:: Disimpan dalam satuan Terbesar dan Terkecil. Setelah dan sebelum PPN
    //	@JsonIgnore
    @Column(name = "PPRICE")
    var pprice = 0.0

    @Column(name = "PPRICE_AFTERPPN")
    var ppriceAfterPpn = 0.0

    //	@JsonIgnore
    @Column(name = "PPRICE2")
    var pprice2 = 0.0

    @Column(name = "PPRICE2_AFTERPPN")
    var pprice2AfterPpn = 0.0

    //	@JsonIgnore
    @Column(name = "SPRICE")
    var sprice = 0.0

    @Column(name = "SPRICE_AFTERPPN")
    var spriceAfterPpn: Double? = null

    //	@JsonIgnore
    @Column(name = "SPRICE2")
    var sprice2 = 0.0

    @Column(name = "SPRICE2_AFTERPPN")
    var sprice2AfterPpn = 0.0

    /*
	 * Min Stok: sama dengan Buffer Stock
	 * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
	 * cara yang paling realistis untuk mengukur stok over adalah
	 * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
	 */
    @JsonIgnore
    @Column(name = "MIN_QTY_STOK", length = 8) //Buffer Stok
    var minQtyStok = 0

    //TIDAK BOLEH DIGANTI-GANTI
    //dalam Mili Liter
    @JsonIgnore
    @Column(name = "VOLUME", length = 7)
    var volumeSmalest = 0

    //Dalam Grams
    @Column(name = "WEIGHT_SMALEST", length = 7)
    var weightSmalest = 0

    //Dalam Grams
    @Column(name = "CASE_WEIGHT", length = 7)
    var caseWeight = 0

    //IN Cm.. Cm3
    @JsonIgnore
    @Column(name = "CASE_WIDTH", length = 7)
    var caseWidth = 0 //Panjang

    @JsonIgnore
    @Column(name = "CASE_HEIGHT", length = 7)
    var caseHeight = 0 //Tinggi

    @JsonIgnore
    @Column(name = "CASE_DEPTH", length = 7)
    var caseDepth = 0 //Lebar (dibalik kan kalau english.. hehehe)

    @JsonIgnore
    @Column(name = "FLAG_NEWITEM")
    var isFlagNewItem = false

    @JsonIgnore
    @Column(name = "FLAG_NEWPRICE")
    var isFlagNewPrice = false

    @JsonIgnore
    @Column(name = "USE_SPRICEALT")
    var isUseSpriceAlt = false

    //#PRICEALT1 -- Retail -->ALL AFTER PPN
    //Retail-Besar
    @JsonIgnore
    @Column(name = "SPRICEALT_RETAIL_BES")
    var spriceAltRetailBes = 0.0

    //Retail-Sedang
    @JsonIgnore
    @Column(name = "SPRICEALT_RETAIL_SED")
    var spriceAltRetailSed = 0.0

    //Retail-Kecil
    @JsonIgnore
    @Column(name = "SPRICEALT_RETAIL_KEC")
    var spriceAltRetailKec = 0.0

    //#PRICEALT2 --> Grosir 1
    //Grosir-Besar
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR1_BES")
    var spriceAltGrosir1Bes = 0.0

    //Grosir-Sed
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR1_SED")
    var spriceAltGrosir1Sed = 0.0

    //Grosir-Kec
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR1_KEC")
    var spriceAltGrosir1Kec = 0.0

    //#PRICEALT3 --> Grosir 2
    //Grosir2-Bes
    //Grosir-Besar
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR2_BES")
    var spriceAltGrosir2Bes = 0.0

    //Grosir-Sed
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR2_SED")
    var spriceAltGrosir2Sed = 0.0

    //Grosir-Kec
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR2_KEC")
    var spriceAltGrosir2Kec = 0.0

    //Grosir Quantity
    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL1")
    var spriceAltGrosirQtyMoreEqual1 = 0.0

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL2")
    var spriceAltGrosirQtyMoreEqual2 = 0.0

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL3")
    var spriceAltGrosirQtyMoreEqual3 = 0.0

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL4")
    var spriceAltGrosirQtyMoreEqual4 = 0.0

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE1")
    var spriceAltGrosirQtyValue1 = 0.0

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE2")
    var spriceAltGrosirQtyValue2 = 0.0

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE3")
    var spriceAltGrosirQtyValue3 = 0.0

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE4")
    var spriceAltGrosirQtyValue4 = 0.0

    @JsonIgnore
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @JsonIgnore
    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @JsonIgnore
    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

    constructor() {}
    constructor(id: Int, pcode: String, pname: String, description: String, freeGood: Boolean, statusActive: Boolean, fdivisionBean: Int, ftaxBean: Int, taxable: Boolean, fvendorBean: FVendor?, materialType: EnumMaterialType?, fdistributionChannelBean: Int, fmaterialGroup3Bean: Int, fmaterialSalesBrandBean: Int, uom1: String, uom2: String, uom3: String, uom4: String, convfact1: Int, convfact2: Int, convfact3: Int, pprice: Double, ppriceAfterPpn: Double, pprice2: Double, pprice2AfterPpn: Double, sprice: Double, spriceAfterPpn: Double?, sprice2: Double, sprice2AfterPpn: Double) {
        this.id = id
        this.pcode = pcode
        this.pname = pname
        this.description = description
        isFreeGood = freeGood
        isStatusActive = statusActive
        this.fdivisionBean = fdivisionBean
        this.ftaxBean = ftaxBean
        isTaxable = taxable
        this.fvendorBean = fvendorBean
        this.materialType = materialType
        this.fdistributionChannelBean = fdistributionChannelBean
        this.fmaterialGroup3Bean = fmaterialGroup3Bean
        this.fmaterialSalesBrandBean = fmaterialSalesBrandBean
        this.uom1 = uom1
        this.uom2 = uom2
        this.uom3 = uom3
        this.uom4 = uom4
        this.convfact1 = convfact1
        this.convfact2 = convfact2
        this.convfact3 = convfact3
        this.pprice = pprice
        this.ppriceAfterPpn = ppriceAfterPpn
        this.pprice2 = pprice2
        this.pprice2AfterPpn = pprice2AfterPpn
        this.sprice = sprice
        this.spriceAfterPpn = spriceAfterPpn
        this.sprice2 = sprice2
        this.sprice2AfterPpn = sprice2AfterPpn
    }

}