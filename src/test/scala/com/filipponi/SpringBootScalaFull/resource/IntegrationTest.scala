package com.filipponi.SpringBootScalaFull.resource

import com.filipponi.SpringBootScalaFull.AsyncRestService
import io.restassured.RestAssured.given
import org.apache.http.HttpStatus
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext


class IntegrationTest extends FlatSpec with BeforeAndAfterAll with Matchers {

  var context: Option[ConfigurableApplicationContext] = None

  /**
    * Creating the spring context using the static SpringApplication.run
    */
  override protected def beforeAll(): Unit = {
    super.beforeAll()
    context = Some(SpringApplication.run(classOf[AsyncRestService]))

  }

  /**
    * Creating the spring context using the static SpringApplication.run
    */
  override protected def afterAll(): Unit = {
    context.foreach {
      ctx =>
        SpringApplication.exit(ctx)
    }
    super.afterAll()
  }


  "/echo" should "respond with a 200 ok" in {

    /** This is using rest assured, but can be changed, just need a http client **/
      given
        .when
        .get(s"http://localhost:8080/hello")
        .`then`
        .statusCode(HttpStatus.SC_OK)
    }

}
