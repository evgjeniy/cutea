<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center; color: white">Авторизация</h1>
    <hr>
    <form action="/login " method="post">
        <div class="form-group">
            <label for="email" style="color: white;">Электронная почта</label>
            <input type="email" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="email" name="username">
            <div id="email" class="invalid-feedback" style="color: red">Неверная электронная почта</div>
        </div>
        <div class="form-group">
            <label for="password" style="color: white">Пароль</label>
            <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
            <div id="password" class="invalid-feedback" style="color: red">Неверный пароль</div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
    </form>
    <hr>
    <h2 style="color: white">Нет аккаунта в CUTEA? <a href="/registration">Зарегистрируйся!</a></h2>
</@t.template>