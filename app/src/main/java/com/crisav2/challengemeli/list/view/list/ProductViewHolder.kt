package com.crisav2.challengemeli.list.view.list

import android.provider.ContactsContract.Intents.Insert.DATA
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crisav2.challengemeli.R
import com.crisav2.core.data.Product
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout

class ProductViewHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView){

    private val requestImage = Glide.with(itemView.context)

    private val textViewProductTitle: TextView = itemView.findViewById(R.id.textViewProductTitle)
    val completeLayout: ConstraintLayout = itemView.findViewById(R.id.completeLayout)
    private val imageProduct: ImageView = itemView.findViewById(R.id.imageProduct)
    private val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
    private val textViewProductSupplier: TextView = itemView.findViewById(R.id.textViewProductSupplier)

    fun setUpProduct(product: Product){
        textViewProductTitle.text = product.titles
        textViewProductPrice.text = product.price
        requestImage
          .load(product.thumbnail)
          .into(imageProduct)
    }


}