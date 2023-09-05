<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center; color: white">Каталог товаров</h1>
    <hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="category" style="color: white">Категория</label>
                <select id="category" name="category" class="form-control">
                    <option value="">Все</option>
                    <option value="Чай">Чай</option>
                    <option value="Кофе">Кофе</option>
                    <option value="Торт">Торт</option>
                </select>
            </div>
            <div class="form-group col-md-10">
                <label for="search" style="color: white">Поиск по названию продукта</label>
                <input type="text" name="search" <#if search??>value="${search}"</#if> class="form-control" id="search">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list products as product>
            <a href="/product/${product.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <img src="/images/${product.previewImageId}" class="card-img-top">
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
        <#else><h1 style="text-align: center; color: white">Не найдено</h1>
        </#list>
    </div>
</@t.template>