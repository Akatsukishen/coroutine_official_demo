import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default){
            var nextPrintTime = startTime
            var i = 0
            while (isActive){ //通过isActive查看当前协程是否被取消了，isActive是 【CoroutineScope】的扩展属性
                if(System.currentTimeMillis() >= nextPrintTime){
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: I can quit!")
    }
}