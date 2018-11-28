package N2KDefs;
import javax.xml.bind.annotation.XmlElement;
public class PGNField
{
  @XmlElement 
    public int Order;
  @XmlElement 
    public String Id;
  @XmlElement 
    public String Name;
  @XmlElement 
    public int BitLength;
  @XmlElement 
    public int BitOffset;
  @XmlElement 
    public int BitStart;
  @XmlElement 
    public String Match;
  @XmlElement 
    public String Type;
  @XmlElement 
    public String Units;
  @XmlElement 
    public double Resolution;
  @XmlElement 
    public boolean Signed;
  @XmlElement 
    public String Description;
  @XmlElement 
    public PGNEnumValues EnumValues;

  public void setEnumValues(PGNEnumValues vals)
  {
    this.EnumValues = vals;
  }
  public PGNEnumValues getEnumValues()
  {
    return(this.EnumValues);
  }
}

