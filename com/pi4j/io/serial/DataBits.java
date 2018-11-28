package com.pi4j.io.serial;

public enum DataBits {
  _8(8);

	@SuppressWarnings("unused")
  private int dataBits = 0;
  private DataBits(int dataBits)
  {
    this.dataBits = dataBits;
  }
}
/*

public enum DataBits {

    _5(com.pi4j.jni.Serial.DATA_BITS_5),
    _6(com.pi4j.jni.Serial.DATA_BITS_6),
    _7(com.pi4j.jni.Serial.DATA_BITS_7),
    _8(com.pi4j.jni.Serial.DATA_BITS_8);

    private int dataBits = 0;

    private DataBits(int dataBits){
        this.dataBits = dataBits;
    }

    public int getValue(){
        return this.dataBits;
    }

    public static DataBits getInstance(int data_bits){
        for(DataBits db : DataBits.values()){
            if(db.getValue() == data_bits){
                return db;
            }
        }
        return null;
    }

}*/