package cuoiki.ltweb.impl;

import java.util.List;

import cuoiki.ltweb.dao.IChatDAO;
import cuoiki.ltweb.models.ChatModel;
import cuoiki.ltweb.services.IChatService;


public class IChatServiceImpl implements IChatService{
   IChatDAO chatdao = new ChatDAOImpl();
   
	@Override
	public List<ChatModel> getChatListBySenderAndReceiver(long senderid,long receiverid) {
		return chatdao.getChatListBySenderAndReceiver(senderid, receiverid);
	}
	@Override
	public boolean addToChatInDB(ChatModel chat) {
		return chatdao.addToChatInDB(chat);
	}
	@Override
	public List<ChatModel> getChatList(){
		return chatdao.getChatList();
	}


}