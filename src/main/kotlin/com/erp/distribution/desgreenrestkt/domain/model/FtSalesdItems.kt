package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumUom
import com.erp.distribution.desgreenrestkt.presentation.model.FtSalesdItemsRes
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FtSalesdItems (
    var id: Long =0L,

    @Column(name = "FREE_GOOD")
    var freeGood  :Boolean =false,

    @Column(name = "NOURUT", length = 4)
    var noUrut  :Int =0,

    @Column(name = "NOTES", length = 120)
    var notes :String ="",

    @Column(name = "SPRICE")
    var sprice  :Double =0.0,

    @Transient
    var spricePpnRp  :Double =0.0,

    /*
	 * Dasar harga total
	 */
    @Column(name = "TAX")
    var tax  :Boolean =true,

    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    //Boleh Kosong
    @Column(name = "ftaxBean", nullable =true)
    var ftaxBean: Int? =0,

    @Column(name = "TAX_PERCENT")
    var taxPercent  :Double =0.0,

    /*
	 * End: Dasar harga total
	 */
    @Transient
    var spriceUom2  :Double =0.0,

    @Transient
    var spriceUom3  :Double =0.0,

    @Transient
    var spriceUom4  :Double =0.0,

    @Transient
    var spriceAfterPpn  :Double =0.0,

    @Transient
    var spriceUom2AfterPpn  :Double =0.0,

    @Transient
    var spriceUom3AfterPpn  :Double =0.0,

    @Transient
    var spriceUom4AfterPpn  :Double =0.0,

    //HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
    @Transient
    var spriceNET_Uom1AfterDiscAfterPpn  :Double =0.0,

    @Transient
    var spriceNET_Uom2AfterDiscAfterPpn  :Double =0.0,

    @Transient
    var spriceNET_Uom3AfterDiscAfterPpn  :Double =0.0,

    @Transient
    var spriceNET_Uom4AfterDiscAfterPpn  :Double =0.0,

    //	@Transient
    //	private Integer qty1=0.0;
    //	@Transient
    //	private Integer qty2=0.0;
    //	@Transient
    //	private Integer qty3=0.0;
    //	@Transient
    //	private Integer qty4=0.0;
    @Transient
    var qty1  :Double =0.0,

    @Transient
    var qty2  :Double =0.0,

    @Transient
    var qty3  :Double =0.0,

    @Transient
    var qty4  :Double =0.0,

    //	@Transient
    //	private Integer qty1Kembali=0.0;
    //	@Transient
    //	private Integer qty2Kembali=0.0;
    //	@Transient
    //	private Integer qty3Kembali=0.0;
    //	@Transient
    //	private Integer qty4Kembali=0.0;
    @Transient
    var qty1Kembali  :Double =0.0,

    @Transient
    var qty2Kembali  :Double =0.0,

    @Transient
    var qty3Kembali  :Double =0.0,

    @Transient
    var qty4Kembali  :Double =0.0,

    /*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
	qty = qtyTerkirim
	Total Jumlah yang diOrder = qty + qtyKembali
	*/
    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    //	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
    //	private Integer qtyKembali  :Int =0,;	
    //	@Transient
    //	private Integer qtyNET=0.0;
    @Column(name = "QTY", length = 9)
    var qty  :Double =0.0,

    @Column(name = "QTY_KEMBALI", length = 9) //Kembali dari pengiriman
    var qtyKembali  :Double =0.0,

    @Transient
    var qtyNET  :Double =0.0,

    /*
	 * Untuk Keperluan Retur
	 */
    //	@Column(name="QTYRETURN", length=9)
    //	private Integer qtyReturn =0.0;
    @Column(name = "QTYRETURN", length = 9)
    var qtyReturn  :Double =0.0,

    /*
	 * PRICE yang muncul pada faktur dengan menggunakan UOM
	 * 0. dan 1 adalah default
	 * 2. uom 2
	 * 3. uom 3
	 * 4.uom 4
	 * 
	 */
    @Column(name = "PRICE_UOM", length = 1)
    @Enumerated(EnumType.ORDINAL)
    var priceUom :EnumUom = EnumUom.UOM1,

    //Sub total sebelum diskon
    @Transient
    var subtotalRp  :Double =0.0,

    @Transient
    var subtotalPpnRp  :Double =0.0,

    @Transient
    var subtotalRpAfterPpn  :Double =0.0,

    @Column(name = "DISC1")
    var disc1  :Double =0.0,

    @Transient
    var disc1Rp  :Double =0.0,

    @Transient
    var disc1PpnRp  :Double =0.0,

    @Transient
    var disc1RpAfterPpn  :Double =0.0,

    @Transient
    var disc1RpAfterPpnUom1  :Double =0.0,

    @Transient
    var disc1RpAfterPpnUom2  :Double =0.0,

    @Transient
    var disc1RpAfterPpnUom3  :Double =0.0,

    @Transient
    var disc1RpAfterPpnUom4  :Double =0.0,

    @Column(name = "DISC2")
    var disc2: Double = 0.0,

    @Transient
    var disc2Rp  :Double =0.0,

    @Transient
    var disc2PpnRp  :Double =0.0,

    @Transient
    var disc2RpAfterPpn  :Double =0.0,

    @Transient
    var disc2RpAfterPpnUom1  :Double =0.0,

    @Transient
    var disc2RpAfterPpnUom2  :Double =0.0,

    @Transient
    var disc2RpAfterPpnUom3  :Double =0.0,

    @Transient
    var disc2RpAfterPpnUom4  :Double =0.0,

    @Column(name = "DISC3")
    var disc3  :Double =0.0,

    @Transient
    var disc3Rp  :Double =0.0,

    @Transient
    var disc3PpnRp  :Double =0.0,

    @Transient
    var disc3RpAfterPpn  :Double =0.0,

    @Transient
    var disc3RpAfterPpnUom1  :Double =0.0,

    @Transient
    var disc3RpAfterPpnUom2  :Double =0.0,

    @Transient
    var disc3RpAfterPpnUom3  :Double =0.0,

    @Transient
    var disc3RpAfterPpnUom4  :Double =0.0,

    @Transient
    var subtotalAfterDisc123Rp  :Double =0.0,

    @Transient
    var subtotalAfterDisc123PpnRp  :Double =0.0,

    @Transient
    var subtotalAfterDisc123RpAfterPpn  :Double =0.0,

    @Column(name = "DISC1_PLUS")
    var disc1Plus  :Double =0.0,

    @Transient
    var disc1PlusRp  :Double =0.0,

    @Transient
    var disc1PlusPpnRp  :Double =0.0,

    @Transient
    var disc1PlusRpAfterPpn  :Double =0.0,

    @Transient
    var disc1PlusRpAfterPpnUom1  :Double =0.0,

    @Transient
    var disc1PlusRpAfterPpnUom2  :Double =0.0,

    @Transient
    var disc1PlusRpAfterPpnUom3  :Double =0.0,

    @Transient
    var disc1PlusRpAfterPpnUom4  :Double =0.0,

    @Transient
    var subtotalAfterDisc1PlusRp  :Double =0.0,

    @Transient
    var subtotalAfterDisc1PlusPpnRp  :Double =0.0,

    @Transient
    var subtotalAfterDisc1PlusRpAfterPpn  :Double =0.0,

    @Column(name = "DISC2_PLUS")
    var disc2Plus  :Double =0.0,

    @Transient
    var disc2PlusRp  :Double =0.0,

    @Transient
    var disc2PlusPpnRp  :Double =0.0, //ppn

    @Transient
    var disc2PlusRpAfterPpn  :Double =0.0,

    @Transient
    var disc2PlusRpAfterPpnUom1  :Double =0.0,

    @Transient
    var disc2PlusRpAfterPpnUom2  :Double =0.0,

    @Transient
    var disc2PlusRpAfterPpnUom3  :Double =0.0,

    @Transient
    var disc2PlusRpAfterPpnUom4  :Double =0.0,

    @Transient
    var subtotalAfterDisc2PlusRp  :Double =0.0,

    @Transient
    var subtotalAfterDisc2PlusPpnRp  :Double =0.0, //ppn

    @Transient
    var subtotalAfterDisc2PlusRpAfterPpn  :Double =0.0,

    /*
	 * AFTER DISKON NOTA :: TAMBAHAN UNTUK MEMUDAHKAN PERHITUNGAN
	 */
    @Transient
    var discNota1  :Double =0.0,

    @Transient
    var discNota1Rp  :Double =0.0,

    @Transient
    var discNota1PpnRp  :Double =0.0, //ppn

    @Transient
    var discNota1RpAfterPpn  :Double =0.0,

    /*
	 * TAMBAHAN
	 */
    @Transient
    var subtotalAfterDiscNota1Rp  :Double =0.0,

    @Transient
    var subtotalAfterDiscNota1PpnRp  :Double =0.0, //ppn

    @Transient
    var subtotalAfterDiscNota1RpAfterPpn  :Double =0.0,

    @Transient
    var discNota2  :Double =0.0,

    @Transient
    var discNota2Rp  :Double =0.0,

    @Transient
    var discNota2PpnRp  :Double =0.0, //ppn

    @Transient
    var discNota2RpAfterPpn  :Double =0.0,

    @Transient
    var subtotalAfterDiscNota2Rp  :Double =0.0,

    @Transient
    var subtotalAfterDiscNota2PpnRp  :Double =0.0, //ppn

    @Transient
    var subtotalAfterDiscNota2RpAfterPpn  :Double =0.0,

    @Transient
    var discNotaPlus_FG  :Double =0.0,

    @Transient
    var discNotaPlusRp_FG  :Double =0.0,

    @Transient
    var discNotaPlusPpnRp_FG  :Double =0.0, //ppn

    @Transient
    var discNotaPlusRpAfterPpn_FG  :Double =0.0,

    @Transient
    var subtotalAfterDiscNotaPlusRp_FG  :Double =0.0,

    @Transient
    var subtotalAfterDiscNotaPlusPpnRp_FG  :Double =0.0, //ppn

    @Transient
    var subtotalAfterDiscNotaPlusRpAfterPpn_FG  :Double =0.0,

    @Transient
    var tempString :String ="",

    @Transient
    var tempInt  :Int =0,

    @Transient
    var tempDouble1  :Double =0.0,

    @Transient
    var tempDouble2  :Double =0.0,

    @Transient
    var tempDouble31  :Int =0,

    //TPR BARANG DAN UANG
    //	@Column(name="TPRB")
    //	private Double tprb=0.0;
    //	@Column(name="TPRU_DISC")
    //	private Double tpruDisc=0.0;
    //	@Column(name="TPRU_CASHBACK")
    //	private Double tpruCashback=0.0;

    @ManyToOne
    @JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
    var ftSaleshBean : FtSalesh = FtSalesh(),
//    @Column(name = "ftSaleshBean", nullable = false)
//    var ftSaleshBean: Long =0L,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean  :Int =0

): Serializable

