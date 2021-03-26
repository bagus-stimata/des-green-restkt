package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*

class KeyIntDate {
    var id = 0
    var date: Date? = null

    constructor() {}
    constructor(iD: Int, date: Date?) : super() {
        id = iD
        this.date = date
    }

    fun getID(): Long {
        return id.toLong()
    }

    fun setID(iD: Int) {
        id = iD
    }


}