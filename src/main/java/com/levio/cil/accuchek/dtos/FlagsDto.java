package com.levio.cil.accuchek.dtos;

public class FlagsDto {

  private boolean timeOffsetPresent;
  private boolean glucoseConcentrationTypeSamplePresent;
  private boolean glucoseConcentrationUnit;
  private boolean sensorStatusAnnunciationPresent;
  private boolean contextInformationsFollows;
  
  public FlagsDto() {
    
  }

  public boolean isTimeOffsetPresent() {
    return timeOffsetPresent;
  }

  public void setTimeOffsetPresent(boolean timeOffsetPresent) {
    this.timeOffsetPresent = timeOffsetPresent;
  }

  public boolean isGlucoseConcentrationTypeSamplePresent() {
    return glucoseConcentrationTypeSamplePresent;
  }

  public void setGlucoseConcentrationTypeSamplePresent(
      boolean glucoseConcentrationTypeSamplePresent) {
    this.glucoseConcentrationTypeSamplePresent = glucoseConcentrationTypeSamplePresent;
  }

  public boolean isGlucoseConcentrationUnit() {
    return glucoseConcentrationUnit;
  }

  public void setGlucoseConcentrationUnit(boolean glucoseConcentrationUnit) {
    this.glucoseConcentrationUnit = glucoseConcentrationUnit;
  }

  public boolean isSensorStatusAnnunciationPresent() {
    return sensorStatusAnnunciationPresent;
  }

  public void setSensorStatusAnnunciationPresent(boolean sensorStatusAnnunciationPresent) {
    this.sensorStatusAnnunciationPresent = sensorStatusAnnunciationPresent;
  }

  public boolean isContextInformationsFollows() {
    return contextInformationsFollows;
  }

  public void setContextInformationsFollows(boolean contextInformationsFollows) {
    this.contextInformationsFollows = contextInformationsFollows;
  }
  
}
