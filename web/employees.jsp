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
        <title>Employees</title>
    </head>
    <body>
        
        <%
            HttpSession mySession = request.getSession();
            Employee user = (Employee)mySession.getAttribute("user");

            if(user == null || !user.getUser().isAdmin()){
                response.sendRedirect("index.jsp");
            }
        %>
        
        <div class="dashboard-container" data-section="employees">
            
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
                                <form action="SvEmployee" method="GET" class="link-nav selected">
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
                        
                <c:choose>
                    <c:when test = "${action.equals('edit')}">    
                        
                        <div class="bg-span visible" >
                            <div class="div-form">

                                <form id="asd" action="SvEmployee" method="POST"> 

                                    <h4>EDIT EMPLOYEE</h4> 

                                    <div class="form-wrapper">
                                        <label for="name">Name: 
                                            <input required type="text" name="name" placeholder="Name.." value="${employee.getName()}">
                                        </label>
                                        <label for="lastname">Last Name: 
                                            <input required type="text" name="lastname" placeholder="LastName.." value="${employee.getLastName()}">
                                        </label>
                                        <label for="dni">DNI: 
                                            <input required type="text" name="dni" placeholder="DNI.." value="${employee.getDni()}">
                                        </label>
                                        <label for="phone">Phone: 
                                            <input required type="text" name="phone" placeholder="Phone.." value="${employee.getPhone()}">
                                        </label>
                                        <label for="email">Email: 
                                            <input required type="text" name="email" placeholder="email.." value="${employee.getEmail()}">
                                        </label>
                                        <label for="nationality">Nationality: 
                                            <input required type="text" name="nationality" placeholder="Nationality.." value="${employee.getNationality()}">
                                        </label>
                                        <label for="address">Address: 
                                            <input required type="text" name="address" placeholder="Address..." value="${employee.getAddress()}">
                                        </label>
                                        <label for="birthdate">Birthdate: 
                                            <input required type="text" name="birthdate" placeholder="Birthdate (DD-MM-YYYY).." value="${sdf.format(employee.getBirthdate())}">
                                        </label>
                                        <label for="salary">Salary:  
                                            <input required type="text" name="salary" placeholder="Salary.." value="${employee.getSalary()}">
                                        </label>
                                        <label for="hireDate">Hire Date:  
                                            <input required type="text" name="hireDate" placeholder="hireDate (DD-MM-YYYY).." value="${sdf.format(employee.getHireDate())}">
                                        </label>
                                        
                                        <hr>
                                          
                                        <label for="username">Username: 
                                            <input required type="text" name="username" placeholer="Username..." value="${employee.getUser().getUsername()}">
                                        </label>
                                        <label for="password">Password: 
                                            <input class="inputPass" type="passoword" name="password"  placeholder="Insert New Password..">
                                        </label>
                                        <label for="admin">Type: 
                                            <select name="admin">
                                                <c:choose>
                                                    <c:when test="${employee.getUser().isAdmin()}">
                                                        <option value="SELLER">SELLER</option>
                                                        <option value="ADMIN" selected>ADMIN</option>
                                                    </c:when>
                                                    <c:when test="${!employee.getUser().isAdmin()}">
                                                        <option value="SELLER" selected>SELLER</option>
                                                        <option value="ADMIN">ADMIN</option>
                                                    </c:when>
                                                </c:choose>
                                                <option value="SELLER">SELLER</option>
                                                <option value="ADMIN">ADMIN</option>
                                            </select>
                                        </label>
                                        
                                    </div>
                                    
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="id" value="${employee.getId()}">
                                    <input type="submit" value="SUBMIT">
                                </form>
                                    
                            </div>
                        </div>
                                    
                    </c:when>
                    
                    <c:when test = "${action.equals('create')}">    
                        
                        <div class="bg-span visible" >
                            <div class="div-form">

                                <form id="asd" action="SvEmployee" method="POST"> 

                                    <h4>CREATE EMPLOYEE</h4> 

                                    <div class="form-wrapper">
                                        <label for="name">Name: 
                                            <input required type="text" name="name" placeholder="Name..">
                                        </label>
                                        <label for="lastname">Last Name: 
                                            <input required type="text" name="lastname" placeholder="LastName..">
                                        </label>
                                        <label for="dni">DNI: 
                                            <input required type="text" name="dni" placeholder="DNI..">
                                        </label>
                                        <label for="phone">Phone: 
                                            <input required type="text" name="phone" placeholder="Phone..">
                                        </label>
                                        <label for="email">Email: 
                                            <input required type="text" name="email" placeholder="email..">
                                        </label>
                                        <label for="nationality">Nationality: 
                                            <input required type="text" name="nationality" placeholder="Nationality..">
                                        </label>
                                        <label for="address">Address: 
                                            <input required type="text" name="address" placeholder="Address...">
                                        </label>
                                        <label for="birthdate">Birthdate: 
                                            <input required type="text" name="birthdate" placeholder="Birthdate (DD-MM-YYYY)..">
                                        </label>
                                        <label for="salary">Salary:  
                                            <input required type="text" name="salary" placeholder="Salary..">
                                        </label>
                                        <label for="hireDate">Hire Date:  
                                            <input required type="text" name="hireDate" placeholder="Hire Date (DD-MM-YYYY)..">
                                        </label>
                                        
                                        <hr>
                                          
                                        <label for="username">Username: 
                                            <input required type="text" name="username" placeholder="Username..." >
                                        </label>
                                        <label for="password">Password: 
                                            <input required class="inputPass" type="password" name="password" placeholder="Password.." autocomplete="new-password" >
                                        </label>
                                        </label>
                                        <label for="admin">Type: 
                                            <select name="admin">
                                                <option value="SELLER">SELLER</option>
                                                <option value="ADMIN">ADMIN</option>
                                            </select>
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
                            <h2 id="crud-title-h2" data-servlet="SvEmployee">EMPLOYEES</h2>
                            <h2>${res}</h2>
                        </div>
                        <div class="crud-top">
                            <input id="crud-filter" type="search" placeholder="Filter...">
                            <form action="SvEmployee" method="GET">
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
                                        <th class="crud-th">DNI</th>
                                        <th class="crud-th">PHONE</th>
                                        <th class="crud-th">EMAIL</th>
                                        <th class="crud-th">NATIONALITY</th>
                                        <th class="crud-th">ADDRESS</th>
                                        <th class="crud-th">BIRTHDATE</th>
                                        <th class="crud-th">SALARY</th>
                                        <th class="crud-th">USER</th>
                                        <th class="crud-th">H.DATE</th>
                                        <th class="crud-th">ADMIN</th>
                                        
                                        <th class="crud-th" >ACTION</th>
                                    </tr>
                                </thead>

                                <tbody>    
                                        
                                    <c:forEach var="e" items="${employees}">

                                        <tr data-row data-id="${e.getId()}">    

                                            <td data-filter>${e.getId()}</td>
                                            <td data-filter>${e.getFullName()}</td>
                                            <td data-filter>${e.getDni()}</td>
                                            <td>${e.getPhone()}</td>
                                            <td>${e.getEmail()}</td>
                                            <td>${e.getNationality()}</td>
                                            <td>${e.getAddress()}</td>
                                            <td>${sdf.format(e.getBirthdate())}</td>
                                            <td>$${e.getSalary()}.-</td>
                                            <td>${e.getUser().getUsername()}</td>
                                            <td>${sdf.format(e.getHireDate())}</td>
                                            <td>${e.getUser().isAdmin()}</td>

                                            <td class="crud-td">
                                                <div>
                                                    <button class="crud-delete crud-btn">
                                                        <img src="assets/img/delete.png" alt="">
                                                    </button>
                                                </div>
                                                <form action="SvEmployee" method="GET">
                                                    <input type="hidden" name="action" value="get-data">

                                                    <input type="hidden" name="id" value="${e.getId()}">
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
