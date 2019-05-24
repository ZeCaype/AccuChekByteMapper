package com.levio.cil.accuchek.mapper;

import com.levio.cil.accuchek.dtos.SensorStatusAnnunciationDto;
import com.levio.cil.accuchek.dtos.FlagsContextDto;
import com.levio.cil.accuchek.dtos.FlagsDto;
import com.levio.cil.accuchek.dtos.GlucoseFeatureDto;
import com.levio.cil.accuchek.dtos.GlucoseFeatureRawDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementContextDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementContextRawDataDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementDto;
import com.levio.cil.accuchek.dtos.GlucoseMeasurementRawDataDto;
import org.springframework.stereotype.Component;

@Component
public class GlucoseServicesBytesMapper {

  public GlucoseServicesBytesMapper() {}
  
  public GlucoseFeatureDto mapToReadableGlucoseFeatureDto(GlucoseFeatureRawDto rawData){
    GlucoseFeatureDto glucoseFeatureDto = new GlucoseFeatureDto();

    setGlucoseFeatureFlags(rawData, glucoseFeatureDto);
    return  glucoseFeatureDto;
  }

  public GlucoseMeasurementDto mapToReadableGlucoseMeasurementDto(
      GlucoseMeasurementRawDataDto rawData) {
    GlucoseMeasurementDto glucoseMeasurementDto = new GlucoseMeasurementDto();
    try {
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
      setSensorStatusAnnunciationFromRawData(rawData, sensorStatusAnnunciationDto);
      setSampleLocationFromRawData(rawData, glucoseMeasurementDto);
      setTimeOffsetFromRawData(rawData, glucoseMeasurementDto);
      setGlucoseConcentrationValueFromRawData(rawData, glucoseMeasurementDto);
      setTypeFromRawData(rawData, glucoseMeasurementDto);

      glucoseMeasurementDto.setSensorStatusAnnunciationDto(sensorStatusAnnunciationDto);
      glucoseMeasurementDto.setDate();
      flags.setGlucoseConcentrationUnit();
      glucoseMeasurementDto.setFlags(flags);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return glucoseMeasurementDto;
  }

  public GlucoseMeasurementContextDto mapToReadableGlucoseMeasurementContextDto(
      GlucoseMeasurementContextRawDataDto rawData) {
    GlucoseMeasurementContextDto glucoseMeasurementContext = new GlucoseMeasurementContextDto();
    int bytePosition = 3;

    try {
      setFlagsContextFromRawData(rawData, glucoseMeasurementContext);
      setSequenceNumberFromRawData(rawData, glucoseMeasurementContext);
      bytePosition = skipExtendedFlagsIfAllocated(glucoseMeasurementContext, bytePosition);
      bytePosition = setCarbohydrateIdFromRawData(rawData, glucoseMeasurementContext, bytePosition);
      bytePosition = setMealFromRawData(rawData, glucoseMeasurementContext, bytePosition);
      bytePosition =
          setTesterAndHealthFromRawData(rawData, glucoseMeasurementContext, bytePosition);
      bytePosition = setExerciceDurationAndIntensityFromRawData(rawData, glucoseMeasurementContext,
          bytePosition);
      bytePosition = setMedicationFromRawData(rawData, glucoseMeasurementContext, bytePosition);
      setHbA1cFromRawData(rawData, glucoseMeasurementContext, bytePosition);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return glucoseMeasurementContext;
  }

  private int skipExtendedFlagsIfAllocated(GlucoseMeasurementContextDto glucoseMeasurementContext,
      int bytePosition) {
    if (glucoseMeasurementContext.getFlags().isExtendedFlagsPresent()) {
      bytePosition++;
    }
    return bytePosition;
  }

  private void setHbA1cFromRawData(GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext, int bytePosition) {
    if (glucoseMeasurementContext.getFlags().isHba1CPresent()) {
      String rawBits = getBitArrayFromSpecificByte(rawData, bytePosition + 1)
          + getBitArrayFromSpecificByte(rawData, bytePosition);
      String exponent = rawBits.substring(0, 4);
      exponent = new StringBuilder(exponent).reverse().toString();
      String mantissa = rawBits.substring(4, 16);
      int mantissaValue = Integer.parseInt(mantissa, 2);
      int exponentValue = Integer.parseInt(exponent.substring(1), 2);

      if (exponent.charAt(0) == '1') {
        exponentValue = -exponentValue;
      }

      glucoseMeasurementContext.setHbA1c((float) (mantissaValue * (Math.pow(10, exponentValue))));
    }
  }


  private int setMedicationFromRawData(GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext, int bytePosition) {
    if (glucoseMeasurementContext.getFlags().isMedicationIdAndMedicationPresent()) {

      String rawBits = getBitArrayFromSpecificByte(rawData, bytePosition);
      int bitsValue = Integer.parseInt(rawBits, 2);

      switch (bitsValue) {
        case 0:
          glucoseMeasurementContext.setMedicationId("Reserved for future use");
          break;
        case 1:
          glucoseMeasurementContext.setMedicationId("Rapid acting insulin");
          break;
        case 2:
          glucoseMeasurementContext.setMedicationId("Short acting insulin");
          break;
        case 3:
          glucoseMeasurementContext.setMedicationId("Intermediate acting insulin");
          break;
        case 4:
          glucoseMeasurementContext.setMedicationId("Long acting insulin");
          break;
        case 5:
          glucoseMeasurementContext.setMedicationId("Pre-mixed insulin");
          break;
        default:
          break;
      }
      bytePosition++;

      if (glucoseMeasurementContext.getFlags().isMedicationValueUnits()) {
        glucoseMeasurementContext.setMedicationUnits("Units of liters");
      } else {
        glucoseMeasurementContext.setMedicationUnits("Units of kilograms");
      }

      String rawBitsForMedicationQuantity = getBitArrayFromSpecificByte(rawData, bytePosition + 1)
          + getBitArrayFromSpecificByte(rawData, bytePosition);
      String exponent = rawBitsForMedicationQuantity.substring(0, 4);
      exponent = new StringBuilder(exponent).reverse().toString();
      String mantissa = rawBitsForMedicationQuantity.substring(4, 16);
      int mantissaValue = Integer.parseInt(mantissa, 2);
      int exponentValue = Integer.parseInt(exponent.substring(1), 2);

      if (exponent.charAt(0) == '1') {
        exponentValue = -exponentValue;
      }

      glucoseMeasurementContext
          .setMedicationQuantity((float) (mantissaValue * (Math.pow(10, exponentValue))));

      bytePosition = bytePosition + 2;
    }
    return bytePosition;
  }


  private int setExerciceDurationAndIntensityFromRawData(
      GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext, int bytePosition) {
    if (glucoseMeasurementContext.getFlags().isExerciseDurationAndExerciseIntensityPresent()) {
      String rawBitsForExerciceDuration = getBitArrayFromSpecificByte(rawData, bytePosition + 1)
          + getBitArrayFromSpecificByte(rawData, bytePosition);
      String rawBitsForExerciceIntensity = getBitArrayFromSpecificByte(rawData, bytePosition + 2);
      glucoseMeasurementContext
          .setExerciceDuration(Integer.parseInt(rawBitsForExerciceDuration, 2));
      glucoseMeasurementContext
          .setExerciceIntensity(Integer.parseInt(rawBitsForExerciceIntensity, 2));
      bytePosition = bytePosition + 3;
    }
    return bytePosition;
  }


  private int setTesterAndHealthFromRawData(GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext, int bytePosition) {
    if (glucoseMeasurementContext.getFlags().isTesterHealthPresent()) {
      String rawBits = getBitArrayFromSpecificByte(rawData, bytePosition);
      int firstNibbleBits = Integer.parseInt(rawBits.substring(0, 4), 2);
      int secondNibbleBits = Integer.parseInt(rawBits.substring(4, 8), 2);

      switch (firstNibbleBits) {
        case 0:
          glucoseMeasurementContext.setTester("Reserved for future use");
          break;
        case 1:
          glucoseMeasurementContext.setTester("Self");
          break;
        case 2:
          glucoseMeasurementContext.setTester("Health Care Professional");
          break;
        case 3:
          glucoseMeasurementContext.setTester("Lab Test");
          break;
        case 15:
          glucoseMeasurementContext.setTester("Tester value not available");
          break;
        default:
          break;
      }

      switch (secondNibbleBits) {
        case 0:
          glucoseMeasurementContext.setHealth("Reserved for future use");
          break;
        case 1:
          glucoseMeasurementContext.setHealth("Minor health issues");
          break;
        case 2:
          glucoseMeasurementContext.setHealth("Major health issues");
          break;
        case 3:
          glucoseMeasurementContext.setHealth("During Menses");
          break;
        case 4:
          glucoseMeasurementContext.setHealth("Under stress");
          break;
        case 5:
          glucoseMeasurementContext.setHealth("No health issues");
          break;
        case 15:
          glucoseMeasurementContext.setTester("Health value not available");
          break;
        default:
          break;
      }
      bytePosition++;
    }
    return bytePosition;
  }


  private int setMealFromRawData(GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext, int bytePosition) {
    if (glucoseMeasurementContext.getFlags().isMealPresent()) {
      String rawBits = getBitArrayFromSpecificByte(rawData, bytePosition);
      int bitsValue = Integer.parseInt(rawBits, 2);

      switch (bitsValue) {
        case 0:
          glucoseMeasurementContext.setMeal("Reserved for future use");
          break;
        case 1:
          glucoseMeasurementContext.setMeal("Preprendial (before meal)");
          break;
        case 2:
          glucoseMeasurementContext.setMeal("Postprendial (after meal)");
          break;
        case 3:
          glucoseMeasurementContext.setMeal("Fasting");
          break;
        case 4:
          glucoseMeasurementContext.setMeal("Casual (snacks, drinks, etc.)");
          break;
        case 5:
          glucoseMeasurementContext.setMeal("Bedtime");
          break;
        default:
          break;
      }
      bytePosition++;
    }
    return bytePosition;
  }


  private int setCarbohydrateIdFromRawData(GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext, int bytePosition) {
    if (glucoseMeasurementContext.getFlags().isCarbohydrateIdAndCarbohydratePresent()) {
      String rawBits = getBitArrayFromSpecificByte(rawData, bytePosition);
      int bitsValue = Integer.parseInt(rawBits, 2);

      switch (bitsValue) {
        case 0:
          glucoseMeasurementContext.setCarbohydrateId("Reserved for future use");
          break;
        case 1:
          glucoseMeasurementContext.setCarbohydrateId("Breakfast");
          break;
        case 2:
          glucoseMeasurementContext.setCarbohydrateId("Lunch");
          break;
        case 3:
          glucoseMeasurementContext.setCarbohydrateId("Dinner");
          break;
        case 4:
          glucoseMeasurementContext.setCarbohydrateId("Snack");
          break;
        case 5:
          glucoseMeasurementContext.setCarbohydrateId("Drink");
          break;
        case 6:
          glucoseMeasurementContext.setCarbohydrateId("Supper");
          break;
        case 7:
          glucoseMeasurementContext.setCarbohydrateId("Brunch");
          break;
        default:
          break;
      }
      bytePosition++;

      glucoseMeasurementContext.setCarbohydrateUnits("Units of kilograms");

      String rawBitsForCarbohydrateConcentration =
          getBitArrayFromSpecificByte(rawData, bytePosition + 1)
              + getBitArrayFromSpecificByte(rawData, bytePosition);
      String exponent = rawBitsForCarbohydrateConcentration.substring(0, 4);
      exponent = new StringBuilder(exponent).reverse().toString();
      String mantissa = rawBitsForCarbohydrateConcentration.substring(4, 16);
      int mantissaValue = Integer.parseInt(mantissa, 2);
      int exponentValue = Integer.parseInt(exponent.substring(1), 2);

      if (exponent.charAt(0) == '1') {
        exponentValue = -exponentValue;
      }

      glucoseMeasurementContext
          .setMedicationQuantity((float) (mantissaValue * (Math.pow(10, exponentValue))));

      bytePosition = bytePosition + 2;

    }
    return bytePosition;
  }


  private void setSequenceNumberFromRawData(GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext) {
    String rawSequenceNumberBits =
        getBitArrayFromSpecificByte(rawData, 2) + getBitArrayFromSpecificByte(rawData, 1);
    glucoseMeasurementContext.setSequenceNumber(Integer.parseInt(rawSequenceNumberBits, 2));
  }


  private void setFlagsContextFromRawData(GlucoseMeasurementContextRawDataDto rawData,
      GlucoseMeasurementContextDto glucoseMeasurementContext) {
    FlagsContextDto flags = new FlagsContextDto();
    String rawFlagsBits = getBitArrayFromSpecificByte(rawData, 0);
    rawFlagsBits = new StringBuilder(rawFlagsBits).reverse().toString();
    int bitCount = 0;

    for (char bit : rawFlagsBits.toCharArray()) {
      switch (bitCount) {
        case 0:
          flags.setCarbohydrateIdAndCarbohydratePresent(getBooleanFromBitValue(bit));
          break;
        case 1:
          flags.setMealPresent(getBooleanFromBitValue(bit));
          break;
        case 2:
          flags.setTesterHealthPresent(getBooleanFromBitValue(bit));
          break;
        case 3:
          flags.setExerciseDurationAndExerciseIntensityPresent(getBooleanFromBitValue(bit));
          break;
        case 4:
          flags.setMedicationIdAndMedicationPresent(getBooleanFromBitValue(bit));
          break;
        case 5:
          flags.setMedicationValueUnits(getBooleanFromBitValue(bit));
          break;
        case 6:
          flags.setHba1CPresent(getBooleanFromBitValue(bit));
          break;
        case 7:
          flags.setExtendedFlagsPresent(getBooleanFromBitValue(bit));
          break;
        default:
          break;
      }
      bitCount++;
    }
    glucoseMeasurementContext.setFlags(flags);
  }

  private void setTypeFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSampleLocationBits = getBitArrayFromSpecificByte(rawData, 14).substring(4, 8);
    int value = Integer.parseInt(rawSampleLocationBits, 2);
    switch (value) {
      case 0:
        glucoseMeasurementDto.setType("Reserved for future use");
        break;
      case 1:
        glucoseMeasurementDto.setType("Capillary Whole blood");
        break;
      case 2:
        glucoseMeasurementDto.setType("Capillary Plasma");
        break;
      case 3:
        glucoseMeasurementDto.setType("Venous Whole blood");
        break;
      case 4:
        glucoseMeasurementDto.setType("Venous Plasma");
        break;
      case 5:
        glucoseMeasurementDto.setType("Arterial Whole blood");
        break;
      case 6:
        glucoseMeasurementDto.setType("Arterial Plasma");
        break;
      case 7:
        glucoseMeasurementDto.setType("Undetermined Whole blood");
        break;
      case 8:
        glucoseMeasurementDto.setType("Undetermined Plasma");
        break;
      case 9:
        glucoseMeasurementDto.setType("Interstitial Fluid (ISF)");
        break;
      case 10:
        glucoseMeasurementDto.setType("Control Solution");
        break;
      default:
        break;
    }
  }

  private void setSampleLocationFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSampleLocationBits = getBitArrayFromSpecificByte(rawData, 14).substring(0, 4);
    int value = Integer.parseInt(rawSampleLocationBits, 2);

    switch (value) {
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

  private void setSensorStatusAnnunciationFromRawData(GlucoseMeasurementRawDataDto rawData,
      SensorStatusAnnunciationDto sensorStatusAnnunciationDto) {
    String rawSensorStatusAnnunciationByte1 = getBitArrayFromSpecificByte(rawData, 15);
    String rawSensorStatusAnnunciationByte2 = getBitArrayFromSpecificByte(rawData, 16);
    rawSensorStatusAnnunciationByte1 =
        new StringBuilder(rawSensorStatusAnnunciationByte1).reverse().toString();
    rawSensorStatusAnnunciationByte2 =
        new StringBuilder(rawSensorStatusAnnunciationByte2).reverse().toString();
    int bitCount = 0;

    for (char bit : rawSensorStatusAnnunciationByte1.toCharArray()) {
      switch (bitCount) {
        case 0:
          sensorStatusAnnunciationDto
              .setDeviceBatteryLowAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        case 1:
          sensorStatusAnnunciationDto
              .setSensorMalfunctionOrFaultingAtTimeOfMeasurement(getBooleanFromBitValue(bit));
          break;
        case 2:
          sensorStatusAnnunciationDto
              .setSampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement(
                  getBooleanFromBitValue(bit));
          break;
        case 3:
          sensorStatusAnnunciationDto.setStripInsertionError(getBooleanFromBitValue(bit));
          break;
        case 4:
          sensorStatusAnnunciationDto.setStripTypeIncorrectForDevice(getBooleanFromBitValue(bit));
          break;
        case 5:
          sensorStatusAnnunciationDto
              .setSensorResultHigherThanTheDeviceCanProcess(getBooleanFromBitValue(bit));
          break;
        case 6:
          sensorStatusAnnunciationDto
              .setSensorResultLowerThanTheDeviceCanProcess(getBooleanFromBitValue(bit));
          break;
        case 7:
          sensorStatusAnnunciationDto
              .setSensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement(
                  getBooleanFromBitValue(bit));
          break;
        default:
          break;
      }
      bitCount++;
    }

    bitCount = 0;

    for (char bit : rawSensorStatusAnnunciationByte2.toCharArray()) {
      switch (bitCount) {
        case 0:
          sensorStatusAnnunciationDto
              .setSensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement(
                  getBooleanFromBitValue(bit));
          break;
        case 1:
          sensorStatusAnnunciationDto
              .setSensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement(
                  getBooleanFromBitValue(bit));
          break;
        case 2:
          sensorStatusAnnunciationDto
              .setGeneralDeviceFaultHasOccurredInTheSensor(getBooleanFromBitValue(bit));
          break;
        case 3:
          sensorStatusAnnunciationDto.setTimeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate(
              getBooleanFromBitValue(bit));
          break;
        default:
          break;
      }
      bitCount++;
    }
  }

  private void setGlucoseConcentrationValueFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String exponent = getBitArrayFromSpecificByte(rawData, 13).substring(0, 4);
    exponent = new StringBuilder(exponent).reverse().toString();
    String mantissa = getBitArrayFromSpecificByte(rawData, 13).substring(4, 8)
        + getBitArrayFromSpecificByte(rawData, 12);
    int mantissaValue = Integer.parseInt(mantissa, 2);
    int exponentValue = Integer.parseInt(exponent.substring(1), 2);

    if (exponent.charAt(0) == '1') {
      exponentValue = -exponentValue;
    }

    glucoseMeasurementDto
        .setGlucoseConcentration((float) (mantissaValue * (Math.pow(10, exponentValue))));
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
    String rawSequenceNumberBits =
        getBitArrayFromSpecificByte(rawData, 4) + getBitArrayFromSpecificByte(rawData, 3);
    int year = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setYear(year);
  }

  private void setTimeOffsetFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits =
        getBitArrayFromSpecificByte(rawData, 11) + getBitArrayFromSpecificByte(rawData, 10);
    int timeOffset = Integer.parseInt(rawSequenceNumberBits, 2);
    glucoseMeasurementDto.setTimeOffset(timeOffset);
  }

