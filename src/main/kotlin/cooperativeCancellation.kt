import kotlinx.coroutines.*

/**
 * 协程的取消： 协作式的
 * 调用协程的cancel()方法，是表示建议协程取消
 * 至于协程是否取消，那看协程的实现。如果不接受取消的建议，那么继续执行。
 *
 * 协程自身的所有挂起函数（kotlinx.coroutines）都是可取消的。
 * 下面的代码协程并没有调用任何内部的挂起函数，也没有查看协程是否被取消了， 所以并没有
 * 响应协程的取消。
 *
 *
 */
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {//这里要切换搞个线程，否则一直阻塞下面代码的执行，可以一直打印完成
        var nextPrintTime = startTime
        var i = 0
        while (i < 10) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}