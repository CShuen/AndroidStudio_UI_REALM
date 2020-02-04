package com.example.ui_design_honda

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import kotlinx.android.synthetic.main.cases.view.*

class casesAdapter(val context: Context, val names:List<Cases>):RecyclerView.Adapter<casesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): casesAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cases, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cname = names[position]
        val nric = names[position]
        val plcno = names[position]
        holder.setData(cname, nric, plcno, position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentName: Cases? = null
        var currentNRIC: Cases? = null
        var currentPlcNo: Cases? = null
        var currentPosition: Int = 0

        init {
            itemView.dltbtn.setOnClickListener {

            }


        }


        fun setData(cname: Cases?, nric: Cases?, plcno: Cases?, pos: Int) {
            itemView.cusname.text = cname!!.cname
            itemView.nricno.text = nric!!.nric
            itemView.policyno.text = plcno!!.plcno


            itemView.dltbtn.setOnClickListener {
                Realm.getDefaultInstance().executeTransaction{
                    cname.deleteFromRealm()
                }
                notifyDataSetChanged()
            }

            itemView.editbtn.setOnClickListener {
                val intent = Intent(context, ItemActivity::class.java)
                var iname: String? = currentName!!.cname
                var inric: String? = currentNRIC!!.nric
                var iplcno: String? = currentPlcNo!!.plcno
                intent.putExtra("Customer Name",iname)
                intent.putExtra("NRIC",inric)
                intent.putExtra("PLCNO",iplcno)
                startActivity(context, intent, null);
            }

            this.currentName = cname
            this.currentNRIC = nric
            this.currentPlcNo = plcno
            this.currentPosition = pos
        }
    }



}