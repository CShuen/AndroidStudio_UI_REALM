package com.example.ui_design_honda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.cases.view.*
import kotlinx.android.synthetic.main.cases2.view.*
import kotlinx.android.synthetic.main.updateitem.*

class ItemActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updateitem)



        val bundle: Bundle? = intent.extras
        val c_name = bundle!!.getString("Customer Name")
        val n_ric = bundle!!.getString("NRIC")
        val plc_no = bundle!!.getString("PLCNO")

        val cn_text = findViewById<EditText>(R.id.ivedname)
        val nric_text = findViewById<EditText>(R.id.ivednric)
        val plcno_text = findViewById<EditText>(R.id.ivedplcno)
        cn_text.setText(c_name)
        nric_text.setText(n_ric)
        plcno_text.setText(plc_no)

        updatebtn.setOnClickListener {
            val uniqueid = Realm.getDefaultInstance().where<Cases>().equalTo("plcno",plc_no).findFirst()

            Realm.getDefaultInstance().executeTransaction {
                uniqueid?.cname = cn_text.text.toString()
                uniqueid?.nric = nric_text.text.toString()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }


        }


    }
}