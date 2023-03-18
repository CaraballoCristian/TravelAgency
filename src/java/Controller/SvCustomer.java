package Controller;

import Entity.Customer;
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

@WebServlet(name = "SvCustomer", urlPatterns = {"/SvCustomer"})
public class SvCustomer extends HttpServlet {
    private final Controller control = Controller.getInstance();
    private List<Customer> customers;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("link")){
            
            customers = control.getActiveCustomers();
            
            mySession.removeAttribute("res");
            mySession.setAttribute("customers", customers);
            mySession.setAttribute("action", "read");
            
            response.sendRedirect("customers.jsp");       
            
        }else if(action.equals("create")){
                            
            mySession.setAttribute("action", "create"); 
            response.sendRedirect("customers.jsp");
            
        }else if(action.equals("get-data")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            Customer cus = control.getCustomer(id);
            
            mySession.setAttribute("customer", cus);                  
            mySession.setAttribute("action", "edit");            
            
            response.sendRedirect("customers.jsp");
            
        }else if(action.equals("search-dni")){
            
            String strDni = request.getParameter("dni");
            String regexp = "\\d+";
            
            long dni = 0;
           
            if(strDni.matches(regexp)) dni = Long.parseLong(request.getParameter("dni"));
                       
            Customer cus = control.getCustomerByDni(dni);
            
            mySession.setAttribute("customer", cus);
            
            response.sendRedirect("new-sale.jsp");
            
        }
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("edit")){
            
            try {
                long dni = 0;
                
                long id = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                String lastName = request.getParameter("lastname");
                if(!request.getParameter("dni").isEmpty()) dni = Long.parseLong(request.getParameter("dni"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String nationality = request.getParameter("nationality");
                String address = request.getParameter("address");
                String birthdate = request.getParameter("birthdate");
                
                control.editCustomer(id, name, lastName, dni, phone, email, nationality, address, birthdate);
               
                mySession.setAttribute("res", "Success");
                
            } catch (Exception ex) {
                
                mySession.setAttribute("res", "Updating failed");
                
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(action.equals("create")){
            
            try {
                long dni = 0;
                
                String name = request.getParameter("name");
                String lastName = request.getParameter("lastname");
                if(!request.getParameter("dni").isEmpty()) dni = Long.parseLong(request.getParameter("dni"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String nationality = request.getParameter("nationality");
                String address = request.getParameter("address");
                String birthdate = request.getParameter("birthdate");
                
                control.createCustomer(name, lastName, dni, phone, email, nationality, address, birthdate);
                
                mySession.setAttribute("res", "Success");
            } catch (Exception ex) {
                
                mySession.setAttribute("res", "Creation failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(action.equals("delete")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            try {
                //LOGIC DELETE
                control.deleteCustomer(id);
            } catch (Exception ex) {
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        customers = control.getActiveCustomers();
        
        mySession.setAttribute("customers", customers);
        mySession.setAttribute("action", "read");

        response.sendRedirect("customers.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
