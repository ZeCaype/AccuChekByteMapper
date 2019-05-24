package com.levio.cil.accuchek.glucose.features.dtos;

public class GlucoseFeatureDto {

  private boolean lowBatteryDetectionDuringMeasurementSupported;
  private boolean sensorMalfunctionDetectionSupported;
  private boolean sensorSampleSizeSupported;
  private boolean sensorStripInsertionErrorDetectionSupported;
  private boolean sensorStripTypeErrorDetectionSupported;
  private boolean sensorResultHighLowDetectionSupported;
  private boolean sensorTemperatureHighLowDetectionSupported;
  private boolean sensorReadInterruptDetectionSupported;
  private boolean generalDeviceFaultSupported;
  private boolean timeFaultSupported;
  private boolean multipleBondSupported;

  public GlucoseFeatureDto() {}

  public boolean isLowBatteryDetectionDuringMeasurementSupported() {
    return lowBatteryDetectionDuringMeasurementSupported;
  }

  public void setLowBatteryDetectionDuringMeasurementSupported(
      boolean lowBatteryDetectionDuringMeasurementSupported) {
    this.lowBatteryDetectionDuringMeasurementSupported =
        lowBatteryDetectionDuringMeasurementSupported;
  }

  public boolean isSensorMalfunctionDetectionSupported() {
    return sensorMalfunctionDetectionSupported;
  }

  public void setSensorMalfunctionDetectionSupported(boolean sensorMalfunctionDetectionSupported) {
    this.sensorMalfunctionDetectionSupported = sensorMalfunctionDetectionSupported;
  }

  public boolean isSensorSampleSizeSupported() {
    return sensorSampleSizeSupported;
  }

  public void setSensorSampleSizeSupported(boolean sensorSampleSizeSupported) {
    this.sensorSampleSizeSupported = sensorSampleSizeSupported;
  }

  public boolean isSensorStripInsertionErrorDetectionSupported() {
    return sensorStripInsertionErrorDetectionSupported;
  }

  public void setSensorStripInsertionErrorDetectionSupported(
      boolean sensorStripInsertionErrorDetectionSupported) {
    this.sensorStripInsertionErrorDetectionSupported = sensorStripInsertionErrorDetectionSupported;
  }

  public boolean isSensorStripTypeErrorDetectionSupported() {
    return sensorStripTypeErrorDetectionSupported;
  }

  public void setSensorStripTypeErrorDetectionSupported(
      boolean sensorStripTypeErrorDetectionSupported) {
    this.sensorStripTypeErrorDetectionSupported = sensorStripTypeErrorDetectionSupported;
  }

  public boolean isSensorResultHighLowDetectionSupported() {
    return sensorResultHighLowDetectionSupported;
  }

  public void setSensorResultHighLowDetectionSupported(
      boolean sensorResultHighLowDetectionSupported) {
    this.sensorResultHighLowDetectionSupported = sensorResultHighLowDetectionSupported;
  }

  public boolean isSensorTemperatureHighLowDetectionSupported() {
    return sensorTemperatureHighLowDetectionSupported;
  }

  public void setSensorTemperatureHighLowDetectionSupported(
      boolean sensorTemperatureHighLowDetectionSupported) {
    this.sensorTemperatureHighLowDetectionSupported = sensorTemperatureHighLowDetectionSupported;
  }

  public boolean isSensorReadInterruptDetectionSupported() {
    return sensorReadInterruptDetectionSupported;
  }

  public void setSensorReadInterruptDetectionSupported(
      boolean sensorReadInterruptDetectionSupported) {
    this.sensorReadInterruptDetectionSupported = sensorReadInterruptDetectionSupported;
  }

  public boolean isGeneralDeviceFaultSupported() {
    return generalDeviceFaultSupported;
  }

  public void setGeneralDeviceFaultSupported(boolean generalDeviceFaultSupported) {
    this.generalDeviceFaultSupported = generalDeviceFaultSupported;
  }

  public boolean isTimeFaultSupported() {
    return timeFaultSupported;
  }

  public void setTimeFaultSupported(boolean timeFaultSupported) {
    this.timeFaultSupported = timeFaultSupported;
  }

  public boolean isMultipleBondSupported() {
    return multipleBondSupported;
  }

  public void setMultipleBondSupported(boolean multipleBondSupported) {
    this.multipleBondSupported = multipleBondSupported;
  }
}
