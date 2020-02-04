package com.example.ui_design_honda

import io.realm.RealmObject

open class Cases2(var cname:String?=null, var nric2:String?=null, var plcno:String?=null, var proc:String?=null) : RealmObject()

object Detail2{
    val names2 = listOf<Cases2>(
        Cases2("Teh Toh Tah", "NRIC: 987654321", "Policy No. : CL847129", "Process: Deposit Withdrawal"),
        Cases2("Teh Toh Tah", "NRIC: 987654321", "Policy No. : CL847129", "Process: Deposit Withdrawal"),
        Cases2("Teh Toh Tah", "NRIC: 987654321", "Policy No. : CL847129", "Process: Deposit Withdrawal"),
        Cases2("Teh Toh Tah", "NRIC: 987654321", "Policy No. : CL847129", "Process: Deposit Withdrawal"),
        Cases2("Teh Toh Tah", "NRIC: 987654321", "Policy No. : CL847129", "Process: Deposit Withdrawal")




    )
}