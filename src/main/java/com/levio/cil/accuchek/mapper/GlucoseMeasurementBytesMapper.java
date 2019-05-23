package com.levio.cil.accuchek.mapper;

import com.levio.cil.accuchek.dtos.FlagsDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementRawDataDto;

@SuppressWarnings("all")
public class GlucoseMeasurementBytesMapper {
  
  public GlucoseMeasurementBytesMapper() {

  }
  
  public GlucoseMeasurementDto mapToReadableGlucoseMeasurementDto(GlucoseMeasurementRawDataDto rawData) {
    GlucoseMeasurementDto glucose = new GlucoseMeasurementDto();
    FlagsDto flags = new FlagsDto();
    
    setFlagsFromRawData(rawData, flags);
    
    glucose.setFlags(flags);
    return glucose;
  }

  private void setFlagsFromRawData(GlucoseMeasurementRawDataDto rawData, FlagsDto flags) {
    String rawFlagsBits = String.format("%8s", Integer.toBinaryString(rawData.getData()[0])).replace(' ', '0');
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
  
}
