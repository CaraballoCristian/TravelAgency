package Controller;

import Entity.Package;
import Entity.Service;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvPackage", urlPatterns = {"/SvPackage"})
public class SvPackage extends HttpServlet {
    private final Controller control = Controller.getInstance();
    private List<Package> packages;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("link")){
            
            packages = control.getActivePackages();
            
            mySession.removeAttribute("res");
            mySession.setAttribute("packs", packages);
            mySession.setAttribute("action", "read");
            
        }else if(action.equals("create")){
                            
            mySession.setAttribute("action", "create"); 
            
        }else if(action.equals("get-data")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            Package pack = control.getPackage(id);
            List<Service> included = pack.getServices();
            
            mySession.setAttribute("included", included);                  
            mySession.setAttribute("pack", pack);
            
            mySession.setAttribute("action", "edit");  
        }
        
        response.sendRedirect("packages.jsp"); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("edit")){
            
            try {
                long id = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                String[] idServs = request.getParameterValues("checkbox-servs");
                
                control.editPackage(id, name, idServs);
                
                mySession.setAttribute("res", "Success");
                
            } catch (Exception ex) {
                mySession.setAttribute("res", "Updating failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(action.equals("create")){
            
            try {
                String name = request.getParameter("name");
                String[] idServs = request.getParameterValues("checkbox-servs");
                
                control.createPackage(name, idServs);
                
                mySession.setAttribute("res", "Success");
                                
            } catch (Exception ex) {
                mySession.setAttribute("res", "Creation failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(action.equals("delete")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            //LOGIC DELETE
            try {
                control.deletePackage(id);
            } catch (Exception ex) {
                Logger.getLogger(SvPackage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        packages = control.getActivePackages();
        mySession.setAttribute("packs", packages);
        mySession.setAttribute("action", "read");

        response.sendRedirect("packages.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
