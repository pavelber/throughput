package org.bernshtam.throughput

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlin.random.Random


@RestController
@RequestMapping("/api")
class BlockingController {

    @Autowired
    private lateinit var repo: TestRepository

    @GetMapping("/block")
    fun getCount():String {
        val s = (1..2)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")
        return repo.find(s).toString()
    }
}

private val charPool : List<Char> = ('a'..'e') +  ('0'..'9')