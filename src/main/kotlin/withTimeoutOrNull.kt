import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

// withTimeout 可以将最后一个表达式的值作为操作结果进行返回
//             但是如果未在指定时间内执行完成，会抛出异常，后续的代码不会被执行
// withTimeoutNull 在超时的情况下，不抛出异常，返回Null值
fun main() {
    runBlocking {

        val result = withTimeout(1300){
            repeat(2){i->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done"
        }
        println("result = $result")

        val result2 = withTimeoutOrNull(1300){
            repeat(1000){i->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done"
        }

        println("result2 = $result2")

        val result3  = withTimeout(1300){
            repeat(1000){i->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done"
        }
        println("result = $result3")

    }
}