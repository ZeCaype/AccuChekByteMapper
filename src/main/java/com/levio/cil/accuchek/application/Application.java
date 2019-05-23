package com.levio.cil.accuchek.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.levio.cil.accuchek.rest.controllers.GlucoseMeasurementController;

@ComponentScan(basePackageClasses = GlucoseMeasurementController.class)
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
