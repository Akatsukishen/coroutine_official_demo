import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = launch(Dispatchers.Default) {
            try {
                var i = 0
                while (i < 5){
                    println("job: I'm sleeping $i ...")
                    //协程自带的挂起函数都会检查是否已经取消，如果取消了，会抛出CancellationException，停止程序的执行
                    //但是CancellationException被认为是协程正常操作的结果,
                    // 可以加上tryCatch捕获，不捕获的话，外层的uncaught exception handler的捕获并不会进行日志输出 -> CancellationException

                    delay(500L)
                    i++
                }
            }catch (e : Exception){
                println("job: exception occurs.")
            }
        }
        delay(1300)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}