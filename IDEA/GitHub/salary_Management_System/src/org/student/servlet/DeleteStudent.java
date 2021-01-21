package org.student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.service.StudentService;

public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		StudentService service = new StudentService();
		boolean result = service.deleteStudentnumber(name);
		response.setContentType("text/html charset=utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println(result);
		if (result) {
			//response.getWriter().print("ɾ���ɹ�");
			response.sendRedirect("manager/employeeManagement.jsp");
		} else
			response.getWriter().print("ɾ��ʧ��");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