  private void setSequenceNumberFromRawData(GlucoseMeasurementRawDataDto rawData,
      GlucoseMeasurementDto glucoseMeasurementDto) {
    String rawSequenceNumberBits =
        getBitArrayFromSpecificByte(rawData, 2) + getBitArrayFromSpecificByte(rawData, 1);
    glucoseMeasurementDto.setSequenceNumber(Integer.parseInt(rawSequenceNumberBits, 2));
  }

  private void setFlagsFromRawData(GlucoseMeasurementRawDataDto rawData, FlagsDto flags) {
    String rawFlagsBits = getBitArrayFromSpecificByte(rawData, 0);
    rawFlagsBits = new StringBuilder(rawFlagsBits).reverse().toString();
    int bitCount = 0;

    for (char bit : rawFlagsBits.toCharArray()) {
      switch (bitCount) {
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
          break;
      }
      bitCount++;
    }
  }

  private void setGlucoseFeatureFlags(GlucoseFeatureRawDto rawData, GlucoseFeatureDto glucoseFeatureDto) {
      String rawGlucoseFeatureByte1 = getBitArrayFromSpecificByte(rawData, 0);
      String rawGlucoseFeatureByte2 = getBitArrayFromSpecificByte(rawData, 1);
      rawGlucoseFeatureByte1 = new StringBuilder(rawGlucoseFeatureByte1).reverse().toString();
      rawGlucoseFeatureByte2 = new StringBuilder(rawGlucoseFeatureByte2).reverse().toString();

      int bitCount = 0;

      for (char bit: rawGlucoseFeatureByte1.toCharArray()) {
        switch(bitCount) {
          case 0:
            glucoseFeatureDto.setLowBatteryDetectionDuringMeasurementSupported(getBooleanFromBitValue(bit));
            break;
          case 1:
            glucoseFeatureDto.setSensorMalfunctionDetectionSupported(getBooleanFromBitValue(bit));
            break;
          case 2:
            glucoseFeatureDto.setSensorSampleSizeSupported(getBooleanFromBitValue(bit));
            break;
          case 3:
            glucoseFeatureDto.setSensorStripInsertionErrorDetectionSupported(getBooleanFromBitValue(bit));
            break;
          case 4:
            glucoseFeatureDto.setSensorStripTypeErrorDetectionSupported(getBooleanFromBitValue(bit));
            break;
          case 5:
            glucoseFeatureDto.setSensorResultHighLowDetectionSupported(getBooleanFromBitValue(bit));
            break;
          case 6:
            glucoseFeatureDto.setSensorTemperatureHighLowDetectionSupported(getBooleanFromBitValue(bit));
            break;
          case 7:
            glucoseFeatureDto.setSensorReadInterruptDetectionSupported(getBooleanFromBitValue(bit));
            break;
          default:
            break;
        }
        bitCount++;
    }

    bitCount = 0;

    for (char bit: rawGlucoseFeatureByte2.toCharArray()) {
      switch(bitCount) {
        case 0:
          glucoseFeatureDto.setGeneralDeviceFaultSupported(getBooleanFromBitValue(bit));
          break;
        case 1:
          glucoseFeatureDto.setTimeFaultSupported(getBooleanFromBitValue(bit));
          break;
        case 2:
          glucoseFeatureDto.setMultipleBondSupported(getBooleanFromBitValue(bit));
          break;
        default:
          break;
      }
      bitCount++;
    }
  }

  @SuppressWarnings("null")
  private boolean getBooleanFromBitValue(char bit) {
    if (bit == '0') {
      return false;
    } else if (bit == '1') {
      return true;
    }
    return (Boolean) null;
  }

  private String getBitArrayFromSpecificByte(GlucoseMeasurementRawDataDto rawData, int i) {
    String bitArray =
        String.format("%8s", Integer.toBinaryString(rawData.getData()[i])).replace(' ', '0');

    if (bitArray.length() != 8) {
      bitArray = bitArray.substring(24);
    }
    return bitArray;
  }

  private String getBitArrayFromSpecificByte(GlucoseMeasurementContextRawDataDto rawData, int i) {
    String bitArray =
        String.format("%8s", Integer.toBinaryString(rawData.getData()[i])).replace(' ', '0');

    if (bitArray.length() != 8) {
      bitArray = bitArray.substring(24);
    }
    return bitArray;
  }
  
  private String getBitArrayFromSpecificByte(GlucoseFeatureRawDto rawData, int i) {
    String bitArray = String.format("%8s", Integer.toBinaryString(rawData.getData()[i])).replace(' ', '0');

    if (bitArray.length() != 8) {
      bitArray = bitArray.substring(24);
    }
    return bitArray;
  }

}
