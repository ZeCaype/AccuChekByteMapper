package com.levio.cil.accuchek.mapper;

import org.springframework.stereotype.Component;
import com.levio.cil.accuchek.dtos.SystemIdDto;
import com.levio.cil.accuchek.dtos.SystemIdRawDto;

@Component
public class MetaDataBytesMapper {

  public MetaDataBytesMapper() {}


  public SystemIdDto mapToReadableSystemIdDto(SystemIdRawDto rawData) {
    SystemIdDto systemIdDto = new SystemIdDto();
    setManufacturerIdentifier(rawData, systemIdDto);
    setOrganizationallyUniqueIdentifier(rawData, systemIdDto);

    return systemIdDto;
  }

  private void setOrganizationallyUniqueIdentifier(SystemIdRawDto rawData,
      SystemIdDto systemIdDto) {
    StringBuilder rawSystemIdByte = new StringBuilder();

    for (int i = 5; i <= 7; i++) {
      rawSystemIdByte
          .append(new StringBuilder(getBitArrayFromSpecificByte(rawData, i)).reverse().toString());
    }
    systemIdDto
        .setOrganizationallyUniqueIdentifier(Integer.parseInt(rawSystemIdByte.toString(), 2));
  }

  private void setManufacturerIdentifier(SystemIdRawDto rawData, SystemIdDto systemIdDto) {
    StringBuilder rawSystemIdByte = new StringBuilder();

    for (int i = 0; i <= 4; i++) {
      rawSystemIdByte
          .append(new StringBuilder(getBitArrayFromSpecificByte(rawData, i)).reverse().toString());
    }
    systemIdDto.setManufacturerIdentifier(Long.parseLong(rawSystemIdByte.toString(), 2));
  }

  private String getBitArrayFromSpecificByte(SystemIdRawDto rawData, int i) {
    String bitArray =
        String.format("%8s", Integer.toBinaryString(rawData.getData()[i])).replace(' ', '0');

    if (bitArray.length() != 8) {
      bitArray = bitArray.substring(24);
    }

    return bitArray;
  }
}
