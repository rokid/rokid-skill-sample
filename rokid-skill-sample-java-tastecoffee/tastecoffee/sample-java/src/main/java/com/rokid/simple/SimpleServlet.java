package com.rokid.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rokid.simple.bean.RKSlots;
import com.rokid.simple.dto.req.ReqRequest;
import com.rokid.simple.dto.res.ResResponse;
import com.rokid.simple.utils.ResponseUtils;

/**
 * Servlet implementation class SimpleServlet
 */
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimpleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestString = getBodyData(request);
		System.out.println("requestdata:" + requestString);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		if (requestString != null && requestString.trim().length() > 0) {
			try {
				Gson gson = new Gson();
				Type t = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ReqRequest.class,
						RKSlots.class);
				ReqRequest<RKSlots> reqRequest = gson.fromJson(requestString, t);
				if (reqRequest != null && reqRequest.getRequest() != null
						&& reqRequest.getRequest().getContent() != null) {
					String intent = reqRequest.getRequest().getContent().getIntent();
					if ("bestcoffeebar".equals(intent)) {
						ResResponse resResponse = ResponseUtils.getTTSResponse(false, "我看位于问溪路89号的米萨咖啡就很不错。");
						PrintWriter out = response.getWriter();
						out.write(new Gson().toJson(resResponse));
					} else if ("nicedrink".equals(intent)) {
						ResResponse resResponse = ResponseUtils.getTTSResponse(false, "只要是咖啡都很好喝。");
						PrintWriter out = response.getWriter();
						out.write(new Gson().toJson(resResponse));
					} else if ("ROKID.INTENT.WELCOME".equals(intent)) {
						ResResponse resResponse = ResponseUtils.getTTSResponse(false, "你好，请问有什么可以帮您。");
						PrintWriter out = response.getWriter();
						out.write(new Gson().toJson(resResponse));
					}else{
						ResResponse resResponse = ResponseUtils.getTTSResponse(true, "对不起，你说的我还不会，你可以换个说法试试");
						PrintWriter out = response.getWriter();
						out.write(new Gson().toJson(resResponse));
					}
				} else {
					ResResponse resResponse = ResponseUtils.getTTSResponse(true, "应用出了点小问题，请稍后再试");
					PrintWriter out = response.getWriter();
					out.write(new Gson().toJson(resResponse));
				}
			} catch (Exception e) {
				e.printStackTrace();
				ResResponse resResponse = ResponseUtils.getTTSResponse(true, "应用出了点小问题，请稍后再试");
				PrintWriter out = response.getWriter();
				out.write(new Gson().toJson(resResponse));
			}
		} else {
			ResResponse resResponse = ResponseUtils.getTTSResponse(true, "对不起，我不明白你说什么");
			PrintWriter out = response.getWriter();
			out.write(new Gson().toJson(resResponse));
		}
	}

	// 获取请求体中的字符串(POST)
	private String getBodyData(HttpServletRequest request) {
		StringBuffer data = new StringBuffer();
		String line = null;
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			while (null != (line = reader.readLine()))
				data.append(line);
		} catch (IOException e) {
		} finally {
		}
		return data.toString();
	}
}
