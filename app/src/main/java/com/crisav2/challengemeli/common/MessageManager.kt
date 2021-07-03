package com.crisav2.challengemeli.common

import android.content.Context

class MessageManager(val context: Context): IMessageManager {

    override val errorEmptyProductID: String
        get() = getMessage(Message.PRODUCT_DETAIL_ERROR_EMPTY_ID)

    override val errorEmptyResults: String
        get() = getMessage(Message.PRODUCT_LIST_ERROR_EMPTY_RESULT)

    override val searchTextViewTitle: String
        get() = getMessage(Message.SEARCH_TEXT_VIEW_TILE)

    override val searchButtonTitle: String
        get() = getMessage(Message.SEARCH_BUTTON_TILE)

    override val errorEmptySearch: String
        get() = getMessage(Message.PRODUCT_LIST_ERROR)

    override val titleProductListScreen: String
        get() = getMessage(Message.PRODUCT_LIST_TITLE)

    private fun getMessage(message: Message): String {
        return context.getString(Message.valueOf(message.name).id)
    }

}