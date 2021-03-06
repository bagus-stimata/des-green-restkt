package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FDistributionChannel
import com.erp.distribution.desgreenrestkt.domain.model.FMaterialGroup2
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fmaterial_group2")
data class FMaterialGroup2Entity (
    @Id
    @Column(name = "ID", length = 9)
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_master",
        allocationSize = 20,
        initialValue = 912345668
    )
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
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

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup1Bean", referencedColumnName="ID")
    //	private FMaterialGroup1 fmaterialGroup1Bean;
    @Column(name = "fmaterialGroup1Bean", nullable = false)
    var fmaterialGroup1Bean  :Int =0,

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

): Serializable

internal fun FMaterialGroup2Entity.toDomain(): FMaterialGroup2 {
    return FMaterialGroup2(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fmaterialGroup1Bean = fmaterialGroup1Bean,
        statusActive = statusActive,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
