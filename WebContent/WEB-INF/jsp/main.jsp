<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Mutter, java.util.List" %>
<%
User loginUser = (User)session.getAttribute("loginUser");

List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList");

String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href="/docoTsubu/Logout">ログアウト</a>
</p>
<form action="/docoTsubu/Main" method="post">
<input type="submit" value="更新">
</form>

<form action="/docoTsubu/Main" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>
<% if(errorMsg != null) { %>
	<p><%= errorMsg %></p>
<% } %>
<%	int i = 1;
	for(Mutter mutter : mutterList) { %>
	<p><%= i %> 名前: <%= mutter.getUserName() %>:<%= mutter.getTime() %>:</p>
	<p>		<%= mutter.getText() %><% i++; %></p>
<% } %>
</body>
</html>