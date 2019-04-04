
layui.use('element', function() {
    function checkLastItem(arr, i) {
        return arr.length == (i + 1);
    }
    function notice(){
        //示范一个公告层
        layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '300px;'
            ,shade: 0.8
            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,btn: ['火速围观', '残忍拒绝']
            ,btnAlign: 'c'
            ,moveType: 1 //拖拽模式，0或者1
            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'
            ,success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: 'http://www.layui.com/'
                    ,target: '_blank'
                });
            }
        });
    };
    function getAhtml(obj){
        return "<a href=\"javascript:;\" onclick=\"addTab('" + obj.name + "','" + obj.url + "')\" >" + obj.name + "</a>";
    }
    //动态菜单
    layui.jquery.ajax({
        url: "/menus",
        method: 'POST',
        success: function(res) {
            var html = "";
            for(var i = 0; i < res.data.length; i++) {
                var strli = "<li class=\"layui-nav-item lay-unselect \" >";
                if (res.data[i].url =='#'){
                    strli = strli + "<a href=\"javascript:;\">" + res.data[i].name + "</a>";
                    console.log(res.data[i].name)
                }else{
                    strli = strli + getAhtml(res.data[i]);
                }
                if(res.data[i].pId == "0" && !checkLastItem(res.data, i) && res.data[i + 1].pId != "0") {
                    strli = strli + "<dl class=\"layui-nav-child\" >";
                    for(; !checkLastItem(res.data, i) && res.data[i + 1].pId != "0"; i++) {
                        strli = strli + "<dd>"+getAhtml(res.data[i+1])+"</dd>";
                    }
                    strli = strli + "</dl>";
                }
                strli = strli + "</li>";
                html = html + strli;
            }
            layui.jquery("#memus").html(html);
            layui.element.init(); //一定初始化一次
        }
    })
});

//添加选项卡
function addTab(name, url) {
    if(layui.jquery(".layui-tab-title li[lay-id='" + name + "']").length > 0) {
        //选项卡已经存在
        layui.element.tabChange('tabDemo', name);
    } else {
        //动态控制iframe高度
        var tabheight = layui.jquery(window).height() - 95;
        contentTxt = '<iframe src="' + url + '" scrolling="no" width="100%" height="' + (tabheight) + 'PX"></iframe>';
        //新增一个Tab项
        layui.element.tabAdd('tabDemo', {
            title: name,
            content: contentTxt,
            id: name
        })
        //切换刷新
        layui.element.tabChange('tabDemo', name)
    }
}

/*$.ajax({
    type: "get",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    url: "/getUserInfo",
    success: function (result) {
        if (result.code != "0") {
            alert(result.errorMessage);
        } else {
            $("#userName").html('<img src="http://t.cn/RCzsdCq" class="layui-nav-img">'+result.data.name);
            console.info(result.data.name);
        }
    }
});*/


