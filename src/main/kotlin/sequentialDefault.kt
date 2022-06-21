import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 挂起函数 并不是 【并发】的意思
 * 只表示 遇到挂起函数，当前【协程】可以挂起，让出资源让其他协程去执行，等挂起完成后，当前【协程】恢复继续往下执行
 * 挂起函数就是个 【可挂起当前协程又可以恢复当前协程的】 函数
 * 它并不能让当前协程并发
 */
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingOne()   // 当前协程挂起一下，让其他协程去执行，等待挂起完成再恢复执行
            val two = doSomethingTwo()   // 当前协程又挂起一下，又让其他协程去执行
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }

}

suspend fun doSomethingOne() : Int {
    delay(1000)
    return 13
}

suspend fun doSomethingTwo() : Int {
    delay(1000)
    return 49
}