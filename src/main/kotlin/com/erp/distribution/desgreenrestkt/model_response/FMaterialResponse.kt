package com.erp.distribution.desgreenrestkt.model_response

import org.hibernate.annotations.Type
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Lob

class FMaterialResponse : Serializable {
    private val id = 0

    /*
     * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
     * keperluan diantaranya:
     * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
     */
    //    @JsonIgnore
    //    private Integer sourceId =0;
    //    @JsonIgnore
    //    private Integer noUrut=0;
    private val pcode = ""
    private val barcode = ""
    private val pname = ""

    @Lob
    @Type(type = "text")
    private val description = ""

    //    @JsonIgnore
    //    private String oldKode1="";
    //    @JsonIgnore
    //    private String varianName="";
    @Column(name = "FREE_GOOD")
    private val freeGood = false

    //    @JsonIgnore
    //    private String shortname="";
    //    @JsonIgnore
    private val statusActive = true

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
    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    private val fdivisionBean = 0

    /*
     * TAX
     */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    @Column(name = "ftaxBean")
    private val ftaxBean = 0

    @Column(name = "TAXABLE")
    private val taxable = true

    /*
     * Adalah Vendor Utama Produk Tersebut
     * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
     *
     * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
     * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
     */
    //    @JsonIgnore
    //    @Column(name="EXCLUSIVE_VENDOR_TRANSACTION")
    //    private boolean exclusiveVendorTransaction=false;
    //    @ManyToOne
    //    @JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //    private FVendor fvendorBean;
    @Column(name = "fvendorBean", nullable = false)
    private val fvendorBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
    //    @JsonIgnore
    //    @Column(name="fwarehouseBean_Utm", nullable = false)
    //    private Integer fwarehouseBean_Utm = 0;
    //    @JsonIgnore
    //    @Enumerated(EnumType.STRING)
    //    private EnumMaterialType materialType;
    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    private val fdistributionChannelBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    @Column(name = "fmaterialGroup3Bean", nullable = false)
    private val fmaterialGroup3Bean = 0

    /*
     * KLASIFIKASI: SALES
     */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    @Column(name = "fmaterialSalesBrandBean", nullable = false)
    private val fmaterialSalesBrandBean = 0
    private val uom1 = ""
    private val uom2 = ""
    private val uom3 = ""
    private val uom4 = ""
    private val convfact1 = 1 //uom1 to uom4
    private val convfact2 = 1 //uom2 to uom4
    private val convfact3 = 1 //uom3 to uom4

    /*
     * PRICE yang muncul pada faktur dengan menggunakan UOM
     * 0. dan 1 adalah default
     * 2. uom 2
     * 3. uom 3
     * 4.uom 4
     *
     */
    //    @JsonIgnore
    //    @Column(name="PRICE_UOM", length=1)
    //    @Enumerated(EnumType.ORDINAL)
    //    private EnumUom priceUom = EnumUom.UOM1;
    //PPRICE:: Disimpan dalam satuan Terbesar dan Terkecil. Setelah dan sebelum PPN
    private val pprice = 0.0
    private val ppriceAfterPpn = 0.0
    private val pprice2 = 0.0
    private val pprice2AfterPpn = 0.0
    private val sprice = 0.0
    private val spriceAfterPpn: Double? = null
    private val sprice2 = 0.0
    private val sprice2AfterPpn = 0.0 /*
     * Min Stok: sama dengan Buffer Stock
     * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
     * cara yang paling realistis untuk mengukur stok over adalah
     * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
     */
    //    @JsonIgnore
    //    @Column(name="MIN_QTY_STOK", length=8)//Buffer Stok
    //    private Integer minQtyStok=0;
    //    public FMaterial toResponse(){
    //        FMaterial fmaterialBean = new FMaterial();
    //            id = id,
    //            pcode = pcode,
    //            pname = pname,
    //
    //            uom1 = uom1,
    //            uom2 = uom2,
    //            uom3 = uom3,
    //            uom4 = uom4,
    //            convfact1 = convfact1,
    //            convfact2 = convfact2,
    //            convfact3 = convfact3,
    //
    //            pprice = pprice,
    //            pprice2 = pprice2,
    //            ppriceAfterPpn = ppriceAfterPpn,
    //            pprice2AfterPpn = pprice2AfterPpn,
    //
    //            sprice = sprice,
    //            sprice2 = sprice2,
    //            spriceAfterPpn = spriceAfterPpn,
    //            sprice2AfterPpn = sprice2AfterPpn,
    //
    //            statusActive = statusActive,
    //
    //            ftaxBean = ftaxBean,
    //            taxable = taxable,
    //            fvendorBean = fvendorBean,
    //            fdivisionBean = fdivisionBean,
    //
    //            fmaterialSalesBrandBean = fmaterialSalesBrandBean,
    //            fmaterialGroup3Bean = fmaterialGroup3Bean;
    //
    //        return fmaterialBean;
    //    }
}