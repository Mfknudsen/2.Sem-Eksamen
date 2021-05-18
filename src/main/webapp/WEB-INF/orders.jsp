<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
            <c:if test="${sessionScope.role == 'employee' }">
                <div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">User</th>
                                <th scope="col">Length</th>
                                <th scope="col">Width</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${sessionScope.orders != null}">
                            <c:forEach var="order" items="${sessionScope.orders}">
                                <form method="post" id="${order.id}" action="${pageContext.request.contextPath}/fc/result">

                                    <input hidden name="length" value="${order.length}">
                                    <input hidden name="width" value="${order.width}">

                                    <tr onclick="clickSubmit(${order.id})" style="cursor : pointer;">
                                        <th scope="row">${order.id}</th>
                                        <td>${order.name}</td>
                                        <td>${order.length}</td>
                                        <td>${order.width}</td>
                                    </tr>

                                </form>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">

                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>

            </c:if>

            <c:if test="${requestScope.error != null}">
                <div class="alert alert-danger alert-dismissible fade show">
                    ${requestScope.error}
                    <button type="button" class="btn-close" onclick="this.parentElement.style.display='none';"></button>
                </div>
            </c:if>

            <c:if test="${requestScope.update != null}">
                <div class="alert alert-success alert-dismissible fade show">
                    ${requestScope.update}
                    <button type="button" class="btn-close" onclick="this.parentElement.style.display='none';"></button>
                </div>
            </c:if>

        </div>

        <script type="text/javascript">
           function clickSubmit(id)
           {
               document.getElementById(id).submit();
           }
        </script>

    </jsp:body>
</t:genericpage>