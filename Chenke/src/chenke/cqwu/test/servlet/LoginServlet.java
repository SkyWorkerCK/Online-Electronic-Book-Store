package chenke.cqwu.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chenke.cqwu.test.biz.UserBizCK;
import chenke.cqwu.test.biz.impl.UserBizImplCK;
import chenke.cqwu.test.dao.UserDaoCK;
import chenke.cqwu.test.dao.impl.UserDaoImplCK;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserBizCK userbizck = null;
	private UserDaoCK userdaock = null;
	
	public void init() throws ServletException{
		userdaock = new UserDaoImplCK();
		userbizck = new UserBizImplCK();
		userbizck.setUserdaock(userdaock);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean canlogin = userbizck.checkLoginCK(username, password);
		
		//Login successfully
		if(canlogin){
			req.getSession().setAttribute("loginuser", username);
			System.out.println("Login name:"+req.getSession().getAttribute("loginuser"));
			resp.sendRedirect("books");
		}
		//Login failly
		else{
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert(\"registed fail! Please relogin again! \")");
			/*pw.println("open(\"login.jsp\",\"_self\");");*/
			pw.println("</script>");
			resp.sendRedirect("./login.jsp");
			pw.close();
		}
		
	}
	
	
	
	
	
	
	
	
	
}
