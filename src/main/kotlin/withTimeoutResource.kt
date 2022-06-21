import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout


// withTimeout 跟里面执行的代码是异步的，可能【任何时候】会超时退出
// 下面的例子中：超时时间200ms,休眠50ms,然后初始化资源，
//             计算timeout开始执行的次数timeoutStartCount和结束的次数timeoutFinishCount，
//             如果没有异常退出的话，那么正常来说timeoutStartCount == timeoutFinishCount,资源acquired最后也为0，
//             从最后的输出日志可以看到，timeoutFinishCount远小于timeoutStartCount
//             说明很多情况下，看起来剩下150ms足够初始化的情况下，还是超时异常退出了launch
//              

var acquired = 0
var acquired2 = 0

var timeoutStartCount = 0
var timeoutFinishCount = 0

class Resource {
    init {
        acquired ++
        acquired2 ++
    }

    fun close(){
        acquired --
        acquired2 --
    }
}

fun main() {
    runBlocking {
        repeat(100000){

            launch {
                var resource : Resource? = null
                withTimeout(200){
                    timeoutStartCount ++
                    delay(50)
                    resource = Resource()
                    timeoutFinishCount++
                }
                //上面超时退出了，都没有机会执行下面的代码了
                resource?.close()
            }

        }
    }
    println(acquired)
    println(acquired2)
    println(timeoutStartCount)
    println(timeoutFinishCount)
}