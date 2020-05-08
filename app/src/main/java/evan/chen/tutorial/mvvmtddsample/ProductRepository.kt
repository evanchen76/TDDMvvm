package evan.chen.tutorial.mvvmtddsample

import evan.chen.tutorial.mvvmtddsample.api.ServiceApi

interface IProductRepository {

}

class ProductRepository(private val serviceApi: ServiceApi) : IProductRepository {

    companion object {

        private var INSTANCE: ProductRepository? = null

        @JvmStatic fun getInstance(serviceApi: ServiceApi) =
            INSTANCE ?: synchronized(ProductRepository::class.java) {
                INSTANCE ?: ProductRepository(serviceApi)
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
}

