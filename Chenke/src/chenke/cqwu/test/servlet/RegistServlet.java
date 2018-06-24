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
import chenke.cqwu.test.entity.UserInfoCK;

public class RegistServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserBizCK userbizck = null;
	private UserDaoCK userdaock = null;
	
	public void init() throws ServletException{
		userdaock = new UserDaoImplCK();
		userbizck = new UserBizImplCK();
		userbizck.setUserdaock(userdaock);
	}
	/*
	 * 实现注册的处理
	 * */
	public void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		UserInfoCK userinfock = new UserInfoCK();
		userinfock.setUsernameCK(username);
		userinfock.setPasswordCK(password);
		userinfock.setEmailCK(email);
		
		if(!userbizck.addUserCK(userinfock)){
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert(\"registed fail！Keep in touch with the Administrator！\")");
			/*pw.println("open(\"login.jsp\",\"_self\");");*/
			pw.println("</script>");
			return;
		}
		resp.sendRedirect("./register_success.jsp");
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action == null){
			regist(req,resp);
		}else{
			//check(req,resp);
		}

	}	
}
