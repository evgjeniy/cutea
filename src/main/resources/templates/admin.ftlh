<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center; color: white">Панель администратора</h1><hr>
    <a class="btn btn-dark mb-3" style="text-align: center; width: 100%" href="/admin/orders">История заказов</a><hr>
    <h3 style="text-align: center; color: white">Список пользователей</h3>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Активность</th>
            <th scope="col">Роли</th>
            <th scope="col">Бан</th>
            <th scope="col">Редактирование</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <th><#list user.roles as role>${role}</#list></th>
                <th>
                    <form action="/admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Бан">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${user.id}">Редактировние</a></th>
                <th><a href="/user/${user.id}">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Пользователей нет</h3>
        </#list>
    </table><hr>
    <h3 style="text-align: center; color: white">Список товаров</h3>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новый товар
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="admin/product/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName" style="color: white">Название товара</label>
                <input type="text" required class="form-control" id="productName" name="title">
            </div>
            <div class="form-group">
                <label for="price" style="color: white">Цена товара</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="price" name="price">
                    <div class="input-group-append"><span class="input-group-text">BYN</span></div>
                </div>
            </div>
            <div class="form-group">
                <label for="categoryName" style="color: white">Категория</label>
                <select id="categoryName" required name="category" class="form-control">
                    <option value="">Не выбрана</option>
                    <option value="Чай">Чай</option>
                    <option value="Кофе">Кофе</option>
                    <option value="Торт">Торт</option>
                </select>
            </div>
            <div class="form-group">
                <label for="description" style="color: white">Описание товара</label>
                <textarea required class="form-control" id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input required type="file" name="file1" id="file1">
                    <label class="custom-file-label" for="file1">Первая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file2" id="file2">
                    <label class="custom-file-label" for="file2">Вторая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file3" id="file3">
                    <label class="custom-file-label" for="file3">Третья фотография к товару</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list products as product>
            <a href="/product/${product.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-top">
                        <form action="admin/product/delete/${product.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">Удалить</button>
                        </form>
                    </div>
                    <img src="/images/${product.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${product.title}</h5>
                        <p class="card-text">${product.price} BYN</p>
                    </div>
                    <div class="card-footer">${product.category}:
                        <#if 50 < product.description?length>
                            ${product.description?substring(0, 50)}...
                        <#else>
                            ${product.description}
                        </#if>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center; color: white">Не найдено</h1>
        </#list>
    </div>
</@t.template>