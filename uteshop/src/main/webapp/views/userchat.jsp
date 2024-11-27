<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thẻ thông báo</title>
<%@include file="/Components/common_css_js.jsp"%>
  <style> 
  <%@include file="/CSS/chat.css"%>
  </style>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
</head>
<body>
<!--navbar -->
<%@include file="/Components/navbar.jsp"%>

<script>
function goToUserController(userId) {
	// Mở controller với userId trong cùng tab
	window.open('/uteshop/user/chat/refresh?useridReceiver=' + userId, '_self');
}
    window.onload = function() {
        <c:if test="${not empty vendorid}">
            goToUserController(${vendorid});
        </c:if>
    }
</script>

<div class="container-fluid px-3 py-3">
<div class="row clearfix">
    <div class="col-lg-12">
        <div class="card chat-app">
            <div id="plist" class="people-list">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-search"></i></span>
                    </div>
                    <input type="text" class="form-control" placeholder="Search...">
                </div>
                <ul class="list-unstyled chat-list mt-2 mb-0" style="max-height: 250px; overflow-y: auto;">
                      <c:forEach var="user" items="${user_list}">
        <li class="clearfix user-item-${user.id}" onclick="goToUserController(${user.id})" style="cursor: pointer;">
            <img src="/uteshop/Images/${user.image}" alt="avatar">
            <div class="about">
                <div class="name">${user.username}</div>
                <div class="status">
                    <i class="fa fa-circle offline"></i>
                    offline
                </div>
            </div>
        </li>
    </c:forEach>
         
                </ul>
            </div>
           <div class="chat">
           
                <div class="chat-header clearfix" data-user-id="<c:if test='${not empty user_receiver}'>${user_receiver.id}</c:if>">

                <c:if test="${not empty user_receiver}">
                    <div class="row">
                        <div class="col-lg-6">
                                <img src="/uteshop/Images/${user_receiver.image}" alt="avatar">
    
                            <div class="chat-about">
                                <h6 class="m-b-0 username">${user_receiver.username}</h6>
                            </div>
                        </div>
                    </div>
                    </c:if>
                </div>
            
                <div class="chat-history">
    <!-- đầu tiên dùng hàm foreach lên lịch sử chat -->
    <ul class="m-b-0" id="chatHistory">
    <!-- code phần code tin nhắn trong database lên đây -->
    <!-- phần chat sẽ được js generate ở đây -->
                 <c:forEach var="chat" items="${chat_list}">
    
             <c:forEach var="chat_mine" items="${chat_list_opposite}">
                   <c:if test="${chat_mine.chatId == chat.chatId}">
           
            <li class="clearfix">
            <div class="message my-message">${chat_mine.message_content}</div>                                  
        </li>  
        </c:if>
             </c:forEach>
             <c:forEach var="chat_opp" items="${chat_list_mine}">
              <c:if test="${chat_opp.chatId == chat.chatId}">
           <li class="clearfix">
                <div class="message other-message float-right">${chat_opp.message_content}</div>
            </li> 
               </c:if>
        </c:forEach>    
        </c:forEach>                     
    </ul>
</div>
                <div class="chat-message clearfix">
    <div class="input-group mb-0">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-send"></i></span>
        </div>
        <!-- Form nhập tin nhắn -->
        <input type="text" id="toUser" placeholder="Enter receiver ID" value="${user_receiver.id}" readonly style="display: none;">
        <input type="text" id="message" placeholder="Enter your message">
        <button onclick="sendMessage()">Send</button>                                  
    </div>
</div>
            </div>
        </div>
    </div>
