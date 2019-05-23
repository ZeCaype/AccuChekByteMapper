package com.levio.cil.accuchek.dtos;

public class FlagsDto {

  private boolean timeOffsetPresent;
  private boolean glucoseConcentrationTypeSamplePresent;
  private boolean glucoseConcentrationUnitFlag;
  private String glucoseConcentrationUnit;
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

  public boolean isGlucoseConcentrationUnitFlag() {
    return glucoseConcentrationUnitFlag;
  }

  public void setGlucoseConcentrationUnitFlag(boolean glucoseConcentrationUnitFlag) {
    this.glucoseConcentrationUnitFlag = glucoseConcentrationUnitFlag;
  }

  public String getGlucoseConcentrationUnit() {
    return glucoseConcentrationUnit;
  }

  public void setGlucoseConcentrationUnit() {
    if (this.glucoseConcentrationUnitFlag) {
      this.glucoseConcentrationUnit = "mol/L";
    } else {
      this.glucoseConcentrationUnit = "kg/L";
    }
  }

}
