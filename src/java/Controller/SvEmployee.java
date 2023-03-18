package Controller;

import Entity.Employee;
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

@WebServlet(name = "SvEmployee", urlPatterns = {"/SvEmployee"})
public class SvEmployee extends HttpServlet {
    private final Controller control = Controller.getInstance();
    private List<Employee> employees;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("link")){
            
            employees = control.getActiveEmployees();
            
            mySession.removeAttribute("res");
            mySession.setAttribute("employees", employees);
            mySession.setAttribute("action", "read");
                  
            
        }else if(action.equals("create")){
                            
            mySession.setAttribute("action", "create"); 
            
        }else if(action.equals("get-data")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            Employee emp = control.getEmployee(id);
            
            mySession.setAttribute("employee", emp);                  
            mySession.setAttribute("action", "edit");   
        }
        
        response.sendRedirect("employees.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("edit")){
            
            try {
                long dni = 0;
                double salary = 0;
                
                long id = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                String lastName = request.getParameter("lastname");
                if(!request.getParameter("dni").isEmpty()) dni = Long.parseLong(request.getParameter("dni"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String nationality = request.getParameter("nationality");
                String address = request.getParameter("address");
                String birthdate = request.getParameter("birthdate");
                if(!request.getParameter("salary").isEmpty()) salary = Double.parseDouble(request.getParameter("salary"));
                String hireDate = request.getParameter("hireDate");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String admin = request.getParameter("admin");
                
                control.editEmployee(id, name, lastName, dni, phone, email, nationality, address, birthdate, salary, hireDate, username, password, admin);
                
                mySession.setAttribute("res", "Success");
            } catch (Exception ex) {
                
                mySession.setAttribute("res", "Updating failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(action.equals("create")){
            
            try {
                long dni = 0;
                double salary = 0;
                
                String name = request.getParameter("name");
                String lastName = request.getParameter("lastname");
                if(!request.getParameter("dni").isEmpty()) dni = Long.parseLong(request.getParameter("dni"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String nationality = request.getParameter("nationality");
                String address = request.getParameter("address");
                String birthdate = request.getParameter("birthdate");
                if(!request.getParameter("salary").isEmpty()) salary = Double.parseDouble(request.getParameter("salary"));
                String hireDate = request.getParameter("hireDate");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String admin = request.getParameter("admin");
                
                control.createEmployee(name, lastName, dni, phone, email, nationality, address, birthdate, salary, hireDate, username, password, admin);
                
                mySession.setAttribute("res", "Success");
                
            } catch (Exception ex) {
                mySession.setAttribute("res", "Creation failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(action.equals("delete")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            try {
                //LOGIC DELETE
                control.deleteEmployee(id);
            } catch (Exception ex) {
                Logger.getLogger(SvEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        employees = control.getActiveEmployees();
        
        mySession.setAttribute("employees", employees);
        mySession.setAttribute("action", "read");
        
        response.sendRedirect("employees.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
