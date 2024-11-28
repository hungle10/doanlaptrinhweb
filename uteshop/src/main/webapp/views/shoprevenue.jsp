
 <div class="container-fluid px-3 py-3">
 <canvas id="revenueChart" width="400" height="200"></canvas>

 </div>
  <script>
        // Lấy shopId từ requestScope và gán vào biến JavaScript
      const shopId = "${requestScope.shop.id}"; // Truyền trực tiếp từ JSP (không cần dấu ngoặc kép nếu là số)

fetch('/uteshop/revenue?shop_id=' + shopId)
    .then(response => response.json())
    .then(data => {
        console.log("Data received:", data);

        // Thay đổi labels để sử dụng ngày (day) thay vì tháng (month)
        const labels = data.map(record => record.day);  // Sử dụng 'day' từ dữ liệu trả về
        const revenues = data.map(record => record.revenue);  // Doanh thu hàng ngày

        const ctx = document.getElementById('revenueChart').getContext('2d');
        new Chart(ctx, {
            type: 'line',  // Loại biểu đồ là line (đường)
            data: {
                labels: labels,  // Hiển thị ngày vào trục X
                datasets: [{
                    label: 'Daily Revenue',  // Thay đổi tên thành "Daily Revenue"
                    data: revenues,  // Doanh thu hàng ngày
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',  // Màu nền của các điểm dữ liệu
                    borderColor: 'rgba(75, 192, 192, 1)',  // Màu đường viền
                    borderWidth: 1  // Độ dày của đường viền
                }]
            },
            options: {
                scales: {
                    y: {
                    	beginAtZero: true,  // Bắt đầu trục Y từ 0
                        ticks: {
                            callback: function(value) {
                                return value.toLocaleString() + ' VND';  // Định dạng số và thêm 'VND'
                            }
                        }
                    }
                }
            }
        });
    })
    .catch(error => console.error('Error:', error));  // In lỗi nếu có sự cố

    </script>
   
    
    