<#import "blocks/template.ftlh" as t>
<@t.template user>
    <hr><h3 style="text-align: center; color: white">Список заказов</h3>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Список товаров</th>
            <th scope="col">Дата и время заказа</th>
            <th scope="col">Стоимость</th>
        </tr>
        <#list user.orders as order>
            <tr>
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