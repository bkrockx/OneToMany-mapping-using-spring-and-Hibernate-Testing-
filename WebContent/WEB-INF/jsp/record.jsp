<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Records</h1>

<c:url var="addUrl" value="/record/add" />
<table width: 100%; text-align:center">
	<tr>
		<td>BookId</td>
		<td>BookName</td>
		<td>Edit</td>
		<td>Delete</td>
		<td>ChapterId</td>
		<td>ChapterName</td>
		<td>Add</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<tbody>
	<c:forEach items="${books}" var="book">
		<c:url var="editUrl" value="/record/edit?id=${book.bookId}" />
		<c:url var="deleteUrl" value="/record/delete?id=${book.bookId}" />
		
		<c:if test="${!empty book.chapter}">
			<c:forEach items="${book.chapter}" var="chapter">
			<tr>
				<td><c:out value="${book.bookId}" /></td>
				<td><c:out value="${book.bookName}" /></td>

				<td><a href="${editUrl}">EditBook</a></td>
				<td><a href="${deleteUrl}">DeleteBook</a></td>
				
				<td><c:out value="${chapter.chapterId}" /></td>
				<td><c:out value="${chapter.chapterName}" /></td>
				
				<c:url var="addCUrl" value="/chapter/add?id=${book.bookId}" />
				<c:url var="editCUrl" value="/chapter/edit?bid=${book.bookId}&cid=${chapter.chapterId}" />
				<c:url var="deleteCUrl" value="/chapter/delete?id=${chapter.chapterId}" />
				<td><a href="${addCUrl}">AddChapter</a></td>
				<td><a href="${editCUrl}">EditChapter</a></td>
				<td><a href="${deleteCUrl}">DeleteChapter</a></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty book.chapter}">
			<tr>
				<td><c:out value="${book.bookId}" /></td>
				<td><c:out value="${book.bookName}" /></td>
	
				<td><a href="${editUrl}"></a></td>
				<td><a href="${deleteUrl}"></a></td>
				
				<td></td>
				<td></td>
				<c:url var="addCcUrl" value="/chapter/add?id=${book.bookId}" />
				<td><a href="${addCcUrl}">AddChapter</a></td>
				<td></td>
				<td></td>
			</tr>
		</c:if>
		
	</c:forEach>
	</tbody>
</table>

<p><a href="${addUrl}">Create new record</a></p>

</body>
</html>