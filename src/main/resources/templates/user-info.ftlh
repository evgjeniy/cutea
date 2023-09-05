<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h1 style="text-align: center; color: white">Информация о пользователе</h1>
    <hr>
    <div style="text-align: center">
        <img src="/static/images/avatar.png" style="border:2px solid black; border-radius: 20px" height="120"><br><br>
        <h2 style="color: white">${user.name}</h2><br>
        <p style="color: white">
            Почта: <b>${user.email}</b><br>
            Номер телефона: <b>${user.phoneNumber}</b>
        </p><hr>
    </div>
    <br>
</@t.template>