<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Main" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../navbar.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <div class="mail-box">
        <aside class="sm-side">
            <div class="user-head">
                <h5><fmt:message key="page.lang.user"/>: ${sessionScope.user.login}</h5>
                <span>${sessionScope.user.fullName}</span>
            </div>
            <div class="inbox-body">
                <form action="/controller">
                    <input type="hidden" name="command" value="toAddCredit">
                    <button data-toggle="modal" title="Compose"
                            <c:if test="${fn:length(sessionScope.accounts) == 0}">disabled </c:if>
                            class="btn btn-compose-success">
                        <fmt:message key="page.lang.add.credit"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="toCreateCard">
                    <button data-toggle="modal" title="Compose" class="btn btn-compose-success">
                        <fmt:message key="page.lang.create.new.card"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="sortAById">
                    <button data-toggle="modal" title="Compose" class="btn btn-compose-info">
                        <fmt:message key="page.lang.sort.accounts.by.number"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="sortAByName">
                    <button data-toggle="modal" title="Compose" class="btn btn-compose-info">
                        <fmt:message key="page.lang.sort.accounts.by.type"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="sortAByBalance">
                    <button data-toggle="modal" title="Compose" class="btn btn-compose-info">
                        <fmt:message key="page.lang.sort.accounts.by.balance"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="toBlockAccount">
                    <button data-toggle="modal" title="Compose"
                            <c:if test="${fn:length(sessionScope.accounts) == 0}">disabled </c:if>
                            class="btn btn-compose">
                        <fmt:message key="page.lang.block.account"/>
                    </button>
                </form>
                <form action="/controller">
                    <input type="hidden" name="command" value="allBalance">
                    <button data-toggle="modal" title="Compose"
                            class="btn btn-compose-warning">
                        <fmt:message key="page.lang.sum.balance"/>
                    </button>
                </form>
                <c:if test="${not empty sessionScope.balance}">
                    <h5><fmt:message key="page.lang.sum.balance"/>: ${sessionScope.allbalance}</h5>
                </c:if>
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
                <h3><fmt:message key="page.lang.accounts"/></h3>
            </div>
            <div class="inbox-body">
                <div class="col-md-7">
                    <table class="table table-inbox table-hover">
                        <tbody>
                        <div class="method">
                            <div class="row margin-0 list-header hidden-sm hidden-xs">
                                <div class="col-md-4">
                                    <div class="header"><fmt:message key="page.lang.account"/></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="header"><fmt:message key="page.lang.account.type"/></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="header"><fmt:message key="page.lang.account.balance"/></div>
                                </div>
                            </div>
                            <c:forEach items="${sessionScope.accounts}" var="account">
                            <c:if test="${account.isBlocked() == false}">
                            <div class="row margin-0">
                                <div class="col-md-4">
                                    <div class="cell">
                                        <div class="propertyname">
                                                ${account.accountId}
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="cell">
                                        <div class="type">
                                            <code>${account.name}</code>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="cell">
                                            ${account.balance}
                                    </div>
                                </div>
                            </div>
                            </c:if>
                            </c:forEach>
                        </tbody>

                        <c:if test="${not empty sessionScope.allBalance}">
                            <h5><fmt:message key="page.lang.sum.balance"/>:${sessionScope.allBalance}</h5>
                        </c:if>
                    </table>

                    <c:if test="${fn:length(sessionScope.accounts) == 0}">
                        <div class="row margin-0">
                            <h5><fmt:message
                                    key="page.lang.you.dont.have.any.accs"/></h5>
                        </div>
                    </c:if>
                </div>
                <div class="col-md-5">
                    <table class="table table-inbox table-hover">
                        <tbody>
                        <div class="method">
                            <div class="row margin-0 list-header hidden-sm hidden-xs">
                                <div class="col-md-6">
                                    <div class="header"><fmt:message key="page.lang.card"/></div>
                                </div>
                                <div class="col-md-6">
                                    <div class="header"><fmt:message key="page.lang.to.account"/></div>
                                </div>
                            </div>
                            <c:forEach items="${sessionScope.cards}" var="card">
                                <div class="row margin-0">
                                    <div class="col-md-6">
                                        <div class="cell">
                                            <div class="description">
                                                    ${card.cardId}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="cell">
                                            <div class="description">
                                                    ${card.accountId}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${fn:length(sessionScope.cards) == 0}">
                                <div class="row margin-0">
                                    <h5>You don't have any cards yet</h5>
                                </div>
                            </c:if>
                        </div>
                        </tbody>
                    </table>
                </div>
            </div>
        </aside>
    </div>
    </div>
</c:if>
<c:if test="${empty sessionScope.user}">
    <div class="bs-calltoaction bs-calltoaction-success">
        <div class="row">
            <div class="col-md-9 cta-contents">
                <h1 class="cta-title">You don't have an account yet?</h1>
                <div class="cta-desc">
                    <p>Register in our system to start you e-payments management!</p>
                </div>
            </div>
            <div class="col-md-3 cta-button">
                <a href="/html/registration.html">
                    <button type="submit" class="btn btn-success btn-block">Confirm</button>
                </a>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>