<#import "parts/common.ftlh" as c>

<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new media
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Введите название фильма/сериала" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="date" placeholder="Год выпуска">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
        <#list mediaList as media>
            <div class="card my-3" style="width: 20rem;">
                <div class="m-2">
                    <span>${media.name}</span>
                    <i class="mr-5">${media.date}</i>
                    <form method="post" action="/library${media.id}">
                        <input class="form-control" type="submit" value="Delete">
                    </form>
                    <a class="btn btn-primary my-2" data-toggle="collapse" href="#change${media.id}" role="button" aria-expanded="false" aria-controls="change">
                        Change
                    </a>
                    <div class="collapse" id="change${media.id}">
                        <div class="form-group mt-3">
                            <form action="/library/edit${media.id}" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="name" placeholder="Введите название фильма/сериала" />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" name="date" placeholder="Год выпуска">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Изменить</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        <#else>
            No media
        </#list>
    </div>
</@c.page>