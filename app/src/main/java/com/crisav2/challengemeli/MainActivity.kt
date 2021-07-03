package com.crisav2.challengemeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.crisav2.challengemeli.home.view.HomeFragment
import com.crisav2.challengemeli.list.view.ProductListFragment
import com.crisav2.challengemeli.product.ProductDetail
import com.crisav2.challengemeli.product.view.ProductDetailFragment

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    replaceFragment(HomeFragment.newInstance(), "HOME_FRAGMENT")
  }

  private fun replaceFragment(fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, fragment,tag)
      .addToBackStack(tag)
      .commitAllowingStateLoss()
  }

  fun goToList(keyword: String){
    actionBar?.setDisplayHomeAsUpEnabled(true)
    replaceFragment(ProductListFragment.newInstance(keyword),"LIST_FRAGMENT")
  }

  fun goToProductDetail(id: String) {
    replaceFragment(ProductDetailFragment.newInstance(id),"PRODUCT_DETAIL_FRAGMENT")
  }
}