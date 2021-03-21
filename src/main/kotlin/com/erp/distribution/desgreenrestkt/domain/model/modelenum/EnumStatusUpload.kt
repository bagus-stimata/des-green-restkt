package com.erp.distribution.desgreenrestkt.domain.model.modelenum

enum class EnumStatusUpload(val intCode: Int, val strCode: String, val description: String) {
    OPEN(0, "OPEN", "Belum dilakukan apapun"), UPLOADED(1, "UPLOADED", "Selesai di upload"), PENDING(2, "PENDING", "Pending atau penundaan");

}