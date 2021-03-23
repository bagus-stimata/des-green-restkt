package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fmaterial_pic")
data class FMaterialPic(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name =  "ID")
    var id: Long  =0L,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fmaterialBean :Int =0,)
    //	private FMaterial fmaterialBean;
    @Column(name =  "fmaterialBean")
    var fmaterialBean  :Int =0,

    /*
	 * Nomor Urut:
	 * 1 = adalah gambar produk depan (Utama)
	 * 2 = adalah gambar produk sisi lain
	 * 3 = adalah gambar produk sisi lain
	 * 4 = aalah gambar produk sisi lain
	 */
    @Column(name =  "NOMOR_URUT")
    var nomorUrut  :Int =0,

    //DOC, PIC, PDF
    @Column(name =  "TIPE_FILE")
    var tipeFile :Int =0,

    @Column(name =  "image_name")
    var imageName :Int =0,

    @Column(name = "TITLE", length = 155)
    var title :Int =0,

    @Column(name = "NOTES", length = 255)
    var description :Int =0,

    /*
	 * MOBILE = diupload dari mobile
	 * APP_WEB = dari aplikasi Web
	 */
    @Column(name = "UPLOAD_FROM", length = 50)
    var uploadFrom :Int =0,

    //	@Column(name =  :Int =0,image_capture_by :Int =0,, length=30)
    //	private String imageCapturedBy =  :Int =0, :Int =0,;
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name ="MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name ="MODIFIED_BY", length = 20)
    var modifiedBy: String = "" //User ID

): Serializable