package com.levio.cil.accuchek.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementContextDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementContextRawDataDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementRawDataDto;
import com.levio.cil.accuchek.mapper.GlucoseServicesBytesMapper;

@RestController
@RequestMapping(value = "/glucose")
public class GlucoseMeasurementController {

  private GlucoseServicesBytesMapper mapper;

  @Autowired
  public GlucoseMeasurementController(GlucoseServicesBytesMapper glucoseMeasurementBytesMapper) {
    this.mapper = glucoseMeasurementBytesMapper;
  }

  @PostMapping("/measurement")
  public GlucoseMeasurementDto mapGlucoseMeasurement(
      @RequestBody GlucoseMeasurementRawDataDto dataDto) {

    return mapper.mapToReadableGlucoseMeasurementDto(dataDto);
  }

  @PostMapping("/measurement-context")
  public GlucoseMeasurementContextDto mapGlucoseMeasurementContext(
      @RequestBody GlucoseMeasurementContextRawDataDto dataDto) {

    return mapper.mapToReadableGlucoseMeasurementContextDto(dataDto);
  }

}
