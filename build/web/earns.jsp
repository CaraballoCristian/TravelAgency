<%@page import="Entity.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/styles/style.css">
        <title>Earns</title>
    </head>
    <body>
             
        <%
            HttpSession mySession = request.getSession();
            Employee user = (Employee)mySession.getAttribute("user");

            if(user == null || !user.getUser().isAdmin()){
                response.sendRedirect("index.jsp");
            }
        %>
    
        <div class="dashboard-container" data-section="earns">
            <!-- ///////////////////////////////////// ASIDE ///////////////////////////////////// -->
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
                            <form action="SvSale" method="GET" class="link-nav">
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
                                <form action="SvEarns" method="GET" class="link-nav selected">
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
                    
                    <div class="earn-container">

                        <div class="earn-stats">

                            <div class="earn-stats-top">
                                <div class="earn-card">
                                    <div class="earn-card-title-box">
                                        <h3>TODAY</h3>
                                    </div>
                                    <div class="amount-box">
                                        <h3>$${earns[0]}.-</h3>
                                    </div>
                                </div>

                                <div class="earn-card">
                                    <div class="earn-card-title-box">
                                        <h3>THIS MONTH</h3>
                                    </div>
                                    <div class="amount-box">
                                        <h3>$${earns[1]}.-</h3> 
                                    </div>
                                </div>
                            </div>

                            <div class="earn-stats-bottom">
                                <div class="earn-card">
                                    <div class="earn-card-title-box">
                                        <h3>THIS YEAR</h3>
                                    </div>
                                    <div class="amount-box">
                                        <h3>$${earns[2]}.-</h3>
                                    </div>
                                </div>

                                <div class="earn-card">
                                    <div class="earn-card-title-box">
                                        <h3>ALL</h3>
                                    </div>
                                    <div class="amount-box">
                                        <h3>$${earns[3]}.-</h3>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="earn-bottom-box">
                            <form id="earn-form" action="SvEarns" method="POST" class="earn-form">
                                <input type="date" name="earn-date1">
                                <input type="date" name="earn-date2">
                                <input type="hidden" name="action" value="filter">
                                <input id="earns-filter-btn" type="submit" value="FILTER">
                            </form>

                            <div class="earn-bottom-right">
                                    <p>${phrase}</p>
                                <div class="earn-result-box">
                                    <c:choose>
                                        <c:when test="${amount != null}">
                                            <h2>$${amount}.-</h2>
                                        </c:when>
                                        <c:when test="${amount == null}">
                                            <h2>$0.-</h2>
                                        </c:when>
                                        
                                    </c:choose>
                                </div>
                            </div>
                    </div>
                </div>
            </main>
        </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.4/gsap.min.js"></script>
    <script type="module" src="scripts/script.js"></script>                            
    </body>
</html>
