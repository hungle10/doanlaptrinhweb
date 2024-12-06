package cuoiki.ltweb.websocket;



import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.websocket.*;
@ServerEndpoint("/chat1")
public class ChatWebSocket {
	// Map lưu trữ session của người dùng (userID -> session)
	private static Map<String,Session> userSessions = new ConcurrentHashMap<>();
	  // Executor để lên lịch gọi hàm broadcastUserList
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Khởi tạo task định kỳ
    static {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                broadcastUserList();
            } catch (Exception e) {
                System.err.println("Error during broadcast: " + e.getMessage());
                e.printStackTrace();
            }
        }, 0, 5, TimeUnit.SECONDS); // Chạy ngay lập tức và lặp lại mỗi 5 giây
    }

	@OnOpen
	public void onOpen(Session session) {
        System.out.println("Connect success");
		}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		if (message.startsWith("register|")) {
            String userId = message.split("\\|")[1];
            session.getUserProperties().put("userID", userId);
            userSessions.put(userId, session);
            System.out.println("User registered with ID: " + userId);
            WebSocketManager.addSession(userId,session);
            // Cập nhật và gửi danh sách user tới tất cả các client
            broadcastUserList();
            return;
        }
		
	
		// Giả sử message có format: "userID_from|userID_to|message"
		String[] messageParts = message.split("\\|");
		 String senderId = (String) session.getUserProperties().get("userID");
		String receiverId = messageParts[1];
		String chatMessage = messageParts[2];

		// Tìm session của người nhận
	    Session recipientSession = WebSocketManager.getSession(receiverId);
	    Session sendSession = WebSocketManager.getSession(senderId);
	    if (recipientSession != null) {
	        // Gửi tin nhắn tới người nhận
	        recipientSession.getAsyncRemote().sendText(senderId  + "|" + chatMessage);
	    } else {
	        // Người nhận không trực tuyến
	    //	sendSession.getAsyncRemote().sendText(receiverId  + "|" + "Người dùng hiện chưa online");
	    }
	}

	@OnClose
	public void onClose(Session session) {
		String userId = (String) session.getUserProperties().get("userID");
        if (userId != null) {
            userSessions.remove(userId);
            
            System.out.println("User disconnected with ID: " + userId);

            // Cập nhật và gửi danh sách user sau khi user ngắt kết nốin
           // broadcastUserList();
            WebSocketManager.removeSession(userId);
        }
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
	    System.out.println("Error on session " + session.getId() + ": " + throwable.getMessage());
	    throwable.printStackTrace(); // Ghi lại stack trace của lỗi
	}
	// Hàm để gửi danh sách user tới tất cả các client
	static synchronized void broadcastUserList() {
        String userList = "userList|" + String.join(",", userSessions.keySet());
        for (Session session : userSessions.values()) {
            session.getAsyncRemote().sendText(userList);
        }
    }
}

