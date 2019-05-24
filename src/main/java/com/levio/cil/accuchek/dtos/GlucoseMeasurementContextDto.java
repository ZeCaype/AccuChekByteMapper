package com.levio.cil.accuchek.dtos;

public class GlucoseMeasurementContextDto {
 
  private FlagsContextDto flags;
  private int sequenceNumber;
  
  public GlucoseMeasurementContextDto() {
    
  }

  public FlagsContextDto getFlags() {
    return flags;
  }

  public void setFlags(FlagsContextDto flags) {
    this.flags = flags;
  }

  public int getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(int sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }
  
}
