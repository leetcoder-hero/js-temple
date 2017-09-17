package package1;

import java.io.Serializable;


public class User implements Serializable
{
	  private static final long serialVersionUID = 5726374138698742258L;  

  

String name=null;
String vary=null;
  public User(String name, String vary)
  {
  
    
  
     this.name=name;
      this.vary=vary;
  }
  public String getUsername() {
 
	  return  name;
  }

  public void setUsername(String name) {
      
	  this.name=name;
	  
  }
  public String getVary()
  {
  	return vary;
  }
  public void setVary(String vary)
  {
	   
		  this.vary=vary;
  }


}