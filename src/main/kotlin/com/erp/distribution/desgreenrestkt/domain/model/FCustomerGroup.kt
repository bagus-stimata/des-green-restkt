package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FAreaEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerGroupEntity
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import com.erp.distribution.desgreenrestkt.presentation.model.FCustomerGroupRes
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FCustomerGroup (
    var id :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId :Int =0,

    @Column(name = "KODE1", length = 10)
    var kode1 :String ="",

    @Column(name = "KODE2", length = 20)
    var kode2 :String ="",

    @Column(name = "DESCRIPTION", length = 70)
    var description: String = "",

    @Column(name = "STATUS_ACTIVE")
    var statusActive :Boolean = true,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    @Column(name = "ftPriceAlthBean", nullable = true)
    var ftPriceAlthBean :Int? =0,

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User I

): Serializable

internal fun FCustomerGroup.toEntity(): FCustomerGroupEntity {
    return FCustomerGroupEntity(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fdivisionBean = fdivisionBean,
        ftPriceAlthBean = ftPriceAlthBean,
        statusActive = statusActive,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}

internal fun FCustomerGroup.toResponse(): FCustomerGroupRes {
    return FCustomerGroupRes(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fdivisionBean = fdivisionBean,
        ftPriceAlthBean = ftPriceAlthBean,
        statusActive = statusActive,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}