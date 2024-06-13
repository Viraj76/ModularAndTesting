package com.appsv.core.utils


class Logger(
    private val tag: String,
    private val isDebug: Boolean = true,
) {
    fun log(msg: String){
        if (!isDebug) {
            // production logging - Crashlytics or whatever you want to use
        }
        else{
            printLogD(tag, msg)
        }
    }


    /*
    There are times we need to use a companion object to define class
    members that are going to be used independently of any instance of
    that class. The Kotlin compiler guarantees
    we will have one and only one instance of a companion object
    */

    companion object Factory{
        fun buildDebug(className: String,): Logger{
            return Logger(
                tag = className,
                isDebug = true,
            )
        }
        fun buildRelease(className: String,): Logger{
            return Logger(
                tag = className,
                isDebug = false,
            )
        }
    }



}

fun printLogD(tag: String?, message: String ) {
    println("$tag: $message")
}