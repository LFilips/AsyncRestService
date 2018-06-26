package com.filipponi.nonblocking

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/** This configuration will simulate to have a class with a main method **/
@SpringBootApplication
class NonBlockingRestService

object NonBlockingRestService {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[NonBlockingRestService])
  }
}


