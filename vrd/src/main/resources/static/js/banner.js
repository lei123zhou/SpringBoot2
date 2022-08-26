let banner_vm = new Vue({
    el:"#myCarousel",
    data:{
        b_arr:[]
    },
    created:function () {
        //查询所有轮播图数据
        axios.get("/banner/select").then(function (response) {
            banner_vm.b_arr=response.data;
        }).catch(function (err) {
            alert(err);
        })
    }
})