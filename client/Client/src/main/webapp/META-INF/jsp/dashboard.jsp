<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css" />
        <!--
        <spring:url value="/css/main.css" var="springCss" />
            <link href="${springCss}" rel="stylesheet" /> -->
        <c:url value="/css/main.css" var="jstlCss" />
        <link href="${jstlCss}" rel="stylesheet" />
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Dashboard</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#resources">Resources</a></li>
                        <li><a href="#about">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="starter-template">
                <h1>Web application portal</h1>
                <h2>Message: ${message}</h2>
            </div>

        </div>
        <script type="text/javascript" src="webjars/bootstrap/4.3.1/dist/js/bootstrap.min.js"></script>
    </body>
</html>