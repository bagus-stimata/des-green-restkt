package com.erp.distribution.desgreenrestkt.data.source.entity_security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

//@Entity(name="tb_user")
@JacksonXmlRootElement
@Entity
@Table(name = "tb_user")
class FUser : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    @Column(unique = true)
    var email = ""

    @Column(unique = true)
    var username = ""
    var password = ""

    @Transient
    var passwordConfirm = ""

    @Column(name = "full_name")
    var fullName = ""

    @Column(name = "phone")
    var phone = ""

    @Column(name = "notes")
    var notes = ""

    @JsonIgnore
    @OneToMany(mappedBy = "fuserBean")
    var fuserRoles: List<FUserRoles>? = null

    @JsonIgnore
    @Transient
    var tempRoles: MutableList<String>? = null

    @JsonIgnore
    @OneToMany(mappedBy = "fuserBean")
    private var fUserVendors: List<FUserVendors>? = null

    @JsonIgnore
    @Transient
    var tempVendors: List<Int>? = null

    // @NotBlank
    // @Size(max = 255)
    // private String role;
    var locked = false

    /**
     * Tambahan for DES Setting
     */
    var fsalesmanBean: Int? = 0
    var fdivisionBean: Int?  = 0
    var fwarehouseBean: Int?  = 0

    @Transient
    var tempInt1 = 0

    @Transient
    var tempBol1 = false

    @Transient
    var tempBol2 = false

    @Column(name = "created")
    var created = Date()

    @Column(name = "lastmodified")
    var lastModified = Date()

    @Column(name = "modified_by")
    var modifiedBy = ""

    override fun toString(): String {
        return "FUser [id=$id]"
    }

    fun getfUserVendors(): List<FUserVendors>? {
        return fUserVendors
    }

    fun setfUserVendors(fUserVendors: List<FUserVendors>?) {
        this.fUserVendors = fUserVendors
    }

}