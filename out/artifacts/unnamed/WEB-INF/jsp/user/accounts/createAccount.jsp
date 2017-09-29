<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 19.09.2017
  Time: 11:39
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
            <div class="panel-heading"><fmt:message key="page.lang.new.account"/></div>
            <div class="panel-body">
                <form action="/controller" method="post">
                    <input type="hidden" name="command" value="createAccount">
                    <div class="row">
                        <div class="oaerror info">
                            <strong><fmt:message key="page.lang.you.create.unique.number"/></strong>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <label class="col-md-4 control-label" for="textinput">
                            <fmt:message key="page.lang.account.number"/>
                        </label>
                        <div class="col-md-8">
                            <input pattern="(\d){8}" id="textinput" name="accountId" type="text"
                                   placeholder="number(8 digits)"
                                   class="form-control input-md"
                                   title="Account id must be a 8-digit number">
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <label class="col-md-4 control-label" for="selectbasic">
                            <fmt:message key="page.lang.account.type"/>
                        </label>
                        <div class="col-md-8">
                            <select id="selectbasic" name="name" class="form-control">
                                <option value="checking account">Checking account</option>
                                <option value="credit account">Credit account</option>
                                <option value="deposit account">Deposit account</option>
                            </select>
                        </div>
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
