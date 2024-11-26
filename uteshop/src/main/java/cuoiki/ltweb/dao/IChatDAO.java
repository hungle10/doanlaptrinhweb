package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.ChatModel;

public interface IChatDAO {

	List<ChatModel> getChatListBySenderAndReceiver(long senderid, long receiverid);

	boolean addToChatInDB(ChatModel chat);

	List<ChatModel> getChatList();

}
