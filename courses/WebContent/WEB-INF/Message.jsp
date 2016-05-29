<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<div class="toolbg">
</div>
<c:if test="${level eq 3 }">
<form action="/message/insert" method="post">
	<textarea placeholder="在这里写下留言内容" rows="5" cols="50" name="content" required="required"></textarea>
	<button class="btn btn-default btn-xs">提交</button>
</form>
</c:if>

<table class="table">
	<tr>
		<th>#</th>
		<th>学生姓名</th>
		<th>发表时间</th>
		<th>留言内容</th>
	</tr>
	<c:forEach items="${list }" var="i">
	<tr>
		<td>${i.id }</td>
		<td>${i.username }</td>
		<td> <fmt:formatDate value="${i.create_time }" pattern="yyyy-MM-dd HH:mm"/> </td>
		<td>${i.content }</td>
	</tr>
	</c:forEach>
</table>

<div class="toolbg"></div>