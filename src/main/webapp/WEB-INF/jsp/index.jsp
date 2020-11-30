<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>

    <div style="padding: 10px; display: grid; justify-content: center; align-items: center; ">

            <h2><b>Weather Data</b></h2>
            <h3 style="color:${color};"><c:out value="${outputMessage}" /></h3>
            <p><b>City:</b>  <c:out value="${weather.name}" /></p>
            <p><b>Temperature:</b>  <c:out value="${weather.getMain().temp}" /></p>
            <p><b>Humidity:</b>  <c:out value="${weather.getMain().humidity}" />
               <br><br><button style="margin-top:5px;" type="submit"><a href="/">Refresh Result</a></button>
            </p>
            <button style="margin-top:5px;" type="submit"><a href="/saveData">Save</a></button><br>
            <button style="margin-top:5px;" type="submit"><a href="/loadData">Load Data</a></button>
            <table border="1" cellpadding="5">
                <caption><h2>Stored Weather Informations</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Country</th>
                    <th>Temperature</th>
                    <th>Humidity</th>
                </tr>
                <c:forEach var="weather1" items="${listWeathers}">
                    <tr>
                        <td><c:out value="${weather1.id}" /></td>
                        <td><c:out value="${weather1.name}" /></td>
                        <td><c:out value="${weather1.main.temp}" /></td>
                        <td><c:out value="${weather1.main.humidity}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</body>
</html>