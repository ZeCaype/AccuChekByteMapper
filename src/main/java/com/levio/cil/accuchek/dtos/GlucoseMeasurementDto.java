package com.levio.cil.accuchek.dtos;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class GlucoseMeasurementDto {

  private FlagsDto flags;
  private LocalDateTime date;
  private SensorStatusAnnunciationDto sensorStatusAnnunciationDto;
  
  @JsonIgnore
  private int sequenceNumber;
  @JsonIgnore
  private int year;
  @JsonIgnore
  private int month;
  @JsonIgnore
  private int day;
  @JsonIgnore
  private int hour;
  @JsonIgnore
  private int minute;
  @JsonIgnore
  private int second;
  @JsonIgnore
  private int sampleLocation;

  public GlucoseMeasurementDto() {

  }

  public FlagsDto getFlags() {
    return flags;
  }

  public void setFlags(FlagsDto flags) {
    this.flags = flags;
  }

  public int getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(int sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public int getSecond() {
    return second;
  }

  public void setSecond(int second) {
    this.second = second;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate() {
    this.date = LocalDateTime.of(this.year, this.month, this.day, this.hour, this.minute, this.second);
  }

  public SensorStatusAnnunciationDto getSensorStatusAnnunciationDto() {
    return sensorStatusAnnunciationDto;
  }

  public void setSensorStatusAnnunciationDto(
      SensorStatusAnnunciationDto sensorStatusAnnunciationDto) {
    this.sensorStatusAnnunciationDto = sensorStatusAnnunciationDto;
  }

  public int getSampleLocation() {
    return sampleLocation;
  }

  public void setSampleLocation(int sampleLocation) {
    this.sampleLocation = sampleLocation;
  }
}
