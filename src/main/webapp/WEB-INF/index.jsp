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

        <div>
            <h2>Our Cool Site</h2>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                Main page for this 2. semester start project used at cphbusiness.dk
            </div>


            <c:if test="${sessionScope.role == 'employee' }">
                <p style="font-size: larger">This is what you can do,
                    since your are logged in as an employee</p>
                 <p><a href="fc/employeepage">Employee Page</a>

                <div>
                    <form method="post" id="1" action="${pageContext.request.contextPath}/fc/result">
                        <input type="hidden" name="name" value="Robin"/>
                        <input type="hidden" name="length" value="130"/>
                        <input type="hidden" name="width" value="880"/>

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
                                    <tr onclick="clickSubmit(1)" style="cursor : pointer;">
                                        <th scope="row">1</th>
                                        <td>Robin</td>
                                        <td>130</td>
                                        <td>880</td>
                                    </tr>
                                </tbody>
                            </table>
                    </form>
                </div>
             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">

                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>

                <form method="post" action="${pageContext.request.contextPath}/fc/sendorder">
                    <label for="length">Længde</label>
                    <input type="number" id="length" name="length" step="0">
                    <label for="width">Bredde</label>
                    <input type="number" id="width" name="width">
                    <input type="submit">
                </form>
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