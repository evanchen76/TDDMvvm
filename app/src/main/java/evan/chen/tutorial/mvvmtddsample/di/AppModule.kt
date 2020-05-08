package evan.chen.tutorial.mvvmtddsample.di

import evan.chen.tutorial.mvvmtddsample.ProductRepository
import evan.chen.tutorial.mvvmtddsample.ProductViewModel
import evan.chen.tutorial.mvvmtddsample.api.BaseInterceptor
import evan.chen.tutorial.mvvmtddsample.api.NetworkService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        val networkServiceApi = NetworkService(BaseInterceptor())
        val productRepository = ProductRepository.getInstance(networkServiceApi.serviceAPI)
        ProductViewModel(productRepository)
    }
}
