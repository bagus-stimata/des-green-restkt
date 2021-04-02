package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FtPriceAlth
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.io.Serializable
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@JacksonXmlRootElement
@Entity
@Table(name =  "ftpricealth" )
data class FtPriceAlthEntity (
    //** Tools: Jangan dihapus
    //** End Tools
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_header",
        allocationSize = 20,
        initialValue = 912345668
    )
    @Column(name =  "ID" , length = 9)
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
//    @JsonIgnore
    @Column(name =  "STATUS_ACTIVE" )
    var statusActive  :Boolean =true,


//    @Transient
    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var ftPriceAltdSet : Set<FtPriceAltdItemsEntity?>? = HashSet<FtPriceAltdItemsEntity>(),
//    var ftPriceAltdSet : Set<FtPriceAltdItemsEntity> = HashSet<FtPriceAltdItemsEntity>(),

//    @Transient
    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fCustomerSet :Set<FCustomerEntity> = HashSet<FCustomerEntity>(),

//    @Transient
    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fSalesmanSet :Set<FSalesmanEntity> = HashSet<FSalesmanEntity>(),

//    @Transient
    @OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fCustomerGroupSet :Set<FCustomerGroupEntity> = HashSet<FCustomerGroupEntity>(),


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


internal fun FtPriceAlthEntity.toDomain(): FtPriceAlth {
    return FtPriceAlth(
        id = id,
        sourceId = sourceId,

        noRek = noRek,
        trDate = trDate,
        description = description,
        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

//        ftPriceAltdSet = ftPriceAltdSet.map { it.toDomain() }.toSet(),
//        fCustomerSet = fCustomerSet,
//        fSalesmanSet = fSalesmanSet,
//        fCustomerGroupSet = fCustomerGroupSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
