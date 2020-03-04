package com.mindyapps.android.quizmoxymvp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import com.mindyapps.android.quizmoxymvp.mvp.models.Category
import android.widget.TextView
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mindyapps.android.quizmoxymvp.R


class SpinnerAdapter(context: Context, var listItems: List<Category>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.spinner_style, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }

        val params = view.layoutParams
        params.height = 75
        view.layoutParams = params

        vh.label.text = listItems[position].name
        return view
    }

    override fun getItem(position: Int): Category {
        return listItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listItems.size
    }

    private class ItemRowHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.txtDropDownLabel) as TextView
    }
}