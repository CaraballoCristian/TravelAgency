package Controller;

import Entity.Employee;
import Entity.Service;
import Entity.Package;
import Entity.Sale;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {
    
    private final Controller control = new Controller();
    private Employee emp;
    private List<Service> services;
    private List<Package> packages;
    private List<Sale> sales;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               
        HttpSession mySession = request.getSession(true);
        
        String action = request.getParameter("action");
        
        if(action.equals("login")){
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            
            emp = control.validateUser(user, pass);
            
            if(emp != null){
                mySession.setAttribute("sdf", sdf);
                
                services = control.getActiveServices();
                packages = control.getActivePackages();
                sales = control.getSales(); 
                
                long length;
                if(!sales.isEmpty()){
                    length = sales.get(sales.size() - 1).getId() + 1;
                }else length = 1;
                
                mySession.setAttribute("sales", sales);
                mySession.setAttribute("billNumber", length);
                mySession.setAttribute("user", emp);
                mySession.setAttribute("services", services);
                mySession.setAttribute("packages", packages);
                mySession.setAttribute("action", "create");
               
                response.sendRedirect("new-sale.jsp");
                
            }else{
                response.sendRedirect("index.jsp");
            }
            
        }else if (action.equals("logout")){
            
            mySession.removeAttribute("user");
            
            mySession.invalidate();
            
            response.sendRedirect("index.jsp");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
