<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#include "/base.ftl">
    <title>user</title>
    <script type="text/javascript">
        $(function () {
            var cookie = document.cookie;
            console.log(cookie);
            $(".cookie").text(cookie);

            $(".add").on("click", function () {
                console.log("add")
                var exp = new Date();
                exp.setTime(exp.getTime() + 1000 * 10);//过期时间 5分钟
                document.cookie="cookieTest=jsjsjsjsjsjsjsjs;expires="+exp.toUTCString();
                document.relo
            });
            $(".clear").on("click", function () {
                console.log("clear");
                clearAllCookie();
            });
        });

        //清除所有cookie函数
        function clearAllCookie() {
            var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
            if(keys) {
                for(var i = keys.length; i--;)
                    document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
            }
        }
    </script>
</head>
<body>
请看说明：${description} <br />
haahssss
<br>
<a href="${ctx}/wx/login">登录</a>
<br>
<a href="${ctx}/wx/logout">登出</a>
<a href="wx/subscribe">点击关注</a>
<a href="weixin://addfriend/founder-apabi">一键关注</a>
<br>
<span class="cookie"></span>
<br>
    <a href="javascript:void(0);" class="add">在页面添加cokie</a>
    <a href="javascript:void(0);" class="clear">在页面清除cokie</a>
</body>
</html>