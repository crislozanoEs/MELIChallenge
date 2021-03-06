package com.crisav2.challengemeli.product.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crisav2.challengemeli.R
import com.crisav2.challengemeli.common.applicationComponent
import com.crisav2.challengemeli.product.ProductDetail
import com.crisav2.challengemeli.product.di.ProductDetailModule
import com.crisav2.core.data.Product
import javax.inject.Inject

private const val ARG_PARAM_1 = "param.1.product.id"

/**
 * ProductDetailFragment - View
 *
 * Fragmento que representa el detalle de un producto.
 * Presenter: ProductDetail.Presenter
 */


class ProductDetailFragment : Fragment(), ProductDetail.View {

  private var productId: String? = null

  private lateinit var textViewProductTitle: TextView
  private lateinit var textViewProductPrice: TextView
  private lateinit var textViewProductQuantity: TextView
  private lateinit var textViewProductCondition: TextView
  private lateinit var textViewError: TextView
  private lateinit var progressBar: ProgressBar
  private lateinit var imageViewProductImage: ImageView
  private lateinit var buttonTryAgain: Button

  private var onClickTryAgain: View.OnClickListener? = null

  @Inject
  lateinit var presenter: ProductDetail.Presenter

  // Lifecycle methods section
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      productId = it.getString(ARG_PARAM_1)

    }
    inject()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val root = inflater.inflate(R.layout.fragment_product_detail, container, false)
    setUpView(root)
    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.initialize(productId)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.destroy()
  }

  // Public methods section
  override fun setProductInView() {
    showProduct(true)
    showError(
      isShowing = false,
      withButton = false)
  }

  override fun setDataInView(product: ProductDetail.ProductInView){
    textViewProductTitle.text = product.title
    textViewProductPrice.text = product.price
    textViewProductQuantity.text = product.quantity
    textViewProductCondition.text = product.condition

    Glide.with(context)
      .load(product.thumbnail)
      .fallback(R.drawable.ic_default_image)
      .error(R.drawable.ic_default_image)
      .into(imageViewProductImage)
  }

  override fun setUpTryAgainButtonTitle(titleTryAgainProductDetail: String) {
    buttonTryAgain.text = titleTryAgainProductDetail
  }

  override fun showLoading(isShowing: Boolean) {
    if(isShowing){
      progressBar.visibility = View.VISIBLE
    }else{
      progressBar.visibility = View.GONE
    }
  }

  override fun setErrorView(error: String, withButton: Boolean) {
    showProduct(false)
    showError(true, withButton)
    textViewError.text = error
  }

  // Private methods section
  private fun setUpView(root: View) {
    textViewProductTitle = root.findViewById(R.id.textViewProductDetailTitle)
    imageViewProductImage = root.findViewById(R.id.imageViewProductDetailImage)
    textViewProductPrice = root.findViewById(R.id.textViewProductDetailPrice)
    textViewProductQuantity = root.findViewById(R.id.textViewProductDetailQuantity)
    textViewProductCondition = root.findViewById(R.id.textViewProductDetailType)
    progressBar = root.findViewById(R.id.progressBarProductDetail)
    textViewError = root.findViewById(R.id.textViewErrorProductDetail)
    buttonTryAgain = root.findViewById(R.id.buttonProductDetailTryAgain)
    onClickTryAgain = View.OnClickListener {
      presenter.tryDetailAgain()
    }
    showError(
      isShowing = false,
      withButton = false)
    showProduct(false)
  }

  private fun showError(isShowing: Boolean, withButton: Boolean) {
    val visibility = if(isShowing){
      View.VISIBLE
    }else{
      View.GONE
    }
    val visibilityButton = if(withButton){
      View.VISIBLE
    }else{
      View.GONE
    }
    val listenerButton = if(withButton){
      onClickTryAgain
    }else{
      null
    }
    textViewError.visibility = visibility
    buttonTryAgain.visibility = visibilityButton
    buttonTryAgain.isEnabled = withButton
    buttonTryAgain.setOnClickListener(listenerButton)
  }

  private fun showProduct(isShowing: Boolean){
    val visibility = if(isShowing){
      View.VISIBLE
    }else{
      View.GONE
    }
    textViewProductTitle.visibility = visibility
    imageViewProductImage.visibility = visibility
  }

  private fun inject() {
    applicationComponent
      .plus(ProductDetailModule((this)))
      .inject(this)
  }

  companion object {
    @JvmStatic
    fun newInstance(productId: String) =
      ProductDetailFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM_1, productId)
        }
      }
  }
}