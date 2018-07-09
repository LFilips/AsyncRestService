package com.filipponi.nonblocking.service

import com.filipponi.nonblocking.client.{RestServicePayload, SampleRestClient}
import com.filipponi.nonblocking.repository.{A, B, SampleRepositoryA, SampleRepositoryB}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import rx.lang.scala.Observable
import rx.lang.scala.schedulers.IOScheduler

@Component
@Autowired
class SampleBusinessLogic(sampleRepositoryA: SampleRepositoryA, sampleRepositoryB: SampleRepositoryB, sampleRestClient: SampleRestClient) {

  /**
    * Example of business logic that is getting data from different type of datasource.
    */
  def getDataFromVariousSource(): Observable[Combinator] = {

    Observable(subscriber => {

      val observableA = sampleRepositoryA.lookUpData().subscribeOn(IOScheduler())

      val observableB = sampleRepositoryB.lookUpData().subscribeOn(IOScheduler())

      val observerRestPayload = sampleRestClient.getData().subscribeOn(IOScheduler())


      val observables = Iterable(observableA, observableB, observerRestPayload)

      /**
        * Example of combining observables
        */
      Observable.combineLatest(observables)(iterable => {
        val list = iterable.toList
        subscriber.onNext(Combinator(list(0).asInstanceOf[A], list(1).asInstanceOf[B], list(2).asInstanceOf[RestServicePayload]))
        subscriber.onCompleted()
      })

    })


  }
}

  case class Combinator(a: A, b: B, restServicePayload: RestServicePayload)
