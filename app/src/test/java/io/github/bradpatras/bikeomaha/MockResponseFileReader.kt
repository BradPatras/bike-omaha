package io.github.bradpatras.bikeomaha

import java.io.InputStreamReader

/*
Courtesy of https://medium.com/@denais.jeremy/testing-your-api-calls-on-android-3e21af803a92
 */
class MockResponseFileReader(path: String) {
     var content: String = ""

     init {
         val classloader = this.javaClass.classLoader
         classloader?.let {
             val reader = InputStreamReader(it.getResourceAsStream(path))
             content = reader.readText()
             reader.close()
         }

     }
}