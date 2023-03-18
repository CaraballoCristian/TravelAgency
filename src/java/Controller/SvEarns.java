package Controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvEarns", urlPatterns = {"/SvEarns"})
public class SvEarns extends HttpServlet {

    private final Controller control = Controller.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("link")){
            
            double[] earns = control.getEarns();
            
            mySession.removeAttribute("res");
            mySession.setAttribute("earns", earns);
            
            response.sendRedirect("earns.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        HttpSession mySession = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action.equals("filter")){
            
            String date1 = request.getParameter("earn-date1");
            String date2 = request.getParameter("earn-date2");
            
            double amount = 0;
            try {
                amount = control.getFilteredAmount(date1, date2);
            } catch (ParseException ex) {
                Logger.getLogger(SvEarns.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            mySession.setAttribute("amount", amount);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            
            String strDt1 = "";
            String strDt2 = "";
            
            if(!date1.isEmpty()){
                Date dt1 = null;
                try {
                    dt1 = sdf.parse(date1);
                } catch (ParseException ex) {
                    Logger.getLogger(SvEarns.class.getName()).log(Level.SEVERE, null, ex);
                }
                strDt1 = sdf1.format(dt1);
            }
            if(!date2.isEmpty()){
                Date dt2 = null;
                try {
                    dt2 = sdf.parse(date2);
                } catch (ParseException ex) {
                    Logger.getLogger(SvEarns.class.getName()).log(Level.SEVERE, null, ex);
                }
                strDt2 = sdf1.format(dt2);
            }
            
            String phrase;
            
            if(!date1.isEmpty() && date1.equals(date2)){
                phrase = "EARNS FOR "+strDt1+" ARE:";
            }else if(!date1.equals("") && !date2.equals("")){
                phrase = "EARNS FROM "+strDt1+"  TO "+strDt2+" ARE:";
            }else if(!date1.equals("") && date2.equals("")){
                phrase = "EARNS FROM "+strDt1+"  TO THE PRESENT DAY ARE:";
            }else if(date1.equals("") && !date2.equals("")){
                phrase = "EARNS FROM THE BEGINNING TO "+strDt2+" ARE:";
            }else{
                phrase = "EARNS FROM THE BEGINNING TO THE PRESENT DAY ARE:";
            }
            
            mySession.setAttribute("phrase", phrase);
            response.sendRedirect("earns.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
