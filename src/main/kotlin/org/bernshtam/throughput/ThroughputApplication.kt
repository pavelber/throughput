package org.bernshtam.throughput

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ThroughputApplication

fun main(args: Array<String>) {
	runApplication<ThroughputApplication>(*args)
}
