class Item
{
  private String value;
  private int index;

  Item(String newValue,int newIndex)
  {
    value=newValue;
    index=newIndex;
  }

  public String getValue()
  {
    return value;
  }

  public int getIndex()
  {
    return index;
  }

  public boolean equals(Item newItem)
  {
    if(value.equals(newItem.getValue()) && index==newItem.getIndex()){
      return true;}
    return false;
  }
}