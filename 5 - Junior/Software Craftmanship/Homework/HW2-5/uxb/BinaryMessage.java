package uxb;

import java.math.BigInteger;

//a message in binary form
public final class BinaryMessage implements Message
{
  //the message being passed as a binary value
  private final BigInteger value;
  
  //set the message. if null, set to zero
  public BinaryMessage(BigInteger value)
  {
    this.value = value == null ? BigInteger.ZERO : value;
  }
  
  public BigInteger getValue() { return value; }
  
  //messages are equal if object compared is not null, is a binary message type, and has the same underlying int value
  @Override
  public boolean equals(Object anObject)
  {
    return anObject != null && anObject instanceof BinaryMessage && this.isValueEqual((BinaryMessage)anObject);
  }
  
  //checks the underlying integer value of the messages
  public boolean isValueEqual(BinaryMessage message)
  {
    return this.getValue().intValue() == message.getValue().intValue();
  }
  
  public void reach(Device device, Connector connector)
  {
    device.recv(this, connector);
  }
}