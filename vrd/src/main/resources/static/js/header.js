//声明自定义组件
Vue.component('myheader',{
    props: ["c_arr","isLogin"],
    template: `
        <header class="container">
    <!--导航条开始-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img alt="Brand" src="images/logo.png">
                </a>
            </div>
            <!--分类开始-->
            <ul class="nav navbar-nav">
                <li><a href="/index.html">首页</a></li>
                <li v-for="c in c_arr"><a :href="'/index.html?cid='+c.id">{{c.name}}</a></li>
                <li>
                    <form action="/index.html">
                        <input type="text" name="wd" placeholder="Search">
                        <button type="submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </form>
                </li>
            </ul>
            <!--分类结束-->
            <ul class="nav navbar-nav navbar-right">
                <li v-if="!isLogin"><a href="/login.html">管理员入口</a></li>
                <li v-if="isLogin"><a href="/admin.html">后台管理页面</a></li>
            </ul>
        </div>
    </nav>
    <!--导航条结束-->
</header>
    
    `
})

let header_vm = new Vue({
    el:"myheader",
    data:{
        c_arr:[], //表示分类的数组
        isLogin:false
    },
    created:function () {
        //发请求询问是否登录过
        axios.get("/checklogin").then(function (response) {
            header_vm.isLogin=response.data;
        })

        //请求分类数据
        axios.get("/category/select").then(function (response) {
            //把查询回来的数据赋值给vue中的数组变量 页面会自动改变
            header_vm.c_arr=response.data;
        }).catch(function (err) {
            alert(err);
        })
    }
})