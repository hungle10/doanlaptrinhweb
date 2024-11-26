package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.ChatModel;

public interface IChatService {

	public List<ChatModel> getChatListBySenderAndReceiver(long senderid, long receiverid);

	public boolean addToChatInDB(ChatModel chat);

	List<ChatModel> getChatList();
	

}
