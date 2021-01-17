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

import model.GetMutterListLogic;
import model.GetThreadTitleLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;
import model.dThread;

@WebServlet("/Main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int id;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//スレッド名に対応するIDを取得する
		request.setCharacterEncoding("UTF-8");
		String temp = request.getParameter("id");
		id	= Integer.parseInt(temp);

		GetThreadTitleLogic getThreadTitleLogic = new GetThreadTitleLogic();
		dThread thread = getThreadTitleLogic.execute(id);
		if(thread != null) {
			request.setAttribute("thread", thread);
		}
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute(id);
		request.setAttribute("mutterList", mutterList);

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {
			//response.sendRedirect("/docoTsubu/");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		} else {


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/thread.jsp");
			dispatcher.forward(request, response);
		}
	}




	//つぶやき機能
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		Timestamp now = new Timestamp(System.currentTimeMillis());

		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		//最新のデータベースを取得する
		List<Mutter> mutterList = getMutterListLogic.execute(id);



		if(text != null && text.length() != 0) {
			HttpSession session = request.getSession();
			if(session == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
			User loginUser = (User)session.getAttribute("loginUser");


			Mutter mutter = new Mutter(mutterList.size(),loginUser.getName(), text, now);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter,id);

		} else if(text != null){
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}

		//最新のデータベースを取得する
		mutterList = getMutterListLogic.execute(id);
		//mutterListをセッションスコープに入れる
		request.setAttribute("mutterList", mutterList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/thread.jsp");
		dispatcher.forward(request, response);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
