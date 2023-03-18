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
        <title>New Sale</title>
    </head>
    <body>        
        
        <%        
            HttpSession mySession = request.getSession();
            Employee user = (Employee)mySession.getAttribute("user");

            if(user == null){
                response.sendRedirect("index.jsp");
            }
        %>
        
        <div class="dashboard-container" data-section="newSale">
            
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

                    <div class="add">

                <c:choose>
                    <c:when test="${action.equals('create')}">
                        <div class="form-box no-printable">
                            <div class="bill-form">
                                <h2>CREATE SALE</h2>   
                                
                                <form action="SvCustomer" method="GET" class="sale-client-top">
                                    <input required type="text" name="dni" id="client-search" placeholder="Search by DNI...">
                                    <input type="hidden" name="action" value="search-dni">
                                    <input type="submit" value="SEARCH">
                                </form>

                                <div class="sale-client-bottom">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>DNI</th>
                                                <th>NAME</th>
                                                <th>LAST NAME</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:choose>
                                                <c:when test = "${customer != null}">
                                                    <tr>    
                                                        <td>${customer.getId()}</td>
                                                        <td>${customer.getDni()}</td>
                                                        <td>${customer.getName()}</td>
                                                        <td>${customer.getLastName()}</td>
                                                    </tr>
                                                </c:when>
                                                <c:when test = "${customer == null}">
                                                    <tr>    
                                                        <td>-</td>                                          
                                                        <td>-</td>
                                                        <td>-</td>
                                                        <td>-</td>
                                                    </tr>
                                                </c:when>
                                            </c:choose>
                                        </tbody>
                                    </table>
                                </div>

                                <form action="SvSale" method="POST" id="sales-client-form">

                                    <input type="hidden" name="cusId" value="${customer.getId()}">
                                    <input type="hidden" name="empId" value="${user.getId()}">

                                    <div class="sale-service-top">
                                        <select name="" id="service-package-selector">
                                            <option value="SERVICE">SERVICE</option>
                                            <option value="PACKAGE">PACKAGE</option>
                                        </select>
                                        <input type="search" id="form-add-service-search" placeholder="Filter..."> 
                                    </div>

                                    <div class="sale-service-bottom">
                                        <table data-service>
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>NAME</th>
                                                    <th>DESCRIPTION</th>
                                                    <th>PRICE</th>
                                                    <th>ADD</th>
                                                </tr>
                                            </thead>
                                            <tbody id="filter-body">
                                               
                                            <c:forEach var="s" items="${services}">
                                                
                                                <tr data-row>    
                                                    <td data-filter>${s.getId()}</td>
                                                    <td data-filter>${s.getName()}</td>
                                                    <td data-filter>${s.getDescription()}</td>
                                                    <td>${s.getPrice()}</td>
                                                    <td>
                                                        <input class="input-bill-preview" type="checkbox" name="checkbox-s" value="${s.getId()}">           
                                                    </td>
                                                </tr>
                                                
                                            </c:forEach>
                                                
                                            </tbody>
                                        </table>

                                        <table data-package> 
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>NAME</th>
                                                    <th>SERVICES</th>
                                                    <th>PRICE</th>
                                                    <th>ADD</th>
                                                </tr>
                                            </thead>
                                            <tbody id="filter-body">
                                                
                                            <c:forEach var="p" items="${packages}">

                                                <tr data-row>    
                                                    <td data-filter>${p.getId()}</td>
                                                    <td data-filter>${p.getName()}</td>
                                                    <td data-filter>${p.getStrServicesNames()}</td>
                                                    <td>${p.getPrice()}</td>
                                                    <td>
                                                        <input class="input-bill-preview" type="checkbox" name="checkbox-p" value="${p.getId()}">           
                                                    </td>
                                                </tr>
                                                
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>

                                    <div class="sale-payment-method">
                                        <select name="payment-method" id="payment-select">
                                            <option value="CASH">CASH</option>
                                            <option value="DEBIT">DEBIT</option>
                                            <option value="CREDIT">CREDIT</option>
                                            <option value="VIRTUAL">VIRTUAL</option>
                                            <option value="TRANSFER">TRANSFERNCE</option>
                                        </select>
                                    </div>

                                    <div class="generate-bill-box">
                                        <input type="hidden" name="action" value="create">
                                        <input type="submit" value="GENERATE BILL" id="generate-bill">
                                    </div>                
                                </form>
                            </div>  
                        </div>
                    </c:when>
                    
                    <c:when test="${action.equals('edit')}">
                        <div class="form-box no-printable">
                            <div class="bill-form">
                                <h2 id="cacapedo">EDIT SALE</h2>   
                                
                                <form action="SvCustomer" method="GET" class="sale-client-top">
                                    <input required type="text" name="dni" id="client-search" placeholder="Search by DNI...">
                                    <input type="hidden" name="action" value="search-dni">
                                    <input type="submit" value="SEARCH">
                                </form>

                                <div class="sale-client-bottom">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>DNI</th>
                                                <th>NAME</th>
                                                <th>LAST NAME</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>    
                                                <td>${sale.getCustomer().getId()}</td>                                           
                                                <td>${sale.getCustomer().getDni()}</td>
                                                <td>${sale.getCustomer().getName()}</td>
                                                <td>${sale.getCustomer().getLastName()}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>

                                <form action="SvSale" method="POST" id="sales-client-form">

                                    <input type="hidden" name="cusId" value="${sale.getCustomer().getId()}">
                                    <input type="hidden" name="empId" value="${sale.getEmployee().getId()}">
                                    <input type="hidden" name="id" value="${sale.getId()}">

                                    <div class="sale-service-top">
                                        <select name="" id="service-package-selector">
                                            <option value="SERVICE">SERVICE</option>
                                            <option value="PACKAGE">PACKAGE</option>
                                        </select>
                                        <input type="search" id="form-add-service-search" placeholder="Filter..."> 
                                    </div>

                                    <div class="sale-service-bottom">
                                        <table data-service>
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>NAME</th>
                                                    <th>DESCRIPTION</th>
                                                    <th>PRICE</th>
                                                    <th>ADD</th>
                                                </tr>
                                            </thead>
                                            <tbody id="filter-body">
                                               
                                            <c:forEach var="s" items="${services}">
                                                
                                                <tr data-row>    
                                                    <td data-filter>${s.getId()}</td>
                                                    <td data-filter>${s.getName()}</td>
                                                    <td data-filter>${s.getDescription()}</td>
                                                    <td>${s.getPrice()}</td>
                                                    <td>
                                                        
                                                        <% boolean matchServ = false; %>
                                                        
                                                        <c:forEach var="i" items="${includedServices}">
                                                            <c:if test="${s.getId() == i.getId()}">
                                                                <% matchServ = true; %>
                                                            </c:if>
                                                        </c:forEach>
                                                        
                                                        <% if(matchServ){ %>
                                                            <input class="input-bill-preview" type="checkbox" name="checkbox-s" value="${s.getId()}" checked>
                                                        <%}else{%>
                                                        <input class="input-bill-preview" type="checkbox" name="checkbox-s" value="${s.getId()}" >
                                                        <%}%>
                                                        
                                                    </td>
                                                </tr>
                                                
                                            </c:forEach>
                                                
                                            </tbody>
                                        </table>

                                        <table data-package>
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>NAME</th>
                                                    <th>SERVICES</th>
                                                    <th>PRICE</th>
                                                    <th>ADD</th>
                                                </tr>
                                            </thead>
                                            <tbody id="filter-body">
                                                
                                            <c:forEach var="p" items="${packages}">

                                                <tr data-row>    
                                                    <td data-filter>${p.getId()}</td>
                                                    <td data-filter>${p.getName()}</td>
                                                    <td data-filter>${p.getStrServices()}</td>
                                                    <td>${p.getPrice()}</td>
                                                    <td>
                                                        
                                                        <% boolean matchPack = false; %>
                                                        
                                                        <c:forEach var="i" items="${includedPackages}">
                                                            <c:if test="${p.getId() == i.getId()}">
                                                                <% matchPack = true; %>
                                                            </c:if>
                                                        </c:forEach>
                                                        
                                                        <% if(matchPack){ %>
                                                            <input class="input-bill-preview" type="checkbox" name="checkbox-p" value="${p.getId()}" checked>
                                                        <%}else{%>
                                                            <input class="input-bill-preview" type="checkbox" name="checkbox-p" value="${p.getId()}">
                                                        <%}%>
                                                        
                                                    </td>
                                                </tr>
                                                
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>

                                    <div class="sale-payment-method">
                                        <select name="payment-method" id="payment-select"> 
                                            <% 
                                                String[] methods = {"CASH", "DEBIT", "CREDIT", "VIRTUAL", "TRANSFER"}; 
                                                pageContext.setAttribute("methods", methods);
                                            %>
                                            <c:forEach var="m" items="${methods}">
                                                
                                                <c:choose>
                                                    <c:when test="${m.equals(sale.getPaymentMethod())}">
                                                        <option value="${m}" selected>${m}</option>
                                                    </c:when>
                                                    <c:when test="${!m.equals(sale.getPaymentMethod())}">
                                                        <option value="${m}">${m}</option>
                                                    </c:when>
                                                </c:choose>
                                                
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="generate-bill-box">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="submit" value="GENERATE BILL" id="generate-bill">
                                    </div>                
                                </form>
                            </div>  
                        </div>
                    </c:when>
                    
                </c:choose>

                        <div class="bill-frame printable">
                            <div class="bill-top-details">
                                <div class="bill-logo-box">
                                    <img src="assets/img/logo4.png" alt="">
                                    <h3>Travel Ipsum</h3>
                                </div>
                                <div class="invoice-number-box">
                                    <label for="invoice-number" id="invoice-number-label">Sale N°</label>
                                    
                                    <c:choose>
                                        <c:when test="${action.equals('create')}">
                                            <input type="text" id="invoice-number" readonly value="${billNumber}">
                                        </c:when>
                                        <c:when test="${action.equals('edit')}">
                                            <input type="text" id="invoice-number" readonly value="${sale.getId()}">
                                        </c:when>
                                    </c:choose>
                                    
                                </div>

                            </div>
                            <div class="bill-details-box">
                                <div class="bill-agency-details">
                                    <h4>Travel Ipsum</h4>
                                    <p>Seller ID: ${user.getId()}</p>
                                    <p>Seller Name: ${user.getFullName()}</p>
                                    <p>Address: Av. 123 N°456. PB</p>
                                    <p>Email: traveipsum@email.com</p>
                                    <p>Tel: (123) 123 - 4567</p>
                                </div>
                                <div class="bill-customer-details">
                                    <h4>Customer</h4>
                                    <p>ID: ${customer.getId()}</p>
                                    <p>Name: ${customer.getFullName()}</p>
                                    <p>Address: ${customer.getAddress()}</p>
                                    <p>Email: ${customer.getEmail()}</p>
                                    <p>Tel: ${customer.getPhone()}</p>
                                </div>
                            </div>
                            <div class="bill-general-details">
                                <div class="bill-top-table-box">
                                    <table  class="bill-table" id="bill-table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>NAME</th>
                                                <th>DETAILS</th>
                                                <th>SUBTOTAL</th>
                                            </tr>
                                        </thead>
                                        <tbody id="bill-body"> 
                                            <!--dinamic data load-->
                                        </tbody>
                                    </table>
                                </div>
                                <table class="bill-table bill-table-total">
                                    <thead>
                                        <tr>
                                            <th>TOTAL</th>
                                            <th id="total-bill">
                                                <!--dinamic data load-->    
                                            </th>
                                        </tr>
                                    </thead>
                                </table>
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
