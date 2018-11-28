package com.pi4j.io.serial;

public enum StopBits {
  _1(1);

	@SuppressWarnings("unused")
  private int val = 0;
  private StopBits(int val)
  {
    this.val = val;
  }

}
