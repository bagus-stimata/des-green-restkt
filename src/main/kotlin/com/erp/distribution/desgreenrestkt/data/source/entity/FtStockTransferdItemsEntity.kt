package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftstocktransferd_items")
data class FtStockTransferdItemsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long =0L,

    @Column(name = "NO_URUT", length = 4)
    var noUrut  :Int =0,

    /*
	 * Dasar harga total
	 */
    @Column(name = "PPRICE")
    var pprice  :Double =0.0, //Harga disimpan dalam Satuan Besar Sebelum Ppn

    @Transient
    var ppriceUom2  :Double =0.0,

    @Transient
    var ppriceUom3  :Double =0.0,

    @Transient
    var ppriceUom4  :Double =0.0,

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

    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    @Column(name = "QTY", length = 9)
    var qty  :Double =0.0,

    /*
	 * Qty Kembali digunakan jika barang kurang atau Rusak
	 */
    //	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
    //	private Integer qtyKembali  :Int =0,;	
    //	@Transient
    //	private Integer qtyNET=0.0;
    @Column(name = "QTY_KEMBALI", length = 9) //Kembali dari pengiriman
    var qtyKembali  :Double =0.0,

    @Transient
    var qtyNET  :Double =0.0,

    //Subtotal sebelum disc
    @Transient
    var subtotalRp  :Double =0.0,

    @Transient
    var tempString :String ="",

    @Transient
    var tempInt  :Int =0,

    @Transient
    var tempFloat  :Double =0.0,

    @Transient
    var tempDouble31  :Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="ftStockTransferhBean", referencedColumnName="refno")
    //	private FtStockTransferh ftStockTransferhBean;
    @Column(name = "ftStockTransferhBean", nullable = false)
    var ftStockTransferhBean: Long =0L,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean  :Int =0

    ): Serializable