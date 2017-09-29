<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 20.09.2017
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Deliver new payment" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../../navbar.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <div class=" col-lg-4 col-sm-4 col-sm-offset-4">
        <div class="panel panel-success">
            <div class="panel-heading">
                <fmt:message key="page.lang.new.payment"/>
            </div>
            <div class="panel-body">
                <form action="/controller" method="post">
                    <input type="hidden" name="command" value="createPayment">
                    <div class="row">
                        <div class="oaerror success">
                            <strong><fmt:message
                                    key="page.lang.select.send.from"/></strong>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <label class="col-md-4 control-label">
                            <fmt:message
                                    key="page.lang.from.account"/>
                        </label>
                        <c:if test="${fn:length(sessionScope.accounts) != 0}">
                            <div class="col-md-8">
                                <select id="selectbasic" name="sender" class="form-control">
                                    <c:forEach items="${sessionScope.accounts}" var="account">
                                        <c:if test="${account.isBlocked() == false}">
                                            <option value="${account.accountId}">${account.accountId}(${account.name})</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                    <br>
                    <div class="row">
                        <label class="col-md-4 control-label">
                            <fmt:message
                                    key="page.lang.to.account"/>
                        </label>
                        <div class="col-md-8">
                            <input id="textinput" name="receiver" type="text" placeholder="number(8 digits)"
                                   class="form-control input-md">
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <label class="col-md-4 control-label">
                            <fmt:message
                                    key="page.lang.sum"/>
                        </label>
                        <div class="col-md-8">
                            <input name="balance" type="text" placeholder=""
                                   class="form-control input-md">
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit"
                                    <c:if test="${fn:length(sessionScope.accounts) == 0}">
                                        disabled
                                    </c:if>
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
