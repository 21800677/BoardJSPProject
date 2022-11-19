<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-20
  Time: 오전 3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page import="com.example.dao.BoardDAO, com.example.vo.BoardVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 확인</title>
  <link rel="stylesheet" href="member.css">
</head>
<body>
<%
  BoardDAO boardDAO = new BoardDAO();
  String id = request.getParameter("id");
  BoardVO u = boardDAO.getBoard(Integer.parseInt(id));
  request.setAttribute("vo",u);
%>
    <h1>게시물 상세보기</h1>
    <table id="edit">
      <tr>
        <td>category</td><td>${u.getCategory()}</td>
      </tr>
      <tr>
        <td>Title</td><td>${u.getTitle()}</td>
      </tr>
      <tr>
        <td>Writer</td><td>${u.getWriter()}</td>
      </tr>
      <tr>
        <td>Content</td><td>${u.getContent()}</td>
      </tr>
      <tr>
        <td>RegDate</td><<td>${u.getRegdate()}</td>
      </tr>
      <tr>
        <td>File</td><<td>${u.getPhoto()}</td>
      </tr>
      <tr>
        <td>Moddate</td><<td>${u.getModdate()}</td>
      </tr>

    </table>
    <button type="button"onclick="history.back()">뒤로가기</button>
    <button type="button" onclick="location.href ='editform.jsp?id=${vo.getSeq()}'">수정하기</button>
</body>
</html>
