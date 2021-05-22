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

                <svg
                     height="50%" width="50%"  viewBox="0 0 855 690"
                     preserveAspectRatio="xMinYMin">

                    <svg
                         x="75"
                         y="55"
                         width="800"
                         height="530"
                         viewBox="0 0 780 600"
                         preserveAspectRatio="xMinYMin">

                        <!--box-->
                        <rect x="0" y="0" width="780" height="600"
                              style="stroke:#000000; fill: #ffffff"></rect>

                        <!--horizontal lines-->
                        <rect x="5" y="64" width="770" height="6"
                              style="stroke:#000000; fill: #ffffff"></rect>
                        <rect x="5" y="536" width="770" height="6"
                              style="stroke:#000000; fill: #ffffff"></rect>

                        <!--top boxes-->
                        <rect x="80" y="55" width="20" height="20"
                              style="stroke:#000000; fill: #ffffff"></rect>
                        <rect x="400" y="55" width="20" height="20"
                              style="stroke:#000000; fill: #ffffff"></rect>
                        <rect x="680" y="55" width="20" height="20"
                              style="stroke:#000000; fill: #ffffff"></rect>

                        <!--bottom boxes-->
                        <rect x="80" y="530" width="20" height="20"
                              style="stroke:#000000; fill: #ffffff"></rect>
                        <rect x="400" y="530" width="20" height="20"
                              style="stroke:#000000; fill: #ffffff"></rect>
                        <rect x="680" y="530" width="20" height="20"
                              style="stroke:#000000; fill: #ffffff"></rect>

                        <!--dotted lines-->
                        <line x1="50" y1="65" x2="600" y2="545"
                              style="stroke:#000000; stroke-dasharray: 10 5;"></line>
                        <line x1="600" y1="65" x2="50" y2="545"
                              style="stroke:#000000; stroke-dasharray: 10 5;"></line>

                        <!--normal lines-->
                        <line x1="5" y1="0" x2="5" y2="600"
                              style="stroke:#000000;"></line>
                        <line x1="775" y1="0" x2="775" y2="600"
                              style="stroke:#000000;"></line>
                    </svg>

                    <defs>
                        <marker
                                id="beginArrow"
                                markerWidth="12"
                                markerHeight="12"
                                refX="0"
                                refY="6"
                                orient="auto">
                            <path d="M0,6 L12,0 L12,12 L0,6" style="fill: #000000;"></path>
                        </marker>
                        <marker
                                id="endArrow"
                                markerWidth="12"
                                markerHeight="12"
                                refX="12"
                                refY="6"
                                orient="auto">
                            <path d="M0,0 L12,6 L0,12 L0,0 " style="fill: #000000;"></path>
                        </marker>
                    </defs>

                    <!--y-line-->
                    <line x1="50"  y1="590" x2="50"   y2="50"
                          style="stroke: #000000;
                          marker-start: url(#beginArrow);
                          marker-end: url(#endArrow);"></line>

                    <!--x-line-->
                    <line x1="70"  y1="610" x2="770"   y2="610"
                          style="stroke: #000000;
                          marker-start: url(#beginArrow);
                          marker-end: url(#endArrow);"></line>

                    <text style="text-anchor: middle" transform="translate(30,300) rotate(-90)">${requestScope.width} cm</text>
                    <text style="text-anchor: middle" transform="translate(428,626)">${requestScope.length} cm</text>
                </svg>

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