package com.filipponi.SpringBootScalaFull

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/** This configuration will simulate to have a class with a main method **/
@SpringBootApplication
class AsyncRestService

object AsyncRestService {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[AsyncRestService])
  }
}


