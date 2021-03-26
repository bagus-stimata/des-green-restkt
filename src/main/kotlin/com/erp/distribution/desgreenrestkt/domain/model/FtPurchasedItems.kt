package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumUom
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FtPurchasedItems (
    var id: Long =0L,

    @Column(name = "NO_URUT", length = 4)
    var noUrut  :Int =0,

    /*
	 * IN PURCHASE REQUESITION TAX USED : APPROVE
	 */
    @Column(name = "FREE_GOOD")
    var freeGood  :Boolean =false,

    /*
	 * EXCLUDE COGS or No Cogs
	 * Jika True maka tidak dihitung sebagai HPP/COGS dan Defaultnya adalah Dihitung sebagai COGS
	 * Syarat: "FreeGood  :Boolean =true," (hanya untuk barang free good)
	 * Jika Exclude COGS maka nilainya akan secara langsung di jurnal menjadi Jurnal Memorial
	 * 
	 * TIDAK JADI
	 * 
	 */
    //	@Column(name="EXCL_COGS")
    //	private boolean exclCogs=false;
    @Column(name = "NOTES", length = 120)
    var notes :String ="",

    /*
	 * Dasar harga total
	 */
    @Column(name = "PPRICE")
    var pprice  :Double =0.0, //Harga disimpan dalam Satuan Besar Sebelum Ppn

    @Transient
    var ppricePpnRp  :Double =0.0,

    /*
	 * IN PURCHASE REQUESITION TAX USED TO : REJECT
	 */
    @Column(name = "TAX")
    var isTax  :Boolean =true,

    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    @Column(name = "ftaxBean")
    var ftaxBean  :Int =0,

    @Column(name = "TAX_PERCENT")
    var taxPercent  :Double =0.0,

    /*
	 * End: Dasar harga total
	 */
    @Transient
    var ppriceUom2  :Double =0.0,

    @Transient
    var ppriceUom3  :Double =0.0,

    @Transient
    var ppriceUom4  :Double =0.0,

    //HARGA BELI SEBELUM
    @Transient
    var ppriceOldAfterPpn  :Double =0.0, //sama dengan ppriceUom1OldAfterPpn

    @Transient
    var ppriceUom2OldAfterPpn  :Double =0.0,

    @Transient
    var ppriceUom3OldAfterPpn  :Double =0.0,

    @Transient
    var ppriceUom4OldAfterPpn  :Double =0.0,

    @Transient
    var selisihHargaBeliLama :String ="",

    @Transient
    var ppriceAfterPpn  :Double =0.0,

    @Transient
    var ppriceUom2AfterPpn  :Double =0.0,

    @Transient
    var ppriceUom3AfterPpn  :Double =0.0,

    @Transient
    var ppriceUom4AfterPpn  :Double =0.0,

    //HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
    @Transient
    var ppriceNET_Uom1AfterDiscAfterPpn  :Double =0.0,

    @Transient
    var ppriceNET_Uom2AfterDiscAfterPpn  :Double =0.0,

    @Transient
    var ppriceNET_Uom3AfterDiscAfterPpn  :Double =0.0,

    @Transient
    var ppriceNET_Uom4AfterDiscAfterPpn  :Double =0.0,

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

    @Transient
    var qty1Kembali  :Double =0.0,

    @Transient
    var qty2Kembali  :Double =0.0,

    @Transient
    var qty3Kembali  :Double =0.0,

    @Transient
    var qty4Kembali  :Double =0.0,

    /*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
	*/
    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    @Column(name = "QTY", length = 9)
    var qty  :Double =0.0,

    @Column(name = "QTY_KEMBALI", length = 9) //Kembali dari pengiriman
    var qtyKembali  :Double =0.0,

    @Transient
    var qtyNET  :Double =0.0,

    //	@Column(name="QTY_RETURN", length=9) //Dipakai untuk Algoritma Return: Berbeda dengan kegunaan dari FtSalesditems
    //	private Integer qtyReturn  :Int =0,;
    @Column(name = "QTY_RETURN", length = 9) //Dipakai untuk Algoritma Return: Berbeda dengan kegunaan dari FtSalesditems
    var qtyReturn  :Double =0.0,

    //	@Column(name="QTY_USED_BYCHILD", length=9)
    //	private Integer qty_usedByChild =0.0;
    @Column(name = "QTY_USED_BYCHILD", length = 9)
    var qty_usedByChild  :Double =0.0,

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

    //Subtotal sebelum disc
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
    var subtotalAfterDiscNota12Rp  :Double =0.0,

    @Transient
    var subtotalAfterDiscNota12PpnRp  :Double =0.0, //ppn

    @Transient
    var subtotalAfterDiscNota12RpAfterPpn  :Double =0.0,

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
    var discNotaExclCogs  :Double =0.0,

    @Transient
    var discNotaExclCogsRp  :Double =0.0,

    @Transient
    var discNotaExclCogsPpnRp  :Double =0.0, //ppn

    @Transient
    var discNotaExclCogsRpAfterPpn  :Double =0.0,

    @Transient
    var subtotalAfterDiscNotaExclCogsRp  :Double =0.0,

    @Transient
    var subtotalAfterDiscNotaExclCogsPpnRp  :Double =0.0, //ppn

    @Transient
    var subtotalAfterDiscNotaExclCogsRpAfterPpn  :Double =0.0,

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

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	private FtPurchaseh ftPurchasehBean;
    @Column(name = "ftPurchasehBean", nullable = false)
    var ftPurchasehBean: Long =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean  :Int =0

): Serializable