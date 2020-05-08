package evan.chen.tutorial.mvvmtddsample

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ProductViewModel(private val productRepository: IProductRepository) :
    ViewModel() {

    var productId: MutableLiveData<String> = MutableLiveData()
    var productName: MutableLiveData<String> = MutableLiveData()
    var productDesc: MutableLiveData<String> = MutableLiveData()
    var productPrice: MutableLiveData<Int> = MutableLiveData()
    var productItems: MutableLiveData<String> = MutableLiveData()

    init {
        productItems.value = ""
    }

    fun getProduct(id: String) {
        this.productId.value = id

        //由於API是寫死的，不是真的可以傳入id，所以這裡不傳入id到getProduct()
        productRepository.getProduct()
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.mainThread())
            .subscribe({ data ->
                productId.value = data.id
                productName.value = data.name
                productDesc.value = data.desc
                productPrice.value = data.price
            },
                { throwable ->
                    println(throwable)
                })
    }
}