package ca.bcit.comp4655.rest.webservices;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import ca.bcit.comp4655.rest.bean.Message;
import ca.bcit.comp4655.rest.message.repository.MessageRepository;

// POJO, no interface no extends

//Sets the path to base URL + /hello
@Path("/hello")
public class HelloWorldResource
{

	@Context 
	UriInfo uriInfo;
	


	@GET
	@Path( "/{userName}" )
	@Produces( MediaType.TEXT_HTML )
	public String sayHelloUser( @PathParam("userName" ) String userName ) 
	{
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		Set<String> keys = params.keySet();
		for ( String key: keys )
		{
			System.out.println ( key + ":" + params.get(key));
		}
		return "Hello " + userName;
	}
	
	@GET
	@Path( "/{userName}/{firstName:[a-z]*}" )
	@Produces( MediaType.TEXT_HTML )
	public String sayHelloToUserFirstName( @PathParam("userName" ) String userName, @PathParam("firstName")String firstName) 
	{
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		Set<String> keys = params.keySet();
		for ( String key: keys )
		{
			System.out.println ( key + ":" + params.get(key));
		}
		return "Hello " + userName + ". Your first Name is " + firstName;
	}
	
	// This method is called if TEXT_PLAIN is requested
	@GET
	@Produces ( MediaType.TEXT_PLAIN )
	public String sayPlainTextHello() 
	{
		return "Hello REST (text)";
	}

	// This method is called if XML is requested
	@GET
	@Produces( MediaType.TEXT_XML )
	public Message sayXMLHello()
	{
		Message msg = new Message();
		msg.setContent("Hello REST(XML)" );
		msg.setId( "12345" ); 
		return msg;
	}

	// This method is called if HTML is requested
	@GET
	@Produces( MediaType.TEXT_HTML )
	public String sayHtmlHello() 
	{
		return "<html> " + "<title>" + "Hello REST" + "</title>"
				+ "<body><h1>" + "Hello REST (HTML)" + "</body></h1>" + "</html>";
	}
	
	@PUT
	@Path( "/addMessage/{newMessage}" )
	public void addMessage()
	{
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String newMessage = params.getFirst("newMessage");
		
		boolean success = MessageRepository.getMessages().add( newMessage );
		System.out.println ( newMessage + " added? " + success );
	}
	
	@POST
	@Path( "/messageUpdate/{message}" )
	@Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
	@Produces ( MediaType.TEXT_XML )
	public Message updateMessage ( @FormParam( "message" ) String oldMessage )
	{
		
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String newMessage = params.getFirst("message");
		
		
		List<String> list =MessageRepository.getMessages(); 
		String updatedMesssage = null;
		
		if (list.contains( oldMessage ) )
		{
			updatedMesssage = list.set(list.indexOf(oldMessage),newMessage);
		}
		Message response = new Message();
		if( oldMessage.equals( updatedMesssage ) )
		{
			response.setContent( oldMessage + " was successfuly replaced by " + newMessage );
		}
		else
		{
			response.setContent( oldMessage + " was NOT replaced by " + newMessage );
		}
		
		return response;
	}
	
	
	
	@DELETE
	@Path( "/messageDelete/{message}" )
	public void deleteMessge ()
	{	
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String messageToDelete = params.getFirst("message");
		int index = MessageRepository.getMessages().indexOf( messageToDelete );
		String messageDeleted=null;
		if ( index>=0 )
		{
			messageDeleted = MessageRepository.getMessages().remove(index);
		}
		if ( messageDeleted!=null )
		{
			System.out.println( "Message " + messageDeleted + " was removed. " );
		}
		else
		{
			System.out.println( "Message " + messageToDelete + " was NOT found" );
		}
		
	}
	
}
