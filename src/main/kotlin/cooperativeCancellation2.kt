import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = launch(Dispatchers.Default) {
            var i = 0
            while (i < 5){
                println("job: I'm sleeping $i ...")
                //协程自带的挂起函数都会检查是否已经取消，如果取消了，会抛出CancellationException，停止程序的执行
                //但是CancellationException被认为是协程正常操作的结果，并不会被抛出从而被外层捕获
                delay(500L)
                i++
            }
        }
        delay(1300)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}