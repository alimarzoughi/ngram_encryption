import java.util.*;
import java.lang.*;

public class NgramA {
  public static void printPermArr(ArrayList<Item> key)
  {
    int num;
    System.out.print("( ");
    for(int x=1; x<key.size()-1; x++)
    {
      /*num=key.get(x).getIndex();
      if(num==-1){
        System.out.print(") ( ");}
      else{
        System.out.print(num+" ");}*/
      num=key.get(x).getIndex();
      if(num==-1){
        System.out.print(") ( ");}
      else{
        System.out.print(key.get(x).getValue()+" ");}
    }
    System.out.print(")\n");
  }

  public static String format(String word)
  {
      String newWord="";
      for(int x=0; x<word.length(); x++)
      {
          char letter=word.charAt(x);
          if(letter>=32 && letter<=126){
              newWord+=Character.toString(letter);}
      }
      return newWord;
  }

  public static boolean contains(ArrayList<Item> arr, Item item)
  {
    for(int x=0; x<arr.size(); x++)
    {
      if(item.equals(arr.get(x))){
        return true;}
    }
    return false;
  }

  public static boolean containsValue(ArrayList<Item> arr, String value)
  {
    for(int x=0; x<arr.size(); x++)
    {
      if(value.equals(arr.get(x).getValue())){
        return true;}
    }
    return false;
  }

  public static int findIndex(ArrayList<Item> arr, String value)
  {
    int ind=0;
    String newValue;
    for(int x=0; x<arr.size(); x++)
    {
      newValue=arr.get(x).getValue();
      if(value.equals(arr.get(x).getValue()) && arr.get(x).getIndex()!=-1)
      {
        ind=arr.get(x).getIndex();
        break;
      }
    }
    return ind;
  }

  public static int numOfValues(ArrayList<Item> arr)
  {
    ArrayList<Item> difValues = new ArrayList<Item>();
    for(int x=0; x<arr.size(); x++)
    {
      if(!contains(difValues,arr.get(x))){
        difValues.add(arr.get(x));}
    }
    return difValues.size()-1;
  }

  public static ArrayList<Item> arrayForm(String word, int itemLen)
  {
    ArrayList<Item> arr = new ArrayList<Item>();
    while(word.length()%itemLen!=0){
      word+="A";}
    int pos1=0;
    int pos2=itemLen;
    int num=0;
    int ind;
    String substr;
    while(pos2<=word.length())
    {
      substr=word.substring(pos1,pos2);
      if(!containsValue(arr,substr))
      {
        arr.add(new Item(substr,num));
        num++;
      }
      else
      {
        ind=findIndex(arr,substr);
        arr.add(new Item(substr,ind));
      }
      pos1+=itemLen;
      pos2+=itemLen;
    }
    return arr;
  }

  public static Item permFind(ArrayList<Item> arr, Item item)
	{
		for(int x=(arr.size()-1); x>=0; x--)
		{
      if(item.equals(arr.get(x)))
      {
        if(arr.get(x+1).equals(new Item(" ",-1)))
        {
          for(int y=x; y>=0; y--)
          {
            if(arr.get(y).equals(new Item(" ",-1)))
            {
              item=arr.get(y+1);
              x=y;
              break;
            }
          }
        }
        else{
          item=arr.get(x+1);}
      }
		}
		return item;
	}

  public static Item permBackwards(ArrayList<Item> arr, Item item)
  {
    for(int x=(arr.size()-1); x>=0; x--)
    {
		  if(item.equals(arr.get(x)))
      {
        if(arr.get(x-1).getIndex()==-1)
        {
          for(int y=x; y<arr.size(); y++)
          {
            if(arr.get(y).getIndex()==-1)
            {
              item=arr.get(y-1);
              break;
            }
          }
        }
        else
        {
          item=arr.get(x-1);
          break;
        }
      }
    }
    return item;
  }

  public static boolean permFound(ArrayList<Item> arr, Item item)
	{
    int pos;
		for(int x=(arr.size()-1); x>=0; x--)
		{
		  if(arr.get(x).getIndex()==-1) 
		  {
				pos=x;
			  for(int y=pos; y<arr.size(); y++)
			  {
			    if(item.equals(arr.get(y))){
				    return true;}
				}
		  }
      if(arr.get(x).getIndex()==-1){
        break;}
	  }
	  return false;
	}

  public static String shiftEncrypt(String word, int itemLen)
  {
    char letter;
    String newWord="";
    for(int x=0; x<word.length(); x++)
    {
      letter=(char)((((int)word.charAt(x)+itemLen-32)%94)+32);
      newWord+=letter;
    }
    return newWord;
  }

  public static String shiftDecrypt(String word, int itemLen)
  {
    char letter;
    String newWord="";
    for(int x=0; x<word.length(); x++)
    {
      letter=(char)(((((int)word.charAt(x)-itemLen-32)%94+94)%94)+32);
      newWord+=letter;
    }
    return newWord;
  }

