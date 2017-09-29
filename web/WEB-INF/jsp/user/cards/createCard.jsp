<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 19.09.2017
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="New card" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../../navbar.jsp" %>
<c:if test="${not empty sessionScope.user}">

    <div class=" col-lg-4 col-sm-4 col-sm-offset-4">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="page.lang.create.new.card"/></div>
            <div class="panel-body">
                <form method="post" action="/controller">
                    <input type="hidden" name="command" value="createCard">
                    <div class="row">
                        <c:if test="${fn:length(sessionScope.accounts) != 0}">
                            <label class="col-md-4 control-label" for="selectbasic"><fmt:message
                                    key="page.lang.choose.an.account.to.deposit"/></label>
                            <div class="col-md-8">
                                <select id="selectbasic" name="accountId" class="form-control">
                                    <c:forEach items="${sessionScope.accounts}" var="account">
                                        <c:if test="${account.isBlocked() == false}">
                                            <option value="${account.accountId}">
                                                    ${account.accountId}(${account.name})
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                        <c:if test="${fn:length(sessionScope.accounts) == 0}">
                            <div class="oaerror danger">
                                <strong><fmt:message key="page.lang.create.to.attach"/></strong>
                            </div>
                        </c:if>
                    </div>
                    <br>
                    <div class="row">
                        <label class="col-md-4 control-label" for="textinput">Card number</label>
                        <div class="col-md-8">
                            <input id="textinput" name="cardId" type="text" value="${sessionScope.cardNumber}"
                                   class="form-control input-md" readonly>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <label class="col-md-4 control-label" for="textinput">Card PIN</label>
                        <div class="col-md-8">
                            <input name="password" type="password" placeholder="pin-code(4 digits)"
                                   class="form-control input-md" pattern="(\d){4}"
                                   title="PIN-code must consist of 4 numbers">
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit"
                                    <c:if test="${fn:length(sessionScope.accounts) == 0}">disabled </c:if>
                                    class="btn btn-success btn-block">
                                <fmt:message
                                        key="page.lang.confirm"/>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <div class="col-md-12">
                        <form action="/controller">
                            <input type="hidden" name="command" value="toCabinet">
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
</body>
</html>
