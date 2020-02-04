package com.example.ui_design_honda

import io.realm.RealmObject

open class Cases(var cname:String?=null, var nric:String?=null, var plcno:String?=null):RealmObject()



object Detail{
    val names = listOf<Cases>(
        Cases("Chong Mah Xing", "NRIC: 123456789", "Policy No. : TL239948"),
        Cases("Chong Mah Xing", "NRIC: 123456789", "Policy No. : TL239948"),
        Cases("Chong Mah Xing", "NRIC: 123456789", "Policy No. : TL239948")
    )
}