<#include "header.ftl">
<H3>Transaction History</H3>

<table class="table table-striped table-bordered table-hover">
    <thead class="thead-light">
    <tr>
        <th scope="col">Date</th>
        <th scope="col">Service</th>
        <th scope="col">Amount</th>
        <th scope="col">Voucher Code</th>
    </tr>
    </thead>
    <tbody>
    <#list transactions as transaction>
    <tr>
        <td>${transaction.date}</td>
        <td>${transaction.service}</td>
        <td>${transaction.amount}</td>
        <td>${transaction.code}</td>
    </tr>
    </#list>
    </tbody>
</table>
<#include "footer.ftl">