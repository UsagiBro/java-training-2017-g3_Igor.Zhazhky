<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 27.09.2017
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="New account" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../navbar.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="updateUser">
        <div class="col-lg-6 col-sm-7 col-sm-offset-3">
            <div id="contact-form" class="form-container" data-form-container>
                <div class="row">
                    <div class="form-title">
                        <span>Update data</span>
                    </div>
                </div>
                <div class="input-container">
                    <div class="row">
					<span class="req-input">
                        <span class="input-status" data-toggle="tooltip" data-placement="top"> </span>
						<input type="text" name="login" class="form-control input-sm"
                               placeholder="Login" pattern="(\pL){3,16}" title="user login must contain only latters" +
                                "and be longer than 3 symbols">
					</span>
                    </div>
                    <div class="row">
					<span class="req-input">
                        <span class="input-status" data-toggle="tooltip" data-placement="top"> </span>
						<input type="text" name="fullname" class="form-control input-sm"
                               placeholder="Full name" pattern="(\pL|\s){4,32}"
                               title="full name consist only letters and spaces">
					</span>
                    </div>
                    <div class="row submit-row">
                        <button type="submit" class="btn btn-block submit-form">
                            <fmt:message
                                    key="page.lang.confirm"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</c:if>
</body>
</html>