internal fun FtSalesdItems.toEntity(): FtSalesdItemsEntity {
    return FtSalesdItemsEntity(
        id = id,
        freeGood = freeGood,
        noUrut = noUrut,
        notes = notes,

        sprice = sprice,
        spricePpnRp = spricePpnRp,
        tax = tax,
        ftaxBean = ftaxBean,
        taxPercent = taxPercent,

        spriceUom2 = spriceUom2,
        spriceUom3 = spriceUom3,
        spriceUom4 = spriceUom4,
        spriceAfterPpn = spriceAfterPpn,
        spriceUom2AfterPpn = spriceUom2AfterPpn,
        spriceUom3AfterPpn = spriceUom3AfterPpn,
        spriceUom4AfterPpn = spriceUom4AfterPpn,

        spriceNET_Uom1AfterDiscAfterPpn = spriceNET_Uom1AfterDiscAfterPpn,
        spriceNET_Uom2AfterDiscAfterPpn = spriceNET_Uom2AfterDiscAfterPpn,
        spriceNET_Uom3AfterDiscAfterPpn = spriceNET_Uom3AfterDiscAfterPpn,
        spriceNET_Uom4AfterDiscAfterPpn = spriceNET_Uom4AfterDiscAfterPpn,
        qty1 = qty1,
        qty2 = qty2,
        qty3 = qty3,
        qty4 = qty4,

        qty1Kembali = qty1Kembali,
        qty2Kembali = qty2Kembali,
        qty3Kembali = qty3Kembali,
        qty4Kembali = qty4Kembali,

        qty = qty,
        qtyKembali = qtyKembali,
        qtyNET = qtyNET,
        qtyReturn = qtyReturn,

        priceUom = priceUom,
        subtotalRp = subtotalRp,
        subtotalPpnRp = subtotalPpnRp,
        subtotalRpAfterPpn = subtotalRpAfterPpn,
        disc1 = disc1,
        disc1Rp = disc1Rp,
        disc1PpnRp = disc1PpnRp,
        disc1RpAfterPpn = disc1RpAfterPpn,

        disc1RpAfterPpnUom1 = disc1RpAfterPpnUom1,
        disc1RpAfterPpnUom2 = disc1RpAfterPpnUom2,
        disc1RpAfterPpnUom3 = disc1RpAfterPpnUom3,
        disc1RpAfterPpnUom4 = disc1RpAfterPpnUom4,

        disc2 = disc2,
        disc2Rp = disc2Rp,
        disc2PpnRp = disc2PpnRp,
        disc2RpAfterPpn = disc2RpAfterPpn,

        disc2RpAfterPpnUom1 = disc2RpAfterPpnUom1,
        disc2RpAfterPpnUom2 = disc2RpAfterPpnUom2,
        disc2RpAfterPpnUom3 = disc2RpAfterPpnUom3,
        disc2RpAfterPpnUom4 = disc2RpAfterPpnUom4,

        disc3 = disc3,
        disc3Rp = disc3Rp,
        disc3PpnRp = disc3PpnRp,
        disc3RpAfterPpn = disc3RpAfterPpn,

        disc3RpAfterPpnUom1 = disc3RpAfterPpnUom1,
        disc3RpAfterPpnUom2 = disc3RpAfterPpnUom2,
        disc3RpAfterPpnUom3 = disc3RpAfterPpnUom3,
        disc3RpAfterPpnUom4 = disc3RpAfterPpnUom4,

        subtotalAfterDisc123Rp = subtotalAfterDisc123Rp,
        subtotalAfterDisc123PpnRp = subtotalAfterDisc123PpnRp,
        subtotalAfterDisc123RpAfterPpn = subtotalAfterDisc123RpAfterPpn,

        disc1Plus = disc1Plus,
        disc1PlusRp = disc1PlusRp,
        disc1PlusPpnRp = disc1PlusPpnRp,
        disc1PlusRpAfterPpn = disc1PlusRpAfterPpn,

        disc1PlusRpAfterPpnUom1 = disc1PlusRpAfterPpnUom1,
        disc1PlusRpAfterPpnUom2 = disc1PlusRpAfterPpnUom2,
        disc1PlusRpAfterPpnUom3 = disc1PlusRpAfterPpnUom3,
        disc1PlusRpAfterPpnUom4 = disc1PlusRpAfterPpnUom4,

        subtotalAfterDisc1PlusRp = subtotalAfterDisc1PlusRp,
        subtotalAfterDisc1PlusPpnRp = subtotalAfterDisc1PlusPpnRp,
        subtotalAfterDisc1PlusRpAfterPpn = subtotalAfterDisc1PlusRpAfterPpn,
        disc2Plus = disc2Plus,
        disc2PlusRp = disc2PlusRp,
        disc2PlusPpnRp = disc2PlusPpnRp,
        disc2PlusRpAfterPpn = disc2PlusRpAfterPpn,

        disc2PlusRpAfterPpnUom1 = disc2PlusRpAfterPpnUom1,
        disc2PlusRpAfterPpnUom2 = disc2PlusRpAfterPpnUom2,
        disc2PlusRpAfterPpnUom3 = disc2PlusRpAfterPpnUom3,
        disc2PlusRpAfterPpnUom4 = disc2PlusRpAfterPpnUom4,

        subtotalAfterDisc2PlusRp = subtotalAfterDisc2PlusRp,
        subtotalAfterDisc2PlusPpnRp = subtotalAfterDisc2PlusPpnRp,
        subtotalAfterDisc2PlusRpAfterPpn = subtotalAfterDisc2PlusRpAfterPpn,

        discNota1 = discNota1,
        discNota1Rp = discNota1Rp,
        discNota1PpnRp = discNota1PpnRp,
        discNota1RpAfterPpn = discNota1RpAfterPpn,

        subtotalAfterDiscNota1Rp = subtotalAfterDiscNota1Rp,
        subtotalAfterDiscNota1PpnRp = subtotalAfterDiscNota1PpnRp,
        subtotalAfterDiscNota1RpAfterPpn = subtotalAfterDiscNota1RpAfterPpn,

        discNota2 = discNota2,
        discNota2Rp = discNota2Rp,
        discNota2PpnRp = discNota2PpnRp,
        discNota2RpAfterPpn = discNota2RpAfterPpn,
        subtotalAfterDiscNota2Rp = subtotalAfterDiscNota2Rp,
        subtotalAfterDiscNota2PpnRp = subtotalAfterDiscNota2PpnRp,
        subtotalAfterDiscNota2RpAfterPpn = subtotalAfterDiscNota2RpAfterPpn,

        discNotaPlus_FG = discNotaPlus_FG,
        discNotaPlusRp_FG = discNotaPlusRp_FG,
        discNotaPlusPpnRp_FG = discNotaPlusPpnRp_FG,
        discNotaPlusRpAfterPpn_FG = discNotaPlusRpAfterPpn_FG,
        subtotalAfterDiscNotaPlusRp_FG = subtotalAfterDiscNotaPlusRp_FG,
        subtotalAfterDiscNotaPlusPpnRp_FG = subtotalAfterDiscNotaPlusPpnRp_FG,
        subtotalAfterDiscNotaPlusRpAfterPpn_FG = subtotalAfterDiscNotaPlusRpAfterPpn_FG,

        tempString = tempString,
        tempInt = tempInt,
        tempDouble1 = tempDouble1,
        tempDouble2 = tempDouble2,
        tempDouble31 = tempDouble31,

        ftSaleshBean = ftSaleshBean?.let { FtSaleshEntity(ftSaleshBean.refno) },
        fmaterialBean = fmaterialBean,
    )
}

