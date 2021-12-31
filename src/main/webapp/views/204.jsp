<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Auction Web Application</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha512-SfTiTlX6kk+qitfevl/7LibUOeJWlt9rbyDn92a1DqWOw9vWG2MFoays0sgObmWazO5BQPiFucnnEAjpAB+/Sw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            height: 99vh;
        }
    </style>
</head>
<body>
<div class="container-fluid h-100">
    <div class="row h-100 align-items-center">
        <div class="col-sm-12">
            <div class="card mx-auto w-50 shadow">
                <div class="card-body">
                    <h1 class="card-title">Oops.</h1>
                    <h2 class="text-danger">204. We cannot find the specific data.</h2>
                    <p class="card-text">Sorry, an error has occurred.</p>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/Admin/Category" role="button">
                    <i class="fa fa-arrow-left" aria-hidden="true"></i>
                    Back</a>
                    <a class="btn btn-success" href="#" role="button">
                        <i class="fa fa-envelope" aria-hidden="true"></i>
                        Contact Supports</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</body>
</html>