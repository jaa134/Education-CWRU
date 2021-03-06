package uxb;

import java.util.List;

//all peripheral devices
public abstract class AbstractPeripheral<T extends AbstractPeripheral.Builder<T>> extends AbstractDevice<T>
{
  protected AbstractPeripheral(Builder<T> builder) { super(builder); } 
    
  public static abstract class Builder<T> extends AbstractDevice.Builder<T>
  {
    public Builder(Integer version) { super(version); }
    
    //validate builder values
    protected void validate()
    {
      super.validate();
      //if there isn't any connectors throw an error
      if (getConnectors().isEmpty()) 
        throw new IllegalStateException("Validation failed because the list was null.");
      //if there are connector types other than peripherals throw an error
      if (!containsOnlyPeripherals(getConnectors()))
        throw new IllegalStateException("Validation failed because non-peripheral type connections were detected.");
    }
    
    //check list to see if it only contains peripheral connector types
    private boolean containsOnlyPeripherals(List<Connector.Type> connectorTypes)
    {
      //streams throwing array index out of bounds exception?
      //return connectorTypes.stream().allMatch(s -> s == Connector.Type.PERIPHERAL);
      for(Connector.Type type : connectorTypes)
      {
         if (type != Connector.Type.PERIPHERAL) return false;
      }
      return true;
    }
  }
}