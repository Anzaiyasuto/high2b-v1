package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetThreadListLogic;
import model.LoginLogic;
import model.PostThreadLogic;
import model.User;
import model.dThread;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");


		HttpSession session = request.getSession();

		if(session.getAttribute("loginUser")==null)
		{
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");

			User user = new User(name,pass);

			LoginLogic loginLogic = new LoginLogic();
			boolean isLogin = loginLogic.execute(user);

			if(isLogin) {
				//**//
				session.setAttribute("loginUser", user);
			}
		} else {

			GetThreadListLogic getThreadListLogic = new GetThreadListLogic();
			List<dThread> threadList = getThreadListLogic.execute();


			String dTitle = request.getParameter("dTitle");
			if(dTitle != null && dTitle.length() != 0) {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				dThread thread = new dThread(threadList.size(),dTitle,now);

				PostThreadLogic postThreadLogic = new PostThreadLogic();
				postThreadLogic.execute(thread);
			} else if (dTitle != null && dTitle.length() == 0){

				request.setAttribute("errorMsg", "スレッド名が入力されていません");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CreateThread.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}

		//session.setAttribute("dTitle", dTitle);


		//最新のDTHREADデータベースを取得する
		GetThreadListLogic getThreadListLogic = new GetThreadListLogic();
		List<dThread> threadList = getThreadListLogic.execute();

		request.setAttribute("threadList", threadList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);

	}

}
