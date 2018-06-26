package com.filipponi.SpringBootScalaFull.resource

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
class Resource {


  /**
    *
    * I need support from the framework for realeasing the tomcat thread while waiting otherwise there is no differnce using
    * the reactive framework or not.
    *
    * @return
    */
  @RequestMapping(path = Array("/hello"),
    method = Array(RequestMethod.GET),
    produces = Array("application/json"))
  def hello(): ResponseEntity[String] = {
    ResponseEntity.ok("Hello!")
  }

}



