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
        <title>Services</title>
    </head>
    <body>
        
        <%
            HttpSession mySession = request.getSession();
            Employee user = (Employee)mySession.getAttribute("user");

            if(user == null || !user.getUser().isAdmin()){
                response.sendRedirect("index.jsp");
            }
        %>
        
        
        <div class="dashboard-container" data-section="services">
            
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
                                <form action="SvService" method="GET" class="link-nav selected">
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
                    
                <c:choose>
                    <c:when test = "${action.equals('edit')}">
                        
                        <div class="bg-span visible" >
                            <div class="div-form">

                                <form id="asd" action="SvService" method="POST"> 

                                    <h4>EDIT SERVICE</h4> 

                                    <div class="form-wrapper">
                                        <label for="name">Name: 
                                            <input required type="text" name="name" placeholder="Name.." value="${service.getName()}">
                                        </label>
                                        <label for="description">Description: 
                                            <textarea required class="textarea" name="description" placeholder="Description.." rows="10" cols="30">${service.getDescription()}</textarea>
                                        </label>
                                        <label for="price">Price: 
                                            <input required type="text" name="price" placeholder="Price.." value="${service.getPrice()}">
                                        </label>
                                        <label for="serviceDate">Service Date:
                                            <input required type="text" name="serviceDate" placeholder="Date (DD-MM-YYYY).." value="${sdf.format(service.getServiceDate())}">
                                        </label>

                                    </div>

                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="id" value="${service.getId()}">
                                    <input type="submit" value="SUBMIT">
                                </form>

                            </div>
                        </div>
                            
                    </c:when>
                    
                    <c:when test = "${action.equals('create')}">
                        
                        <div class="bg-span visible" >
                            <div class="div-form">

                                <form id="asd" action="SvService" method="POST"> 

                                    <h4>CREATE SERVICE</h4> 

                                    <div class="form-wrapper">
                                        <label for="name">Name: 
                                            <input required type="text" name="name" placeholder="Name..">
                                        </label>
                                        <label for="description">Description: 
                                            <textarea required class="textarea" name="description" placeholder="Description.." rows="10" cols="30"></textarea>
                                        </label>
                                        <label for="price">Price: 
                                            <input required type="text" name="price" placeholder="Price..">
                                        </label>
                                        <label for="serviceDate">Service Date :
                                            <input required type="text" name="serviceDate" placeholder="Date (DD-MM-YYYY)..">
                                        </label>

                                    </div>

                                    <input type="hidden" name="action" value="create">
                                    <input type="hidden" name="id" value="${user.getId()}">
                                    <input type="submit" value="SUBMIT">
                                </form>

                            </div>
                        </div>
                            
                    </c:when>
                    
                </c:choose>

                        <div class="crud-title">
                            <h2 id="crud-title-h2" data-servlet="SvService">SERVICES</h2>
                            <h2>${res}</h2>
                        </div>
                        <div class="crud-top">
                            <input id="crud-filter" type="search" placeholder="Filter...">
                            <form action="SvService" method="GET">
                                <input type="hidden" name="action" value="create">
                                <button id="crud-add-btn" type="submit"><img src="assets/img/newSale.png" alt=""></button>
                            </form>
                        </div>
                        <div class="crud-bottom">
                            
                            <table class="crud-table">
                                <thead>
                                    <tr>
                                        <th class="crud-th">ID</th>
                                        <th class="crud-th">NAME</th>
                                        <th class="crud-th">DESCRIPTION</th>
                                        <th class="crud-th">PRICE</th>
                                        <th class="crud-th">PACKAGES</th>
                                        <th class="crud-th">SERVICE DATE</th>
                                        <th class="crud-th">SALES COUNT</th>
                                        
                                        <th class="crud-th" >ACTION</th>
                                    </tr>
                                </thead>

                               <tbody>
                                   
                               <c:forEach var="s" items="${services}">
                                   
                                   <tr data-row data-id="${s.getId()}">
                                       
                                       <td data-filter>${s.getId()}</td>
                                       <td data-filter>${s.getName()}</td>
                                       <td data-filter>${s.getDescription()}</td>
                                       <td>$${s.getPrice()}.-</td>
                                       <td>${s.getStrPackages()}</td>
                                       <td data-filter>${sdf.format(s.getServiceDate())}</td>
                                       <td>${s.getSalesCount()}</td>
                                       
                                       <td class="crud-td">
                                            <div>
                                                <button class="crud-delete crud-btn">
                                                    <img src="assets/img/delete.png" alt="">
                                                </button>
                                            </div>
                                            <form action="SvService" method="GET">
                                                <input type="hidden" name="action" value="get-data">

                                                <input type="hidden" name="id" value="${s.getId()}">
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
