package evan.chen.tutorial.mvvmtddsample

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.mvvmtddsample.databinding.ActivityProductBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProductActivity : AppCompatActivity() {

    private val productId = "pixel3"
    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val dataBinding = DataBindingUtil.setContentView<ActivityProductBinding>(this, R.layout.activity_product)

        dataBinding.productViewModel = productViewModel
        dataBinding.lifecycleOwner = this

        productViewModel.getProduct(productId)
    }
}
