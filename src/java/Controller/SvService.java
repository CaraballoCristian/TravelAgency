package Controller;

import Entity.Service;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvService", urlPatterns = {"/SvService"})
public class SvService extends HttpServlet {
    private final Controller control = Controller.getInstance();
    private List<Service> services;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("link")){
            
            services = control.getActiveServices();
            
            mySession.removeAttribute("res");
            mySession.setAttribute("services", services);
            mySession.setAttribute("action", "read");     
            
            
        }else if(action.equals("create")){
                            
            mySession.setAttribute("action", "create"); 
            
        }else if(action.equals("get-data")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            Service serv = control.getService(id);
            
            mySession.setAttribute("service", serv);                  
            mySession.setAttribute("action", "edit");            
        }
        
        response.sendRedirect("services.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("edit")){
            try {
                double price = 0;
                
                long id = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                if(!request.getParameter("price").isEmpty()) price = Double.parseDouble(request.getParameter("price"));
                String serviceDate = request.getParameter("serviceDate");
                
                try {
                    control.editService(id, name, description, price, serviceDate);
                    
                    mySession.setAttribute("res", "Success");
                    
                } catch (Exception ex) {
                    
                    mySession.setAttribute("res", "Updating failed");
                    Logger.getLogger(SvService.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (NumberFormatException ex) {
                mySession.setAttribute("res", "Updating failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }else if(action.equals("create")){
            try {
                
                double price = 0;
                
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                if(!request.getParameter("price").isEmpty()) price = Double.parseDouble(request.getParameter("price"));
                String serviceDate = request.getParameter("serviceDate");
                
                control.createService(name, description, price, serviceDate);
                mySession.setAttribute("res", "Success");
                
            } catch (NumberFormatException | ParseException ex) {
                mySession.setAttribute("res", "Creation failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(action.equals("delete")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            try {
                //LOGIC DELETE
                control.deleteService(id);
            } catch (Exception ex) {
                Logger.getLogger(SvService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        services = control.getActiveServices();
        
        mySession.setAttribute("services", services);
        mySession.setAttribute("action", "read");

        request.getRequestDispatcher("services.jsp").forward(request,response);    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
