<%@ page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>main</title>
</head>
<body>
<h1 align="center">欢迎来到主页面</h1>
<div align="right">

    <%--记住我登录/认证成功之后展示的内容--%>
    <shiro:user>
        您好：<span style="color: coral "><shiro:principal/></span>，欢迎来到主页面，<a href="${path}/logout" >退出</a>
        <div align="left">
                <%--判断用户角色--%>
            <shiro:hasRole name="admin">
                <button>用户管理</button>
                <shiro:hasPermission name="admin:query">
                    查询
                </shiro:hasPermission>
                <shiro:hasPermission name="admin:update">
                    修改
                </shiro:hasPermission>
                <shiro:hasPermission name="admin:delete">
                    删除
                </shiro:hasPermission>
                <shiro:hasPermission name="admin:insert">
                    添加
                </shiro:hasPermission>
                <br><br>
                <button>类别管理</button>
                <br><br>
                <button>视频管理</button>
                <br><br>
                <button>反馈管理</button>
                <br><br>
            </shiro:hasRole>

            <shiro:hasRole name="superAdmin">
                <button>管理员管理</button>
                <br><br>
                <button>日志管理</button>
                <br><br>
            </shiro:hasRole>
        </div>
    </shiro:user>
    <%--来宾用户 未认证成功展示的--%>
    <shiro:guest>
        如果想浏览更多信息请<a href="${path}/user/login.jsp">登录</a>
        <h2 align="center">这是没有登录展示的内容</h2>
    </shiro:guest>
</div>
</body>
</html>