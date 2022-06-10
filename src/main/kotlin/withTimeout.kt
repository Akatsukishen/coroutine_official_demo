import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

// 1. withTimeout 阻塞代码执行，只有它内部代码执行完成，后面的代码才会执行
// 2. withTimeout 如果包裹的代码在指定的时间内执行完成，则后面的代码，如果未在指定时间
//                内完成的话，就会抛出TimeoutCancellationException，被uncaught exception handler捕获并打印，
//                后续的代码不再执行。
fun main() {
    runBlocking {
        withTimeout(1300){
            repeat(5){ i ->
                println("withTimeout: I'm sleeping $i ...")
                delay(500)
            }
        }
        launch {
            repeat(10){
                println("launch: I'm sleeping $it ...")
                delay(10)
            }
        }
        println("main: I can quit!")
    }
}