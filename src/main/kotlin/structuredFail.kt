import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 结构性并发: 一损俱损
 * 如下demo, 在第二个协程异常后,第一个线程也【一损俱损】异常，最后父协程收到异常信息，也异常退出了。
 */
fun main() {
    runBlocking {
        try {
            failedConcurrentSum()
        }catch (e : ArithmeticException){
            println("Computation failed with ArithmeticException")
        }
    }
}

suspend fun failedConcurrentSum() : Int = coroutineScope {

    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE)
            42
        } finally {
            println("First child was cancelled")
        }
    }

    val two = async<Int> {
        println("Second Child throws an exception")
        throw java.lang.ArithmeticException()
    }

    one.await() + two.await()
}