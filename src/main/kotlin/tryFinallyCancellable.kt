import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//如果通过取消协程进入到finally模块的话，
//在协程的内部再次调用挂起函数，挂起函数会因为协程已经取消，抛出CancellationException导致finally模块中的代码不能完全执行
fun main() {
    runBlocking {

        val job = launch {
            try {
                repeat(2){
                    println("job : I'm sleeping $it ...")
                    delay(500L)
                }
            }finally {
                println("job: I'm running finally.")
                delay(500)  //再次调用挂起函数，下面不会打印
                println("==> job: And I'v just delayed for 500 millSecond.")
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting")
        job.cancelAndJoin()
        println("main: I can quit!")
    }
}