package com.levio.cil.accuchek.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/glucosefeature")
public class GlucoseMeasurementController {
  
  private String response = "";

  @Autowired
  public GlucoseMeasurementController() {
    this.response = "Glucose";
  }

  @GetMapping
  public String getGlucoseMeasurement() {

    return this.response;
  }

}
