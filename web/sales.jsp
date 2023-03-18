<%@page import="Entity.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/styles/style.css">
        <title>Sales</title>
    </head>
    <body>
        
        <%        
            HttpSession mySession = request.getSession();
            Employee user = (Employee)mySession.getAttribute("user");

            if(user == null){
                response.sendRedirect("index.jsp");
            }
        %>
         
        <div class="dashboard-container" data-section="sales">
            
            <aside class="no-printable aside">
                <div class="content-aside">
                    <div class="aside-title-box">
                        <img id="logo-menu" src="assets/img/logo4.png" alt="">
                        <h3>Travel Ipsum</h3>
                    </div>
                    
                    <hr>
                    
                    <nav>
                        
                        <div class="main-nav">
                            <form action="SvIndex" method="GET" class="link-nav new-sale">
                                <input type="hidden" name="action" value="link"> 
                                <img src="assets/img/newSale.png" alt="">
                                <input type="submit" value="New Sale">
                            </form>
                            <form action="SvCustomer" method="GET" class="link-nav ">
                                <input type="hidden" name="action" value="link"> 
                                <img src="assets/img/customer.png" alt="">
                                <input type="submit" value="Customers">
                            </form>
                            <form action="SvSale" method="GET" class="link-nav selected">
                                <input type="hidden" name="action" value="link"> 
                                <img src="assets/img/sales.png" alt="">
                                <input type="submit" value="Sales">
                            </form>
                        </div>
                        
                        <c:if test="${user.getUser().isAdmin()}">
                            
                            <div class="admin-nav">
                                <form action="SvEmployee" method="GET" class="link-nav">
                                    <input type="hidden" name="action" value="link"> 
                                    <img src="assets/img/employee.png" alt="">
                                    <input type="submit" value="Employees">
                                </form>
                                <form action="SvService" method="GET" class="link-nav">
                                    <input type="hidden" name="action" value="link"> 
                                    <img src="assets/img/service.png" alt="">
                                    <input type="submit" value="Services">
                                </form>
                                <form action="SvPackage" method="GET" class="link-nav">
                                    <input type="hidden" name="action" value="link">
                                    <img src="assets/img/pack.png" alt="">
                                    <input type="submit" value="Packages">
                                </form>
                                <form action="SvEarns" method="GET" class="link-nav">
                                    <input type="hidden" name="action" value="link">
                                    <img src="assets/img/earns.png" alt="">
                                    <input type="submit" value="Earns">
                                </form>
                            </div>
                        
                        </c:if>

                    </nav>
                        
                    <form action="SvLogin" method="POST" class="log-out-box">
                        <input type="hidden" name="action" value="logout">
                        <input id="log-out" type="submit" value="LOG OUT">
                    </form>

                </div>
            </aside>
            
            <main class="dashboard-main">
                <header class="no-printable">
                    <div id="hamb-menu-box">
                        <div class="hamb-menu-top hamb-menu-bar"></div>
                        <div class="hamb-menu-middle-top hamb-menu-bar"></div>
                        <div class="hamb-menu-middle-bottom hamb-menu-bar"></div>    
                        <div class="hamb-menu-bottom hamb-menu-bar"></div>
                    </div> 
                    
                    <button id="header-btn"> ${user.getFullName().toUpperCase()} </button>
                </header>

                <div class="container">
                    
                    <div class="crud-container">
                        
                        <div class="crud-title">
                            <h2 id="crud-title-h2" data-servlet="SvSale">SALES</h2>
                            <h2>${res}</h2>
                        </div>
                        <div class="crud-top">
                            <input id="crud-filter" type="search" placeholder="Filter...">
                        </div>
                        <div class="crud-bottom">

                            <table class="crud-table">
                                <thead>
                                    <tr>
                                        
                                        <th class="crud-th">ID</th>
                                        <th class="crud-th">PRICE</th>
                                        <th class="crud-th">P.METHOD</th>
                                        <th class="crud-th">CUSTOMER</th>
                                        <th class="crud-th">EMPLOYEE</th>
                                        <th class="crud-th">PACKAGES</th>
                                        <th class="crud-th">SERVICES</th>
                                        <th class="crud-th">DATE</th>
                                       
                                        <th class="crud-th" >ACTION</th>
                                        
                                    </tr>
                                </thead>

                               <tbody>
                                   
                                <c:forEach var="sale" items="${sales}">
                                    
                                    <tr data-row data-id="${sale.getId()}">    
                                        
                                        <td data-filter>${sale.getId()}</td>
                                        <td>${sale.getPrice()}</td>
                                        <td>${sale.getPaymentMethod()}</td>
                                        <td data-filter>${sale.getCustomer().getId()}</td>
                                        <td data-filter>${sale.getEmployee().getId()}</td>
                                        <td>${sale.getStrPackages()}</td>
                                        <td>${sale.getStrServices()}</td>
                                        <td data-filter>${sdf.format(sale.getDate())}</td>
                                        
                                        <td class="crud-td">
                                            <div>
                                                <button class="crud-delete crud-btn">
                                                    <img src="assets/img/delete.png" alt="">
                                                </button>
                                            </div>
                                            <form action="SvSale" method="GET">
                                                <input type="hidden" name="action" value="get-data">
                                                <input type="hidden" name="id" value="${sale.getId()}">
                                                <button class="crud-edit crud-btn" type="submit">
                                                    <img src="assets/img/edit.png" alt="">
                                                </button>
                                            </form>
                                        </td>
                                        
                                    </tr>
                                    
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.4/gsap.min.js"></script>
    <script type="module" src="scripts/script.js"></script>
    </body>
</html>
