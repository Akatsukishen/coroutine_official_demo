import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 1. launch 返回一个句柄 job
// 2. 通过job可以取消掉协程
// 3. cancel完成之后，并不会立即响应，得等待取消完成
// 4. join插入并等待协程结束
// 5. cancel() join() 可以合并为 cancelAndJoin
// 6. 协程的cancel并未抛出任何异常
fun main() = runBlocking{

    try {
        val job = launch {
            repeat(1000){
                println("I'm sleeping $it")
                delay(500L)
            }
        }
        delay(1300)
        println("main: I'm tired of waiting!")
        //开始取消
        job.cancel()
        //等待取消完成
        job.join()
//    job.cancelAndJoin()
        println("main: I can quit!")
    }catch (e : Exception){
        e.printStackTrace()
    }

}