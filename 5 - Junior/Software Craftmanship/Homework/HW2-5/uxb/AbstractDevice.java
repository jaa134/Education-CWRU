package uxb;

import java.util.Optional;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

// a protoypical UXB device
public abstract class AbstractDevice<T extends AbstractDevice.Builder<T>> implements Device
{
  private final Optional<Integer> productCode;
  private final Optional<BigInteger> serialNumber;
  private final Integer version;
  private final List<Connector.Type> connectorTypes;
  private final List<Connector> connectors;
  
  //initialize the device from the builders fields
  protected AbstractDevice(Builder<T> builder)
  {
    this.productCode = builder.productCode;
    this.serialNumber = builder.serialNumber;
    this.version = builder.version;
    this.connectorTypes = builder.connectors;
    
    //make connector list
    LinkedList<Connector> tempList = new LinkedList<Connector>();
    for(int i = 0; i < getConnectorTypes().size(); i++)
    {
      tempList.add(new Connector(this, i, getConnectorTypes().get(i)));
    }
    this.connectors = tempList;
  }
  
  public Optional<Integer> getProductCode() { return productCode; }
  
  public Optional<BigInteger> getSerialNumber() { return serialNumber; }
  
  public Integer getVersion() { return version; }
  
  public List<Connector> getConnectors() { return new ArrayList<Connector>(this.connectors); }
  
  public List<Connector.Type> getConnectorTypes() { return this.connectorTypes; }
  
  public Connector getConnector(int index) 
  { 
    return getConnectors().isEmpty() ? null : getConnectors().get(index);
  }
  
  public Integer getConnectorCount() { return connectors.size(); }
  
  protected void verifyRecv(Message message, Connector connector)
  {
    if (message == null || connector == null) throw new NullPointerException();
  }
  
  //find all peer devices to the connectors
  public Set<Device> peerDevices()
  {
    HashSet<Device> devices = new HashSet<Device>();
    for(Connector c : this.connectors)
    {
      if (c.getPeer().isPresent())
        devices.add(c.getPeer().get().getDevice());
    }
    return devices;
  }
  
  //returns all devices that are reachable
  public Set<Device> reachableDevices()
  {
    return breadthFirstSearch(Optional.empty());
  }
  
  //determines if the device is connected in line
  public boolean isReachable(Device device)
  {
    HashSet<Device> reachables = new HashSet<Device>(breadthFirstSearch(Optional.of(device)));
    return reachables.contains(device);
  }
  
  private Set<Device> breadthFirstSearch(Optional<Device> device)
  {
    //keep track of the previous list size
    int previousSetSize = 0;
    HashSet<Device> reachables = new HashSet<Device>(peerDevices());
    //find peers until the list size doesnt change
    while(previousSetSize < reachables.size())
    {
      previousSetSize = reachables.size();
      HashSet<Device> reachablesCopy = new HashSet<Device>(reachables);
      //add all the peers to our set
      for(Device d : reachables)
      {
        reachablesCopy.addAll(d.peerDevices());
        //if one of the devices in the set is the same, then the device is reachable
        if(device.isPresent() && this.equals(device.get()))
          return reachables;
      }
      reachables.addAll(reachablesCopy);
    }
    return reachables;
  }
  
  //test if two devices are equal
  public boolean equals(Device device)
  {
    return device != null &&
           device.getDeviceClass() == this.getDeviceClass() &&
           this.checkValues(device);        
  }
  
  //compare the values of two different devices
  private boolean checkValues(Device device)
  {
    return device.getProductCode().equals(this.getProductCode()) &&
           device.getSerialNumber().equals(this.getSerialNumber()) &&
           device.getVersion().equals(this.getVersion()) &&
           device.getConnectors().equals(this.getConnectors());
  }
  
  //forwards a message to all immediate connectors, with an exclusion list 
  protected void forwardMessage(Message message, List<Connector> potentialRecipients, List<Connector> startExceptions)
  {
    LinkedList<Connector> currentExceptions = new LinkedList<Connector>(startExceptions);
    for(Connector c : potentialRecipients)
    {
      if(!currentExceptions.contains(c) && c.getPeer().isPresent())
      {
         message.reach(c.getPeer().get().getDevice(), c);
         currentExceptions.add(c);
      }
    }
  }
  
  //a builder that will allow valiation of a device object before its initialization
  public static abstract class Builder<T>
  {
    private Optional<Integer> productCode;
    private Optional<BigInteger> serialNumber;
    private Integer version;
    private List<Connector.Type> connectors;
    
    //intialize builder with passed version number
    public Builder(Integer version) { this.version = version; }
    
    //set the builder's product code. if null, flag as unset
    public T productCode(Integer productCode) 
    {
      this.productCode = productCode == null ? Optional.empty() : Optional.of(productCode);
      return getThis();
    }
    
    //set the builder's serial number. if null, flag as unset
    public T serialNumber(BigInteger serialNumber) 
    {
      this.serialNumber = serialNumber == null ? Optional.empty() : Optional.of(serialNumber);
      return getThis();
    }
    
    //set the builder's connector type list. if null, list will be empty
    public T connectors(List<Connector.Type> connectors) 
    {
      this.connectors = connectors == null ? new ArrayList<Connector.Type>() : connectors;
      return getThis();
    }
    
    protected abstract T getThis();
    
    //get the builder's list of connectors
    protected List<Connector.Type> getConnectors() { return connectors; }
    
    //make sure a version has been assigned to the builder or throw exception
    protected void validate()
    {
      if (this.version == null)
        throw new NullPointerException("Validation failed because the version number was null");
    }
  }
}