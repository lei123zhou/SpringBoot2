let grid_vm = new Vue({
    el:".grid",
    data:{
        p_arr:[]
    },
    created:function () {
        //判断请求地址中是否包含搜索的关键字
        if (location.search.indexOf("wd")!=-1){
            //得到搜索的内容
            let wd = location.search.split("=")[1];
            //发出搜索的请求
            axios.get("/product/selectbywd?wd="+wd).then(function (response) {
                grid_vm.p_arr = response.data;
            })
        }else if (location.search.indexOf("cid")!=-1){//说明包含cid
            //从地址栏中取出分类id   ?cid=1
            let cid = location.search.split("=")[1];
            //发出请求 某个分类下作品
            axios.get("/product/selectbycid?cid="+cid).then(function (response) {
                grid_vm.p_arr = response.data;
            })
        }else{//请求地址中不包含cid
            //请求所有作品信息
            axios.get("/product/select").then(function (response) {
                grid_vm.p_arr = response.data;
            })
        }


    },
    updated:function () { //当vue对象中的变量发生改变导致页面更新时执行的方法
        //瀑布流初始化代码写在这个位置
        $(".grid").imagesLoaded().progress(function () {
            //页面中图片加载完成会调用这里
            $(".grid").masonry({
                itemSelector:".grid-item",
                columnWidth:210
            })
            //给瀑布流元素添加鼠标移入移出事件
            $(".grid-item").hover(function () {//鼠标移入执行
                //this代表的是鼠标移入的瀑布流元素div
                $(this).children("div").stop().fadeIn(500);
            },function () {//鼠标移出执行
                $(this).children("div").stop().fadeOut(500);
            })

        });
    }
})