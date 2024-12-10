package cuoiki.ltweb.mail;



public class MailMessenger {

	public static void successfullyRegister(String userName, String userEmail) {

		String subject = "Welcome to UTEShop - Successful Registration!";
		String body = "Hi " + userName
				+ ",<p>Congratulations and a warm welcome to UTEShop! Chúng tôi rất vui khi bạn đã trở thành thành viên. Thank you for choosing us for your online shopping needs.</p>"
				+ "<p>Your registration was successful, and we are excited to inform you that you are now a valued member of our platform. With UTEShop, you'll discover a wide selection of products and exciting deals that cater to your interests and preferences."
				+ "<p>Once again, welcome aboard! We look forward to serving you and making your shopping experience delightful and rewarding.</p>"
				+ "<p>Happy shopping!</p>";
		try {
			Mail.sendMail(userEmail, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void successfullyRegisteredShopOwner(String userName, String userEmail, String shopName) {
	    String subject = "Shop Registration Confirmation - Welcome to UTEShop!";
	    String body = "Hi " + userName
	            + ",<p>Congratulations! Your shop, <b>" + shopName + "</b>, has been successfully registered on UTEShop.</p>"
	            + "<p>We are thrilled to welcome you to our community of shop owners. Your shop registration is now under review by our team. Once it is approved, you'll be able to start managing your shop and connecting with customers.</p>"
	            + "<p>Shop Details: <br>" + "Shop Name: " + shopName + "</p>"
	            + "<p>If you have any questions or need assistance, please feel free to reach out to our support team. We're here to help you every step of the way.</p>"
	            + "<p>Thank you for trusting UTEShop. We look forward to seeing your shop thrive on our platform.</p>"
	            + "<p>Warm regards,<br><b>UTEShop Team</b></p>";
	    try {
	        Mail.sendMail(userEmail, subject, body);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static void successfullyOrderPlaced(String userName, String userEmail, String orderId, String OrderDate) {
		String subject = "Order Confirmation - Your Product is on its way!";
		String body = "Hi " + userName
				+ ",<p>We are delighted to inform you that your order has been successfully placed and is now being processed. Thank you for choosing UTEShop for your shopping needs!</p>"
				+ "<p>Order Details: <br>" + "Order Number: " + orderId + "<br>Order Date: " + OrderDate + "</p>"
				+ "<p>Please note that your order is currently being prepared for shipment. Our dedicated team is working diligently to ensure that your products are packed securely and dispatched at the earliest.</p>"
				+ "<p>Once your order is shipped, we will send you another email containing the tracking details, allowing you to monitor its journey until it reaches your doorstep. We understand how exciting it is to receive a package, and we'll do our best to get it to you as soon as possible.</p>"
				+ "<p>Thank you for shopping with us! Your trust in <b>UTEShop</b> means a lot to us, and we promise to provide you with an exceptional shopping experience.</p>";
		try {
			Mail.sendMail(userEmail, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void RegisteredShopOwnerWaitToAccepted(String userName, String userEmail, String shopName) {
	    String subject = "Shop Registration Pending Approval - UTEShop";
	    String body = "Hi " + userName
	            + ",<p>Thank you for registering your shop, <b>" + shopName + "</b>, on UTEShop!</p>"
	            + "<p>We are excited to welcome you to our platform. Your shop registration is currently under review by our admin team to ensure compliance with our guidelines. This process typically takes up to 24-48 hours.</p>"
	            + "<p>Shop Details: <br>" + "Shop Name: " + shopName + "</p>"
	            + "<p>Once your shop is approved, you will receive a confirmation email, and you'll be able to start managing your shop and engaging with customers.</p>"
	            + "<p>If you have any questions in the meantime, feel free to contact our support team. We are here to assist you.</p>"
	            + "<p>Thank you for choosing UTEShop. We are committed to helping your shop succeed on our platform!</p>"
	            + "<p>Warm regards,<br><b>UTEShop Team</b></p>";
	    try {
	        Mail.sendMail(userEmail, subject, body);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static void RegisteredShopOwnerAccepted(String userName, String userEmail, String shopName) {
	    String subject = "Shop Registration has been accepted - UTEShop";
	    String body = "Hi " + userName
	            + ",<p>Thank you for registering your shop, <b>" + shopName + "</b>, on UTEShop!</p>"
	            + "<p>We are excited to welcome you to our platform. Your shop registration is accepted.</p>"
	            + "<p>Shop Details: <br>" + "Shop Name: " + shopName + "</p>"
	            + "<p>If you have any questions, feel free to contact our support team. We are here to assist you.</p>"
	            + "<p>Thank you for choosing UTEShop. We are committed to helping your shop succeed on our platform!</p>"
	            + "<p>Warm regards,<br><b>UTEShop Team</b></p>";
	    try {
	        Mail.sendMail(userEmail, subject, body);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static void RegisteredShopOwnerPended(String userName, String userEmail, String shopName) {
	    String subject = "Shop Registration has been pended - UTEShop";
	    String body = "Hi " + userName
	            + "<p>Your shop registration is pended.</p>"
	            + "<p>Shop Details: <br>" + "Shop Name: " + shopName + "</p>"
	            + "<p>If you have any questions, feel free to contact our support team. We are here to assist you.</p>"
	            + "<p>Thank you for choosing UTEShop. We are committed to helping your shop succeed on our platform!</p>"
	            + "<p>Warm regards,<br><b>UTEShop Team</b></p>";
	    try {
	        Mail.sendMail(userEmail, subject, body);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public static void orderShipped(String userName, String userEmail, String orderId, String OrderDate) {
		String subject = "Your Order is Out for Delivery!";
		String body = "Hi " + userName
				+ "<p>Greetings from <b>UTEShop</b>! We are thrilled to inform you that your order is on its way to you. Your package has been successfully shipped and will soon be at your doorstep!</p>"
				+ "<p>Order Details: <br>" + "Order Number: " + orderId + "<br>Order Date: " + OrderDate + "</p>"
				+ "<p>Our dedicated team has carefully processed and packed your order to ensure that it reaches you in perfect condition. As it is out for delivery, our trusted delivery partner is committed to bringing your package to you as swiftly as possible.</p>"
				+ "<p>Once again, we appreciate your trust in <b>UTEShop</b> for your shopping needs. We aim to provide you with an outstanding shopping experience, and your satisfaction is our priority.</p>"
				+ "<p>Thank you for choosing us!</p>";
		try {
			Mail.sendMail(userEmail, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendOtp(String userEmail, int code) {
		String subject = "Verification code for password change";
		String body = "Hi, " + "<p>Please use the below verification code to reset your password!</p>" + "<h3>" + code
				+ "</h3>";
		try {
			Mail.sendMail(userEmail, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}