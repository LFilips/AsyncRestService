package com.filipponi.nonblocking.repository

import org.springframework.stereotype.Component
import rx.lang.scala.Observable

@Component
class SampleRepositoryB {

  /**
    * Simulating to getting Data from a repository that retrieves the class A
    *
    * @return
    */
  def lookUpData(): Observable[B] = {
    Observable(subscriber => {
      /**
        * The sleep is to simulate the latency of a database lookup
        */
      Thread.sleep(100)
      subscriber.onNext(B("Value of class B"))
      subscriber.onCompleted()
    }
    )
  }
}

case class B(value: String)

