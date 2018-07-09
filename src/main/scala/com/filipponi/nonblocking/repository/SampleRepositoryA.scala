package com.filipponi.nonblocking.repository

import org.springframework.stereotype.Component
import rx.lang.scala.Observable

@Component
class SampleRepositoryA {

  /**
    * Simulating to getting Data from a repository that retrieves the class A
    *
    * @return
    */
  def lookUpData(): Observable[A] = {
    Observable(subscriber => {
      /**
        * The sleep is to simulate the latency of a database lookup
        */
      Thread.sleep(100)
      subscriber.onNext(A("Value of class A"))
      subscriber.onCompleted()
    }
    )
  }

}

case class A(value: String)

