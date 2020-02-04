package com.example.ui_design_honda

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import kotlinx.android.synthetic.main.cases.view.*
import kotlinx.android.synthetic.main.cases.view.cusname
import kotlinx.android.synthetic.main.cases.view.nricno
import kotlinx.android.synthetic.main.cases.view.policyno
import kotlinx.android.synthetic.main.cases2.view.*
import kotlinx.android.synthetic.main.cases2.view.dltbtn2

class anotherAdapter(val context: Context, val names2:List<Cases2>):RecyclerView.Adapter<anotherAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): anotherAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cases2, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return names2.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cname = names2[position]
        val nric = names2[position]
        val plcno = names2[position]
        val proc = names2[position]
        holder.setData(cname, nric, plcno,proc, position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentName: Cases2? = null
        var currentNRIC: Cases2? = null
        var currentPlcNo: Cases2? = null
        var currentProc: Cases2? = null
        var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {

            }


        }

        fun setData(cname: Cases2?, nric: Cases2?, plcno: Cases2?,proc:Cases2?, pos: Int) {
            itemView.cusname.text = cname!!.cname
            itemView.nricno.text = nric!!.nric2
            itemView.policyno.text = plcno!!.plcno
            itemView.processtitle.text = proc!!.proc

            itemView.dltbtn2.setOnClickListener {
                Realm.getDefaultInstance().executeTransaction{
                    cname.deleteFromRealm()

                }
                notifyDataSetChanged()
            }



            itemView.editbtn2.setOnClickListener {
                val intent = Intent(context, ItemAcitivity_PS::class.java)
                var iname2: String? = currentName!!.cname
                var inric2: String? = currentNRIC!!.nric2
                var iplcno2: String? = currentPlcNo!!.plcno
                val iproc: String? = currentProc!!.proc
                intent.putExtra("Customer Name2",iname2)
                intent.putExtra("NRIC2",inric2)
                intent.putExtra("PLCNO2",iplcno2)
                intent.putExtra("PROC",iproc)
                startActivity(context, intent, null);
            }

            this.currentName = cname
            this.currentNRIC = nric
            this.currentPlcNo = plcno
            this.currentProc = proc
            this.currentPosition = pos
        }

    }



}