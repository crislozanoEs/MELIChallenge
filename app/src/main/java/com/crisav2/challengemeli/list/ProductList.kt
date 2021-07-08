package com.crisav2.challengemeli.list

import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.core.data.Product
import com.crisav2.core.usecase.Transformation

/**
 * MVP core interfaces.
 */

interface ProductList {

    interface View{

        /**
         * Muestra un error en la pantalla. Elimina todas las demas vistas.
         * @param error Error a mostrar en la pantalla
         */

        fun showError(error: String)

        /**
         * Muestra u oculta progress bar.
         * @param isShowing Mostrar o no mostrar "cargando"
         */

        fun showLoading(isShowing: Boolean)

        /**
         * Añade el titulo a la pantalla.
         * @param keyword Palabra de busqueda del usuario
         */

        fun setTitle(keyword: String)

        /**
         * Muestra la lista de productos.
         * @param productList Lista de productos a mostrar
         */

        fun showList(productList: List<Product>)

        /**
         * Navega a la proxima pantalla.
         * @param id Id del producto seleccionado por el usuario
         */

        fun goToProductDetail(id: String)
    }

    interface Presenter{

        val messageManager: IMessageManager

        val transformation: Transformation

        /**
         * Inicializa el presenter.
         * @param keyword palabra de busqueda del usuario
         */

        fun initialize(keyword: String?)

        /**
         * Destruye el presenter y todos los objectos necesarios
         */

        fun destroy()

        /**
         * Valida el id del producto seleccionado por el usuario
         * y navega a la siguiente pantalla
         */

        fun validateIdAndNavigate(id: String)
    }
}