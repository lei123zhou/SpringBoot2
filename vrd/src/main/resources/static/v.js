//声明一个自定义组件
Vue.component('myview',{
    props:["arr","fn","isShow"],
    template:`
    <div id="mydiv">
        <ul>
        <!--在自定义的组件中 是不能直接访问Vue对象中的变量的
        只能访问props中声明的变量-->
            <li v-for="name in arr" @click="fn(name)">{{name}}</li>
        </ul>
        <h1 v-if="isShow">看不见我!</h1>
    </div>
    `
})
let vm = new Vue({
    el:"myview",
    data:{
        arr:["张三","李四","王五"],isShow:false
    },
    methods:{
        fn:function (name) {
            alert(name);
        }
    }
})