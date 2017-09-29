<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 26.09.2017
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Payments" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../../navbar.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <div class="mail-box">
        <aside class="sm-side">
            <div class="user-head">
                <h5><fmt:message
                        key="page.lang.user"/>: ${sessionScope.user.login}</h5>
                <span>${sessionScope.user.fullName}</span>
            </div>
            <div class="inbox-body">
                <form action="/controller">
                    <input type="hidden" name="command" value="sortPByDateUp">
                    <button data-toggle="modal" title="Compose" class="btn btn-compose-info">
                        <fmt:message
                                key="page.lang.sort.from.new.to.old"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="sortPByDateDown">
                    <button data-toggle="modal" title="Compose" class="btn btn-compose-info">
                        <fmt:message
                                key="page.lang.sort.from.old.to.new"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="toDeleteUnsentPayments">
                    <button data-toggle="modal" title="Compose" class="btn btn-compose">
                        <fmt:message
                                key="page.lang.delete.unsent.payments"/>
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
                <h3><fmt:message
                        key="page.lang.payments"/></h3>
            </div>
            <div class="inbox-body">
                <div class="row">
                    <div class="col-md-12">
                        <h5>
                            <fmt:message
                                    key="page.lang.sent.payments"/>
                        </h5>
                        <table class="table table-inbox table-hover">
                            <tbody>
                            <div class="method">
                                <div class="row margin-0 list-header hidden-sm hidden-xs">
                                    <div class="col-md-2">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.date"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.sender.account"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.receiver.account"/></div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.sum"/></div>
                                    </div>
                                    <div class="col-md-1">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.status"/></div>
                                    </div>
                                </div>
                                <c:forEach items="${sessionScope.payments}" var="payment">
                                    <c:if test="${payment.status == 'sent'}">
                                        <div class="row margin-0">
                                            <div class="col-md-2">
                                                <div class="cell">
                                                    <code>${payment.date}</code>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="cell">
                                                        ${payment.accountSender}
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="cell">
                                                        ${payment.accountReceiver}
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="cell">
                                                        ${payment.balance}
                                                </div>
                                            </div>
                                            <div class="col-md-1">
                                                <div class="cell">
                                                        ${payment.status}
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            </tbody>
                        </table>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-12">
                        <h5>
                            <fmt:message
                                    key="page.lang.you.can.confirm.status"/>
                        </h5>
                        <table class="table table-inbox table-hover">
                            <tbody>
                            <div class="method">
                                <div class="row margin-0 list-header hidden-sm hidden-xs">
                                    <div class="col-md-2">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.date"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.from.account"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.receiver.account"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.sum"/>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <div class="header">
                                            <fmt:message
                                                    key="page.lang.status"/>
                                        </div>
                                    </div>
                                </div>
                                <c:forEach items="${sessionScope.payments}" var="payment">
                                    <c:if test="${payment.status == 'prepared'}">
                                        <div class="row margin-0">
                                            <div class="col-md-2">
                                                <div class="cell">
                                                    <code>${payment.date}</code>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="cell">
                                                        ${payment.accountSender}
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="cell">
                                                        ${payment.accountReceiver}
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="cell">
                                                        ${payment.balance}
                                                </div>
                                            </div>
                                            <div class="col-md-1">
                                                <div class="cell">
                                                        ${payment.status}
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </aside>
    </div>
</c:if>
<c:if test="${empty sessionScope.user}">
    <div class="bs-calltoaction bs-calltoaction-success">
        <div class="row">
            <div class="col-md-9 cta-contents">
                <h1 class="cta-title"><fmt:message
                        key="page.lang.dont.have.acc.yet"/></h1>
                <div class="cta-desc">
                    <p></p>
                </div>
            </div>
            <div class="col-md-3 cta-button">
                <a href="/html/registration.html">
                    <button type="submit" class="btn btn-success btn-block">
                        <fmt:message
                                key="page.lang.confirm"/>
                    </button>
                </a>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
