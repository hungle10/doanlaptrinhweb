<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thẻ thông báo</title>
  <style> 
.notification {
      background-color: #f2f2f2;
      border: 1px solid #ddd;
      padding: 10px;
      border-radius: 5px;
      margin-bottom: 10px;
    }
    body{
    background-color: #f4f7f6;
    margin-top:20px;
}
.card {
    background: #fff;
    transition: .5s;
    border: 0;
    margin-bottom: 30px;
    border-radius: .55rem;
    position: relative;
    width: 100%;
    box-shadow: 0 1px 2px 0 rgb(0 0 0 / 10%);
}
.chat-app .people-list {
    width: 280px;
    position: absolute;
    left: 0;
    top: 0;
    padding: 20px;
    z-index: 7
}

.chat-app .chat {
    margin-left: 280px;
    border-left: 1px solid #eaeaea
}

.people-list {
    -moz-transition: .5s;
    -o-transition: .5s;
    -webkit-transition: .5s;
    transition: .5s
}

.people-list .chat-list li {
    padding: 10px 15px;
    list-style: none;
    border-radius: 3px
}

.people-list .chat-list li:hover {
    background: #efefef;
    cursor: pointer
}

.people-list .chat-list li.active {
    background: #efefef
}

.people-list .chat-list li .name {
    font-size: 15px
}

.people-list .chat-list img {
    width: 45px;
    border-radius: 50%
}

.people-list img {
    float: left;
    border-radius: 50%
}

.people-list .about {
    float: left;
    padding-left: 8px
}

.people-list .status {
    color: #999;
    font-size: 13px
}

.chat .chat-header {
    padding: 15px 20px;
    border-bottom: 2px solid #f4f7f6
}

.chat .chat-header img {
    float: left;
    border-radius: 40px;
    width: 40px
}

.chat .chat-header .chat-about {
    float: left;
    padding-left: 10px
}

.chat .chat-history {
    padding: 20px;
    border-bottom: 2px solid #fff
}

.chat .chat-history ul {
    padding: 0
}

.chat .chat-history ul li {
    list-style: none;
    margin-bottom: 30px
}

.chat .chat-history ul li:last-child {
    margin-bottom: 0px
}

.chat .chat-history .message-data {
    margin-bottom: 15px
}

.chat .chat-history .message-data img {
    border-radius: 40px;
    width: 40px
}

.chat .chat-history .message-data-time {
    color: #434651;
    padding-left: 6px
}

.chat .chat-history .message {
    color: #444;
    padding: 18px 20px;
    line-height: 26px;
    font-size: 16px;
    border-radius: 7px;
    display: inline-block;
    position: relative
}

.chat .chat-history .message:after {
    bottom: 100%;
    left: 7%;
    border: solid transparent;
    content: " ";
    height: 0;
    width: 0;
    position: absolute;
    pointer-events: none;
    border-bottom-color: #fff;
    border-width: 10px;
    margin-left: -10px
}

.chat .chat-history .my-message {
    background: #efefef
}

.chat .chat-history .my-message:after {
    bottom: 100%;
    left: 30px;
    border: solid transparent;
    content: " ";
    height: 0;
    width: 0;
    position: absolute;
    pointer-events: none;
    border-bottom-color: #efefef;
    border-width: 10px;
    margin-left: -10px
}

.chat .chat-history .other-message {
    background: #e8f1f3;
    text-align: right
}

.chat .chat-history .other-message:after {
    border-bottom-color: #e8f1f3;
    left: 93%
}

.chat .chat-message {
    padding: 20px
}

.online,
.offline,
.me {
    margin-right: 2px;
    font-size: 8px;
    vertical-align: middle
}

.online {
    color: #86c541
}

.offline {
    color: #e47297
}

.me {
    color: #1d8ecd
}

.float-right {
    float: right
}

.clearfix:after {
    visibility: hidden;
    display: block;
    font-size: 0;
    content: " ";
    clear: both;
    height: 0
}
.chat-app .chat-list {
        height: 480px;
        overflow-x: auto
    }
    .chat-app .chat-history {
        height: calc(100vh - 350px);
        overflow-x: auto
    }    
  </style>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
</head>
<body>

<div class="container">
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
                <ul class="list-unstyled chat-list mt-2 mb-0">
                      
                </ul>
            </div>
           <div class="chat">
                <div class="chat-header clearfix"> <!-- cập nhật dựa vào controller -->
                    <div class="row">
                        <div class="col-lg-6">
                                <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="avatar">
                            </a>
                            <div class="chat-about">
                                <h6 class="m-b-0">Aiden Chavez</h6>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="chat-history">
    <!-- đầu tiên dùng hàm foreach lên lịch sử chat -->
    <ul class="m-b-0" id="chatHistory">
        <li class="clearfix">
            <div class="message other-message float-right"> <!-- người khác gửi --> </div>
        </li>
        <li class="clearfix">
            <div class="message my-message"> <!-- mình gửi --> </div>                                  
        </li>                               
    </ul>
</div>
                <div class="chat-message clearfix">
    <div class="input-group mb-0">
        <div class="input-group-prepend">
            <span class="input-group-text"><i class="fa fa-send"></i></span>
        </div>
        <!-- Form nhập tin nhắn -->
        <input type="text" id="toUser" placeholder="Enter receiver ID">
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
        var ws = new WebSocket("ws://localhost:8082/server.chat/chat1");
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

      	        // Lấy phần tử HTML để hiển thị danh sách người dùng
      	        var userListElement = document.querySelector(".chat-list");
      	        userListElement.innerHTML = ""; // Xóa danh sách hiện tại

      	        // Duyệt qua từng userID và tạo template
      	        users.forEach(function(userId) {
      	        	var user_id = userId;
      	            var userItem = document.createElement("li");
      	            userItem.classList.add("clearfix");
      	            
      	            // Thiết lập ảnh đại diện và tên người dùng
      	            userItem.innerHTML =
      	                '<img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="avatar">'+
      	                '<div class="about">'+
      	                    '<div class="name">'+ user_id+'</div>'+
      	                    '<div class="status"> <i class="fa fa-circle online"></i> online </div>'+                                            
      	                '</div>';

      	            userListElement.appendChild(userItem);
      	        }
      	        );
      	        
      	    } 
      	  else {
      	// Thêm tin nhắn vào lịch sử chat
          var chatHistory = document.getElementById("chatHistory");
          var newMessage = document.createElement("li");
          newMessage.classList.add("clearfix");
          newMessage.innerHTML = '<div class="message my-message">' + message + '</div>';
          chatHistory.appendChild(newMessage);
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

             // Thêm tin nhắn vào lịch sử chat (gửi)
             var chatHistory = document.getElementById("chatHistory");
             var newMessage = document.createElement("li");
             newMessage.classList.add("clearfix");
             newMessage.innerHTML = '<div class="message other-message float-right">' + message + '</div>';
             chatHistory.appendChild(newMessage);

             // Xóa ô nhập tin nhắn sau khi gửi
             document.getElementById("message").value = "";
        }
    </script>
   
</body>
</html>