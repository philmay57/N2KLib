package N2KDefs;
import javax.xml.bind.annotation.XmlAttribute;

public class PGNEnumPair
{
  @XmlAttribute(name = "Value")
  public int Value;
  @XmlAttribute(name = "Name")
  public String Name;
}

