package com.example.ui_design_honda

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.adddata.*
import kotlinx.android.synthetic.main.casesview.*

class AddData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adddata)
        var value = true


        donebtn.setOnClickListener {

            if (value == false) {
                Realm.init(this)
                val realm = Realm.getDefaultInstance()
                var name_add = findViewById<EditText>(R.id.name_edit).text.toString()
                var nric_add = findViewById<EditText>(R.id.nric_edit).text.toString()
                var plcno_add = findViewById<EditText>(R.id.plcno_edit).text.toString()


                var addeditem = Cases(name_add, nric_add, plcno_add)
                realm.beginTransaction()
                realm.insert(addeditem)
                realm.commitTransaction()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else{
                Realm.init(this)
                val realm = Realm.getDefaultInstance()
                var name_add2 = findViewById<EditText>(R.id.name_edit).text.toString()
                var nric_add2 = findViewById<EditText>(R.id.nric_edit).text.toString()
                var plcno_add2 = findViewById<EditText>(R.id.plcno_edit).text.toString()
                var proc_add2 = findViewById<EditText>(R.id.process_edit).text.toString()


                var addeditem2 = Cases2(name_add2, nric_add2, plcno_add2,proc_add2)
                realm.beginTransaction()
                realm.insert(addeditem2)
                realm.commitTransaction()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

        }


        val radionb = findViewById<RadioButton>(R.id.radioBtnNB)
        val radioplc = findViewById<RadioButton>(R.id.radioBtnPlc)


        radionb.setOnClickListener{

            processf.visibility = View.GONE
            process_edit.visibility = View.GONE
            value = false
        }


        radioplc.setOnClickListener{
            processf.visibility = View.VISIBLE
            process_edit.visibility = View.VISIBLE
            value = true
        }
}
}