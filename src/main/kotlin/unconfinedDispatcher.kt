import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * unconfined : 【不限定】线程，
 * 【不限定】指
 *   1. 遇到第一个挂起函数之前在当前线程中运行
 *   2. 挂起函数挂起执行后恢复，恢复后的代码在哪个线程执行由【挂起函数】决定
 *
 * 使用场景：
 *     协程不需要调度或者调度后会出错的情况，【平常极不推荐使用】
 */
fun main() {
    runBlocking {
        //刚开始继承父协程的上下文，runBlocking运行在主线程
        //delay挂起恢复后在哪个线程执行由delay函数决定。
        launch(Dispatchers.Unconfined){
            println("Unconfined       : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined       : I'm working in thread ${Thread.currentThread().name}")
        }
        launch {
            println("main runBlocking : I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking : I'm working in thread ${Thread.currentThread().name}")
        }
    }
}