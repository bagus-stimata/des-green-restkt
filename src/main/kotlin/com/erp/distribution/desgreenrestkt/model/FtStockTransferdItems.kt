package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftstocktransferd_items")
class FtStockTransferdItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long = 0

    @Column(name = "NO_URUT", length = 4)
    var noUrut = 0

    /*
	 * Dasar harga total
	 */
    @Column(name = "PPRICE")
    var pprice = 0.0 //Harga disimpan dalam Satuan Besar Sebelum Ppn

    @Transient
    var ppriceUom2 = 0.0

    @Transient
    var ppriceUom3 = 0.0

    @Transient
    var ppriceUom4 = 0.0

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

    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    @Column(name = "QTY", length = 9)
    var qty = 0.0

    /*
	 * Qty Kembali digunakan jika barang kurang atau Rusak
	 */
    //	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
    //	private Integer qtyKembali = 0;	
    //	@Transient
    //	private Integer qtyNET=0.0;
    @Column(name = "QTY_KEMBALI", length = 9) //Kembali dari pengiriman
    var qtyKembali = 0.0

    @Transient
    var qtyNET = 0.0

    //Subtotal sebelum disc
    @Transient
    var subtotalRp = 0.0

    @Transient
    var tempString = ""

    @Transient
    var tempInt = 0

    @Transient
    var tempFloat = 0.0

    @Transient
    var tempDouble31 = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftStockTransferhBean", referencedColumnName="refno")
    //	private FtStockTransferh ftStockTransferhBean;
    @Column(name = "ftStockTransferhBean", nullable = false)
    var ftStockTransferhBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean = 0

}