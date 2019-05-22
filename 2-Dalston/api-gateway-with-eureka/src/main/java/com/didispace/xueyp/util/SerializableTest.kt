package com.didispace.xueyp.util

import org.apache.tomcat.util.http.fileupload.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.time.ZonedDateTime

data class User(
    val signinTime: ZonedDateTime,
    val name: String,
    val age: Int,
    val email: String,
    val book: Book,
    val status: UserStatus
): Serializable {
    /**
     * 有writeObject方法的话底层会通过反射找到这个方法然后用这个方法进行序列化
     */
//    private fun writeObject(s: ObjectOutputStream) {
//        println("My writeObject")
//        s.writeObject(this.signinTime)
//        s.writeObject(this.name)
//        s.writeObject(this.age)
//        s.writeObject(this.email)
//        s.writeObject(this.book)
//        s.writeObject(this.status)
//    }
}
enum class UserStatus(val value: String): Serializable {
    VIP("vip"),
    NORMAL("normal"),
}

data class Book(
    val title: String
): Serializable

fun main() {
    val book = Book("song of ice&fire")
    val user = User(
        ZonedDateTime.now(),
        "xueyp",
        23,
        "xue@aa.com",
        book,
        UserStatus.NORMAL)
    val file = File("tempFile")
    val oos = ObjectOutputStream(FileOutputStream(file))
    try {
        oos.writeObject(user)
    } catch (e: IOException) {
        println("IOException")
    } finally {
        IOUtils.closeQuietly(oos)
    }
    val ois = ObjectInputStream(FileInputStream(file))
    try {
        val user = ois.readObject() as User
        println(user)
    } catch (e: IOException) {
        println("IOException")
    } finally {
        IOUtils.closeQuietly(ois)
    }

}

