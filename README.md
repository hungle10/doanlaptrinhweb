# Đồ án lập trình web , xây dựng website bán hàng UTESHOP sử dụng Servlet,JDBC,JSP/JSTL,Decorator sitemesh, Websocket (Shopping website)
## About:
Mã số sinh viên : 22110154
### Công nghệ sử dụng cho đồ án:-
1. Front-End Development:
- HTML
- CSS
- Javascript/jQuery
- BootStrap
- Decorator sitemesh

2. Back-End Development:
- Java 22
-	JDBC (Java Database Connectivity)
-	Servlet
-	JSP/JSTL (JavaServer Pages)

3. Database:
- SQL Server 2019
- Xem chi tiết database trong fileword báo cáo

4. Software and Tools
- Spring tools
- Tomcat 10.1
- SQL Server Management Studio 2019

5. Bussiness :
- Thanh toán bằng cổng paypal, môi trường sandbox thử nghiệm

###  Jar files used :
Xem file pom.xml trong dự án

###  Đây là bản đồ thực thể trong database : 
![image](https://github.com/user-attachments/assets/8a141a85-b64c-41be-808c-70a8e17b4e00)

### Giao diện bên phía người bán
#### Quản lí cửa hàng
Để truy cập vào trang quản lý của hàng, tài khoản user phải đăng kí tạo shop bằng cách nhấn vào Register shop trên thanh navbar
![image](https://github.com/user-attachments/assets/da326626-df98-4f5a-bbfd-a503ef959475)
![image](https://github.com/user-attachments/assets/067d94f3-a22e-4310-b154-3736cd9bc454)
Chức năng này cho phép người dùng nhập các thông tin của cửa hàng đang sở hữu sau đó nhấn vào nút submit thì server sẽ kiểm tra dữ liệu từ người dùng sao đó cập nhật xuống database, và gửi email để user biết rằng shop mình đã được tạo.
Khi shop chưa được duyệt lúc này trạng thái của shop sẽ là pending 
![image](https://github.com/user-attachments/assets/7ae103bb-a3e9-4d2b-bd4b-2ba8d4cd1877)
Sau khi được duyệt bởi admin . Hình ví dụ
![image](https://github.com/user-attachments/assets/3da7b927-cd3b-4e13-8371-d8f66a6af855)
Sau khi được duyệt người bán sẽ nhấn được vào Watch more detail.  
Trang quản lí thông tin shop. Người bán có thể cập nhật thông tin của shop.
![image](https://github.com/user-attachments/assets/4f6ee497-de51-4f38-8da8-bff9d0ee0460)
#### Quản lí sản phẩm :
Người bán có thể xem , cập nhật , xóa sản phẩm của người bán
![image](https://github.com/user-attachments/assets/aa4a2c60-6516-48c3-b5ce-0840f62119c9)
Thêm sản phẩm
![image](https://github.com/user-attachments/assets/dcb99e43-e47b-46ec-b10a-3a1c70a23817)
#### Quản lí đơn hàng :
Trang quản lí các đơn hàng của shop 
Shop sẽ cập nhật đơn hàng theo 2 trạng thái pending , Order Confirmed ( order đã được shop chấp nhận), Refuned ( đã hoàn trả tiền cho người mua)
![image](https://github.com/user-attachments/assets/06ad4038-beee-4e19-85f1-ef4bb81c43db)
#### Thống kê
Thống kê doanh thu theo ngày của Shop theo biểu đồ đường sử dụng thư viện https://cdn.jsdelivr.net/npm/chart.js
![image](https://github.com/user-attachments/assets/4c0a9942-4af1-4f48-a9f1-f51f383dd323)
Hàm getDailyRevenue trả về một List kiểu Map để convert sang json ở Controller để gửi dữ liệu sang giao diện biểu đồ . Những đơn được tính doanh thu cho shop sẽ là nhưng đơn đã được giao “Delivered”,
hoặc những đơn người mua hủy yêu cầu trả hàng. 
![image](https://github.com/user-attachments/assets/db4177a6-1348-4270-8923-7295dee28ee2)
Đây là API để convert dữ liệu sang json để trả cho giao diện sử dụng
![image](https://github.com/user-attachments/assets/dcd17942-6938-46cb-b9e2-cd21a55c5bc2)
Đây là phần giao diện thống kê
![image](https://github.com/user-attachments/assets/3f69ef70-cc78-449a-959f-790ec1afa4d6)
### Giao diện bên phía admin
#### Trang chủ của admin
Ở trang chủ của admin, admin có thể quản lí doanh mục(Category) , quản lí sản phẩm(Product), quản lí các đơn đặt hàng(Order), quản lí người dùng trong hệ thống (User), quản lí đơn vị vận chuyển(Shipping Unit), quản lí các cửa hàng trong hệ thống(Shops)
![image](https://github.com/user-attachments/assets/597ac6fb-f076-44b3-bab3-6cf9e72be00b)
#### Quản lí người dùng trong hệ thống
Admin có thể tìm kiếm , xóa , cập nhật trạng thái của tài khoản 
Khóa tài khoản hoặc cho tài khoản hoạt động
![image](https://github.com/user-attachments/assets/1c95bdc4-0df1-4b12-9bca-f109351a65bf)
#### Quản lí cửa hàng trong hệ thống
Nếu admin pending một shop ,người bán sẽ không sử dụng được shop của họ ,các sản phẩm trong shop đó sẽ không được show ra trên hệ thống
![image](https://github.com/user-attachments/assets/8b000c65-50b8-43b5-9b76-0df61397215c)
#### Quản lí loại sản phẩm trong hệ thống
Đây là trang quản lí doanh mục hoặc có thể gọi là loại sản phẩm trong shop. Admin có thể cập nhật thông tin của doanh mục , xóa doanh mục , thêm doanh mục 
![image](https://github.com/user-attachments/assets/f328cb71-9fca-4ae3-94d0-7e3877033d29)
Thêm loại sản phẩm trong hệ thống
![image](https://github.com/user-attachments/assets/a1d9d68c-8a5b-4a99-8312-6c03d1af4ed1)
#### Quản lí sản phẩm trong hệ thống
Admin có thể xem và xóa các sản phẩm trong hệ thống
![image](https://github.com/user-attachments/assets/43103a1d-eeba-4232-9462-09c90691f1eb)
#### Quản lí đơn hàng trong hệ thống
Admin có thể xem , xóa ,cập nhật trạng thái cho đơn hàng trong hệ thống.
Vì đồ án không có phần người giao hàng , nên admin sẽ kiêm luôn vai trò người giao hàng.
Ở đây sẽ có phần cập nhật tình trạng trả tiền cho việc COD , cash on delivery
![image](https://github.com/user-attachments/assets/3814cdc2-443e-4f00-8847-cd7530a37c03)
#### Quản lí đơn vị vận chuyển
Đây là trang admin sẽ quản lí đơn vị vận chuyển. Thêm, xóa, sửa đơn vị vận chuyển.
![image](https://github.com/user-attachments/assets/e93c3a78-f4b2-40e1-a82c-cfd3eecc66e8)
Chỉnh sửa đơn vị vận chuyển
![image](https://github.com/user-attachments/assets/ebfe85c4-1e44-4db6-be31-c308ad365fbe)
Thêm đơn vị vận chuyển
![image](https://github.com/user-attachments/assets/2a735721-0807-44e1-9f03-fea7395da899)
### Giao diện Customer (Khách hàng)
#### Trang chủ 
Ở đây sẽ có hai tình huống Guest ( khách hàng chưa đăng nhập vào hệ thống ) , User (Khách hàng đã đăng nhập vào hệ thống).
Về cơ bản trang chủ ( index.jsp) của Guest và User sẽ giống nhau. Nhưng User khi bấm vào Product trên thanh navbar sẽ xem được thêm nhiều sản phẩm. Còn Guest sẽ chỉ xem được những sản phẩm được show trên index.jsp
![image](https://github.com/user-attachments/assets/ac5e0a39-d4e3-45f1-ab60-7edf03d25298)
![image](https://github.com/user-attachments/assets/38c9c27c-5364-4913-996c-9dc688168425)
Top sản phẩm hot nhất sẽ là những sản phẩm bán được trên 10 sản phâm của các shop được sắp xếp từ lớn đến nhỏ. Đây là hàm tính
![image](https://github.com/user-attachments/assets/ce0daefc-f87f-467f-b7da-2b7a0b4083b7)
#### Xem sản phẩm
Tính năng xem sản phẩm sẽ giúp cho khách hàng có thể hiểu rõ về những đặc tính ,cũng như quan sát được sản phẩm một cách rõ ràng hơn. Có phân trang giúp tối ưu performance của website , 20 sản phẩm trên 1 trang.
Guest sẽ không xem được các sản phẩm trong trang này.
![image](https://github.com/user-attachments/assets/e7df9dbc-d828-48da-917a-cd4bafdcfb39)
![image](https://github.com/user-attachments/assets/63b1d50b-f9a0-41cb-b716-321d0046ff23)
![image](https://github.com/user-attachments/assets/a8c2a732-11ad-4d8e-a980-e743f63b86a0)
![image](https://github.com/user-attachments/assets/0500e85a-8710-401f-9ce3-07c31f046349)
Xem bình luận về sản phẩm của các user khác, user có thể tham gia bình luận sản phẩm. Hạn chế ở đồ án của nhóm là bình luận chỉ được 1 video và 1 ảnh. 1 bình luận không thể trên 1 ảnh hoặc 1 video. 
Hạn chế tiếp theo là nhóm không làm chức năng đánh giá rating điểm cho sản phẩm.
![image](https://github.com/user-attachments/assets/cc348068-3761-45cd-bdf2-b5fa12b9e644)
Nếu Guest đi vào xem chi tiết những sản phẩm được thấy trên trang index.jsp  sẽ thấy được như này, nhưng không có chức năng thêm hàng ,đặt hàng
![image](https://github.com/user-attachments/assets/24c05a5d-0424-478e-a7cd-4397341834f6)
#### Xem cửa hàng
Với tính năng xem cửa hàng ,khách hàng có thể tìm hiểu và xem những sản phẩm mà doanh nghiệp hoặc người bán đã đăng lên trên cửa hàng online của họ.
![image](https://github.com/user-attachments/assets/a2e809ba-cee9-4294-8368-721fb7fea81d)
#### Xem giỏ hàng
Khi người dùng xem và thích một sản phẩm nào đó,họ có thể thêm sản phẩm mà mình yêu thích vào giỏ hàng .Nút thanh toán(check out) có chức năng chuyển tới các thao tác thanh toán nếu người dùng muốn mua sản phẩm đó. 
Người dùng có thể điều chỉnh số lượng theo ý muốn, xóa sản phẩm ra khỏi giỏ hàng.
![image](https://github.com/user-attachments/assets/0d06a3ce-cf70-40e0-ad0f-a27c7053317c)
#### Thông tin người dùng và địa chỉ
Bất kì một người dùng nào khi đăng kí tài khoản trên trang đều sẽ phải cung cấp những thông tin cá nhân như tên ,tuổi,số điện thoại ,email,….Form user information còn cho phép người dùng chỉnh sửa thông tin cá nhân của họ.
![image](https://github.com/user-attachments/assets/6231f4d3-a8f5-4ef4-bb76-ea31128a96b7)
#### Xem sản phẩm yêu thích
Người dùng có thể xóa sản phẩm ra khỏi mục yêu thích 
Product được yêu thích sẽ có trái tim màu đỏ. Những sản phẩm không thích sẽ có trái tim màu xám.
![image](https://github.com/user-attachments/assets/2c78d11d-95f8-4163-809b-c32f5b66139b)
Có thể thêm xóa bằng cách nhấn vào trái tim, hoặc đi vào phần My Wishlist để điều chỉnh.
![image](https://github.com/user-attachments/assets/63dc764e-8c15-473d-9b3b-080621b343eb)
#### Quên mật khẩu
Khi người dùng bấm quên mật khẩu ,trang web sẽ hiện ra 1 trang quên mật khẩu để giúp cho người dùng tạo mật khẩu mới chỉ với email và tìm lại tài khoản khi quên .
![image](https://github.com/user-attachments/assets/4d7d6ae2-8049-4164-b3e7-5ebc29a26ec3)
![image](https://github.com/user-attachments/assets/913d1af2-fce9-47b0-baa5-086c66933bd3)
Nhập otp đã được gửi qua email
![image](https://github.com/user-attachments/assets/41d1e003-ff14-4758-b77f-2b23fa9079f6)
![image](https://github.com/user-attachments/assets/05e25ebd-6b49-497b-a436-3139a0712a48)
Sau khi nhập đúng OTP
![image](https://github.com/user-attachments/assets/b0949e7a-0c9b-4cbe-ab4a-d94c14ec7378)
#### Đặt hàng
Trang đặt hàng giúp cho người dùng xác định các hình thức thanh toán và đặt những món hàng mà mình đã lựa chọn vào giỏ hàng(cart) khi nhấn nút đặt hàng. Có thể lựa chọn nhà vận chuyển. 
Hạn chế ở đây giá vận chuyển bị cố định chứ không tính theo số kilomet vận chuyển như trên các nền tảng bán hàng khác.
![image](https://github.com/user-attachments/assets/1bc34941-de61-44a3-a52e-8ab8e57032cd)
Nếu người dụng chọn Credit web sẽ chuyển hướng sang trang thanh toán bằng paypal sau khi thanh toán xong đơn sẽ được cập nhật là paid . 
Nếu chọn COD (Cash on delivery) tình trạn của đơn hàng là unpaid , khi giao hàng tới và người dùng đưa tiền admin sẽ cập nhật đơn là paid. Sử dụng môi trường thanh toán sandbox.
![image](https://github.com/user-attachments/assets/d86abb1f-3275-4ab9-b877-c2efffca3bb8)
Chọn thanh toán bằng thẻ , trường hợp nếu người mua có ví paypal thì người mua sẽ nhấn đăng nhập vào paypal. 
Chọn thẻ ngân hàng fake để test
![image](https://github.com/user-attachments/assets/e2b398df-49ca-4c6f-9c26-4f61c66e6b7f)
Các thông số và hàm cần thiết để thực hiện thanh toán bằng ví điện tử 
![image](https://github.com/user-attachments/assets/f7d362e6-29b6-482d-af49-8c1c74e5be22)
![image](https://github.com/user-attachments/assets/2fd7d183-0ab6-42f2-bfd8-f08de5519980)
Sau khi đặt hàng thành công. Sẽ có email gửi đến cho người dùng.
![image](https://github.com/user-attachments/assets/beffcd02-319f-4588-9571-6dd7a14c9a14)
![image](https://github.com/user-attachments/assets/c548b1c9-eeb8-4712-9e87-8b1fd75db5b0)
Kiểm tra trên paypal để coi đơn đã thực sự được thanh toán chưa. Do paypal không hỗ trợ tiền việt nên nhóm đã chuyển tiền việt sang usd dựa theo tỉ giá mới nhất do paypal cung cấp.
![image](https://github.com/user-attachments/assets/9d706015-559c-4869-8e09-b9cb74d704e0)
![image](https://github.com/user-attachments/assets/ce2ee607-cd75-4a91-ba66-61ca64e9779a)
User xem đơn đặt hàng họ đã đặt
User có thể cập nhật trạng thái đơn như :
-Returned : user muốn trả hàng
-Cancel Returned : user hủy mong muốn trả hàng
-Cancel : user hủy đơn đặt hàng nhưng chỉ khi đơn đang chờ người bán duyệt (Pending). Một khi đã nhấn cancel thì đơn được hủy và user sẽ không thể cập nhật gì cho đơn đó nữa.
![image](https://github.com/user-attachments/assets/ddd44bb1-094c-401b-984d-a9917884bc30)
#### Nhắn tin với người bán
Sử dụng thư viện jakatar websocket để hỗ trợ chat real time
Nhấn nút chat, hệ thống sẽ điều hướng bạn đến chủ của shop này( user sở hữu shop này)
![image](https://github.com/user-attachments/assets/8863fcd3-3660-48b6-ab0f-7ce08d11c634)
![image](https://github.com/user-attachments/assets/94e10dc4-3d55-464f-a11d-751acfc85c56)
Hàm xử lí các tin nhắn trong class websocket 
![image](https://github.com/user-attachments/assets/580194d1-f577-4b63-9b3b-5e0dccc4d856)
Kết nối với giao thức websocket . chọn localhost:8082 vì web server của nhóm chạy trên port 8082. Chọn đúng endpoint /uteshop/chat1 
![image](https://github.com/user-attachments/assets/07e6ae9e-ceef-4d9d-be77-bc29b0f68906)
![image](https://github.com/user-attachments/assets/9a60246e-2b0e-4182-b776-7c136a3c6780)













