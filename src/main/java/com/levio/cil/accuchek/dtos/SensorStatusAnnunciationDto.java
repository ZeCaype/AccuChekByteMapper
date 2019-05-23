package com.levio.cil.accuchek.dtos;

public class SensorStatusAnnunciationDto {
  private boolean deviceBatteryLowAtTimeOfMeasurement;
  private boolean sensorMalfunctionOrFaultingAtTimeOfMeasurement;
  private boolean sampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement;
  private boolean stripInsertionError;
  private boolean stripTypeIncorrectForDevice;
  private boolean sensorResultHigherThanTheDeviceCanProcess;
  private boolean sensorResultLowerThanTheDeviceCanProcess;
  private boolean sensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement;
  private boolean sensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement;
  private boolean sensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement;
  private boolean generalDeviceFaultHasOccurredInTheSensor;
  private boolean timeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate;

  public boolean isDeviceBatteryLowAtTimeOfMeasurement() {
    return deviceBatteryLowAtTimeOfMeasurement;
  }

  public void setDeviceBatteryLowAtTimeOfMeasurement(boolean deviceBatteryLowAtTimeOfMeasurement) {
    this.deviceBatteryLowAtTimeOfMeasurement = deviceBatteryLowAtTimeOfMeasurement;
  }

  public boolean isSensorMalfunctionOrFaultingAtTimeOfMeasurement() {
    return sensorMalfunctionOrFaultingAtTimeOfMeasurement;
  }

  public void setSensorMalfunctionOrFaultingAtTimeOfMeasurement(
      boolean sensorMalfunctionOrFaultingAtTimeOfMeasurement) {
    this.sensorMalfunctionOrFaultingAtTimeOfMeasurement = sensorMalfunctionOrFaultingAtTimeOfMeasurement;
  }

  public boolean isSampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement() {
    return sampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement;
  }

  public void setSampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement(
      boolean sampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement) {
    this.sampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement = sampleSizeForBloodOrControlSolutionInsufficientAtTimeOfMeasurement;
  }

  public boolean isStripInsertionError() {
    return stripInsertionError;
  }

  public void setStripInsertionError(boolean stripInsertionError) {
    this.stripInsertionError = stripInsertionError;
  }

  public boolean isStripTypeIncorrectForDevice() {
    return stripTypeIncorrectForDevice;
  }

  public void setStripTypeIncorrectForDevice(boolean stripTypeIncorrectForDevice) {
    this.stripTypeIncorrectForDevice = stripTypeIncorrectForDevice;
  }

  public boolean isSensorResultHigherThanTheDeviceCanProcess() {
    return sensorResultHigherThanTheDeviceCanProcess;
  }

  public void setSensorResultHigherThanTheDeviceCanProcess(
      boolean sensorResultHigherThanTheDeviceCanProcess) {
    this.sensorResultHigherThanTheDeviceCanProcess = sensorResultHigherThanTheDeviceCanProcess;
  }

  public boolean isSensorResultLowerThanTheDeviceCanProcess() {
    return sensorResultLowerThanTheDeviceCanProcess;
  }

  public void setSensorResultLowerThanTheDeviceCanProcess(
      boolean sensorResultLowerThanTheDeviceCanProcess) {
    this.sensorResultLowerThanTheDeviceCanProcess = sensorResultLowerThanTheDeviceCanProcess;
  }

  public boolean isSensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement() {
    return sensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement;
  }

  public void setSensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement(
      boolean sensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement) {
    this.sensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement = sensorTemperatureTooHighForValidTestResultAtTimeOfMeasurement;
  }

  public boolean isSensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement() {
    return sensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement;
  }

  public void setSensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement(
      boolean sensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement) {
    this.sensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement = sensorTemperatureTooLowForValidTestResultAtTimeOfMeasurement;
  }

  public boolean isSensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement() {
    return sensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement;
  }

  public void setSensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement(
      boolean sensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement) {
    this.sensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement = sensorReadInterruptedBecauseStripWasPulledTooSoonAtTimeOfMeasurement;
  }

  public boolean isGeneralDeviceFaultHasOccurredInTheSensor() {
    return generalDeviceFaultHasOccurredInTheSensor;
  }

  public void setGeneralDeviceFaultHasOccurredInTheSensor(
      boolean generalDeviceFaultHasOccurredInTheSensor) {
    this.generalDeviceFaultHasOccurredInTheSensor = generalDeviceFaultHasOccurredInTheSensor;
  }

  public boolean isTimeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate() {
    return timeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate;
  }

  public void setTimeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate(
      boolean timeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate) {
    this.timeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate = timeFaultHasOccurredInTheSensorAndTimeMayBeInaccurate;
  }
}
