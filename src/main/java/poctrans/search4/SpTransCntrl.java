package poctrans.search4;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class SpTransCntrl
 */
@WebServlet("/poctrans/search4/spTransCntrl")
public class SpTransCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpTransCntrl() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page= request.getParameter("page");
		String opr = request.getParameter("opr");
		int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));
		int limit= (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("limit")));
		
		RequestDispatcher rd;
		SpTransDBService spTransDBService =new SpTransDBService();
		SpTrans spTrans =new SpTrans();
		//Action for close buttons
		String homeURL=(null==request.getSession().getAttribute("homeURL")?"":(String)request.getSession().getAttribute("homeURL"));		
		if(page.equals("spTransDashboard"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="spTransCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			
			if(opr.equals("showAll")) 
			{
				ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
				
				if(pageNo==0)
				spTransList = spTransDBService.getSpTransList();
				else { //pagination
					int totalPages= spTransDBService.getTotalPages(limit);
					spTransList = spTransDBService.getSpTransList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("spTransList",spTransList);
				rd = request.getRequestDispatcher("spTransDashboard.jsp");
				rd.forward(request, response);
			} 
			else if(opr.equals("addNew")) //CREATE
			{
				spTrans.setDefaultValues();
				spTrans.displayValues();
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("addNewSpTrans.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) //UPDATE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans = spTransDBService.getSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("updateSpTrans.jsp");
				rd.forward(request, response);
			}
			//Begin: modified by Dr PNH on 06-12-2022
			else if(opr.equals("editNext")) //Save and Next
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans = spTransDBService.getSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("updateNextSpTrans.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("saveShowNext")) //Save, show & next
			{
				spTrans.setDefaultValues();
				spTrans.displayValues();
				request.setAttribute("spTrans",spTrans);
				
				ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
				
				if(pageNo==0)
				spTransList = spTransDBService.getSpTransList();
				else { //pagination
					int totalPages= spTransDBService.getTotalPages(limit);
					spTransList = spTransDBService.getSpTransList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("spTransList",spTransList);
				rd = request.getRequestDispatcher("saveShowNextSpTrans.jsp");
				rd.forward(request, response);
			}
			//End: modified by Dr PNH on 06-12-2022
			else if(opr.equals("delete")) //DELETE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans.setId(id);
				spTransDBService.deleteSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("deleteSpTransSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) //READ
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans = spTransDBService.getSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("viewSpTrans.jsp");
				rd.forward(request, response);
			}
			
		}
		else if(page.equals("addNewSpTrans")) 
		{
			if(opr.equals("save"))
			{
				spTrans.setRequestParam(request);
				spTrans.displayValues();
				spTransDBService.createSpTrans(spTrans);
				request.setAttribute("spTrans",spTrans);
				if(pageNo!=0) {//pagination
					int totalPages= spTransDBService.getTotalPages(limit);
					homeURL="spTransCntrl?page=spTransDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				rd = request.getRequestDispatcher("addNewSpTransSuccess.jsp");
				rd.forward(request, response);
			}
		}
		//Begin: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateNextSpTrans")) 
		{
			if(opr.equals("update"))
			{
				spTrans.setRequestParam(request);
				spTransDBService.updateSpTrans(spTrans);
				request.setAttribute("spTrans",spTrans);
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("spTransCntrl?page=spTransDashboard&opr=editNext&pageNo=0&limit=0&id=10");			}
		}
		else if(page.equals("saveShowNextSpTrans")) 
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="spTransCntrl?page=spTransDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0";
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("addNew")) //save new record
			{
				spTrans.setRequestParam(request);
				spTrans.displayValues();
				spTransDBService.createSpTrans(spTrans);
				request.setAttribute("spTrans",spTrans);
				if(pageNo!=0) {//pagination
					int totalPages= spTransDBService.getTotalPages(limit);
					homeURL="spTransCntrl?page=spTransDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("spTransCntrl?page=spTransDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd = request.getRequestDispatcher("spTransCntrl?page=spTransDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd.forward(request, response);
			}
			else if(opr.equals("edit"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans = spTransDBService.getSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				
				ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
				if(pageNo==0)
				spTransList = spTransDBService.getSpTransList();
				else { //pagination
					int totalPages= spTransDBService.getTotalPages(limit);
					spTransList = spTransDBService.getSpTransList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("spTransList",spTransList);
				rd = request.getRequestDispatcher("saveShowNextSpTrans.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("update"))
			{
				spTrans.setRequestParam(request);
				spTransDBService.updateSpTrans(spTrans);
				request.setAttribute("spTrans",spTrans);
				request.getSession().setAttribute("msg", "Record updated successfully");
				response.sendRedirect("spTransCntrl?page=spTransDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
			}
			else if(opr.equals("delete"))
			{
					int id = Integer.parseInt(request.getParameter("id"));
					spTrans.setId(id);
					spTransDBService.deleteSpTrans(id);
					request.setAttribute("spTrans",spTrans);
					request.getSession().setAttribute("msg", "Record deleted successfully");
					response.sendRedirect("spTransCntrl?page=spTransDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			else if(opr.equals("reset")||opr.equals("cancel"))
			{
					response.sendRedirect("spTransCntrl?page=spTransDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			
		}
		//End: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateSpTrans")) 
		{
			if(opr.equals("update"))
			{
				spTrans.setRequestParam(request);
				spTransDBService.updateSpTrans(spTrans);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("updateSpTransSuccess.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("viewSpTrans")) 
		{
			if(opr.equals("print")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans = spTransDBService.getSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("printSpTrans.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("searchSpTrans"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="spTransCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("search")) 
			{
				spTrans.setRequestParam(request);
				spTrans.displayValues();
				request.getSession().setAttribute("spTransSearch",spTrans);
				request.setAttribute("opr","search");
				ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
				if(pageNo==0)
				spTransList = spTransDBService.getSpTransList(spTrans);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=spTransDBService.getTotalPages(spTrans,limit);
					pageNo=1;
					spTransList = spTransDBService.getSpTransList(spTrans,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("spTransList",spTransList);
				rd = request.getRequestDispatcher("searchSpTrans.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
//begin:code for download/print button
			else if(opr.equals("downloadPrint")) 
			{
				spTrans.setRequestParam(request);
				spTrans.displayValues();
				request.getSession().setAttribute("spTransSearch",spTrans);
				request.setAttribute("opr","spTrans");
				ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
				if(pageNo==0)
				spTransList = spTransDBService.getSpTransList(spTrans);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=spTransDBService.getTotalPages(spTrans,limit);
					pageNo=1;
					spTransList = spTransDBService.getSpTransList(spTrans,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("spTransList",spTransList);
				rd = request.getRequestDispatcher("searchSpTransDownloadPrint.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			//end:code for download/print button
			
			else if(opr.equals("showAll")) 
			{
				spTrans=(SpTrans)request.getSession().getAttribute("spTransSearch");
				ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
				if(pageNo==0)
				spTransList = spTransDBService.getSpTransList(spTrans);
				else { //pagination
					int totalPages= spTransDBService.getTotalPages(spTrans,limit);
					spTransList = spTransDBService.getSpTransList(spTrans,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("spTransList",spTransList);
				rd = request.getRequestDispatcher("searchSpTrans.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("searchNext")||opr.equals("searchPrev")||opr.equals("searchFirst")||opr.equals("searchLast")) 
			{
				request.setAttribute("opr","search");
				spTrans=(SpTrans)request.getSession().getAttribute("spTransSearch");
				ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
				if(pageNo==0)
				spTransList = spTransDBService.getSpTransList(spTrans);
				else { //pagination
					int totalPages= spTransDBService.getTotalPages(spTrans,limit);
					spTransList = spTransDBService.getSpTransList(spTrans,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("spTransList",spTransList);
				rd = request.getRequestDispatcher("searchSpTrans.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("showNone"))
			{
				spTrans.setDefaultValues();
				spTrans.displayValues();
				request.getSession().setAttribute("spTransSearch",spTrans);
				request.setAttribute("opr","showNone");
				rd = request.getRequestDispatcher("searchSpTrans.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans = spTransDBService.getSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("updateSpTrans.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("delete")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				spTrans.setId(id);
				spTransDBService.deleteSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("deleteSpTransSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) 
			{
 			int id = Integer.parseInt(request.getParameter("id"));
				spTrans = spTransDBService.getSpTrans(id);
				request.setAttribute("spTrans",spTrans);
				rd = request.getRequestDispatcher("viewSpTrans.jsp");
				rd.forward(request, response);
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("page=updateSpTrans&opr=close&homePage=spTransDashboard");
		String v = uri.getQuery();
		
	}
}
