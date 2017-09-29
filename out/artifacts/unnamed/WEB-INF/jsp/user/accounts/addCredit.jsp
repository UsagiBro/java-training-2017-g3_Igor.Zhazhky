<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 24.09.2017
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="New account" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../../navbar.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <div class=" col-lg-4 col-sm-4 col-sm-offset-4">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="page.lang.add.credit"/></div>
            <div class="panel-body">
                <div class="row">
                    <div class="oaerror info">
                        <fmt:message key="page.lang.select.an.account.and.a.sum.for.deposit"/>
                    </div>
                </div>
                <br>
                <form action="/controller" method="post">
                    <input type="hidden" name="command" value="addCredit">
                    <div class="row">
                        <c:if test="${fn:length(sessionScope.accounts) != 0}">
                            <label class="col-md-4 control-label"><fmt:message
                                    key="page.lang.choose.acc"/></label>
                            <div class="col-md-8">
                                <select name="accountId" class="form-control">
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
                        <label class="col-md-4 control-label"><fmt:message
                                key="page.lang.enter.a.sum.for.deposit"/></label>
                        <div class="col-md-8">
                            <input name="balance" type="text" placeholder="sum"
                                   class="form-control input-md" pattern="(\d)+"
                                   title="Value must be an integer!">
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-success btn-block"><fmt:message
                                    key="page.lang.confirm"/></button>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <div class="col-md-12">
                        <form action="/controller">
                            <input type="hidden" name="command" value="toCabinet">
                            <button type="submit" class="btn btn-danger btn-block"><fmt:message
                                    key="page.lang.cancel"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
