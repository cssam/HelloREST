package ca.bcit.comp4655.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message 
{
	String id;
	String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
