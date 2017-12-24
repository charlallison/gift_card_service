<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="#">

    <title>Gift Card Service</title>

    <!-- Bootstrap core CSS -->
<#--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"-->
<#--integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">-->

    <!-- Custom styles for this template -->

    <link href="/css/bootstrap-min.css" rel="stylesheet">
    <link href="/css/stylesheet.css" rel="stylesheet">

</head>

<body data-gr-c-s-loaded="true">
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="/gift_card_service/">Gift card</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                    aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

                <ul class="navbar-nav ml-auto">
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link" href="/gift_card_service/u/profile">Account <span
                                class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/gift_card_service/u/">Transaction History</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/gift_card_service/u/logout">Logout</a>
                    </li>
                <#elseif !user??>
                    <li class="nav-item">
                        <a class="nav-link" href="/gift_card_service/u/login">Login</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/gift_card_service/u/reg">Register</a>
                    </li>
                </#if>
                </ul>

            </div>
        </div>
    </nav>
</header>