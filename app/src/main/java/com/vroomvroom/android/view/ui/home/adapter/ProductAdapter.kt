package com.vroomvroom.android.view.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vroomvroom.android.MerchantQuery
import com.vroomvroom.android.R
import com.vroomvroom.android.databinding.ItemProductBinding
import com.vroomvroom.android.utils.OnProductClickListener

class ProductAdapter(
    private val product: List<MerchantQuery.Product?>,
    private val listenerProduct: OnProductClickListener
    ) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ItemProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = product[position]
        holder.binding.product = product

        holder.binding.productPrice.text = "₱${"%.2f".format(product?.price)}"

        holder.binding.root.setOnClickListener {
            listenerProduct.onClick(product)
        }
    }

    override fun getItemCount(): Int {
        return product.size
    }
}

@BindingAdapter("productImageUrl")
fun setProductImageUrl(imageView: ImageView, url: String?) {
    imageView.load(url)
}
