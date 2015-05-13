package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.QueryDAO;

/**
 * Servlet implementation class YouMiServlet
 */
@WebServlet("/YouMiServlet")
public class YouMiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YouMiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String appid = request.getParameter("appid");
		String idfa = request.getParameter("idfa");
		
		StringBuffer sb = new StringBuffer();
		
		if(appid == null || idfa == null) {
			PrintWriter out = response.getWriter();
			out.print("");
			return;
		}

		QueryDAO query = new QueryDAO();
		
		if (appid.equals("595672427")) {
			
			String[] idfas = idfa.split(",");

			sb.append("{");
			for (int i = 0; i < idfas.length; i++) {
				sb.append("\"");
				sb.append(idfas[i]);
				sb.append("\":");
				int res = query.isExist(idfas[i]);
				sb.append(res);
				if (i != idfas.length - 1) {
					sb.append(",");
				}
				
				query.record(idfas[i], res);  
			}
			sb.append("}");

		} else {
			String[] idfas = idfa.split(",");

			sb.append("{");
			for (int i = 0; i < idfas.length; i++) {
				sb.append("\"");
				sb.append(idfas[i]);
				sb.append("\":");
				sb.append(1);
				if (i != idfas.length - 1) {
					sb.append(",");
				}
			}
			sb.append("}");

		}

		PrintWriter out = response.getWriter();
		out.print(sb.toString());
	}

}
