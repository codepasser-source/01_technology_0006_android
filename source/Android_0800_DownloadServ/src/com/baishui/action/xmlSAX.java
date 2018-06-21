package com.baishui.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class xmlSAX extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public xmlSAX() {
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

		response.setContentType("text/xml;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter(); 
		
		String requestData = request.getParameter("requestData");
		System.out.println(requestData);
		
		String decode = URLDecoder.decode("%"+requestData+"%B8%AD%E6%96%87","UTF-8");
        System.out.println("requestData:"+decode);	
        
        
	String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
		"<userlist>"+
		    "<user id=\"1\">"+
			    "<name>中文</name>"+
				 "<age>19</age>"+
			"</user>"+
			"<user id=\"2\">"+
			    "<name>user2</name>"+
				"<age>16</age>"+
			"</user>"+
			 "<user id=\"3\">"+
			    "<name>user3</name>"+
				  "<age>19</age>"+
			"</user>"+
			"<user id=\"4\">"+
			    "<name>user4</name>"+
				  "<age>16</age>"+
			"</user>"+
			 "<user id=\"5\">"+
			    "<name>user5</name>"+
				 "<age>17</age>"+
			"</user>"+
		"</userlist>";
	out.print(xmlData);
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
