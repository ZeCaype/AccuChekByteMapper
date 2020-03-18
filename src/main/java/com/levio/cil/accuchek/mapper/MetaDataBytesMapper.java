package com.levio.cil.accuchek.mapper;

import com.levio.cil.accuchek.dtos.ModelNumberStringDto;
import com.levio.cil.accuchek.dtos.ModelNumberStringRawDto;
import com.levio.cil.accuchek.dtos.SystemIdDto;
import com.levio.cil.accuchek.dtos.SystemIdRawDto;
import org.springframework.stereotype.Component;

@Component
public class MetaDataBytesMapper {

  public MetaDataBytesMapper() {
  }

  public SystemIdDto mapToReadableSystemIdDto(SystemIdRawDto rawData){
    SystemIdDto systemIdDto = new SystemIdDto();
    setManufacturerIdentifier(rawData, systemIdDto);
    setOrganizationallyUniqueIdentifier(rawData, systemIdDto);
    return systemIdDto;
  }

  public ModelNumberStringDto mapToReadableModelNumberString(ModelNumberStringRawDto rawData) {
    ModelNumberStringDto modelNumberStringDto = new ModelNumberStringDto();
    setModelNumber(rawData, modelNumberStringDto);
    return modelNumberStringDto;
  }

  private void setModelNumber(ModelNumberStringRawDto rawData, ModelNumberStringDto modelNumberStringDto) {
    byte[] bytes = int2byte(rawData.getData());
    String result = new String(bytes);
    modelNumberStringDto.setModelNumber(result);
  }

  private void setOrganizationallyUniqueIdentifier(SystemIdRawDto rawData, SystemIdDto systemIdDto) {
    StringBuilder rawSystemIdByte = new StringBuilder();

    for (int i = 5; i <= 7; i++){
      rawSystemIdByte.append(
          new StringBuilder(getBitArrayFromSpecificByte(rawData, i))
              .reverse()
              .toString());
    }
    systemIdDto.setOrganizationallyUniqueIdentifier(Integer.parseInt(rawSystemIdByte.toString(), 2));
  }

  private void setManufacturerIdentifier(SystemIdRawDto rawData, SystemIdDto systemIdDto) {
    StringBuilder rawSystemIdByte = new StringBuilder();

    for (int i = 0; i <= 4; i++){
      rawSystemIdByte.append(
          new StringBuilder(getBitArrayFromSpecificByte(rawData, i))
              .reverse()
              .toString());
    }
    systemIdDto.setManufacturerIdentifier(Long.parseLong(rawSystemIdByte.toString(),2));
  }

  private String getBitArrayFromSpecificByte(SystemIdRawDto rawData, int i) {
    String bitArray = String.format("%8s", Integer.toBinaryString(rawData.getData()[i])).replace(' ', '0');

    if (bitArray.length() != 8) {
      bitArray = bitArray.substring(24);
    }
    return bitArray;
  }

  public byte[] int2byte(int[] input) {
    int inputLength = input.length;
    byte[] output = new byte[inputLength];

    for (int i=0; i<inputLength; i++) {
      int x = input[i];
      output[i] = (byte) x;
    }
    return output;
  }
}
