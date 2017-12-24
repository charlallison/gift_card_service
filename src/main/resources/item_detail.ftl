<#include "header.ftl">
<main role="main">

    <div class="album text-muted">
        <div class="container">
            <div class="row">
                <div class="card">
                    <img data-src="/${ item.photo }.jpg" alt="Thumbnail [100%x280]"
                         style="height: 280px; width: 100%; display: block;"
                         src="/images/${ item.photo }.jpg"
                         data-holder-rendered="true">
                    <a href="#" class="badge badge-warning">${item.name} $${item.price}</a>
                <#if item.description??><p class="card-text">${item.description}</p></#if>
                <#if user?? ><a href="/gift_card_service/u/buy/id/${item.id}"
                                class="mt-3 btn btn-primary btn-md">Buy</a></#if>
                </div>
            </div>
        </div>
    </div>
</main>
<#include "footer.ftl">