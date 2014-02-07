package ur.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String user = null;

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
