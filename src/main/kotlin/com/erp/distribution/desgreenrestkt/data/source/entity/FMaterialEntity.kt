package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumMaterialType
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumUom
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "fmaterial")
data class FMaterialEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    var id  :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 */
    @JsonIgnore
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId  :Int =0,

    @JsonIgnore
    @Column(name = "NO_URUT", length = 6)
    var noUrut  :Int =0,

    @Column(name = "PCODE", length = 15)
    var pcode :String ="",

    @Column(name = "BARCODE", length = 50)
    var barcode :String ="",

    @Column(name = "PNAME", length = 120)
    var pname :String ="",

    @Lob
    @Type(type = "text")
    @Column(name = "DESCRIPTION")
    var description :String ="",

    @JsonIgnore
    @Column(name = "OLD_KODE1", length = 15)
    var oldKode1 :String ="",

    @JsonIgnore
    @Column(name = "VARIAN_NAME", length = 25)
    var varianName :String ="",

    @Column(name = "FREE_GOOD")
    var freeGood  :Boolean =false,

    @JsonIgnore
    @Column(name = "SHORTNAME", length = 30)
    var shortname :String ="",

    @Column(name = "STATUS_ACTIVE")
    var statusActive  :Boolean =true,

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
    var exclusiveDivisionTransaction  :Boolean =false,

    @JsonIgnore
    @Column(name = "EXCLUSIVE_DIVISION_VIEW")
    var exclusiveDivisionView  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean  :Int =0,

    /*
	 * TAX
	 */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID", nullable = true)
    //	private FTax ftaxBean;
    @Column(name = "ftaxBean", nullable = true)
    var ftaxBean  :Int? =0,

    @Column(name = "TAXABLE")
    var taxable  :Boolean =true,

    /*
	 * Adalah Vendor Utama Produk Tersebut
	 * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
	 * 
	 * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 */
    @JsonIgnore
    @Column(name = "EXCLUSIVE_VENDOR_TRANSACTION")
    var exclusiveVendorTransaction  :Boolean =false,

    @ManyToOne
    @JoinColumn(name = "fvendorBean", referencedColumnName = "ID")
    var fvendorBean: FVendor? = FVendor(),

    //	@Column(name="fvendorBean", nullable = false)
    //	private Integer fvendorBean  :Int =0,;
    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
    @JsonIgnore
    @Column(name = "fwarehouseBean_Utm", nullable = true)
    var fwarehouseBean_Utm  :Int? =0,

    @JsonIgnore
    @Column(name = "MATERIAL_TYPE", length = 5)
    @Enumerated(EnumType.STRING)
    var materialType: EnumMaterialType? = EnumMaterialType.FERT,

    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    @Column(name = "fdistributionChannelBean", nullable = false)
    var fdistributionChannelBean  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    @Column(name = "fmaterialGroup3Bean", nullable = false)
    var fmaterialGroup3Bean  :Int =0,

    /*
	 * KLASIFIKASI: SALES
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    @Column(name = "fmaterialSalesBrandBean", nullable = false)
    var fmaterialSalesBrandBean  :Int =0,

    //BATCH CODE --> Berhubungan dengan Stockist atau Gudang
    //PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
    @JsonIgnore
    @Column(name = "PRINCIPAL_CODE", length = 25)
    var principalCode :String ="",

    @JsonIgnore
    @Column(name = "BATCH_CODE", length = 30)
    var batchCode :String ="",

    @JsonIgnore
    @Column(name = "PRODUCTION_CODE", length = 30)
    var productionCode :String ="",

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "PRODUCTION_DATE")
    var productionDate :Date =Date(),

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRED_DATE")
    var expiredDate :Date =Date(),

    @JsonIgnore
    @Column(name = "PRODCLASS", length = 3)
    var prodclass  :Int =0,

    @Column(name = "UOM1", length = 10)
    var uom1 :String ="",

    @Column(name = "UOM2", length = 10)
    var uom2 :String ="",

    @Column(name = "UOM3", length = 10)
    var uom3 :String ="",

    @Column(name = "UOM4", length = 10) //UOM4 Paling Kecil
    var uom4 :String ="",

    @Column(name = "CONVFACT1", length = 6)
    var convfact1 :Int = 1, //uom1 to uom4

    @Column(name = "CONVFACT2", length = 5)
    var convfact2 :Int = 1, //uom2 to uom4

    @Column(name = "CONVFACT3", length = 4)
    var convfact3 :Int = 1, //uom3 to uom4

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
    var priceUom :EnumUom = EnumUom.UOM1,

    //	@Transient
    //	private Integer temp_QtySaldo  :Int =0,; //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur
    @JsonIgnore
    @Transient
    var temp_QtySaldo  :Double =0.0, //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur

    /*
	 * Harga Beli berbeda dengan HPP
	 */
    @Transient
    @JsonIgnore
    var hargaBeliUOM4NetAfterPpn  :Double =0.0,

    /*
	 * HPP disimpan Perdivisi pada Tabel AccBalanceHpp
	 * HPP adalah Harga Net Per Barang SEBELUM PPN
	 */
    @JsonIgnore
    @Column(name = "HPP_AWAL")
    var hppAwalPprice2  :Double =0.0, //Jika tidak ada HPP Awal maka menggunakan Harga Barang Net Sebelum PPN

    @Transient
    @JsonIgnore
    var hppLifo  :Double =0.0,

    @Transient
    @JsonIgnore
    var hppLifoTotalAmount  :Double =0.0,

    @Transient
    @JsonIgnore
    var hppAverage  :Double =0.0,

    @Transient
    @JsonIgnore
    var hppAverageTotalAmount  :Double =0.0,

    @Transient
    @JsonIgnore
    var hppFifo  :Double =0.0,

    @Transient
    @JsonIgnore
    var hppFifoTotalAmount  :Double =0.0,

    //PPRICE:: Disimpan dalam satuan Terbesar dan Terkecil. Setelah dan sebelum PPN
    //	@JsonIgnore
    @Column(name = "PPRICE")
    var pprice  :Double =0.0,

    @Column(name = "PPRICE_AFTERPPN")
    var ppriceAfterPpn  :Double =0.0,

    //	@JsonIgnore
    @Column(name = "PPRICE2")
    var pprice2  :Double =0.0,

    @Column(name = "PPRICE2_AFTERPPN")
    var pprice2AfterPpn  :Double =0.0,

    //	@JsonIgnore
    @Column(name = "SPRICE")
    var sprice  :Double =0.0,

    @Column(name = "SPRICE_AFTERPPN")
    var spriceAfterPpn: Double = 0.0,

    //	@JsonIgnore
    @Column(name = "SPRICE2")
    var sprice2  :Double =0.0,

    @Column(name = "SPRICE2_AFTERPPN")
    var sprice2AfterPpn  :Double =0.0,

    /*
	 * Min Stok: sama dengan Buffer Stock
	 * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
	 * cara yang paling realistis untuk mengukur stok over adalah
	 * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
	 */
    @JsonIgnore
    @Column(name = "MIN_QTY_STOK", length = 8) //Buffer Stok
    var minQtyStok  :Int =0,

    //TIDAK BOLEH DIGANTI-GANTI
    //dalam Mili Liter
    @JsonIgnore
    @Column(name = "VOLUME", length = 7)
    var volumeSmalest  :Int =0,

    //Dalam Grams
    @Column(name = "WEIGHT_SMALEST", length = 7)
    var weightSmalest  :Int =0,

    //Dalam Grams
    @Column(name = "CASE_WEIGHT", length = 7)
    var caseWeight  :Int =0,

    //IN Cm.. Cm3
    @JsonIgnore
    @Column(name = "CASE_WIDTH", length = 7)
    var caseWidth  :Int =0, //Panjang

    @JsonIgnore
    @Column(name = "CASE_HEIGHT", length = 7)
    var caseHeight  :Int =0, //Tinggi

    @JsonIgnore
    @Column(name = "CASE_DEPTH", length = 7)
    var caseDepth  :Int =0, //Lebar (dibalik kan kalau english.. hehehe)

    @JsonIgnore
    @Column(name = "FLAG_NEWITEM")
    var flagNewItem  :Boolean =false,

    @JsonIgnore
    @Column(name = "FLAG_NEWPRICE")
    var flagNewPrice  :Boolean =false,

    @JsonIgnore
    @Column(name = "USE_SPRICEALT")
    var useSpriceAlt  :Boolean =false,

    //#PRICEALT1 -- Retail -->ALL AFTER PPN
    //Retail-Besar
    @JsonIgnore
    @Column(name = "SPRICEALT_RETAIL_BES")
    var spriceAltRetailBes  :Double =0.0,

    //Retail-Sedang
    @JsonIgnore
    @Column(name = "SPRICEALT_RETAIL_SED")
    var spriceAltRetailSed  :Double =0.0,

    //Retail-Kecil
    @JsonIgnore
    @Column(name = "SPRICEALT_RETAIL_KEC")
    var spriceAltRetailKec  :Double =0.0,

    //#PRICEALT2 --> Grosir 1
    //Grosir-Besar
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR1_BES")
    var spriceAltGrosir1Bes  :Double =0.0,

    //Grosir-Sed
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR1_SED")
    var spriceAltGrosir1Sed  :Double =0.0,

    //Grosir-Kec
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR1_KEC")
    var spriceAltGrosir1Kec  :Double =0.0,

    //#PRICEALT3 --> Grosir 2
    //Grosir2-Bes
    //Grosir-Besar
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR2_BES")
    var spriceAltGrosir2Bes  :Double =0.0,

    //Grosir-Sed
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR2_SED")
    var spriceAltGrosir2Sed  :Double =0.0,

    //Grosir-Kec
    @JsonIgnore
    @Column(name = "SPRICEALT_GROSIR2_KEC")
    var spriceAltGrosir2Kec  :Double =0.0,

    //Grosir Quantity
    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL1")
    var spriceAltGrosirQtyMoreEqual1  :Double =0.0,

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL2")
    var spriceAltGrosirQtyMoreEqual2  :Double =0.0,

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL3")
    var spriceAltGrosirQtyMoreEqual3  :Double =0.0,

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_MORE_EQUAL4")
    var spriceAltGrosirQtyMoreEqual4  :Double =0.0,

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE1")
    var spriceAltGrosirQtyValue1  :Double =0.0,

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE2")
    var spriceAltGrosirQtyValue2  :Double =0.0,

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE3")
    var spriceAltGrosirQtyValue3  :Double =0.0,

    @JsonIgnore
    @Column(name = "SPRICE_ALT_GROSIRQTY_VALUE4")
    var spriceAltGrosirQtyValue4  :Double =0.0,

    @JsonIgnore
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @JsonIgnore
    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @JsonIgnore
    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID


): Serializable