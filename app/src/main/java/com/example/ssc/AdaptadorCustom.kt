package com.example.ssc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.LayoutInflaterFactory

class AdaptadorCustom(var context:Context, items:ArrayList<Movimiento>): BaseAdapter() {

    var items:ArrayList<Movimiento>? = null

    init {
        this.items = items
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder:ViewHolder? = null

        var vista:View? = convertView
        if(vista==null){
            vista= LayoutInflater.from(context).inflate(R.layout.template, null)
            holder = ViewHolder(vista)
            vista.tag = holder
        }else{
            holder = vista.tag as? ViewHolder
        }

        val item = getItem(position) as Movimiento
        holder?.tipo?.text = item.tipo
        holder?.cliente?.text = item.nCliente
        holder?.fecha?.text = item.fecha

        return vista!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items?.count()!!
    }

    private class ViewHolder(vista:View){
        var tipo:TextView? = null
        var cliente:TextView? = null
        var fecha:TextView? = null

        init{
            tipo = vista.findViewById(R.id.tipo)
            cliente = vista.findViewById(R.id.cliente)
            fecha = vista.findViewById(R.id.fecha)
        }
    }
}