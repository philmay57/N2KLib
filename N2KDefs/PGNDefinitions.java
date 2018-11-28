package N2KDefs;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="PGNDefinitions")
  public class PGNDefinitions 
{
  @XmlElement
    public PGNs pgns;

  public void setPGNs(PGNs pgns)
  {
    this.pgns = pgns;
  }
  public PGNs getPGNs()
  {
    return(pgns);
  }
}

