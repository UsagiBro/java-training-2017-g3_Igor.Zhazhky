<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 17.09.2017
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%@include file="/WEB-INF/jspf/navbarButtons/logo.jspf" %>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                    <%@include file="/WEB-INF/jspf/navbarButtons/createAccountButton.jspf" %>
                </li>
                <li>
                    <%@include file="/WEB-INF/jspf/navbarButtons/createPaymentButton.jspf" %>
                </li>
                <li>
                    <%@include file="/WEB-INF/jspf/navbarButtons/paymentManagementButton.jspf" %>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <%@include file="/WEB-INF/jspf/navbarButtons/adminCabinetButton.jspf" %>
                </li>
                <li>
                    <%@include file="/WEB-INF/jspf/navbarButtons/profileButton.jspf" %>
                </li>
                <li>
                    <%@include file="/WEB-INF/jspf/navbarButtons/changeLanguageButton.jspf" %>
                </li>
                <li class="active">
                    <%@include file="/WEB-INF/jspf/navbarButtons/exitButton.jspf" %>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
