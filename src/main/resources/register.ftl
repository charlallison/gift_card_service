<#include "header.ftl">
<div class="container">
    <form method="post" action="/gift_card_service/reg" class="form-signin">
    <#if user??>
        <div><h2 class="form-signin-heading">Edit User Profile</h2></div>
        <#if message??>
            <div class="alert alert-success" role="alert">${ message }</div></#if>
    <#else>
        <h2 class="form-signin-heading">User Registration</h2>
    </#if>
    <#--<#include "userform.ftl">-->
        <label for="fullname" class="sr-only">Full Name:</label>
        <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Full Name" required
               value="<#if regUser?? >${ regUser.fullName }</#if>" autofocus>

        <label for="email" class="sr-only">Email:</label>
        <input type="email" id="email" name="email" class="form-control" placeholder="Email" required
               value="<#if regUser?? >${ regUser.email }</#if>">

        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Username" required
               value="<#if regUser?? >${ regUser.username }</#if>">

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
               value="<#if regUser?? >${ regUser.password }</#if>" required>

        <input type="hidden" name="id" value="<#if regUser?? >${ regUser.id }</#if>"/>
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit"/>
    </form>
</div> <!-- /container -->

<#if !user??>
</body>
</html>
<#else>
    <#include "footer.ftl">
</#if>