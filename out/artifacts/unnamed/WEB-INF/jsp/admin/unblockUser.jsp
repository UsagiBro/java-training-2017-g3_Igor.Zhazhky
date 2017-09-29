<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 27.09.2017
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Unblock user" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../navbar.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <div class=" col-lg-4 col-sm-4 col-sm-offset-4">
        <div class="panel panel-info">
            <div class="panel-heading">New account</div>
            <div class="panel-body">
                <form action="/controller" method="post">
                    <input type="hidden" name="command" value="unblockUser">
                    <br>
                    <div class="row">
                        <c:if test="${fn:length(sessionScope.users) != 0}">
                            <label class="col-md-4 control-label" for="selectbasic">Choose user</label>
                            <div class="col-md-8">
                                <select id="selectbasic" name="login" class="form-control">
                                    <c:forEach items="${sessionScope.users}" var="user">
                                        <c:if test="${user.isBlocked() == true}">
                                            <option value="${user.login}">
                                                    ${user.login}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-success btn-block">
                                <fmt:message
                                        key="page.lang.confirm"/>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <div class="col-md-12">
                        <form action="/controller">
                            <input type="hidden" name="command" value="toAdminCabinet">
                            <button type="submit" class="btn btn-danger btn-block">
                                <fmt:message
                                        key="page.lang.cancel"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<u:permit role="admin"/>
</body>
</html>
