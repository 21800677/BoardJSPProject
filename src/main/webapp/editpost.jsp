<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.example.dao.BoardDAO"%>
<%@ page import="com.example.util.FileUpload" %>
<%@ page import="com.example.vo.BoardVO" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="u" class="com.example.vo.BoardVO" />
<jsp:setProperty property="*" name="u"/>

<%
	request.setCharacterEncoding("utf-8");
	BoardDAO boardDAO = new BoardDAO();
	FileUpload upload = new FileUpload();
	BoardVO U = upload.uploadPhoto(request);

	int i=boardDAO.updateBoard(u);
	response.sendRedirect("posts.jsp");
%>