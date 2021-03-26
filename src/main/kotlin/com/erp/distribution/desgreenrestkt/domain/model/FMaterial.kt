package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialSalesBrandEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumMaterialType
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumUom
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialRes
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.*

data class FMaterial(
    var id  :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 */
    @JsonIgnore
    var sourceId  :Int =0,

    @JsonIgnore
    var noUrut  :Int =0,

    var pcode :String ="",

    var barcode :String ="",

    var pname :String ="",

    @Lob
    @Type(type = "text")
    var description :String ="",

    @JsonIgnore
    var oldKode1 :String ="",

    @JsonIgnore
    var varianName :String ="",

    var freeGood  :Boolean =false,

    @JsonIgnore
    var shortname :String ="",

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
    var exclusiveDivisionTransaction  :Boolean =false,

    @JsonIgnore
    var exclusiveDivisionView  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean  :Int =0,

    /*
	 * TAX
	 */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID", nullable = true)
    //	private FTax ftaxBean;
    var ftaxBean  :Int? =0,

    var taxable  :Boolean =true,

    /*
	 * Adalah Vendor Utama Produk Tersebut
	 * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
	 * 
	 * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 */
    @JsonIgnore
    var exclusiveVendorTransaction  :Boolean =false,

//    @ManyToOne
//    @JoinColumn(name = "fvendorBean", referencedColumnName = "ID")
    var fvendorBean: FVendorEntity = FVendorEntity(),
//    var fvendorBean  :Int =0,


    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
    @JsonIgnore
    var fwarehouseBean_Utm  :Int? =0,

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    var materialType: EnumMaterialType? = EnumMaterialType.FERT,

    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    var fdistributionChannelBean  :Int =0,

    //	@ManyToOne
//    @JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    val fmaterialGroup3EntityBean :FMaterialGroup3Entity = FMaterialGroup3Entity(),
//    var fmaterialGroup3Bean  :Int =0,

    /*
	 * KLASIFIKASI: SALES
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    var fmaterialSalesBrandEntityBean :FMaterialSalesBrandEntity = FMaterialSalesBrandEntity(),
//    var fmaterialSalesBrandBean  :Int =0,

    //BATCH CODE --> Berhubungan dengan Stockist atau Gudang
    //PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
    @JsonIgnore
    var principalCode :String ="",

    @JsonIgnore
    var batchCode :String? ="",

    @JsonIgnore
    var productionCode :String? ="",

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    var productionDate :Date? =Date(),

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    var expiredDate :Date? =Date(),

    @JsonIgnore
    var prodclass  :Int =0,

    var uom1 :String ="",

    var uom2 :String ="",

    var uom3 :String ="",

    var uom4 :String ="",

    var convfact1 :Int = 1, //uom1 to uom4

    var convfact2 :Int = 1, //uom2 to uom4

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
    var pprice  :Double =0.0,

    var ppriceAfterPpn  :Double =0.0,

    //	@JsonIgnore
    var pprice2  :Double =0.0,

    var pprice2AfterPpn  :Double =0.0,

    //	@JsonIgnore
    var sprice  :Double =0.0,

    var spriceAfterPpn: Double = 0.0,

    //	@JsonIgnore
    var sprice2  :Double =0.0,

    var sprice2AfterPpn  :Double =0.0,

    /*
	 * Min Stok: sama dengan Buffer Stock
	 * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
	 * cara yang paling realistis untuk mengukur stok over adalah
	 * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
	 */
    @JsonIgnore
    var minQtyStok  :Int =0,

    //TIDAK BOLEH DIGANTI-GANTI
    //dalam Mili Liter
    @JsonIgnore
    var volumeSmalest  :Int =0,

    //Dalam Grams
    var weightSmalest  :Int =0,

    //Dalam Grams
    var caseWeight  :Int =0,

    //IN Cm.. Cm3
    @JsonIgnore
    var caseWidth  :Int =0, //Panjang

    @JsonIgnore
    var caseHeight  :Int =0, //Tinggi

    @JsonIgnore
    var caseDepth  :Int =0, //Lebar (dibalik kan kalau english.. hehehe)

    @JsonIgnore
    var flagNewItem  :Boolean =false,

    @JsonIgnore
    var flagNewPrice  :Boolean =false,

    @JsonIgnore
    var useSpriceAlt  :Boolean =false,

    //#PRICEALT1 -- Retail -->ALL AFTER PPN
    //Retail-Besar
    @JsonIgnore
    var spriceAltRetailBes  :Double =0.0,

    //Retail-Sedang
    @JsonIgnore
    var spriceAltRetailSed  :Double =0.0,

    //Retail-Kecil
    @JsonIgnore
    var spriceAltRetailKec  :Double =0.0,

    //#PRICEALT2 --> Grosir 1
    //Grosir-Besar
    @JsonIgnore
    var spriceAltGrosir1Bes  :Double =0.0,

    //Grosir-Sed
    @JsonIgnore
    var spriceAltGrosir1Sed  :Double =0.0,

    //Grosir-Kec
    @JsonIgnore
    var spriceAltGrosir1Kec  :Double =0.0,

    //#PRICEALT3 --> Grosir 2
    //Grosir2-Bes
    //Grosir-Besar
    @JsonIgnore
    var spriceAltGrosir2Bes  :Double =0.0,

    //Grosir-Sed
    @JsonIgnore
    var spriceAltGrosir2Sed  :Double =0.0,

    //Grosir-Kec
    @JsonIgnore
    var spriceAltGrosir2Kec  :Double =0.0,

    //Grosir Quantity
    @JsonIgnore
    var spriceAltGrosirQtyMoreEqual1  :Double =0.0,

    @JsonIgnore
    var spriceAltGrosirQtyMoreEqual2  :Double =0.0,

    @JsonIgnore
    var spriceAltGrosirQtyMoreEqual3  :Double =0.0,

    @JsonIgnore
    var spriceAltGrosirQtyMoreEqual4  :Double =0.0,

    @JsonIgnore
    var spriceAltGrosirQtyValue1  :Double =0.0,

    @JsonIgnore
    var spriceAltGrosirQtyValue2  :Double =0.0,

    @JsonIgnore
    var spriceAltGrosirQtyValue3  :Double =0.0,

    @JsonIgnore
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
    var modifiedBy :String? ="" //User ID


): Serializable


