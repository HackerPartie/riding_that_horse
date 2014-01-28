package ur.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@WebServlet("/cook")
public class CookieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		HttpSession session = req.getSession();
		String sessionId = session.getId();
		
		Cookie cookie = new Cookie("ur", sessionId);
		cookie.setMaxAge(20*20);
		resp.addCookie(cookie);
				
		pw.println("Cookie created");
		
		Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
		Elements newHeadlines = doc.select("#mp-itn b a");
		System.out.println(newHeadlines);
		pw.println(newHeadlines);
		
		PasswordService passwordService = new DefaultPasswordService();
		String p = passwordService.encryptPassword("zenturio");	
		pw.println(p);
		String o = "password";
		Boolean paSaMa = passwordService.passwordsMatch(o, p);
		pw.println(paSaMa);
	}

	
	
}
