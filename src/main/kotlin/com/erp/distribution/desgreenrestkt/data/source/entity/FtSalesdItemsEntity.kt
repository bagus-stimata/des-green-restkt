package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumUom
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftsalesd_items")
data class FtSalesdItemsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long =0L,

    @Column(name = "FREE_GOOD")
    var isFreeGood  :Boolean =false,

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
    var isTax  :Boolean =true,

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
    //	@ManyToOne
    //	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
    //	private FtSalesh ftSaleshBean;
    @Column(name = "ftSaleshBean", nullable = false)
    var ftSaleshBean: Long =0L,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean  :Int =0

): Serializable