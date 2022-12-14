package org.bernshtam.throughput

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random


@RestController
@RequestMapping("/api")
class BlockingController {

    @Autowired
    private lateinit var repo: TestRepository

    @Autowired
    private lateinit var rest: CallExternalAPIService

    private val r = Random(1000)

    @GetMapping("/block")
    fun getCount(): String {
        val s = (1..2)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")
        val toFind = if (r.nextBoolean())
            s + rest.callExternalRest(s)
        else s
        return repo.find(toFind).toString()
    }
}

private val charPool: List<Char> = ('a'..'e') + ('0'..'9')