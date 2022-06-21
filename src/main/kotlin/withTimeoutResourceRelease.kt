import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * withTimeout超时后，会因为超时退出当前的协程
 * 只要我们在协程退出前释放资源，就可以保证资源不会泄露
 * finally模块保证扫尾工作
 */
fun main() {
    runBlocking {
        repeat(10000){
            launch {
                var resource : Resource? = null
                try {
                    withTimeout(60){
                        delay(50)
                        resource= Resource()
                    }
                }finally {
                    resource?.close()
                }
            }
        }
        println(acquired)
    }
}