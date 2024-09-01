package com.zeph7.test_utils.util

class TestJsonReader {

    fun readJsonFile(filename: String): String {
        val classLoader = javaClass.classLoader
        return classLoader?.getResource(filename)?.readText() ?: ""
    }
}