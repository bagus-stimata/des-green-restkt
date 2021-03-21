package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumUom
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpurchased_items")
class FtPurchasedItems : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long = 0

    @Column(name = "NO_URUT", length = 4)
    var noUrut = 0

    /*
	 * IN PURCHASE REQUESITION TAX USED : APPROVE
	 */
    @Column(name = "FREE_GOOD")
    var isFreeGood = false

    /*
	 * EXCLUDE COGS or No Cogs
	 * Jika True maka tidak dihitung sebagai HPP/COGS dan Defaultnya adalah Dihitung sebagai COGS
	 * Syarat: "FreeGood = true" (hanya untuk barang free good)
	 * Jika Exclude COGS maka nilainya akan secara langsung di jurnal menjadi Jurnal Memorial
	 * 
	 * TIDAK JADI
	 * 
	 */
    //	@Column(name="EXCL_COGS")
    //	private boolean exclCogs=false;
    @Column(name = "NOTES", length = 120)
    var notes = ""

    /*
	 * Dasar harga total
	 */
    @Column(name = "PPRICE")
    var pprice = 0.0 //Harga disimpan dalam Satuan Besar Sebelum Ppn

    @Transient
    var ppricePpnRp = 0.0

    /*
	 * IN PURCHASE REQUESITION TAX USED TO : REJECT
	 */
    @Column(name = "TAX")
    var isTax = true

    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    @Column(name = "ftaxBean")
    var ftaxBean = 0

    @Column(name = "TAX_PERCENT")
    var taxPercent = 0.0

    /*
	 * End: Dasar harga total
	 */
    @Transient
    var ppriceUom2 = 0.0

    @Transient
    var ppriceUom3 = 0.0

    @Transient
    var ppriceUom4 = 0.0

    //HARGA BELI SEBELUM
    @Transient
    var ppriceOldAfterPpn = 0.0 //sama dengan ppriceUom1OldAfterPpn

    @Transient
    var ppriceUom2OldAfterPpn = 0.0

    @Transient
    var ppriceUom3OldAfterPpn = 0.0

    @Transient
    var ppriceUom4OldAfterPpn = 0.0

    @Transient
    var selisihHargaBeliLama = ""

    @Transient
    var ppriceAfterPpn = 0.0

    @Transient
    var ppriceUom2AfterPpn = 0.0

    @Transient
    var ppriceUom3AfterPpn = 0.0

    @Transient
    var ppriceUom4AfterPpn = 0.0

    //HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
    @Transient
    var ppriceNET_Uom1AfterDiscAfterPpn = 0.0

    @Transient
    var ppriceNET_Uom2AfterDiscAfterPpn = 0.0

    @Transient
    var ppriceNET_Uom3AfterDiscAfterPpn = 0.0

    @Transient
    var ppriceNET_Uom4AfterDiscAfterPpn = 0.0

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

    @Transient
    var qty1Kembali = 0.0

    @Transient
    var qty2Kembali = 0.0

    @Transient
    var qty3Kembali = 0.0

    @Transient
    var qty4Kembali = 0.0

    /*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
	*/
    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    @Column(name = "QTY", length = 9)
    var qty = 0.0

    @Column(name = "QTY_KEMBALI", length = 9) //Kembali dari pengiriman
    var qtyKembali = 0.0

    @Transient
    var qtyNET = 0.0

    //	@Column(name="QTY_RETURN", length=9) //Dipakai untuk Algoritma Return: Berbeda dengan kegunaan dari FtSalesditems
    //	private Integer qtyReturn = 0;
    @Column(name = "QTY_RETURN", length = 9) //Dipakai untuk Algoritma Return: Berbeda dengan kegunaan dari FtSalesditems
    var qtyReturn = 0.0

    //	@Column(name="QTY_USED_BYCHILD", length=9)
    //	private Integer qty_usedByChild =0.0;
    @Column(name = "QTY_USED_BYCHILD", length = 9)
    var qty_usedByChild = 0.0

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

    //Subtotal sebelum disc
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
    var subtotalAfterDiscNota12Rp = 0.0

    @Transient
    var subtotalAfterDiscNota12PpnRp = 0.0 //ppn

    @Transient
    var subtotalAfterDiscNota12RpAfterPpn = 0.0

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
    var discNotaExclCogs = 0.0

    @Transient
    var discNotaExclCogsRp = 0.0

    @Transient
    var discNotaExclCogsPpnRp = 0.0 //ppn

    @Transient
    var discNotaExclCogsRpAfterPpn = 0.0

    @Transient
    var subtotalAfterDiscNotaExclCogsRp = 0.0

    @Transient
    var subtotalAfterDiscNotaExclCogsPpnRp = 0.0 //ppn

    @Transient
    var subtotalAfterDiscNotaExclCogsRpAfterPpn = 0.0

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

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	private FtPurchaseh ftPurchasehBean;
    @Column(name = "ftPurchasehBean", nullable = false)
    var ftPurchasehBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean = 0

}