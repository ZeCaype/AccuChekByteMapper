package com.levio.cil.accuchek.mapper;

import org.apache.tomcat.util.buf.StringUtils;
import com.levio.cil.accuchek.dtos.FlagsDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementRawDataDto;

@SuppressWarnings("all")
public class GlucoseMeasurementBytesMapper {
  
  public GlucoseMeasurementBytesMapper() {

  }
  
  public GlucoseMeasurementDto mapToReadableGlucoseMeasurementDto(GlucoseMeasurementRawDataDto rawData) {
    GlucoseMeasurementDto glucoseMeasurementDto = new GlucoseMeasurementDto();
    FlagsDto flags = new FlagsDto();
    
    setFlagsFromRawData(rawData, flags);
    setSequenceNumberFromRawData(rawData, glucoseMeasurementDto);
    setYearFromRawData(rawData, glucoseMeasurementDto);
    setMonthFromRawData(rawData, glucoseMeasurementDto);
    setDayFromRawData(rawData, glucoseMeasurementDto);
    setHourFromRawData(rawData, glucoseMeasurementDto);
    setMinutesFromRawData(rawData, glucoseMeasurementDto);
    setSecondsFromRawData(rawData, glucoseMeasurementDto);
    
    glucoseMeasurementDto.setDate();
    glucoseMeasurementDto.setFlags(flags);
    return glucoseMeasurementDto;
  }
  
  private void setSecondsFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 9);
    int seconds = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setSecond(seconds);
  }
  
  private void setMinutesFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 8);
    int minutes = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setMinute(minutes);
  }
  
  private void setHourFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 7);
    int hour = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setHour(hour);
  }
  
  private void setDayFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 6);
    int day = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setDay(day);
  }
  
  private void setMonthFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 5);
    int month = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setMonth(month);
  }

  private void setYearFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 4) + getBitArrayFromSpecificByte(rawData, 3);
    int year = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setYear(year);
  }

  private void setSequenceNumberFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 2) + getBitArrayFromSpecificByte(rawData, 1);
    glucoseMeasurementDto.setSequenceNumber(Integer.parseInt(rawSequenceNumberBits, 2));
  }

  private void setFlagsFromRawData(GlucoseMeasurementRawDataDto rawData, FlagsDto flags) {
    String rawFlagsBits = getBitArrayFromSpecificByte(rawData, 0);
    rawFlagsBits = new StringBuilder(rawFlagsBits).reverse().toString();
    int bitCount = 0;
    
    for (char bit: rawFlagsBits.toCharArray()) {
      switch(bitCount) {
        case 0:
          flags.setTimeOffsetPresent(getBooleanFromBitValue(bit));
          break;
        case 1:
          flags.setGlucoseConcentrationTypeSamplePresent(getBooleanFromBitValue(bit));
          break;
        case 2:
          flags.setGlucoseConcentrationUnit(getBooleanFromBitValue(bit));
          break;
        case 3:
          flags.setSensorStatusAnnunciationPresent(getBooleanFromBitValue(bit));
          break;
        case 4:
          flags.setContextInformationsFollows(getBooleanFromBitValue(bit));
          break;
          
        default:
          //Cases 5-6-7 are bits reserved for future uses.
          break;
      }
      bitCount++;
    }
  }
  
  private boolean getBooleanFromBitValue(char bit) {
    if (bit == '0') {
      return false;
    } else if (bit == '1') {
      return true;
    }
    return (Boolean) null;
  }
  
  private String getBitArrayFromSpecificByte(GlucoseMeasurementRawDataDto rawData, int i) {
    String bitArray = String.format("%8s", Integer.toBinaryString(rawData.getData()[i])).replace(' ', '0');
    
    if (bitArray.length() != 8) {
      bitArray = bitArray.substring(24);
    }
    return bitArray;
  }
  
}
