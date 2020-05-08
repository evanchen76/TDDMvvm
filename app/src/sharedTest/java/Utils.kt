import java.io.ByteArrayOutputStream
import java.io.InputStream

class Utils {
    //    @Throws(Exception::class)
    companion object {
        fun readTextStream(inputStream: InputStream): String {
            val result = ByteArrayOutputStream()
            val buffer = ByteArray(1024)

            var read: Int = -1
            inputStream.use { input ->
                result.use {
                    while (input.read().also { read = it } != -1) {
                        it.write(read)
                    }
                }
            }
            return result.toString("UTF-8")
        }

        fun readStringFromResource(resourceName: String) =
            readTextStream(javaClass.classLoader!!.getResourceAsStream(resourceName))


    }
}