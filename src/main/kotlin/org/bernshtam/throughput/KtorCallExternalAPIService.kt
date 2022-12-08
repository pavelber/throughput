package org.bernshtam.throughput

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.springframework.stereotype.Service
import java.net.URL

@Service
class KtorCallExternalAPIService {


    private val ktor = HttpClient() {
        install(HttpTimeout) {
            requestTimeoutMillis = 200000
            connectTimeoutMillis = 200000
        }
    }

    suspend fun callExternalRest(s: String): String {
        println("ktor client ${Thread.currentThread().name}")
        val response = ktor.get("http://httpbin.org/delay/3")
        val text = response.bodyAsText()
        return text.toCharArray()[10].toString()
    }
}
