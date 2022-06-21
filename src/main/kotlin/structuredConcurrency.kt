import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 结构化并发 : 有【关联】的并发，多个并发的任务并不是独立的，它们之间存在关联，它们自己的运行状况会影响其他的任务
 * 一个任务失败，那么协程作用域失败，那么这个协程作用域下的所有协程都失败
 * 可以说：同一个协程作用域下的协程间不一定一荣俱荣，但肯定是一损俱损的。
 *
 */
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            println("The answer is ${concurrentSum()}")
        }
        println("Completed in $time ms")
    }
}

// async 是定义在【协程作用域】上的 【协程构建器】
// 所以需要在协程作用域上调用
suspend fun concurrentSum() : Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingTwo() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne() : Int {
    delay(1000)
    return 13
}

suspend fun doSomethingUsefulTwo() : Int {
    delay(1000)
    return 49
}