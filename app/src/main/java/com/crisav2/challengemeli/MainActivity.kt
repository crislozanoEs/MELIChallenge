package com.crisav2.challengemeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.crisav2.challengemeli.common.Constants
import com.crisav2.challengemeli.home.view.HomeFragment
import com.crisav2.challengemeli.list.view.ProductListFragment
import com.crisav2.challengemeli.product.view.ProductDetailFragment

class MainActivity : AppCompatActivity() {

  private var currentFragment: Fragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    savedInstanceState?.let{
      currentFragment = supportFragmentManager.getFragment(savedInstanceState, Constants.currentFragmentTag)
    }?: run{
      replaceFragment(HomeFragment.newInstance(), Constants.homeFragmentTag)
    }
  }

  private fun replaceFragment(fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, fragment,tag)
      .addToBackStack(tag)
      .commitAllowingStateLoss()
  }

  fun goToList(keyword: String){
    replaceFragment(ProductListFragment.newInstance(keyword),Constants.listFragmentTag)
  }

  fun goToProductDetail(id: String) {
    replaceFragment(ProductDetailFragment.newInstance(id),Constants.productFragmentTag)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    currentFragment?.let{
      supportFragmentManager.putFragment(outState,Constants.currentFragmentTag, it)
    }
  }


}