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

                <div style="margin-right: 50vh;">
                    <table class="table table-bordered border-dark table-sm">
                        <thead style="text-align: center;">
                        <tr>
                            <th scope="col" style="min-width: 225px;">Beskrivelse</th>
                            <th scope="col" style="min-width: 69px;">Længde</th>
                            <th scope="col" style="min-width: 60px;">Antal</th>
                            <th scope="col" style="min-width: 60px;">Enhed</th>
                            <th scope="col" style="min-width: 300px;">Beskrivelse</th>
                        </tr>
                        </thead>
                    </table>

                    <table class="table table-bordered border-dark table-sm">
                        <thead>
                            <tr>
                                <th scope="col" style="min-width: 225px; text-align: center;">Træ & Tagplader</th>
                                <th scope="col" style="min-width: 69px;"></th>
                                <th scope="col" style="min-width: 60px;"></th>
                                <th scope="col" style="min-width: 60px;"></th>
                                <th scope="col" style="min-width: 300px;"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="materials" items="${requestScope.tableItems}">
                                <tr>
                                    <td>${materials.name}</td>
                                    <td style="text-align: center">${materials.length}</td>
                                    <td style="text-align: center">${materials.quantity}</td>
                                    <td style="text-align: center">${materials.unit}</td>
                                    <td>${materials.description}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                <form method="post" action="#">
                    <div class="input-group mb-3" style="min-width: 387px;">
                        <input type="number" id="finalPrice" class="form-control border-dark" style="border-radius: 0;">
                        <div class="input-group-append">
                            <button type="button" onclick="updateNotify()" class="btn btn-outline-dark" style="border-radius: 0">Send</button>
                        </div>
                    </div>
                </form>
                <div class="alert alert-success alert-dismissible fade show" style="display: none; min-width: 400px">
                    <p id="successSendMessage"></p>
                    <button type="button" class="btn-close" onclick="this.parentElement.style.display='none';"></button>
                </div>
                <div class="alert alert-danger alert-dismissible fade show" style="display: none; min-width: 400px">
                    <p id="failSendMessage"></p>
                    <button type="button" class="btn-close" onclick="this.parentElement.style.display='none';"></button>
                </div>
                </div>


             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">

                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>
            </c:if>


        </div>
        <script>
            let sum = 0;
            window.onload = function()
            {

                <c:forEach items="${requestScope.tableItems}" var="material">
                sum += ${material.pricePerUnit} * ${material.quantity}
                </c:forEach>

                document.getElementById('finalPrice').setAttribute('placeholder', "Udregnede pris: " + sum);
            }
        </script>

        <script>
            const success = document.getElementById('successSendMessage');
            const input = document.getElementById('finalPrice');
            const failure = document.getElementById('failSendMessage');

            function updateNotify()
            {
                if (input.value > 0)
                {
                    success.innerHTML = "Tilbud sendt!";
                    success.parentElement.style.display='block';
                    failure.parentElement.style.display='none';
                    return
                }
                failure.innerHTML = "Kan ikke sende et tilbud til 0!"
                failure.parentElement.style.display='block';
                success.parentElement.style.display='none';
            }
        </script>

    </jsp:body>
</t:genericpage>