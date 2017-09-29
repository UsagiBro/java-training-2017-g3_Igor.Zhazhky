<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 26.09.2017
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../navbar.jsp" %>
<div class="mail-box">
    <aside class="sm-side">
        <div class="user-head">
            <h5><fmt:message key="page.lang.admin"/>: ${sessionScope.user.login}</h5>
            <span>${sessionScope.user.fullName}</span>
        </div>
        <div class="inbox-body">
            <form action="/controller">
                <input type="hidden" name="command" value="toBlockUser">
                <button data-toggle="modal" title="Compose"
                        <c:if test="${fn:length(sessionScope.users) == 0}">disabled </c:if>
                        class="btn btn-compose">
                    <fmt:message key="page.lang.block.user"/>
                </button>
            </form>
            <form action="/controller">
                <input type="hidden" name="command" value="toUnblockUser">
                <button data-toggle="modal" title="Compose"
                        <c:if test="${fn:length(sessionScope.users) == 0}">disabled </c:if>
                        class="btn btn-compose-warning">
                    <fmt:message key="page.lang.unblock.user"/>
                </button>
            </form>
            <form action="/controller">
                <input type="hidden" name="command" value="toUnblockAccount">
                <button data-toggle="modal" title="Compose"
                        <c:if test="${fn:length(sessionScope.accounts) == 0}">disabled </c:if>
                        class="btn btn-compose-warning">
                    <fmt:message key="page.lang.unblock.account"/>
                </button>
            </form>
        </div>
        <div class="inbox-body text-center">
            <div class="btn-group">
                <div class="btn mini btn-primary" style="cursor: default;" disabled></div>
            </div>
            <div class="btn-group">
                <div class="btn mini btn-success" style="cursor: default;" disabled></div>
            </div>
            <div class="btn-group">
                <div class="btn mini btn-info" style="cursor: default;" disabled></div>
            </div>
        </div>

    </aside>
    <aside class="lg-side">
        <div class="inbox-head">
            <h3>
                <fmt:message key="page.lang.welcome.admin"/>
            </h3>
        </div>
        <div class="inbox-body">
            <div class="col-md-7">
                <u:search users="${requestScope.users}" user="${sessionScope.user}"/>
            </div>
            <div class="col-md-5">
                <table class="table table-inbox table-hover">
                    <tbody>
                    <h5><fmt:message key="page.lang.list.blocked.accounts"/></h5>
                    <div class="method">
                        <div class="row margin-0 list-header hidden-sm hidden-xs">
                            <div class="col-md-6">
                                <div class="header"><fmt:message key="page.lang.account"/></div>
                            </div>
                            <div class="col-md-6">
                                <div class="header"><fmt:message key="page.lang.account.type"/></div>
                            </div>
                        </div>
                        <c:forEach items="${sessionScope.accounts}" var="account">
                        <c:if test="${account.isBlocked() == true}">
                        <div class="row margin-0">
                            <div class="col-md-6">
                                <div class="cell">
                                    <div class="propertyname">
                                            ${account.accountId}
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="cell">
                                    <div class="type">
                                        <code>${account.name}</code>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </aside>
</div>
</div>
</div>
<u:permit role="admin"/>
</body>
</html>
