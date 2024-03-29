package nhk

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = ["io.github.io.github.nhk_news_web_easy"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
