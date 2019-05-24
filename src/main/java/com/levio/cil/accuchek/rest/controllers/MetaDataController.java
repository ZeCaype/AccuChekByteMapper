package com.levio.cil.accuchek.rest.controllers;

import com.levio.cil.accuchek.dtos.ModelNumberStringDto;
import com.levio.cil.accuchek.dtos.ModelNumberStringRawDto;
import com.levio.cil.accuchek.dtos.SystemIdDto;
import com.levio.cil.accuchek.dtos.SystemIdRawDto;
import com.levio.cil.accuchek.mapper.MetaDataBytesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/meta")
public class MetaDataController {

  private MetaDataBytesMapper mapper;

  @Autowired
  public MetaDataController(MetaDataBytesMapper metaDataBytesMapper) {
    this.mapper = metaDataBytesMapper;
  }

  @PostMapping("/systemId")
  public SystemIdDto mapSystemId(
      @RequestBody SystemIdRawDto dataDto) {
    return mapper.mapToReadableSystemIdDto(dataDto);
  }

  @PostMapping("/modelNumberString")
  public ModelNumberStringDto mapModelNumberString(
      @RequestBody ModelNumberStringRawDto dataDto) {
    return mapper.mapToReadableModelNumberString(dataDto);
  }
}
