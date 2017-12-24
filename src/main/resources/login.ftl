<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <link href="/css/bootstrap-min.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form method="post" action="/gift_card_service/login" class="form-signin">
    <#if message??>
        <div class="alert alert-info" role="alert">${ message }</div></#if>
    <#if error??>
        <div class="alert alert-danger" role="alert">${ error }</div></#if>
        <h2 class="form-signin-heading">Please sign in</h2>
    <#include "userform.ftl">
        <a class="nav-link" href="/gift_card_service/reg">New User? Register Here</a>
    </form>
</div> <!-- /container -->
</body>
</html>