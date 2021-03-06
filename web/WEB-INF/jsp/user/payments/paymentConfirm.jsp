<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 24.09.2017
  Time: 18:01
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
            <div class="panel-heading">Confirmation</div>
            <div class="panel-body">
                <form action="/controller" method="post">
                    <input type="hidden" name="command" value="paymentConfirm">
                    <div class="row">
                        <div class="oaerror success">
                            <c:if test="${sessionScope.lastCheque != null}">
                                <fmt:message
                                        key="page.lang.execute.operation"/>
                                <strong></strong>
                            </c:if>
                            <c:if test="${sessionScope.lastCheque == null}">
                                <strong>Operation has been over</strong>
                            </c:if>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit"
                                    <c:if test="${sessionScope.lastCheque == null}">
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

