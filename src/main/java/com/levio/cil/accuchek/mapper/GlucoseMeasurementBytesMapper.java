package com.levio.cil.accuchek.mapper;

import com.levio.cil.accuchek.dtos.SensorStatusAnnunciationDto;
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
    SensorStatusAnnunciationDto sensorStatusAnnunciationDto = new SensorStatusAnnunciationDto();

    setFlagsFromRawData(rawData, flags);
    setSequenceNumberFromRawData(rawData, glucoseMeasurementDto);
    setYearFromRawData(rawData, glucoseMeasurementDto);
    setMonthFromRawData(rawData, glucoseMeasurementDto);
    setDayFromRawData(rawData, glucoseMeasurementDto);
    setHourFromRawData(rawData, glucoseMeasurementDto);
    setMinutesFromRawData(rawData, glucoseMeasurementDto);
    setSecondsFromRawData(rawData, glucoseMeasurementDto);
    setSensorStatusAnnunciation(rawData, sensorStatusAnnunciationDto);
    setSampleLocation(rawData, glucoseMeasurementDto);
    setTimeOffsetFromRawData(rawData, glucoseMeasurementDto);
    setGlucoseConcentrationValueFromRawData(rawData, glucoseMeasurementDto);
    
    glucoseMeasurementDto.setSensorStatusAnnunciationDto(sensorStatusAnnunciationDto);
    glucoseMeasurementDto.setDate();
    flags.setGlucoseConcentrationUnit();
    glucoseMeasurementDto.setFlags(flags);
    
    return glucoseMeasurementDto;
  }

  private void setSampleLocation(GlucoseMeasurementRawDataDto rawData, GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSampleLocationBits = getBitArrayFromSpecificByte(rawData, 14).substring(0,4);
    int value = Integer.parseInt(rawSampleLocationBits, 2);

    switch (value){
      case 0:
        glucoseMeasurementDto.setSampleLocation("Reserved for future use");
        break;
      case 1:
        glucoseMeasurementDto.setSampleLocation("Finger");
        break;
      case 2:
        glucoseMeasurementDto.setSampleLocation("Alternate Site Test (AST)");
        break;
      case 3:
        glucoseMeasurementDto.setSampleLocation("Earlobe");
        break;
      case 4:
        glucoseMeasurementDto.setSampleLocation("Control solution");
        break;
      case 15:
        glucoseMeasurementDto.setSampleLocation("Sample Location value not available");
        break;
      default:
        break;
    }
  }

  private void setSensorStatusAnnunciation(GlucoseMeasurementRawDataDto rawData, SensorStatusAnnunciationDto sensorStatusAnnunciationDto) {
    String rawSensorStatusAnnunciationByte1 = getBitArrayFromSpecificByte(rawData, 15);
    String rawSensorStatusAnnunciationByte2 = getBitArrayFromSpecificByte(rawData, 16);
    rawSensorStatusAnnunciationByte1 = new StringBuilder(rawSensorStatusAnnunciationByte1).reverse().toString();
    rawSensorStatusAnnunciationByte2 = new StringBuilder(rawSensorStatusAnnunciationByte2).reverse().toString();
    int bitCount = 0;

    for (char bit: rawSensorStatusAnnunciationByte1.toCharArray()) {
      switch(bitCount) {
        case 0:
          sensorStatusAnnunciationDto.setDeviceBatteryLowAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        case 1:
          sensorStatusAnnunciationDto.setSensorMalfunctionOrFaultingAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        case 2:
          sensorStatusAnnunciationDto.setSampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        case 3:
          sensorStatusAnnunciationDto.setStripInsertionError(getBooleanFromBitValue(bit));
          break;
        case 4:
          sensorStatusAnnunciationDto.setStripTypeIncorrectForDevice(getBooleanFromBitValue(bit));
          break;
        case 5:
          sensorStatusAnnunciationDto.setSensorResultHigherThanTheDeviceCanProcess(getBooleanFromBitValue(bit));
          break;
        case 6:
          sensorStatusAnnunciationDto.setSensorResultLowerThanTheDeviceCanProcess(getBooleanFromBitValue(bit));
          break;
        case 7:
          sensorStatusAnnunciationDto.setSensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        default:
          break;
      }
      bitCount++;
    }

    bitCount = 0;

    for (char bit: rawSensorStatusAnnunciationByte2.toCharArray()) {
      switch(bitCount) {
        case 0:
          sensorStatusAnnunciationDto.setSensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        case 1:
          sensorStatusAnnunciationDto.setSensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        case 2:
          sensorStatusAnnunciationDto.setGeneralDeviceFaultHasOccurredInTheSensor(getBooleanFromBitValue(bit));
          break;
        case 3:
          sensorStatusAnnunciationDto.setTimeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate(getBooleanFromBitValue(bit));
          break;
        default:
          //Cases 4-5-6-7 are bits reserved for future uses.
          break;
      }
      bitCount++;
    }
  }

  private void setGlucoseConcentrationValueFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String exponent = getBitArrayFromSpecificByte(rawData, 13).substring(0, 4);
    exponent = new StringBuilder(exponent).reverse().toString();
    String mantissa = getBitArrayFromSpecificByte(rawData, 13).substring(4, 8) + getBitArrayFromSpecificByte(rawData, 12);
    int mantissaValue = Integer.parseInt(mantissa, 2);
    int exponentValue = Integer.parseInt(exponent.substring(1), 2);
    
    if (exponent.charAt(0) == '1') {
      exponentValue = -exponentValue;
    }

    glucoseMeasurementDto.setGlucoseConcentration((float) (mantissaValue * (Math.pow(10, exponentValue))));
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
  
  private void setTimeOffsetFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits = getBitArrayFromSpecificByte(rawData, 11) + getBitArrayFromSpecificByte(rawData, 10);
    int timeOffset = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setTimeOffset(timeOffset);
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
          flags.setGlucoseConcentrationUnitFlag(getBooleanFromBitValue(bit));
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
