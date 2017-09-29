<%--
  Created by IntelliJ IDEA.
  User: Igor Zhazhky
  Date: 20.09.2017
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Error" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>
<br>
<br>
<br>
<div class="col-xs-10">
    <div id="tb-testimonial" class="testimonial testimonial-danger">
        <div class="testimonial-section">
            ${sessionScope.error}
        </div>
        <div class="testimonial-desc">
            <img src="/css/error_symbol.png">
            <div class="testimonial-writer">
                <div class="testimonial-writer-name"><fmt:message key="page.lang.error.page"/></div>
                <div class="testimonial-writer-designation"><fmt:message key="page.lang.went.wrong"/>(</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
