import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

/**
 * 所有的协程都运行在协程上下文中
 * 协程上下文是多个元素的集合，每个元素限定协程的一些特性。
 *
 * 协程分发器：Dispatcher 限定协程运行在某个线程或者某个线程池中。
 *
 */
fun main() {
    runBlocking {
        // launch和async 没有参数时，它继承父协程或者作用域的上下文
        launch {
            println("main runBlocking       : I'm working in thread ${Thread.currentThread().name}")
        }
        //不限定协程
        launch(Dispatchers.Unconfined) {
            println("Unconfined             : I'm working in thread ${Thread.currentThread().name}")
        }
        //【显示】地指定协程运行在哪个线程池
        launch(Dispatchers.Default) {
            println("Default                : I'm working in thread ${Thread.currentThread().name}")
        }
        //单独的线程
        launch(newSingleThreadContext("MyOwnThread")) {
            println("newSingleThreadContext : I'm working in thread ${Thread.currentThread().name}")
        }
    }
}