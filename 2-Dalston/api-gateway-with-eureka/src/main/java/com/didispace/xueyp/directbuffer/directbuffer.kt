package com.didispace.xueyp.directbuffer
import java.nio.ByteBuffer

class Direct {

}

fun main() {
    val buffer = ByteBuffer.allocateDirect(10*1024*1024)
    buffer.isDirect
    println(buffer.int)
}
