package com.crisav2.challengemeli.home

import android.content.Context
import io.reactivex.subjects.PublishSubject

interface Home {
    interface View{

        /**
         * Inicializa los listeners de la vista
         */

        fun initListeners()

        /**
         * Navega a la vista de la lista de productos
         * @param keyword palabra de busqueda
         */

        fun goListScreen(keyword: String)

        /**
         * Habilita el boton para buscar
         * @param enabled habilitar o no
         */

        fun enableSearchButton(enabled: Boolean)

        /**
         * Agrega el titulo de la pantalla
         * @param title titulo completo
         */

        fun setTextViewTitle(title: String)

        /**
         * Agrega el titulo del boton de busqueda
         * @param title titulo
         */

        fun setButtonTitle(title: String)

    }
    interface Presenter{

        /**
         * Inicializa el presenter
         */

        fun initialize()

        /**
         * Inicializa los disposables asociados a la vista
         * @param inputSubject disposable cuando el usuario digita la palabra clave
         */

        fun initDisposables(inputSubject: PublishSubject<String>)

        /**
         * Limpia todas los objetos
         */

        fun clear()

        /**
         * Verifica si se puede seguir a la siguiente vista
         */

        fun authorizeNextFragment()
    }

}