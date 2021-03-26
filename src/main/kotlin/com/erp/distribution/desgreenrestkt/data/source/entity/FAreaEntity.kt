package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FArea
import com.erp.distribution.desgreenrestkt.domain.model.FMaterial
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.type.IntegerType
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlTransient
import kotlin.collections.HashSet

@JacksonXmlRootElement
@Entity
@Table(name = "farea")
class FAreaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id : Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
    var sourceId : Int =0,

    @Column(name = "KODE1", length = 10)
    var kode1 : String ="",

    @Column(name = "KODE2", length = 20)
    var kode2 : String ="",

    @Column(name = "DESCRIPTION", length = 100)
    var description : String ="",

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable =false)
    var fdivisionBean : Int =0,

//    @ManyToOne
//    @JoinColumn(name="fregionBean", referencedColumnName="ID")
//    var fregionBean: FRegion? = FRegion(),
    @Column(name = "fregionBean", nullable = true)
    var fregionBean : Int? =0,

    @Column(name = "STATUS_ACTIVE")
    var statusActive : Boolean =true,


    @JsonIgnore
    @OneToMany(mappedBy="fareaBean", fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fsubareaSet :Set<FSubAreaEntity> = HashSet<FSubAreaEntity>(),

//    @XmlTransient
//    @OneToMany(mappedBy="validAreaBean", fetch=FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
//    var fpromotionRulesdValidCustsSet :Set<FPromotionRulesdValidCustsEntity> = HashSet<FPromotionRulesdValidCustsEntity>(),

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created : Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified : Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy : String ="" //User ID

): Serializable

internal fun FAreaEntity.toDomain(): FArea {
    return FArea(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fdivisionBean = fdivisionBean,
        fregionBean = fregionBean,
        statusActive = statusActive,

        fsubareaSet = fsubareaSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy

    )
}