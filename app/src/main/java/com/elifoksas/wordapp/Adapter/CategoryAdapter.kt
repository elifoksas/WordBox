package com.elifoksas.wordapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.elifoksas.wordapp.R
import com.elifoksas.wordapp.model.Categories
import com.squareup.picasso.Picasso

class CategoryAdapter(context: Context, categoryList: ArrayList<Categories>) : ArrayAdapter <Categories>(context, 0,categoryList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listitemView = convertView

        if (listitemView==null){

            listitemView = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false)
        }

        val categories : Categories? = getItem(position)

        val categoryImage = listitemView?.findViewById<ImageView>(R.id.categoryImage)
        val categoryName = listitemView?.findViewById<TextView>(R.id.categoryName)

        //categoryImage.setImageResource(categories.categoryImage)
        Picasso.get().load(categories?.categoryImage).into(categoryImage)
        categoryName?.setText(categories?.categoryName)



        return listitemView!!





    }


}