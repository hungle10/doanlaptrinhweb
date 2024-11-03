package cuoiki.ltweb.controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/admin/home")
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    // Create a PrintWriter object to write the HTML content
	    PrintWriter out = resp.getWriter();

	    // Write the HTML code for the greeting message
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Hello World</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Xin chào admin!</h1>");
	    out.println("</body>");
	    out.println("</html>");
	}

}
