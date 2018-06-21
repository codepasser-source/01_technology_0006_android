package com.ensun.esy;

import java.io.IOException;
import java.io.PrintWriter; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewListAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public NewListAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/text;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		
		String json = "[ "
			              +"{\"word\":\"你能帮我买票吗\",\"vedioName\":\"vedio0.mp4\",\"vedioSize\":\"1234\",\"vedioUri\":\"http://192.168.1.104:8080/esy/vedio/vedio0.mp4\"},"
                          +"{\"word\":\"你能帮我买票吗\",\"vedioName\":\"vedio1.mp4\",\"vedioSize\":\"1234\",\"vedioUri\":\"http://192.168.1.104:8080/esy/vedio/vedio1.mp4\"},"
                          +"{\"word\":\"十分感谢你对我的帮助\",\"vedioName\":\"vedio2.mp4\",\"vedioSize\":\"4321\",\"vedioUri\":\"http://192.168.1.104:8080/esy/vedio/vedio2.mp4\"},"
                          +"]";
		
		out.print(json); 
		out.flush(); 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
