package Controller;

import Entity.Package;
import Entity.Sale;
import Entity.Service;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet {
    private final Controller control = Controller.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("link")){
            List<Package> packs = control.getActivePackages();
            List<Service> servs = control.getActiveServices();
            List<Sale> sales = control.getSales();
            
            long length;
            if(!sales.isEmpty()){
                length = sales.get(sales.size() - 1).getId() + 1;
            }else length = 1;
                
            mySession.removeAttribute("res");
            mySession.setAttribute("sales", sales);
            mySession.setAttribute("billNumber", length);
            mySession.setAttribute("packages", packs);
            mySession.setAttribute("services", servs);
            mySession.setAttribute("action", "create");
            
            response.sendRedirect("new-sale.jsp");        
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
