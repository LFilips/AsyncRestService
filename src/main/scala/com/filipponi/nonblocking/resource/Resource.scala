package com.filipponi.nonblocking.resource

import com.filipponi.nonblocking.service.{Combinator, SampleBusinessLogic}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}
import org.springframework.web.context.request.async.DeferredResult

@RestController
@Autowired
class Resource(sampleBusinessLogic: SampleBusinessLogic) {

  val Logger = LoggerFactory.getLogger(getClass.getName)

  /**
    * Using deferred result for releasing the request thread and setting it back using RxScheduler
    * @return
    */
  @RequestMapping(path = Array("/nonBlockingEndpoint"), method = Array(RequestMethod.GET), produces = Array("application/json"))
  def nonBlockingHello(): DeferredResult[ResponseEntity[Combinator]] = {

    val deferredResult = new DeferredResult[ResponseEntity[Combinator]]()

    val observable = sampleBusinessLogic.getDataFromVariousSource()

    observable.subscribe(
      combinator => deferredResult.setResult(ResponseEntity.ok(combinator)),
      throwable => deferredResult.setResult(ResponseEntity.status(500).build()),
      () => Unit)

    deferredResult
  }

}







