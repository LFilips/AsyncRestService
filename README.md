# NonBlockingRestService

Sample study implementation for a rest services using reactive library. The idea is to create a rest service that is non-blocking, in this way we will have a better resource utilisation.

In this example I wille rxScala, that is scala wrapper around RxJava developed by netflix.

```https://github.com/ReactiveX/RxScala```

The spring boot will be use for packaging/running the application.

## Problem

The context is that multithreads application servers usually have available a fixed number of threads for serving the requests, for this reason the number of concurrent requests is limited to the number of threads that the webserver has available.

So any thread will be blocked until is released from the application that is processing the request. If for example a request need 1 second to be resolved and we have 10 threads in our server if 11 user will arrive concurrently, 1 user will need to wait until a thread is released plus the time of the request.

Another key fact while reasoning about the thread utilization is that the application server threads used by the application ofter are blocked waiting for some sort of IO (maybe calling a rest or soap service), and in that moment it will be effectively be idle and unused.

## Solution

Using non blocking IO and reactive programming is possible to have the maximum utilization possible from the thread.

For doing this we will need to have an application that will release any threads as soon it is idle, and a web frawork which allow to release the request threads. For this case study i'll use rxScala and Spring Boot 

RxJava is an event base reactive library for scala which allows non blocking IO and can be used inside the application for avoid to be blocked during IO event. Spring boot is used to map a method to a url path, to which we can specify a response type called deferredResults, that can be set and processed in a non blocking and asynchronously mannaer, release the webserver thread for serving other requests.
 
