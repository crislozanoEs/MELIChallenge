package com.crisav2.challengemeli.list.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crisav2.challengemeli.R
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.common.MessageManager
import com.crisav2.challengemeli.repository.model.ProductNetwork
import com.crisav2.core.data.Product
import com.crisav2.core.usecase.Transformation

class ProductAdapter(
    private val onProductClick: (id: String) -> Unit,
    private val messageManager: IMessageManager,
    private val transformation: Transformation
): RecyclerView.Adapter<ProductViewHolder>() {

    var productList: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, messageManager, transformation)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setUpProduct(productList[position])
        holder.completeLayout.setOnClickListener {
            onProductClick(productList[position].id)
        }
    }

    override fun getItemCount(): Int = productList.size
}