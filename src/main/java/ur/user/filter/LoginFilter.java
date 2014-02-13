package ur.user.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns={"/horse", "posting.jsp"})
public class LoginFilter implements Filter {
	
	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Jepp!!");
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		HttpSession session = httpServletRequest.getSession();
		String user;

		if (session.getAttribute("user") == null) {
			this.context.log("nix da!!");
			httpServletResponse.sendRedirect("login.jsp");
			this.context.log("ois passt");
			return;
		} else {
			user = (String) session.getAttribute("user");
		}

		String userName = null;
		String sessionID = null;

		Cookie[] cookies = httpServletRequest.getCookies(); 
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(user)) { 
					userName = cookie.getName();
					System.out.println(userName);
				}
				if (cookie.getName().equals("JSESSIONID")) { 
					sessionID = cookie.getValue();
				}
			}
		}
		
		System.out.println(userName);
		
		req.setAttribute("userName", userName);
		req.setAttribute("sessionID", sessionID);
		chain.doFilter(req, resp);
	}

	public void destroy() {	}

}
