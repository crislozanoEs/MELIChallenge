package com.crisav2.challengemeli.common

import com.crisav2.challengemeli.R

enum class Message(val key: String, val id: Int) {
    //Search screen
    SEARCH_TEXT_VIEW_TILE("search.text.view.title", R.string.search_edit_text_title),
    SEARCH_BUTTON_TILE("search.button.title", R.string.search_button_title),
    //Product list screen
    PRODUCT_LIST_ERROR("product.list.error.empty.search",R.string.product_list_error_empty_search),
    PRODUCT_LIST_TITLE("product.list.title",R.string.product_list_title),
    PRODUCT_LIST_ERROR_EMPTY_RESULT("product.list.error.empty.result",R.string.product_list_error_empty_result),
    PRODUCT_DETAIL_ERROR_EMPTY_ID("product.detail.error.empty.id",R.string.product_detail_error_empty_id)
}