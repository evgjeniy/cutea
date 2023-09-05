<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center; color: white;">Личный кабинет</h1><hr><br>
    <div style="text-align: center; color: white;">
        <img src="/static/images/avatar.png" style="border:2px solid black; border-radius: 20px" height="120"><br><br>
        <h2 style="color: white">${user.name}</h2>
        <p style="color: white">Электоронная почта: <b>${user.email}</b></p>
        <p style="color: white">Номер телефона: <b>${user.phoneNumber}</b></p>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/admin">Панель администратора</a><br>
        </#if>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/user/orders-history/${user.id}">История заказов</a><br>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/user/order/${user.id}">Сделать заказ</a><br>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">Выйти из аккаунта</button>
        </form>
    </div>
</@t.template>