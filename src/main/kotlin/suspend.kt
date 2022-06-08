import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    launch { doWorld() }
//    doWorld() //直接调用，就是一个普通的函数，并不会异步执行。
    println("Hello")
}


// suspend 可挂起函数,只能用在【协程作用域】或者【另一个挂起函数】中，因为只有在这些情况下，才可以提供挂起和恢复的条件。
// 可挂起函数：类似于可挂起的任务，不是所有任务都是可挂起的,只有确实耗时很久的任务才会被真正挂起
private suspend fun doWorld(){
    delay(1000L) //delay 也是 可挂起函数，要在此处使用，必须doWorld也是可挂起函数
    println("World!")
}

