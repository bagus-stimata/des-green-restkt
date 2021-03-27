package com.erp.distribution.desgreenrestkt.presentation.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FStockEntity
import com.erp.distribution.desgreenrestkt.domain.model.FStock
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FStockRes (
    var refno: Long = 0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @JsonIgnore
    @Column(name =  "SOURCE_ID" )
    var sourceId: Long = 0,

    /*
	 * HARI YANG DIPAKAI JUGA PADA HPP
	 */
    @Column(name =  "STOCK_DATE" )
    @Temporal(TemporalType.DATE)
    var stockDate: Date = Date(),

    //	@Column(name= SALDO_AWAL )
    //	private Integer saldoAwal =0.0;
    @Column(name =  "SALDO_AWAL" )
    var saldoAwal  :Double =0.0,

    /*
	 * NEXT AKAN DIBIKIN TRANSIEN
	 */
    //	@Column(name= QTY_IN )
    //	private Integer qtyIn =0.0;
    //	@Column(name= QTY_OUT )
    //	private Integer qtyOut =0.0;
    @JsonIgnore
    @Column(name =  "QTY_IN" )
    var qtyIn  :Double =0.0,

    @JsonIgnore
    @Column(name =  "QTY_OUT" )
    var qtyOut  :Double =0.0,

    //	@Column(name= QTY_ADJUST , length=10)	
    //	private Integer qtyAdjust =0.0;
    //	@Column(name= SALDO_AKHIR )
    //	private Integer saldoAkhir =0.0;
    @JsonIgnore
    @Column(name =  "QTY_ADJUST" , length = 10)
    var qtyAdjust  :Double =0.0,

    @Column(name =  "SALDO_AKHIR" )
    var saldoAkhir  :Double =0.0,

    /*
	 * ORDERED STOCK
	 */
    //	@Column(name= QTY_HOLD , length=9)	
    //	private Integer qtyHold =0.0;
    @JsonIgnore
    @Column(name =  "QTY_HOLD" , length = 9)
    var qtyHold  :Double =0.0,

    /*
	 * HARGA BELI NET TERAKHIR
	 * TAPI HARGA BELI TERAKHIR INI MENGGUNAKAN PPN (after PPN)
	 */
    @JsonIgnore
    @Column(name =  "CLOSING_PPRICE2" )
    var closingPprice2_AfterPpn  :Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name= fwarehouseBean , referencedColumnName= ID )
    //	private FWarehouse fwarehouseBean;
    @Column(name =  "fwarehouseBean" , nullable = false)
    var fwarehouseBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fmaterialBean , referencedColumnName= ID )
    //	private FMaterial fmaterialBean;
    @Column(name =  "fmaterialBean" , nullable = false)
    var fmaterialBean :Int =0

): Serializable

internal fun FStockRes.toDomain(): FStock {
    return FStock(
        refno = refno,
        sourceId = sourceId,

        stockDate = stockDate,
        saldoAwal = saldoAwal,
        qtyIn = qtyIn,
        qtyOut = qtyOut,
        qtyAdjust = qtyAdjust,
        saldoAkhir = saldoAkhir,
        qtyHold = qtyHold,
        closingPprice2_AfterPpn = closingPprice2_AfterPpn,
        fwarehouseBean = fwarehouseBean,
        fmaterialBean = fmaterialBean,
    )
}
