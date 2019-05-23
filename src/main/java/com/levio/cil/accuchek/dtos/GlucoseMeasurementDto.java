package com.levio.cil.accuchek.dtos;

public class GlucoseMeasurementDto {
  
  private FlagsDto flags;
  private int sequenceNumber;
  
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
  
}
