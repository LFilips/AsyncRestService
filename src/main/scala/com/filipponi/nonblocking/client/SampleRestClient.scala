package com.filipponi.nonblocking.client

import org.springframework.stereotype.Component
import rx.lang.scala.Observable

@Component
class SampleRestClient {

  /**
    * Simulate to get data from a rest service
    * @return the payload from the rest service
    */
  def getData(): Observable[RestServicePayload] = {

    Observable(subscriber => {
      /**
        * The sleep is to simulate the latency of a database lookup
        */
      Thread.sleep(100)
      subscriber.onNext(RestServicePayload("Value of class A"))
      subscriber.onCompleted()
    }
    )
  }

}

case class RestServicePayload(value: String)
