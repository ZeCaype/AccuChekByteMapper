package com.levio.cil.accuchek.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementRawDataDto;
import com.levio.cil.accuchek.mapper.GlucoseMeasurementBytesMapper;

@RestController
@RequestMapping(value = "/glucosefeature")
public class GlucoseMeasurementController {

  private GlucoseMeasurementBytesMapper mapper;

  @Autowired
  public GlucoseMeasurementController() {
    mapper = new GlucoseMeasurementBytesMapper();
  }

  @PostMapping
  public GlucoseMeasurementDto getGlucoseMeasurement(
      @RequestBody GlucoseMeasurementRawDataDto dataDto) {

    return mapper.mapToReadableGlucoseMeasurementDto(dataDto);
  }

}
