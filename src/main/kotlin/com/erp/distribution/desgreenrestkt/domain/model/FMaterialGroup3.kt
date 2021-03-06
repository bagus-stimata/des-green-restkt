package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialGroup3Res
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FMaterialGroup3 (
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

    @Column(name = "DESCRIPTION", length = 100)
    var description :String ="",

    @Transient
    var tempInt1  :Int =0,

    @Transient
    var tempInt2  :Int =0,

    @Transient
    var tempInt3  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup2Bean", referencedColumnName="ID")
    //	private FMaterialGroup2 fmaterialGroup2Bean;
    @Column(name = "fmaterialGroup2Bean", nullable = false)
    var fmaterialGroup2Bean  :Int =0,

    @Column(name = "STATUS_ACTIVE")
    var statusActive  :Boolean =true,

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID

): Serializable{
    constructor(theId: Int): this(id = theId)
}

internal fun FMaterialGroup3.toEntity(): FMaterialGroup3Entity {
    return FMaterialGroup3Entity(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fmaterialGroup2Bean = fmaterialGroup2Bean,
        statusActive = statusActive,

        tempInt1 = tempInt1,
        tempInt2 = tempInt2,
        tempInt3 = tempInt3,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
internal fun FMaterialGroup3.toResponse(): FMaterialGroup3Res {
    return FMaterialGroup3Res(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fmaterialGroup2Bean = fmaterialGroup2Bean,
        statusActive = statusActive,

        tempInt1 = tempInt1,
        tempInt2 = tempInt2,
        tempInt3 = tempInt3,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}

