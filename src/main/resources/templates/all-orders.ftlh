<#import "blocks/template.ftlh" as t>
<@t.template user>
    <hr><h3 style="text-align: center; color: white">Список всех заказов</h3>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Заказчик</th>
            <th scope="col">Список товаров</th>
            <th scope="col">Дата и время заказа</th>
            <th scope="col">Стоимость</th>
        </tr>
        <tr>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col" style="text-align: end">Итого: </th>
            <th scope="col">${revenue} BYN</th>
        </tr>
        <#list orders as order>
            <tr>
                <th>
                    <p>${order.user.name}</p>
                    <p>${order.user.email}</p>
                </th>
                <th>
                    <#list order.bill?split('\n') as row>
                        <p style="color: white">${row}</p>
                    </#list>
                </th>
                <th>${order.dateOfCreated}</th>
                <th>${order.orderPrice} BYN</th>
            </tr>
        <#else>
            <h3 style="color: white">Заказов нет</h3>
        </#list>
    </table><hr>
</@t.template>