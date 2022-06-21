import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * async 让后面大括号中的代码与当前点的协程【并发】执行
 * 返回Deferred,表示一个有延迟数据返回的句柄
 * 可以通过await() 来确切的拿到返回的数据
 *
 */
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = async { doSomethingOne() }
            val two = async { doSomethingTwo() }
            //注意这时候 one,two已经开始并发执行，
            // 如果 one.await()之后，在调用上面的two，那么一定要等one执行完成后，才会调用two,就不是并发执行了。
            println("The answer is ${one.await() + two.await()} ")
        }
        println("Completed in $time ms")
    }
}