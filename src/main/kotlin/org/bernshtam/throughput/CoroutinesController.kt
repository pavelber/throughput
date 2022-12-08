package org.bernshtam.throughput

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.newFixedThreadPoolContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import kotlin.random.Random


@RestController
@RequestMapping("/api")
class CoroutinesController {

    @Autowired
    private lateinit var repo: TestRepository

    @Autowired
    private lateinit var rest: KtorCallExternalAPIService
    private val fixedThreadPoolContext = newFixedThreadPoolContext(3, "background")
    private val scope = CoroutineScope(fixedThreadPoolContext + SupervisorJob())
    private val r = Random(1000)

    @GetMapping("/coroutines")
    suspend fun getCount(): String {
        val s = (1..2)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")
        val toFind = if (r.nextBoolean())
            s + rest.callExternalRest(s)
        else s
        val find = scope.async(fixedThreadPoolContext) {
            println("coroutines async ${Thread.currentThread().name}")
            repo.find(toFind)
        }
        return find.await().toString()
    }
}

private val charPool: List<Char> = ('a'..'e') + ('0'..'9')