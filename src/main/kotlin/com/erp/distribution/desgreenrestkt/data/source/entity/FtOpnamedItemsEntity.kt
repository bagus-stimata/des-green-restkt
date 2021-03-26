package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "ftopnamed_items" )
data class FtOpnamedItemsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name =  "ID" )
    var id: Long=0L,

    @Column(name =  "NO_URUT" , length = 4)
    var noUrut :Int =0,

    /*
	 * Dasar harga total
	 */
    @Column(name =  "PPRICE" )
    var pprice  :Double =0.0, //Harga disimpan dalam Satuan Besar Sebelum Ppn

    @Transient
    var ppriceUom2  :Double =0.0,

    @Transient
    var ppriceUom3  :Double =0.0,

    @Transient
    var ppriceUom4  :Double =0.0,

    @Transient
    var qty1  :Double =0.0,

    @Transient
    var qty2  :Double =0.0,

    @Transient
    var qty3  :Double =0.0,

    @Transient
    var qty4  :Double =0.0,

    //	@Column(name= QTY , length=9)
    //	private Integer qty=0.0; //Qty Fisik
    @Column(name =  "QTY" , length = 9)
    var qty  :Double =0.0, //Qty Fisik

    //	@Column(name= QTY_TEORI , length=9)
    //	private Integer qtyTeori=0.0;	//Qty pada Komputer
    @Column(name =  "QTY_TEORI" , length = 9)
    var qtyTeori  :Double =0.0, //Qty pada Komputer

    @Transient
    var qty1_Adjust  :Double =0.0,

    @Transient
    var qty2_Adjust  :Double =0.0,

    @Transient
    var qty3_Adjust  :Double =0.0,

    @Transient
    var qty4_Adjust  :Double =0.0,

    //	private Integer qtyAdjust=0.0; //Qty setelah dihitung dan dilakukan penyesuaian
    @Column(name =  "QTY_ADJUST" , length = 9)
    var qtyAdjust  :Double =0.0, //Qty setelah dihitung dan dilakukan penyesuaian

    @Column(name =  "VISIBLE" )
    var visible  :Boolean =true,

    //Subtotal sebelum disc
    @Transient
    var subtotalRp  :Double =0.0,

    @Transient
    var subtotalTeoriRp  :Double =0.0,

    @Transient
    var subtotalAdjustRp  :Double =0.0,

    @Transient
    var tempString :String ="",

    @Transient
    var tempInt :Int =0,

    @Transient
    var tempFloat  :Double =0.0,

    @Transient
    var tempDouble31  :Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name= ftOpnamehBean , referencedColumnName= refno )
    //	private FtOpnameh ftOpnamehBean;
    @Column(name =  "ftOpnamehBean" , nullable = false)
    var ftOpnamehBean: Long =0,

    //	@ManyToOne
    //	@JoinColumn(name= fmaterialBean , referencedColumnName= ID )
    //	private FMaterial fmaterialBean;
    @Column(name =  "fmaterialBean" , nullable = false)
    var fmaterialBean :Int =0

): Serializable