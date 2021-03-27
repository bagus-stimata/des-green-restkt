package com.erp.distribution.desgreenrestkt.presentation.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FWarehouseEntity
import com.erp.distribution.desgreenrestkt.domain.model.FWarehouse
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeWarehouse
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FWarehouseRes (
    var id  :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId  :Int =0,

    @Column(name = "KODE1", length = 10)
    var kode1 :String ="",

    @Column(name = "KODE2", length = 20)
    var kode2 :String ="",

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean  :Int =0,

    @Column(name = "PRODUCT_HPP_SAVED")
    var productHppSaved  :Boolean =false,

    @Column(name = "NUMBER_PRIORITY")
    var numberPriority: Int? = 0,

    @Column(name = "DESCRIPTION", length = 100)
    var description :String ="",

    @Column(name = "GUDANG_UTAMA")
    var gudangUtama  :Boolean =false,

    @Column(name = "ADDRESS1", length = 100)
    var address1 :String ="",

    @Column(name = "CITY1", length = 30)
    var city1: String? = "",

    @Column(name = "STATE1", length = 30)
    var state1 :String ="",

    @Column(name = "PHONE", length = 50)
    var phone :String ="",

    @Column(name = "STATUS_ACTIVE")
    var statusActive  :Boolean =false,

    @Column(name = "GDG_PO")
    var gudangPo  :Boolean =true,

    @Column(name = "GDG_SO")
    var gudangSo  :Boolean =true,

    @Column(name = "GDG_TRANSFER")
    var gudangTransfer  :Boolean =true,

    @Column(name = "GDG_RETAIL")
    var gudangRetail  :Boolean =true,

    @Column(name = "GDG_PUSAT_COMPANY")
    var gudangPusatCompany  :Boolean =true,

    @Column(name = "GDG_TRANSIT_DIV")
    var gudangTransitDiv  :Boolean =true,

    @Column(length = 5, name = "TIPE_WAREHOUSE")
    @Enumerated(EnumType.STRING)
    var tipeWarehouse: EnumTipeWarehouse = EnumTipeWarehouse.GS,

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    @Column(name = "WSPORT", length = 10)
    var wsport :String ="",

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID

): Serializable

internal fun FWarehouseRes.toDomain(): FWarehouse {
    return FWarehouse(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

        productHppSaved = productHppSaved,
        numberPriority = numberPriority,
        gudangUtama = gudangUtama,
        address1 = address1,
        city1 = city1,
        state1 = state1,
        phone = phone,
        gudangPo = gudangPo,
        gudangSo = gudangSo,
        gudangTransfer = gudangTransfer,
        gudangRetail = gudangRetail,
        gudangPusatCompany = gudangPusatCompany,
        gudangTransitDiv = gudangTransitDiv,
        tipeWarehouse = tipeWarehouse,
        wsport = wsport,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}

