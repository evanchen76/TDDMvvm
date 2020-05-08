package evan.chen.tutorial.mvvmtddsample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import evan.chen.tutorial.mvvmtddsample.api.ProductResponse
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProductViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var stubRepository: IProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getProduct() {
        val product = ProductResponse()
        product.id = "pixel3"
        product.name = "Google Pixel3"
        product.price = 27000
        product.desc = "5.5吋全螢幕"

        `when`(stubRepository.getProduct()).thenReturn(Single.just(product))
        val viewModel = ProductViewModel(stubRepository)
        viewModel.getProduct(product.id)

        Assert.assertEquals(product.name, viewModel.productName.value)
        Assert.assertEquals(product.desc, viewModel.productDesc.value)
        Assert.assertEquals(product.price, viewModel.productPrice.value)
    }

}