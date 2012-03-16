package com.ericsson.jigsaw.simulator.mail.web.action;

import java.io.IOException;

import javax.mail.Message;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ericsson.jigsaw.simulator.mail.web.control.mail.receive.ReceiveMail;
import com.ericsson.jigsaw.simulator.mail.web.model.User;
import com.ericsson.jigsaw.simulator.mail.web.model.WebMail;
import com.ericsson.jigsaw.simulator.mail.web.util.ContentInfoMapper;
import com.ericsson.jigsaw.simulator.mail.web.util.MimeMailUtil;

/**
 * Servlet implementation class ShowContent
 */
public class ShowContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowContentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream servletOutput = response.getOutputStream();
		final String userId = request.getParameter("id");
		final int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		User u = new User(userId);
		ReceiveMail receiveMail = new ReceiveMail();
		try {
			Message message = receiveMail.getOneMail(u, subjectId); // TODO
																	// consider
																	// using
																	// message
																	// ID
			WebMail mail = MimeMailUtil.parse(message);
			//response.setContentType(message.getContentType());
			response.setContentType("text/html");
			//System.out.println(message.getContentType());
			if (MimeMailUtil.isHtml(mail.getDisplayBody().getContentType())) {

				if (mail.getEmbedMedias().size() > 0) {
					MimeMailUtil.replaceContentIdOrContentLocationInHtmlBody(
							mail, new ContentInfoMapper() {
								public String getContentIdMapper(
										String contentIdOrContentLocation,
										WebMail mail) {
									return "DownloadMedia?id="+userId+"&subjectId="+subjectId+"&contentId="+contentIdOrContentLocation;
								}

							});
				}
				
			}
			//response.getWriter().print((String)mail.getDisplayBody().getContent());
			servletOutput.print((String)mail.getDisplayBody().getContent());
			receiveMail.close();
			//servletOutput.close();//TODO check whether this needs to be closed.

		} catch (Exception e) {
			e.printStackTrace();
			/*request.setAttribute("errorInfo", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);*/
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
