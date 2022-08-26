let right_vm = new Vue({
    el:"#right_div",
    data:{
        view_arr:[],
        like_arr:[]
    },
    created:function () {
        //发请求获取浏览最多作品数据
        axios.get("/product/select/view").then(function (response) {
            right_vm.view_arr = response.data;
        })
        //发请求获取最受欢迎作品数据
        axios.get("/product/select/like").then(function (response) {
            right_vm.like_arr = response.data;
        })
    }
})