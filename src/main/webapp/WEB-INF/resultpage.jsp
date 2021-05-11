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
                            <th scope="col" style="width: 69px;">Længde</th>
                            <th scope="col" style="width: 60px;">Antal</th>
                            <th scope="col" style="width: 60px;">Enhed</th>
                            <th scope="col" style="width: 400px;">Beskrivelse</th>
                        </tr>
                        </thead>
                    </table>

                    <table class="table table-bordered border-dark table-sm">
                        <thead>
                            <tr>
                                <th scope="col" style="width: 225px; text-align: center;">Træ & Tagplader</th>
                                <th scope="col" style="width: 69px;"></th>
                                <th scope="col" style="width: 60px;"></th>
                                <th scope="col" style="width: 60px;"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>25x200 mm.trykimp. Brædt</th>
                                <td style="text-align: center">360</td>
                                <td style="text-align: center">4</td>
                                <td style="text-align: center">Stk</td>
                                <td>understernbrædder til for & bag ende</th>
                            </tr>
                            <tr>
                                <td>25x200 mm.trykimp. Brædt</th>
                                <td style="text-align: center">540</td>
                                <td style="text-align: center">4</td>
                                <td style="text-align: center">Stk</td>
                                <td>understernbrædder til siderne</th>
                            </tr>
                            <tr>
                                <td>25x125 mm.trykimp. Brædt</th>
                                <td style="text-align: center">360</td>
                                <td style="text-align: center">2</td>
                                <td style="text-align: center">Stk</td>
                                <td>understernbrædder til forenden</th>
                            </tr>
                            <tr>
                                <td>25x125 mm.trykimp. Brædt</th>
                                <td style="text-align: center">540</td>
                                <td style="text-align: center">4</td>
                                <td style="text-align: center">Stk</td>
                                <td>oversternbrædder til siderne</th>
                            </tr>
                        </tbody>
                    </table>

                    <table class="table table-bordered border-dark table-sm">
                        <thead>
                        <tr>
                            <th scope="col" style="width: 225px; text-align: center;">Beslag & Skruer</th>
                            <th scope="col" style="width: 69px;"></th>
                            <th scope="col" style="width: 60px;"></th>
                            <th scope="col" style="width: 60px;"></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>plastmo bundskruer 200 stk.</th>
                            <td></td>
                            <td style="text-align: center">3</td>
                            <td style="text-align: center">pakke</td>
                            <td>Skruer til tagplader</th>
                        </tr>
                        <tr>
                            <td>hulbånd 1x20 mm. 10 mtr.</th>
                            <td></td>
                            <td style="text-align: center">2</td>
                            <td style="text-align: center">Rulle</td>
                            <td>Til vindkryds på spær</th>
                        </tr>
                        <tr>
                            <td>universal 190 mm højre</th>
                            <td></td>
                            <td style="text-align: center">15</td>
                            <td style="text-align: center">Stk</td>
                            <td>Til montering af spær på rem</th>
                        </tr>
                        <tr>
                            <td>universal 190 mm venstre</th>
                            <td></td>
                            <td style="text-align: center">15</td>
                            <td style="text-align: center">Stk</td>
                            <td>Til montering af spær på rem</th>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div>
                    <table>
                        <thead>
                        <tr>
                            <th>Dækningsgrad eller prisforslag</th>
                            <th>Prisforslag ved salg</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>"Hard coded value ex. $800"</td>
                            <td>
                                <form method="get" id="customPrice">
                                    <input type="number">
                                </form>
                                <p></p>
                                <button type="submit" form="customPrice" value="Submit">Submit</button>
                            </td>
                        </tr>
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
    </jsp:body>
</t:genericpage>