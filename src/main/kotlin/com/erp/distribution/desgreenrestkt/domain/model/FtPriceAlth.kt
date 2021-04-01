package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.*
import com.erp.distribution.desgreenrestkt.presentation.model.FtPriceAlthRes
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FtPriceAlth (
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

    /**
     * Pastikan yang ditarik pasti aktif
     */
    @JsonIgnore
    @Column(name =  "STATUS_ACTIVE" )
    var statusActive  :Boolean =true,


//    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    var ftPriceAltdSet : Set<FtPriceAltdItems> = HashSet<FtPriceAltdItems>(),

//    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    var fCustomerSet :Set<FCustomer> = HashSet<FCustomer>(),

//    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    var fSalesmanSet :Set<FSalesman> = HashSet<FSalesman>(),

    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fCustomerGroupSet :Set<FCustomerGroup> = HashSet<FCustomerGroup>(),

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


): Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FtPriceAlth

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

internal fun FtPriceAlth.toEntity(): FtPriceAlthEntity {
    return FtPriceAlthEntity(
        id = id,
        sourceId = sourceId,

        noRek = noRek,
        trDate = trDate,
        description = description,
        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

//        ftPriceAltdSet = ftPriceAltdSet.map { it.toEntity() }.toSet(),
//        fCustomerSet = fCustomerSet,
//        fSalesmanSet = fSalesmanSet,
//        fCustomerGroupSet = fCustomerGroupSet,


        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
internal fun FtPriceAlth.toResponse(): FtPriceAlthRes {
    return FtPriceAlthRes(
        id = id,
        sourceId = sourceId,

        noRek = noRek,
        trDate = trDate,
        description = description,
        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

//        ftPriceAltdSet = ftPriceAltdSet.map { it.toResponse() }.toSet(),

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}

