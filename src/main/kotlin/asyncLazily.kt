import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * async启动的时候，可以设置是否是 【懒启动】
 * 默认的async定义的时候，立即开始执行，任务的把控不在上层调用者的手中
 *
 * 而指定start为LAZY的话，只是定义了一个并发执行的协程，但是该协程并没有并发执行
 * 只是返回一个句柄给你，你想啥时候并发执行就啥啥时候调用start执行
 *
 * 如果没有调用start的话,就调用await()方法的话，它是不会并发执行的，类似于thread.run()
 *
 * 类似于定义了一个线程，线程的执行需要调用 t.start()
 *
 */
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) {
                doSomethingOne()
            }
            val two = async(start = CoroutineStart.LAZY){
                doSomethingTwo()
            }

            one.start()
            two.start()

            println("The answer is ${one.await() + two.await()}")
        }

        println("Completed in $time ms")
    }
}