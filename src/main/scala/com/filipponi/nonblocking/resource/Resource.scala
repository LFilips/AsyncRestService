package com.filipponi.nonblocking.resource

import java.util.concurrent.{Callable, Executors}

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}
import org.springframework.web.context.request.async.DeferredResult

@RestController
class Resource {

  val threadPool = Executors.newFixedThreadPool(2)
  val Logger = LoggerFactory.getLogger(getClass.getName)

  /**
    *
    * I need support from the framework for releasing the tomcat thread while waiting otherwise there is no differnce using
    * the reactive framework or not.
    *
    * @return
    */
  @RequestMapping(path = Array("/nonBlockingHello"), method = Array(RequestMethod.GET), produces = Array("application/json"))
  def nonBlockingHello(): DeferredResult[String] = {

    Logger.debug("Processing the request! This should be nio tomcat thread")
    //I should start a process that will set the result in another thread
    val deferredResult = new DeferredResult[String]()

    /**
      * I can create a callable that will be processed by the executor service,
      * and this will be responsible of setting the result and give back the response.
      */
    val callable = new Callable[Unit] {
      override def call(): Unit = {
        Logger.debug("Inside the callable, this should be executor service thread pool")
        Thread.sleep(1000)
        deferredResult.setResult("""{ "result":"This is my non blocking result"}""")
      }
    }

    threadPool.submit(callable)

    deferredResult
  }

}