  public static ArrayList<Item> createPerms(String word, int itemLen)
  {
    ArrayList<Item> arr=arrayForm(word,itemLen);
    ArrayList<Item> perms = new ArrayList<Item>();
    perms.add(new Item(" ",-1));
    for(int x=0; x<arr.size(); x++)
    {
      if(permFound(perms,arr.get(x))) 
      {
        perms.add(new Item(" ",-1));
        perms.add(arr.get(x));
      }
      else{
        perms.add(arr.get(x));}
    }
    perms.add(new Item(" ",-1));
    return perms;
  }

  public static ArrayList<Item> permsProduct(ArrayList<Item> arr)
	{
    Item item=arr.get(1);
    ArrayList<Item> key = new ArrayList<Item>();
    key.add(new Item(" ",-1));
    key.add(item);
    for(int x=0; x<numOfValues(arr)-1; x++)
    {
      item=permFind(arr,item);
      if(contains(key,item))
      {
        key.add(new Item(" ",-1));
        for(int y=0; y<arr.size(); y++)
        {
          if(!contains(key,arr.get(y))){
            item=arr.get(y);}
        }
      }
      key.add(item);
    }
    key.add(new Item(" ",-1));
    return key;
	}

  public static String encrypt(String plaintext, ArrayList<Item> key, int itemLen)
  {
    String ciphertext="";
    ArrayList<Item> ptArr = arrayForm(plaintext,itemLen);
    System.out.print("Round "+itemLen+" plaintext: ");
    for(int x=0; x<ptArr.size(); x++)
    {
      System.out.print(ptArr.get(x).getValue());
      ciphertext+=permFind(key,ptArr.get(x)).getValue();
    }
    System.out.println();
    return ciphertext;
  }

  public static String decrypt(String ciphertext, ArrayList<Item> key, int itemLen)
  {
    String plaintext="";
    ArrayList<Item> ctArr = arrayForm(ciphertext,itemLen);
    System.out.print("Round "+itemLen+" ciphertext: ");
    for(int x=0; x<ctArr.size(); x++)
    {
      System.out.print(ctArr.get(x).getValue());
      plaintext+=(permBackwards(key,ctArr.get(x)).getValue());
    }
    System.out.println();
    return plaintext;
  }

  public static String removeExtras(String text)
  {
    String fixed="";
    ArrayList<String> textArr = new ArrayList<String>();
    for(int x=0; x<text.length(); x++){
      textArr.add(String.valueOf(text.charAt(x)));}
    int c=textArr.size()-1;
    while(textArr.get(c).equals("A"))
    {
      textArr.remove(c);
      c--;
    }
    for(int x=0; x<textArr.size(); x++){
      fixed+=textArr.get(x);}
    return fixed;
  }

  public static Item permEncrypt(String plaintext)
	{
    int len=plaintext.length();
    if(len==1)
    {
      Item ct = new Item(plaintext,1);
      return ct;
    }
    int itemLen=1;
    String ciphertext=plaintext;
    while(itemLen<=(len/2))
    {
      ArrayList<Item> perms=createPerms(ciphertext,itemLen);
      ArrayList<Item> key=permsProduct(perms);
      ciphertext=encrypt(ciphertext,key,itemLen);
      System.out.print("Round "+itemLen+" key: ");
      printPermArr(key);
      System.out.println("Round "+itemLen+" pre-shift: "+ciphertext);
      ciphertext=shiftEncrypt(ciphertext,itemLen);
      System.out.println("Round "+itemLen+" ciphertext: "+ciphertext);
      itemLen++;
    }
    System.out.println("Encrypted plaintext: "+ciphertext);
    Item ct = new Item(ciphertext,len);
    return ct;
  }

  public static String permDecrypt(Item ct)
  {
    if(ct.getIndex()==1){
      return ct.getValue();}
    String ciphertext=ct.getValue();
    int len=ct.getIndex();
    int itemLen=len/2;
    String plaintext=ciphertext;
    while(itemLen>=1)
    {
      ArrayList<Item> perms=createPerms(plaintext,itemLen);
      ArrayList<Item> key=permsProduct(perms);
      plaintext=decrypt(plaintext,key,itemLen);
      System.out.print("Round "+itemLen+" key: ");
      printPermArr(key);
      System.out.println("Round "+itemLen+" pre-shift: "+plaintext);
      plaintext=shiftDecrypt(plaintext,itemLen);
      plaintext=removeExtras(plaintext);
      System.out.println("Round "+itemLen+" plaintext: "+plaintext);
      itemLen--;
    }
    System.out.println("Decrypted plaintext: "+plaintext);
    return plaintext;
  }

  public static void NgramA()
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter message: ");
    String message=input.nextLine();
    String plaintext=format(message);
    System.out.println("\nEncryption: ");
    Item encrypted=permEncrypt(plaintext);
    System.out.println("\nDecryption: ");
    try{
    String decrypted=permDecrypt(encrypted);}
    catch(Exception e){
      System.out.println("oopsies!");}
  }
}