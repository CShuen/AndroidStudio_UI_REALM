package com.example.ui_design_honda

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.updateitem.*

class ItemAcitivity_PS:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updateitem2)

        val bundle: Bundle? = intent.extras
        val c_name = bundle!!.getString("Customer Name2")
        val n_ric = bundle!!.getString("NRIC2")
        val plc_no = bundle!!.getString("PLCNO2")
        val proc = bundle!!.getString("PROC")

        val cn_text = findViewById<EditText>(R.id.ivedname)
        val nric_text = findViewById<EditText>(R.id.ivednric)
        val plcno_text = findViewById<EditText>(R.id.ivedplcno)
        val proc_text = findViewById<EditText>(R.id.ivedproc)
        cn_text.setText(c_name)
        nric_text.setText(n_ric)
        plcno_text.setText(plc_no)
        proc_text.setText(proc)

        updatebtn.setOnClickListener {
            val uniqueid =
                Realm.getDefaultInstance().where<Cases2>().equalTo("plcno", plc_no).findFirst()

            Realm.getDefaultInstance().executeTransaction {
                uniqueid?.cname = cn_text.text.toString()
                uniqueid?.nric2 = nric_text.text.toString()
                uniqueid?.proc = proc_text.text.toString()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }
    }
}