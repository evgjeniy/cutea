<#import "blocks/template.ftlh" as t>
<@t.template user>
    <hr>
    <h1 class="text-center mt-4" style="color: white">Корзина</h1>
    <hr>
    <div class="row">
        <div class="col-md-8">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col" style="color: white">Название</th>
                    <th scope="col" style="color: white">Цена</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <#list user.cart as product>
                    <tr>
                        <th style="color: white">${product.category} ${product.title}</th>
                        <th style="color: white">${product.price} BYN</th>
                        <th style="color: white; width: 200px">
                            <form method="POST" action="/product/cart/delete/${product.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit" class="btn btn-dark" style="text-align: center; width: 100%; color: white;">Удалить из корзины</button>
                            </form>
                        </th>
                    </tr>
                <#else>
                    <h3 style="color: white">Корзина пуста</h3>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Итого к оплате</h5>
                    <p class="card-text">Сумма заказа: ${user.getCartPrice()} BYN</p>
                    <form method="POST" action="/user/order/${user.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-dark " style="text-align: center; width: 100%; color: white;">Оформить заказ</button>
                    </form>
                    <p class="card-text" style="color: red; font-size: 13px">${error!""}</p>
                </div>
            </div>
        </div>
    </div>
</@t.template>