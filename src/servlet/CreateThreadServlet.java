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

import model.GetThreadListLogic;
import model.PostThreadLogic;
import model.dThread;

/**
 * Servlet implementation class CreateThread
 */
@WebServlet("/CreateThread")
public class CreateThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CreateThread.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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

		//最新のDTHREADデータベースを取得する
		getThreadListLogic = new GetThreadListLogic();
		threadList = getThreadListLogic.execute();

		request.setAttribute("threadList", threadList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);

	}

}