internal fun FtSalesdItems.toResponse(): FtSalesdItemsRes {
    return FtSalesdItemsRes(
        id = id,
        freeGood = freeGood,
        noUrut = noUrut,
        notes = notes,

        sprice = sprice,
        spricePpnRp = spricePpnRp,
        tax = tax,
        ftaxBean = ftaxBean,
        taxPercent = taxPercent,

        spriceUom2 = spriceUom2,
        spriceUom3 = spriceUom3,
        spriceUom4 = spriceUom4,
        spriceAfterPpn = spriceAfterPpn,
        spriceUom2AfterPpn = spriceUom2AfterPpn,
        spriceUom3AfterPpn = spriceUom3AfterPpn,
        spriceUom4AfterPpn = spriceUom4AfterPpn,

        spriceNET_Uom1AfterDiscAfterPpn = spriceNET_Uom1AfterDiscAfterPpn,
        spriceNET_Uom2AfterDiscAfterPpn = spriceNET_Uom2AfterDiscAfterPpn,
        spriceNET_Uom3AfterDiscAfterPpn = spriceNET_Uom3AfterDiscAfterPpn,
        spriceNET_Uom4AfterDiscAfterPpn = spriceNET_Uom4AfterDiscAfterPpn,
        qty1 = qty1,
        qty2 = qty2,
        qty3 = qty3,
        qty4 = qty4,

        qty1Kembali = qty1Kembali,
        qty2Kembali = qty2Kembali,
        qty3Kembali = qty3Kembali,
        qty4Kembali = qty4Kembali,

        qty = qty,
        qtyKembali = qtyKembali,
        qtyNET = qtyNET,
        qtyReturn = qtyReturn,

        priceUom = priceUom,
        subtotalRp = subtotalRp,
        subtotalPpnRp = subtotalPpnRp,
        subtotalRpAfterPpn = subtotalRpAfterPpn,
        disc1 = disc1,
        disc1Rp = disc1Rp,
        disc1PpnRp = disc1PpnRp,
        disc1RpAfterPpn = disc1RpAfterPpn,

        disc1RpAfterPpnUom1 = disc1RpAfterPpnUom1,
        disc1RpAfterPpnUom2 = disc1RpAfterPpnUom2,
        disc1RpAfterPpnUom3 = disc1RpAfterPpnUom3,
        disc1RpAfterPpnUom4 = disc1RpAfterPpnUom4,

        disc2 = disc2,
        disc2Rp = disc2Rp,
        disc2PpnRp = disc2PpnRp,
        disc2RpAfterPpn = disc2RpAfterPpn,

        disc2RpAfterPpnUom1 = disc2RpAfterPpnUom1,
        disc2RpAfterPpnUom2 = disc2RpAfterPpnUom2,
        disc2RpAfterPpnUom3 = disc2RpAfterPpnUom3,
        disc2RpAfterPpnUom4 = disc2RpAfterPpnUom4,

        disc3 = disc3,
        disc3Rp = disc3Rp,
        disc3PpnRp = disc3PpnRp,
        disc3RpAfterPpn = disc3RpAfterPpn,

        disc3RpAfterPpnUom1 = disc3RpAfterPpnUom1,
        disc3RpAfterPpnUom2 = disc3RpAfterPpnUom2,
        disc3RpAfterPpnUom3 = disc3RpAfterPpnUom3,
        disc3RpAfterPpnUom4 = disc3RpAfterPpnUom4,

        subtotalAfterDisc123Rp = subtotalAfterDisc123Rp,
        subtotalAfterDisc123PpnRp = subtotalAfterDisc123PpnRp,
        subtotalAfterDisc123RpAfterPpn = subtotalAfterDisc123RpAfterPpn,

        disc1Plus = disc1Plus,
        disc1PlusRp = disc1PlusRp,
        disc1PlusPpnRp = disc1PlusPpnRp,
        disc1PlusRpAfterPpn = disc1PlusRpAfterPpn,

        disc1PlusRpAfterPpnUom1 = disc1PlusRpAfterPpnUom1,
        disc1PlusRpAfterPpnUom2 = disc1PlusRpAfterPpnUom2,
        disc1PlusRpAfterPpnUom3 = disc1PlusRpAfterPpnUom3,
        disc1PlusRpAfterPpnUom4 = disc1PlusRpAfterPpnUom4,

        subtotalAfterDisc1PlusRp = subtotalAfterDisc1PlusRp,
        subtotalAfterDisc1PlusPpnRp = subtotalAfterDisc1PlusPpnRp,
        subtotalAfterDisc1PlusRpAfterPpn = subtotalAfterDisc1PlusRpAfterPpn,
        disc2Plus = disc2Plus,
        disc2PlusRp = disc2PlusRp,
        disc2PlusPpnRp = disc2PlusPpnRp,
        disc2PlusRpAfterPpn = disc2PlusRpAfterPpn,

        disc2PlusRpAfterPpnUom1 = disc2PlusRpAfterPpnUom1,
        disc2PlusRpAfterPpnUom2 = disc2PlusRpAfterPpnUom2,
        disc2PlusRpAfterPpnUom3 = disc2PlusRpAfterPpnUom3,
        disc2PlusRpAfterPpnUom4 = disc2PlusRpAfterPpnUom4,

        subtotalAfterDisc2PlusRp = subtotalAfterDisc2PlusRp,
        subtotalAfterDisc2PlusPpnRp = subtotalAfterDisc2PlusPpnRp,
        subtotalAfterDisc2PlusRpAfterPpn = subtotalAfterDisc2PlusRpAfterPpn,

        discNota1 = discNota1,
        discNota1Rp = discNota1Rp,
        discNota1PpnRp = discNota1PpnRp,
        discNota1RpAfterPpn = discNota1RpAfterPpn,

        subtotalAfterDiscNota1Rp = subtotalAfterDiscNota1Rp,
        subtotalAfterDiscNota1PpnRp = subtotalAfterDiscNota1PpnRp,
        subtotalAfterDiscNota1RpAfterPpn = subtotalAfterDiscNota1RpAfterPpn,

        discNota2 = discNota2,
        discNota2Rp = discNota2Rp,
        discNota2PpnRp = discNota2PpnRp,
        discNota2RpAfterPpn = discNota2RpAfterPpn,
        subtotalAfterDiscNota2Rp = subtotalAfterDiscNota2Rp,
        subtotalAfterDiscNota2PpnRp = subtotalAfterDiscNota2PpnRp,
        subtotalAfterDiscNota2RpAfterPpn = subtotalAfterDiscNota2RpAfterPpn,

        discNotaPlus_FG = discNotaPlus_FG,
        discNotaPlusRp_FG = discNotaPlusRp_FG,
        discNotaPlusPpnRp_FG = discNotaPlusPpnRp_FG,
        discNotaPlusRpAfterPpn_FG = discNotaPlusRpAfterPpn_FG,
        subtotalAfterDiscNotaPlusRp_FG = subtotalAfterDiscNotaPlusRp_FG,
        subtotalAfterDiscNotaPlusPpnRp_FG = subtotalAfterDiscNotaPlusPpnRp_FG,
        subtotalAfterDiscNotaPlusRpAfterPpn_FG = subtotalAfterDiscNotaPlusRpAfterPpn_FG,

        tempString = tempString,
        tempInt = tempInt,
        tempDouble1 = tempDouble1,
        tempDouble2 = tempDouble2,
        tempDouble31 = tempDouble31,

        ftSaleshBean = ftSaleshBean.refno,
        fmaterialBean = fmaterialBean,
    )
}
