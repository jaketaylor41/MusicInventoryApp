package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseDataModel extends ResponseModel {

	private Album data;
	
	public ResponseDataModel()
	{
		super(0, "");
		this.data = null;
	}
	
	public ResponseDataModel(int status, String message, Album data) {
		super(status, message);
		this.data = data; 
	}

	public Album getData() 
	{
		return data;
	}

	public void setData(Album data) 
	{
		this.data = data;
	}	
}
