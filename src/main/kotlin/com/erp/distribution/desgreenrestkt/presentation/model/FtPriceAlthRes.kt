package com.erp.distribution.desgreenrestkt.presentation.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.desgreenrestkt.domain.model.*
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FtPriceAlthRes (
    //** Tools: Jangan dihapus
    //** End Tools
    var id :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @JsonIgnore
    @Column(name =  "SOURCE_ID" , length = 9)
    var sourceId :Int =0,

    @Column(name =  "NO_REK" , length = 15)
    var noRek :String ="",

    @Column(name =  "DESCRIPTION" , length = 50)
    var description :String ="",

    //	@ManyToOne
    //	@JoinColumn(name= fdivisionBean , referencedColumnName= ID , nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name =  "fdivisionBean" , nullable = false)
    var fdivisionBean :Int =0,

    @Temporal(TemporalType.DATE)
    @Column(name =  "TR_DATE" )
    var trDate :Date =Date(),


//    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    var ftPriceAltdSet : Set<FtPriceAltdItemsRes> = HashSet<FtPriceAltdItemsRes>(),

//    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    var fCustomerSet :Set<FCustomerRes> = HashSet<FCustomerRes>(),

//    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    var fSalesmanSet :Set<FSalesmanRes> = HashSet<FSalesmanRes>(),

    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fCustomerGroupSet :Set<FCustomerGroupRes> = HashSet<FCustomerGroupRes>(),

    /**
     * Pastikan yang ditarik pasti aktif
     */
    @JsonIgnore
    @Column(name =  "STATUS_ACTIVE" )
    var statusActive  :Boolean =true,

    @JsonIgnore
    @Column(name =  "CREATED" )
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name =  "MODIFIED" )
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @JsonIgnore
    @Column(name =  "MODIFIED_BY" , length = 20)
    var modifiedBy :String =""    //User ID

): Serializable

internal fun FtPriceAlthRes.toDomain(): FtPriceAlth {
    return FtPriceAlth(
        id = id,
        sourceId = sourceId,

        noRek = noRek,
        trDate = trDate,
        description = description,
        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

//        ftPriceAltdSet = ftPriceAltdSet.map { it.toDomain() }.toSet(),


        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
