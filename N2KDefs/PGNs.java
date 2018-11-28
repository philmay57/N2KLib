package N2KDefs;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;        

public class PGNs 
{
  @XmlElement
    public List<PGNInfo> pgnInfo;

  public void setPGNInfo(List<PGNInfo> pgnInfo) 
  {
    this.pgnInfo = pgnInfo;
  }
  public List<PGNInfo> getPGNInfo() 
  {
    return(pgnInfo);
  }
}

