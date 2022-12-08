package org.bernshtam.throughput

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "test")
data class Test(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val name: String
)

@Repository
interface TestRepository: JpaRepository<Test,Long> {
     @Query(nativeQuery = true,
         value = "select count(*) from test where name like  CONCAT('%',:s,'%')")
    fun find(@Param("s") s:String):Int
}
