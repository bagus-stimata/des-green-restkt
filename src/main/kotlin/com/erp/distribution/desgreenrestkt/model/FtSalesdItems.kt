package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumUom
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftsalesd_items")
class FtSalesdItems : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long = 0

    @Column(name = "FREE_GOOD")
    var isFreeGood = false

    @Column(name = "NOURUT", length = 4)
    var noUrut = 0

    @Column(name = "NOTES", length = 120)
    var notes = ""

    @Column(name = "SPRICE")
    var sprice = 0.0

    @Transient
    var spricePpnRp = 0.0

    /*
	 * Dasar harga total
	 */
    @Column(name = "TAX")
    var isTax = true

    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    //Boleh Kosong
    @Column(name = "ftaxBean", nullable = true)
    var ftaxBean: Int? = null

    @Column(name = "TAX_PERCENT")
    var taxPercent = 0.0

    /*
	 * End: Dasar harga total
	 */
    @Transient
    var spriceUom2 = 0.0

    @Transient
    var spriceUom3 = 0.0

    @Transient
    var spriceUom4 = 0.0

    @Transient
    var spriceAfterPpn = 0.0

    @Transient
    var spriceUom2AfterPpn = 0.0

    @Transient
    var spriceUom3AfterPpn = 0.0

    @Transient
    var spriceUom4AfterPpn = 0.0

    //HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
    @Transient
    var spriceNET_Uom1AfterDiscAfterPpn = 0.0

    @Transient
    var spriceNET_Uom2AfterDiscAfterPpn = 0.0

    @Transient
    var spriceNET_Uom3AfterDiscAfterPpn = 0.0

    @Transient
    var spriceNET_Uom4AfterDiscAfterPpn = 0.0

    //	@Transient
    //	private Integer qty1=0.0;
    //	@Transient
    //	private Integer qty2=0.0;
    //	@Transient
    //	private Integer qty3=0.0;
    //	@Transient
    //	private Integer qty4=0.0;
    @Transient
    var qty1 = 0.0

    @Transient
    var qty2 = 0.0

    @Transient
    var qty3 = 0.0

    @Transient
    var qty4 = 0.0

    //	@Transient
    //	private Integer qty1Kembali=0.0;
    //	@Transient
    //	private Integer qty2Kembali=0.0;
    //	@Transient
    //	private Integer qty3Kembali=0.0;
    //	@Transient
    //	private Integer qty4Kembali=0.0;
    @Transient
    var qty1Kembali = 0.0

    @Transient
    var qty2Kembali = 0.0

    @Transient
    var qty3Kembali = 0.0

    @Transient
    var qty4Kembali = 0.0

    /*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
	qty = qtyTerkirim
	Total Jumlah yang diOrder = qty + qtyKembali
	*/
    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    //	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
    //	private Integer qtyKembali = 0;	
    //	@Transient
    //	private Integer qtyNET=0.0;
    @Column(name = "QTY", length = 9)
    var qty = 0.0

    @Column(name = "QTY_KEMBALI", length = 9) //Kembali dari pengiriman
    var qtyKembali = 0.0

    @Transient
    var qtyNET = 0.0

    /*
	 * Untuk Keperluan Retur
	 */
    //	@Column(name="QTYRETURN", length=9)
    //	private Integer qtyReturn =0.0;
    @Column(name = "QTYRETURN", length = 9)
    var qtyReturn = 0.0

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
    var priceUom = EnumUom.UOM1

    //Sub total sebelum diskon
    @Transient
    var subtotalRp = 0.0

    @Transient
    var subtotalPpnRp = 0.0

    @Transient
    var subtotalRpAfterPpn = 0.0

    @Column(name = "DISC1")
    var disc1 = 0.0

    @Transient
    var disc1Rp = 0.0

    @Transient
    var disc1PpnRp = 0.0

    @Transient
    var disc1RpAfterPpn = 0.0

    @Transient
    var disc1RpAfterPpnUom1 = 0.0

    @Transient
    var disc1RpAfterPpnUom2 = 0.0

    @Transient
    var disc1RpAfterPpnUom3 = 0.0

    @Transient
    var disc1RpAfterPpnUom4 = 0.0

    @Column(name = "DISC2")
    var disc2: Double? = null

    @Transient
    var disc2Rp = 0.0

    @Transient
    var disc2PpnRp = 0.0

    @Transient
    var disc2RpAfterPpn = 0.0

    @Transient
    var disc2RpAfterPpnUom1 = 0.0

    @Transient
    var disc2RpAfterPpnUom2 = 0.0

    @Transient
    var disc2RpAfterPpnUom3 = 0.0

    @Transient
    var disc2RpAfterPpnUom4 = 0.0

    @Column(name = "DISC3")
    var disc3 = 0.0

    @Transient
    var disc3Rp = 0.0

    @Transient
    var disc3PpnRp = 0.0

    @Transient
    var disc3RpAfterPpn = 0.0

    @Transient
    var disc3RpAfterPpnUom1 = 0.0

    @Transient
    var disc3RpAfterPpnUom2 = 0.0

    @Transient
    var disc3RpAfterPpnUom3 = 0.0

    @Transient
    var disc3RpAfterPpnUom4 = 0.0

    @Transient
    var subtotalAfterDisc123Rp = 0.0

    @Transient
    var subtotalAfterDisc123PpnRp = 0.0

    @Transient
    var subtotalAfterDisc123RpAfterPpn = 0.0

    @Column(name = "DISC1_PLUS")
    var disc1Plus = 0.0

    @Transient
    var disc1PlusRp = 0.0

    @Transient
    var disc1PlusPpnRp = 0.0

    @Transient
    var disc1PlusRpAfterPpn = 0.0

    @Transient
    var disc1PlusRpAfterPpnUom1 = 0.0

    @Transient
    var disc1PlusRpAfterPpnUom2 = 0.0

    @Transient
    var disc1PlusRpAfterPpnUom3 = 0.0

    @Transient
    var disc1PlusRpAfterPpnUom4 = 0.0

    @Transient
    var subtotalAfterDisc1PlusRp = 0.0

    @Transient
    var subtotalAfterDisc1PlusPpnRp = 0.0

    @Transient
    var subtotalAfterDisc1PlusRpAfterPpn = 0.0

    @Column(name = "DISC2_PLUS")
    var disc2Plus = 0.0

    @Transient
    var disc2PlusRp = 0.0

    @Transient
    var disc2PlusPpnRp = 0.0 //ppn

    @Transient
    var disc2PlusRpAfterPpn = 0.0

    @Transient
    var disc2PlusRpAfterPpnUom1 = 0.0

    @Transient
    var disc2PlusRpAfterPpnUom2 = 0.0

    @Transient
    var disc2PlusRpAfterPpnUom3 = 0.0

    @Transient
    var disc2PlusRpAfterPpnUom4 = 0.0

    @Transient
    var subtotalAfterDisc2PlusRp = 0.0

    @Transient
    var subtotalAfterDisc2PlusPpnRp = 0.0 //ppn

    @Transient
    var subtotalAfterDisc2PlusRpAfterPpn = 0.0

    /*
	 * AFTER DISKON NOTA :: TAMBAHAN UNTUK MEMUDAHKAN PERHITUNGAN
	 */
    @Transient
    var discNota1 = 0.0

    @Transient
    var discNota1Rp = 0.0

    @Transient
    var discNota1PpnRp = 0.0 //ppn

    @Transient
    var discNota1RpAfterPpn = 0.0

    /*
	 * TAMBAHAN
	 */
    @Transient
    var subtotalAfterDiscNota1Rp = 0.0

    @Transient
    var subtotalAfterDiscNota1PpnRp = 0.0 //ppn

    @Transient
    var subtotalAfterDiscNota1RpAfterPpn = 0.0

    @Transient
    var discNota2 = 0.0

    @Transient
    var discNota2Rp = 0.0

    @Transient
    var discNota2PpnRp = 0.0 //ppn

    @Transient
    var discNota2RpAfterPpn = 0.0

    @Transient
    var subtotalAfterDiscNota2Rp = 0.0

    @Transient
    var subtotalAfterDiscNota2PpnRp = 0.0 //ppn

    @Transient
    var subtotalAfterDiscNota2RpAfterPpn = 0.0

    @Transient
    var discNotaPlus_FG = 0.0

    @Transient
    var discNotaPlusRp_FG = 0.0

    @Transient
    var discNotaPlusPpnRp_FG = 0.0 //ppn

    @Transient
    var discNotaPlusRpAfterPpn_FG = 0.0

    @Transient
    var subtotalAfterDiscNotaPlusRp_FG = 0.0

    @Transient
    var subtotalAfterDiscNotaPlusPpnRp_FG = 0.0 //ppn

    @Transient
    var subtotalAfterDiscNotaPlusRpAfterPpn_FG = 0.0

    @Transient
    var tempString = ""

    @Transient
    var tempInt = 0

    @Transient
    var tempDouble1 = 0.0

    @Transient
    var tempDouble2 = 0.0

    @Transient
    var tempDouble31 = 0

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
    var ftSaleshBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean = 0

}