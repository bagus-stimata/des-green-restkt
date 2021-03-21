package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftopnamed_items")
class FtOpnamedItems {
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

    @Transient
    var qty1 = 0.0

    @Transient
    var qty2 = 0.0

    @Transient
    var qty3 = 0.0

    @Transient
    var qty4 = 0.0

    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0; //Qty Fisik
    @Column(name = "QTY", length = 9)
    var qty = 0.0 //Qty Fisik

    //	@Column(name="QTY_TEORI", length=9)
    //	private Integer qtyTeori=0.0;	//Qty pada Komputer
    @Column(name = "QTY_TEORI", length = 9)
    var qtyTeori = 0.0 //Qty pada Komputer

    @Transient
    var qty1_Adjust = 0.0

    @Transient
    var qty2_Adjust = 0.0

    @Transient
    var qty3_Adjust = 0.0

    @Transient
    var qty4_Adjust = 0.0

    //	private Integer qtyAdjust=0.0; //Qty setelah dihitung dan dilakukan penyesuaian
    @Column(name = "QTY_ADJUST", length = 9)
    var qtyAdjust = 0.0 //Qty setelah dihitung dan dilakukan penyesuaian

    @Column(name = "VISIBLE")
    var isVisible = true

    //Subtotal sebelum disc
    @Transient
    var subtotalRp = 0.0

    @Transient
    var subtotalTeoriRp = 0.0

    @Transient
    var subtotalAdjustRp = 0.0

    @Transient
    var tempString = ""

    @Transient
    var tempInt = 0

    @Transient
    var tempFloat = 0.0

    @Transient
    var tempDouble31 = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftOpnamehBean", referencedColumnName="refno")
    //	private FtOpnameh ftOpnamehBean;
    @Column(name = "ftOpnamehBean", nullable = false)
    var ftOpnamehBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean = 0

}