package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*

class KeyLongDate {
    var iD: Long = 0
    var date: Date? = null

    constructor() {}
    constructor(iD: Long, date: Date?) : super() {
        this.iD = iD
        this.date = date
    }


}