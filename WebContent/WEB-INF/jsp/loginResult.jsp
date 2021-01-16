<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.dThread, java.util.List" %>
<%
User loginUser = (User) session.getAttribute("loginUser");
List<dThread> threadList = (List<dThread>) request.getAttribute("threadList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶログイン</h1>
<% if(loginUser != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getName() %>さん</p>
	<a href="/docoTsubu/Main">つぶやき投稿・閲覧へ</a>
	<a href="/docoTsubu/CreateThread">新規スレッドを作成する</a>
	<a href="/docoTsubu/Logout">ログアウト</a>
	<p></p>
	<%if(threadList != null) { %>
	<%	int i = 1;
	for(dThread thread : threadList) { %>
	<p><%= i %> : <%= thread.getTitle() %>:<%= thread.getData() %></p>
	<p><% i++; %></p>

	<%} %>
<% } %>
<% } else { %>
	<p>ログインに失敗しました</p>
	<a href="/docoTsubu/">TOPへ</a>
<% } %>
</body>
</html>