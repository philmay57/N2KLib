package N2KDefs;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class PGNInfo 
{
  @XmlElement
    public int PGN;
  @XmlElement
    public String Id;
  @XmlElement
    public String Description;
  @XmlElement
    public boolean Complete;
  @XmlElement
    public int Length;
  @XmlElement
    public int RepeatingFields;
  @XmlElement(name="Fields")
  public PGNFields pgnFields;

  public void setFields(PGNFields fields)
  {
    this.pgnFields = fields;
  }
  public PGNFields getFields()
  {
    return(this.pgnFields);
  }

  public String toString()
  {
    int size = 0;
    if (pgnFields != null)
    {
      List<PGNField> fld = pgnFields.pgnField;
      if (fld != null)
      {
        size = fld.size();
      }
    }
    return("PGN:" + PGN + " Id:" + Id + " Description:" + Description + 
           " Complete:" + Complete + " Length:" + Length + 
           " RepeatingFields:" + RepeatingFields + " Field count:" + size); 
  }
}