</div>
</div>

    <script>
        // Kết nối WebSocket tới server
        var ws = new WebSocket("ws://localhost:8082/uteshop/chat1");
        // Xử lý khi nhận tin nhắn từ server
            ws.onopen = function(event) {
            	 var userId = "${requestScope.userid}"; // ID của user đang kết nối
            	 ws.send("register|" + userId);
            	 alert("Kết nối WebSocket thành công!");
           };
        ws.onmessage = function(event) {
        	var message = event.data;
      	  if (message.startsWith("userList|")) {
      	        // Xử lý danh sách người dùng
      	        var userListData = message.substring("userList|".length); // Loại bỏ tiền tố "userList|"
      	        var users = userListData.split(","); // Tách các userID
      	   	     console.log(users); // Kiểm tra từng userId
      	   	// Chuyển tất cả người dùng thành offline trước
      	       document.querySelectorAll(".chat-list .user-item").forEach(function(item) {
      	           var statusElement = item.querySelector('.status');
      	           if (statusElement) {
      	               statusElement.innerHTML = '<i class="fa fa-circle offline"></i> offline';
      	           }
      	       });

      	        // Lấy phần tử HTML để hiển thị danh sách người dùng
      	      //  var userListElement = document.querySelector(".chat-list");
      	     //   userListElement.innerHTML = ""; // Xóa danh sách hiện tại

      	        // Duyệt qua từng userID và tạo template
      	        users.forEach(function(userId) {
      	        	var user_id = userId;
      	            var userItem = document.createElement("li");
      	            userItem.classList.add("clearfix");
      	       // Gọi API để lấy thông tin người dùng
      	          fetch('/uteshop/v1/api/getUserInfo?user_id='+ user_id)
      	              .then(response => response.json())
      	              .then(data => {
      	                  if (data.error) {
      	                      console.error("User not found:", userId);
      	                      return;
      	                        }
      	                var userItem = document.querySelector('.user-item-'+userId);
      	              var statusElement = userItem.querySelector('.status');
                      if (statusElement) {
                          statusElement.innerHTML = '<i class="fa fa-circle online"></i> online';
                      }
      	                          })
      	        });
      	        
      	    } 
      	  else {
      	// Thêm tin nhắn vào lịch sử chat
        //  var chatHistory = document.getElementById("chatHistory");
        //  var newMessage = document.createElement("li");
        //  newMessage.classList.add("clearfix");
        //  newMessage.innerHTML = '<div class="message my-message">' + message + '</div>';
       //   chatHistory.appendChild(newMessage);
                const messageData = event.data.split("|");
               const senderId = messageData[0];
              const chatMessage = messageData[1];
      		  handleIncomingMessage(senderId, chatMessage);
      	  }
        };
        // Xử lý khi kết nối bị đóng
        ws.onclose = function(event) {
            console.log("Kết nối WebSocket đã đóng.");
            alert("Kết nối WebSocket đã bị đóng!");
        };

        // Hàm gửi tin nhắn tới server
        function sendMessage() {
        	 var toUser = document.getElementById("toUser").value;
             var message = document.getElementById("message").value;
             var userId = "${requestScope.userid}";  // ID của user đang kết nối (có thể lấy từ phiên đăng nhập)

             // Gửi tin nhắn theo format: "userID_from|userID_to|message"
             ws.send(userId + "|" + toUser + "|" + message);
             
             // Gửi tin nhắn qua servlet
             fetch("/uteshop/user/chat/saveMessage", { // URL phù hợp với mapping trong servlet
                 method: "POST",
                 headers: {
                     "Content-Type": "application/json"
                 },
                 body: JSON.stringify({
                     userId: userId,
                     toUser: toUser,
                     message: message
                 })
             })
             .then(response => {
                 if (!response.ok) {
                     throw new Error("Có lỗi xảy ra khi gửi tin nhắn!");
                 }
                 return response.json();
             })
           

             // Thêm tin nhắn vào lịch sử chat (gửi)
             var chatHistory = document.getElementById("chatHistory");
             var newMessage = document.createElement("li");
             newMessage.classList.add("clearfix");
             newMessage.innerHTML = '<div class="message other-message float-right">' + message + '</div>';
             chatHistory.appendChild(newMessage);

             // Xóa ô nhập tin nhắn sau khi gửi
             document.getElementById("message").value = "";
        }
       
        function handleIncomingMessage(senderId, message) {
            // Kiểm tra nếu `chat-header` không hiển thị thông tin của `senderId` hoặc cần cập nhật
            const userId = document.querySelector('.chat-header').getAttribute('data-user-id');
            if (userId && userId === senderId) {
                fetch('/uteshop/v1/api/getUserInfo?user_id='+senderId)
                    .then(response => response.json())
                    .then(data => {
                        if (data.error) {
                            console.error("User not found:", senderId);
                            return;
                        }

                        // Cập nhật `chat-header` với thông tin người gửi
                        const chatHeader = document.querySelector('.chat-header');
                        chatHeader.innerHTML =
                            '<div class="row">'+
                                '<div class="col-lg-6">'+
                                    '<img src="/uteshop/Images/' + data.image + '" alt="avatar">'+
                                    '<div class="chat-about">'+
                                        '<h6 class="m-b-0 username">' + data.name + '</h6>'+
                                    '</div>'+
                                '</div>'+
                            '</div>';
                        updateToUserInput(senderId);
                    });
                // Thêm tin nhắn vào `chatHistory`
                updateToUserInput(senderId);
                var chatHistory = document.getElementById("chatHistory");
                var newMessage = document.createElement("li");
                newMessage.classList.add("clearfix");
                newMessage.innerHTML = '<div class="message my-message">'+message+'</div>';
                chatHistory.appendChild(newMessage);
            }
            else{
            	
            }

          
        }
        function updateToUserInput(senderId) {
            const toUserInput = document.getElementById("toUser");
            if (toUserInput && toUserInput.value !== senderId) {
                toUserInput.value = senderId;  // Cập nhật giá trị `senderId`
            }
        }
    </script>
   
</body>
</html>