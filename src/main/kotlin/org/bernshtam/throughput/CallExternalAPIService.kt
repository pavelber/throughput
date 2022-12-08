package org.bernshtam.throughput

import org.springframework.stereotype.Service
import java.net.URL

@Service
class CallExternalAPIService {

    fun callExternalRest(s: String): String {
        println("callExternalRest ${Thread.currentThread().name}")
        val url = URL("http://httpbin.org/delay/3")
        val connection = url.openConnection()
        connection.connectTimeout = 20000
        connection.readTimeout = 20000
        val text = String(connection.getInputStream().readAllBytes())
        return text.toCharArray()[10].toString()
    }
}