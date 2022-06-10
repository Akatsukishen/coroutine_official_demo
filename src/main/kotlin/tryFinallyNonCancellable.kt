import kotlinx.coroutines.*

//如果通过取消协程进入到finally模块的话，
//在协程的内部再次调用挂起函数，挂起函数会因为协程已经取消，抛出CancellationException导致finally模块中的代码不能完全执行
// 可以使用withContext(NonCancellable)操作来保证包裹的代码不可被取消
fun main() {
    runBlocking {
        val job = launch {
            try {
                repeat(20){
                    println("job : I'm sleeping $it ...")
                    delay(500L)
                }
            }finally {
                withContext(NonCancellable){
                    println("job: I'm running finally.")
                    delay(500)
                    println("==> job: And I'v just delayed for 500 millSecond.")
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting")
        job.cancelAndJoin()
        println("main: I can quit!")
    }
}