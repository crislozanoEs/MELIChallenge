package com.crisav2.challengemeli.list.view.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crisav2.challengemeli.R
import com.crisav2.core.data.Product
import androidx.constraintlayout.widget.ConstraintLayout
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.core.usecase.Transformation

class ProductViewHolder(
    itemView: View,
    val messageManager: IMessageManager,
    val transformation: Transformation
): RecyclerView.ViewHolder(itemView){

    private val requestImage = Glide.with(itemView.context)

    private val textViewProductTitle: TextView = itemView.findViewById(R.id.textViewProductTitle)
    val completeLayout: ConstraintLayout = itemView.findViewById(R.id.completeLayout)
    private val imageProduct: ImageView = itemView.findViewById(R.id.imageProduct)
    private val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
    private val textViewProductQuantity: TextView = itemView.findViewById(R.id.textViewProductQuantity)

    fun setUpProduct(product: Product){
        textViewProductTitle.text = product.titles
        textViewProductPrice.text = transformation.getPrettyPrice(product.price)
        textViewProductQuantity.text = "${messageManager.titleProductQuantity} ${product.availableQuantity}"
        requestImage
            .load(product.secureThumbnail)
            .fallback(R.drawable.ic_default_image)
            .error(R.drawable.ic_default_image)
            .into(imageProduct)
    }


}