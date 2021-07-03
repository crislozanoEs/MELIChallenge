package com.crisav2.challengemeli.list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crisav2.challengemeli.MainActivity
import com.crisav2.challengemeli.R
import com.crisav2.challengemeli.common.applicationComponent
import com.crisav2.challengemeli.list.ProductList
import com.crisav2.challengemeli.list.di.ProductListModule
import com.crisav2.challengemeli.list.view.list.ProductAdapter
import com.crisav2.core.data.Product
import javax.inject.Inject

private const val ARG_PARAM_1 = "param.1.keyword"
class ProductListFragment : Fragment(), ProductList.View {

    lateinit var listView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var errorTextView: TextView
    lateinit var titleTextView: TextView

    @Inject
    lateinit var presenter: ProductList.Presenter

    private var keyword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            keyword = it.getString(ARG_PARAM_1)
        }
        inject()
    }

    private fun inject() {
        applicationComponent
            .plus(ProductListModule((this)))
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        keyword?.let {
            presenter.initialize(it)
            keyword = null
        }?: presenter.evaluateKeyword()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_product_list, container, false)
        setUpView(root)
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    private fun setUpView(root: View) {
        listView = root.findViewById(R.id.listViewProductList)
        progressBar = root.findViewById(R.id.progressBarProductList)
        errorTextView = root.findViewById(R.id.textViewErrorSearch)
        titleTextView = root.findViewById(R.id.textViewTitleProductList)
    }

    companion object {
        @JvmStatic
        fun newInstance(keyword: String) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_1, keyword)
                }
            }
    }

    override fun showError(error: String) {
        listView.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = error
    }

    override fun showLoading(isShowing: Boolean) {
        if(isShowing){
            progressBar.visibility = View.VISIBLE
            listView.visibility = View.GONE
            errorTextView.visibility = View.GONE
        }else{
            progressBar.visibility = View.GONE
        }

    }

    override fun setTitle(keyword: String) {
        titleTextView.text = keyword
    }

    override fun showList(productList: List<Product>) {
        errorTextView.visibility = View.GONE
        listView.visibility = View.VISIBLE
        val adapter = ProductAdapter{id -> presenter.validateIdAndNavigate(id)}
        adapter.productList = productList
        listView.adapter = adapter
    }

    override fun goToProductDetail(id: String) {
        (activity as MainActivity).goToProductDetail(id)
    }
}