package com.ensun.esy;

import java.io.IOException;
import java.io.PrintWriter; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeachListAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SeachListAction() {
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
		String word = request.getParameter("word");
		System.out.println("SeachAction word :" +word);
		String json = "";
		if(word!=null){ 
		 
				json = "[ "
                    +"{\"word\":\"单词4\",\"vedioName\":\"vedio4.mp4\",\"vedioSize\":\"1234\",\"vedioUri\":\"http://192.168.1.81:8080/esy/vedio/vedio4.mp4\"},"
                    +"{\"word\":\"单词5\",\"vedioName\":\"vedio5.mp4\",\"vedioSize\":\"4321\",\"vedioUri\":\"http://192.168.1.81:8080/esy/vedio/vedio5.mp4\"},"
                    +"{\"word\":\"单词6\",\"vedioName\":\"vedio6.mp4\",\"vedioSize\":\"2341\",\"vedioUri\":\"http://192.168.1.81:8080/esy/vedio/vedio6.mp4\"}"
                    +"]"; 
				
			  
		}
		 
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
