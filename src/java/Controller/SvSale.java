package Controller;

import Entity.Customer;
import Entity.Sale;
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

@WebServlet(name = "SvSale", urlPatterns = {"/SvSale"})
public class SvSale extends HttpServlet {
    private final Controller control = Controller.getInstance();
    private List<Sale> sales;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("link")){
            
            sales = control.getActiveSales();
                
            mySession.removeAttribute("res");
            mySession.setAttribute("sales", sales);
            
            response.sendRedirect("sales.jsp"); 
            
        }else if(action.equals("get-data")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            Sale sale = control.getSale(id);
            
            Customer customer = sale.getCustomer();
            
            List<Service> includedServices = sale.getServices();
            List<Package> includedPackages = sale.getPackages();
            List<Package> packages = control.getActivePackages();
            List<Service> services = control.getActiveServices();
            
            mySession.setAttribute("customer", customer);
            mySession.setAttribute("services", services);
            mySession.setAttribute("packages", packages);
            
            mySession.setAttribute("includedServices", includedServices);
            mySession.setAttribute("includedPackages", includedPackages);
            
            mySession.setAttribute("sale", sale);
            mySession.setAttribute("action", "edit");             
            
            response.sendRedirect("new-sale.jsp");
        }
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("create")){
            
            try {
                
                long cusId = Long.parseLong(request.getParameter("cusId"));
                long empId = Long.parseLong(request.getParameter("empId"));
                String[] idServs = request.getParameterValues("checkbox-s");
                String[] idPacks = request.getParameterValues("checkbox-p");
                String payMethod = request.getParameter("payment-method");
                
                if(idServs.length > 0 || idPacks.length > 0){
                    control.createSale(cusId, empId, idPacks, idServs, payMethod);
                    mySession.setAttribute("res", "Success");
                }else{
                    mySession.setAttribute("res", "Fail: Empty values");
                }
            } catch (Exception ex) {
                mySession.setAttribute("res", "Creation failed");
                Logger.getLogger(SvSale.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            mySession.removeAttribute("customer");
            
        }else if( action.equals("edit")){
             
            try {
                
                long saleId = Long.parseLong(request.getParameter("id"));
                long cusId = Long.parseLong(request.getParameter("cusId"));
                long empId = Long.parseLong(request.getParameter("empId"));
                String[] idServs = request.getParameterValues("checkbox-s");
                String[] idPacks = request.getParameterValues("checkbox-p");
                String payMethod = request.getParameter("payment-method");
                
                if(idServs.length > 0 || idPacks.length > 0){
                    control.editSale( saleId, cusId, empId, idPacks, idServs, payMethod);
                    mySession.setAttribute("res", "Success");
                }else{
                    mySession.setAttribute("res", "Fail: Empty values");
                }
                
            } catch (Exception ex) {
                mySession.setAttribute("res", "Updating failed");
                Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
        }else if( action.equals("delete")){
            
            long id = Long.parseLong(request.getParameter("id"));
            
            try {
                //LOGIC DELETE
                control.deleteSale(id);
                mySession.setAttribute("res", "success");
            } catch (Exception ex) {
                mySession.setAttribute("res", "fail");
                Logger.getLogger(SvSale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        sales = control.getActiveSales();
        mySession.setAttribute("sales", sales);
        response.sendRedirect("sales.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
