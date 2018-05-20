package com.filipponi.SpringBootScalaFull.resource

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
class Resource {

  @RequestMapping(path = Array("/hello"),
    method = Array(RequestMethod.GET),
    produces = Array("application/json"))
  def getNewBoard(): ResponseEntity[String] = {
    ResponseEntity.ok("Hello!")
  }

}