internal fun FMaterial.toEntity(): FMaterialEntity {
    return FMaterialEntity(
        id = id,
        sourceId = sourceId,
        noUrut = noUrut,
        pcode = pcode,
        barcode = barcode,
        pname = pname,
        description = description,
        oldKode1 = oldKode1,
        varianName = varianName,
        freeGood = freeGood,
        shortname = shortname,
        statusActive = statusActive,
        exclusiveDivisionTransaction = exclusiveDivisionTransaction,
        exclusiveDivisionView = exclusiveDivisionView,

        taxable = taxable,
        exclusiveVendorTransaction = exclusiveDivisionTransaction,
        materialType = materialType,
        principalCode = principalCode,
        batchCode = batchCode,
        productionCode = productionCode,
        productionDate = productionDate,
        expiredDate = expiredDate,
        prodclass = prodclass,

        uom1 = uom1,
        uom2 = uom2,
        uom3 = uom3,
        uom4 = uom4,
        convfact1 = convfact1,
        convfact2 = convfact2,
        convfact3 = convfact3,

        priceUom = priceUom,
        temp_QtySaldo = temp_QtySaldo,

        hppAwalPprice2 = hppAwalPprice2,
        hppLifo = hppLifo,
        hppLifoTotalAmount = hppAverageTotalAmount,
        hppAverage = hppAverageTotalAmount,
        hppFifo = hppFifo,
        hppFifoTotalAmount = hppFifoTotalAmount,

        pprice = pprice,
        pprice2 = pprice2,
        ppriceAfterPpn = ppriceAfterPpn,
        pprice2AfterPpn = pprice2AfterPpn,
        sprice = sprice,
        sprice2 = sprice2,
        spriceAfterPpn = spriceAfterPpn,
        sprice2AfterPpn = sprice2AfterPpn,

        minQtyStok = minQtyStok,
        volumeSmalest = volumeSmalest,
        weightSmalest = weightSmalest,

        caseWeight = caseWeight,
        caseWidth = caseWidth,
        caseHeight = caseHeight,
        caseDepth = caseDepth,

        flagNewItem = flagNewItem,
        flagNewPrice = flagNewPrice,

        useSpriceAlt = useSpriceAlt,

        spriceAltRetailBes  = spriceAltRetailBes,
        spriceAltRetailSed = spriceAltRetailSed,
        spriceAltRetailKec = spriceAltRetailKec,

        spriceAltGrosir1Bes = spriceAltGrosir1Bes,
        spriceAltGrosir1Sed = spriceAltGrosir1Sed,
        spriceAltGrosir1Kec = spriceAltGrosir1Kec,

        spriceAltGrosir2Bes = spriceAltGrosir2Bes,
        spriceAltGrosir2Sed = spriceAltGrosir2Sed,
        spriceAltGrosir2Kec = spriceAltGrosir2Kec,

        spriceAltGrosirQtyMoreEqual1 = spriceAltGrosirQtyMoreEqual1,
        spriceAltGrosirQtyMoreEqual2 = spriceAltGrosirQtyMoreEqual2,
        spriceAltGrosirQtyMoreEqual3 = spriceAltGrosirQtyMoreEqual3,
        spriceAltGrosirQtyMoreEqual4 = spriceAltGrosirQtyMoreEqual4,

        spriceAltGrosirQtyValue1 = spriceAltGrosirQtyValue1,
        spriceAltGrosirQtyValue2 = spriceAltGrosirQtyValue2,
        spriceAltGrosirQtyValue3 = spriceAltGrosirQtyValue3,
        spriceAltGrosirQtyValue4 = spriceAltGrosirQtyValue4,


        fmaterialGroup3EntityBean = fmaterialGroup3EntityBean,
        fmaterialSalesBrandEntityBean = fmaterialSalesBrandEntityBean?.let { it },
        ftaxBean = ftaxBean?.let { it },
        fvendorBean = fvendorBean,
        fdivisionBean = fdivisionBean,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy!!

    )

}
internal fun FMaterial.toResponse(): FMaterialRes {
    return FMaterialRes(
        id = id,
        sourceId = sourceId,
        pcode = pcode,
        pname = pname,
        uom1 = uom1,
        uom2 = uom2,
        uom3 = uom3,
        uom4 = uom4,
        convfact1 = convfact1,
        convfact2 = convfact2,
        convfact3 = convfact3,
        pprice = pprice,
        pprice2 = pprice2,
        ppriceAfterPpn = ppriceAfterPpn,
        pprice2AfterPpn = pprice2AfterPpn,
        sprice = sprice,
        sprice2 = sprice2,
        spriceAfterPpn = spriceAfterPpn,
        sprice2AfterPpn = sprice2AfterPpn,

        statusActive = statusActive,

        fmaterialGroup3Bean = fmaterialGroup3EntityBean.id,
        fmaterialSalesBrandBean = fmaterialSalesBrandEntityBean?.let { it.id },
        ftaxBean = ftaxBean?.let { it },
        taxable = taxable,
        fvendorBean = fvendorBean.id,
        fdivisionBean = fdivisionBean,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy!!

    )

}