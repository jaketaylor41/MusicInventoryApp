package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Track {

	private String title;
	
	private int number;
	
	public Track()
	{
		this.title = "Test Title";
		this.number = 1;
	}
	
	public Track(String title, int number) 
	{
		this.title = title;
		this.number = number;
	}
	
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public int getNumber() 
	{
		return number;
	}
	public void setNumber(int number) 
	{
		this.number = number;
	}
	
	
}
