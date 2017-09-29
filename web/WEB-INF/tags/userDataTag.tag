<%@ tag language="java" pageEncoding="UTF-8" %>

<%@ attribute name="users" type="java.util.List" %>
<%@ attribute name="user" type="ua.nure.zhazhky.SummaryTask4.model.User" %>

<%@include file="/WEB-INF/jspf/taglib.jspf" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<c:if test="${not empty users}">
    <table class="table table-inbox table-hover">
        <h5><fmt:message key="page.lang.list.registered.users"/></h5>
        <tbody>
        <div class="method">
            <div class="row margin-0 list-header hidden-sm hidden-xs">
                <div class="col-md-4">
                    <div class="header"><fmt:message key="page.lang.login"/></div>
                </div>
                <div class="col-md-4">
                    <div class="header"><fmt:message key="page.lang.fullName"/></div>
                </div>
                <div class="col-md-4">
                    <div class="header"><fmt:message key="page.lang.blocked"/></div>
                </div>
            </div>
            <c:forEach items="${sessionScope.users}" var="user">
            <div class="row margin-0">
                <div class="col-md-4">
                    <div class="cell">
                        <div class="propertyname">
                            <code>${user.login}</code>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="cell">
                        <div class="type">
                                ${user.fullName}
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="cell">
                        <div class="type">
                            <c:if test="${user.isBlocked() == true}">
                                Blocked
                            </c:if>
                            <c:if test="${user.isBlocked() == false}">
                                Free
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
        </tbody>
    </table>
</c:if>