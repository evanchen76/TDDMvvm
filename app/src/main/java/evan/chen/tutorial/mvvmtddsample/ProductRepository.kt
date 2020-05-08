package evan.chen.tutorial.mvvmtddsample

import evan.chen.tutorial.mvvmtddsample.api.ProductResponse
import evan.chen.tutorial.mvvmtddsample.api.ServiceApi
import io.reactivex.Single

interface IProductRepository {
    fun getProduct(): Single<ProductResponse>
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

    override fun getProduct(): Single<ProductResponse> {
        return serviceApi.getProduct()
            .map {
                it.body()
            }
    }
}

