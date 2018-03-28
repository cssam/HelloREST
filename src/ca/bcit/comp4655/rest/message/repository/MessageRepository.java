package ca.bcit.comp4655.rest.message.repository;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository
{
	static List<String> messages;
	static 
	{
		messages = new ArrayList<String>();
		messages.add( "Message One" );
		messages.add( "Message Two" );
		messages.add( "Message Tree" );
		messages.add( "Message Four" );
		messages.add( "Message Five" );
		messages.add( "Message Six" );
		messages.add( "Message Seven" );
	}
	
	public static List<String> getMessages()
	{
		return messages;
	}
}
