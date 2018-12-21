<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#include "/base.ftl">
    <title>文件系统</title>
    <script type="text/javascript">
        $(function () {
            var cookie = document.cookie;
            console.log(cookie);

        });
        
        function exportFile() {
            var token = uuid(32,16);
            $.ajax({
                type: "GET",
                url: "${ctx}/export-data/tableData-json-xlsx-0",
                dataType: "json",
                data:{token : token},
                success: function () {
                    console.log('请求完成！');
                    var count = 1;
                    var interval = window.setInterval(function () {
                        if (count === 60) {
                            window.clearInterval(interval);
                            alert("请求超时！");
                            return;
                        }
                        if (getCookie('export-data-'+token)) {
                            window.clearInterval(interval);
                            window.location.href = "${ctx}/export-data/tableData-json-xlsx-1/?token=" + token;
                            return;
                        }
                        count++;
                    },1000);
                },
                error: function (e) {
                    console.log("error", e);
                    alert('导出失败！');
                }
            });
        }

        function getCookie(name) {
            var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
            if(arr=document.cookie.match(reg))
                return unescape(arr[2]);
            else
                return null;
        }

        function uuid(len, radix) {
            //第一个参数长度，第二个参数进制
            var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
            var uuid = [], i;
            radix = radix || chars.length;

            if (len) {
                // Compact form
                for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
            } else {
                // rfc4122, version 4 form
                var r;

                // rfc4122 requires these characters
                uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
                uuid[14] = '4';

                // Fill in random data.  At i==19 set the high bits of clock sequence as
                // per rfc4122, sec. 4.1.5
                for (i = 0; i < 36; i++) {
                    if (!uuid[i]) {
                        r = 0 | Math.random()*16;
                        uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                    }
                }
            }
            return uuid.join('');
        }

    </script>
</head>
<body>

<button onclick="exportFile();">download</button>
</body>
</html>