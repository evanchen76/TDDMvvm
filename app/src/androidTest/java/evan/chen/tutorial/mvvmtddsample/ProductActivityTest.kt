package evan.chen.tutorial.mvvmtddsample

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import evan.chen.tutorial.mvvmtddsample.api.ApiConfig
import evan.chen.tutorial.mvvmtddsample.api.NetworkService
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductActivityTest {

    @get:Rule
    var activityActivityTestRule = ActivityTestRule(ProductActivity::class.java, true, false)

    @Test
    fun productViewTest() {

        val interceptor = MockInterceptor()

        interceptor.setInterceptorListener(object : MockInterceptor.MockInterceptorListener {
            override fun setAPIResponse(url: String): MockAPIResponse? {
                if (url == ApiConfig.productUrl) {
                    val mockAPIResponse = MockAPIResponse()
                    mockAPIResponse.status = 200
                    mockAPIResponse.responseString = Utils.readStringFromResource("product.json")
                    return mockAPIResponse
                }

                return null
            }
        })

        val networkService = NetworkService(interceptor)
        ProductRepository.getInstance(networkService.serviceAPI)

        val intent = Intent()

        activityActivityTestRule.launchActivity(intent)

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.productName))
            .check(ViewAssertions.matches(ViewMatchers.withText("Google Pixel 4")))
    }
}
