package N2KDefs;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
public class PGNFields
{
  @XmlElement(name="Field") 
  public List<PGNField> pgnField;

  public void setField(List<PGNField> field)
  {
    this.pgnField = field;
  }
  public List<PGNField> getField()
  {
    return(pgnField);
  }

}

