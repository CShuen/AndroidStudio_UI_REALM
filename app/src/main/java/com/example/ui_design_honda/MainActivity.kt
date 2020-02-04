package com.example.ui_design_honda

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.casesview.*
import kotlinx.android.synthetic.main.casesview.recyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var check:String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        var realmConFig = RealmConfiguration.Builder()
            .name("task.realm")
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConFig)
        val realm = Realm.getDefaultInstance()


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        var myList = realm.where<Cases>().findAll()
        var adapter = casesAdapter(this, myList)
        recyclerView.adapter = adapter

        check = "New Business"

        var textnbno = findViewById<TextView>(R.id.nbno)
        var textnb = findViewById<TextView>(R.id.nb)
        var textplcno = findViewById<TextView>(R.id.plcno)
        var textplc = findViewById<TextView>(R.id.plc)
        var textt = findViewById<TextView>(R.id.textV)
        Realm.getDefaultInstance().executeTransaction {
            var countnb = realm.where<Cases>().count().toString()
            var countps = realm.where<Cases2>().count().toString()
            textt.setText("Showing" + countnb + "pending case(s)")
            textnbno.setText(countnb)
            textplcno.setText(countps)
        }


        textnbno.setOnClickListener {
            check = "New Business"
            textplcno.setTextColor(Color.parseColor("#40CC99FF"))
            textplc.setTextColor(Color.parseColor("#40ffffff"))
            textnbno.setTextColor(Color.parseColor("#CC99FF"))
            textnb.setTextColor(Color.parseColor("#ffffff"))
            var myList = realm.where<Cases>().findAll()
            var adapter = casesAdapter(this, myList)
            recyclerView.adapter = adapter
            Realm.getDefaultInstance().executeTransaction {
                var countnb = realm.where<Cases>().count().toString()
                textt.setText("Showing " + countnb + " pending case(s)")
                textnbno.setText(countnb)
            }


        };

        textnb.setOnClickListener {
            check = "New Business"
            textplcno.setTextColor(Color.parseColor("#40CC99FF"))
            textplc.setTextColor(Color.parseColor("#40ffffff"))
            textnbno.setTextColor(Color.parseColor("#CC99FF"))
            textnb.setTextColor(Color.parseColor("#ffffff"))
            var myList = realm.where<Cases>().findAll()
            var adapter = casesAdapter(this, myList)
            recyclerView.adapter = adapter
            Realm.getDefaultInstance().executeTransaction {
                var countnb = realm.where<Cases>().count().toString()
                textt.setText("Showing " + countnb + " pending case(s)")
                textnbno.setText(countnb)
            }


        };

        textplcno.setOnClickListener {
            check = "PS"
            textnbno.setTextColor(Color.parseColor("#40CC99FF"))
            textnb.setTextColor(Color.parseColor("#40ffffff"))
            textplcno.setTextColor(Color.parseColor("#CC99FF"))
            textplc.setTextColor(Color.parseColor("#ffffff"))
            var myList = realm.where<Cases2>().findAll()
            var adapter = anotherAdapter(this, myList)
            recyclerView.adapter = adapter
            Realm.getDefaultInstance().executeTransaction {
                var countps = realm.where<Cases2>().count().toString()
                textt.setText("Showing " + countps + " pending case(s)")
                textplcno.setText(countps)
            }


        }

        textplc.setOnClickListener {
            check = "PS"
            textnbno.setTextColor(Color.parseColor("#40CC99FF"))
            textnb.setTextColor(Color.parseColor("#40ffffff"))
            textplcno.setTextColor(Color.parseColor("#CC99FF"))
            textplc.setTextColor(Color.parseColor("#ffffff"))
            var myList = realm.where<Cases2>().findAll()
            var adapter = anotherAdapter(this, myList)
            recyclerView.adapter = adapter
            Realm.getDefaultInstance().executeTransaction {
                var countps = realm.where<Cases2>().count().toString()
                textt.setText("Showing " + countps + " pending case(s)")
                textplcno.setText(countps)
            }


        }

        addbtn.setOnClickListener {
            val intent = Intent(this, AddData::class.java)
            startActivity(intent)
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search()
                return true
            }


            override fun onQueryTextChange(p0: String?): Boolean {
                search()
                return true
            }
        })
    }

    fun search() {
        when (check) {
            "New Business" -> {
                var filteredList: ArrayList<Cases> = ArrayList<Cases>()
                var searchInput = searchView.query.toString().toLowerCase()

                for (case in Realm.getDefaultInstance().where<Cases>().findAll()) {
                    if ((case.plcno?.toLowerCase()!!.contains(searchInput)) || (case.cname?.toLowerCase()!!.contains(searchInput)) || (case.nric?.toLowerCase()!!.contains(searchInput))) {
                        filteredList.add(case)
                    }
                }

                var searchAdapter = casesAdapter(this,filteredList)
                recyclerView.adapter = searchAdapter
            }
            "PS" -> {
                var filteredList: ArrayList<Cases2> = ArrayList<Cases2>()
                var searchInput = searchView.query.toString().toLowerCase()

                for (case in Realm.getDefaultInstance().where<Cases2>().findAll()) {
                    if (case.plcno?.toLowerCase()!!.contains(searchInput)|| case.cname?.toLowerCase()!!.contains(searchInput) || (case.nric2?.toLowerCase()!!.contains(searchInput))|| case.proc?.toLowerCase()!!.contains(searchInput)){
                        filteredList.add(case)
                    }
                }
                var searchAdapter = anotherAdapter(this,filteredList)
                recyclerView.adapter = searchAdapter


            }
        }


    }
}
