package com.pi4j.io.serial;

public enum FlowControl {

  NONE(0);

	@SuppressWarnings("unused")
  private int val = 0;
  private FlowControl(int val)
  {
    this.val = val;
  }

}
