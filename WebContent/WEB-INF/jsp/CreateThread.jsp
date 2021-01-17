<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>芝ちゃんねる</title>
</head>
<body>
<h1>芝ちゃんねる スレッド作成</h1>
<h2>スレッドタイトルを入力してください</h2>
<form action="/docoTsubu/CreateThread" method="post">
<input type="text" name="dTitle">
<input type="submit" value="作成"><br>
<% if(errorMsg != null) { %>
	<%= errorMsg %>
<% } %>
</form>
<p></p>
<form action="/docoTsubu/Login">
<input type="submit" value="キャンセル">
</form>
<p></p>

</body>
</html>