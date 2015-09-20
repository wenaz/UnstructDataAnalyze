<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo-ang
  Date: 2015/9/16
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<p>message:${hello}</p>
<table border="1" cellspacing="0" cellpadding="10">
  <tr>
    <c:forEach items="${title}" var="ti">
      <th>${ti}</th>
    </c:forEach>
  </tr>


  <c:forEach items="${data}" var="hdfsFileEntity">
    <tr>
      <td>${hdfsFileEntity.fileId}</td>
      <td>${hdfsFileEntity.fileName}</td>
      <td>${hdfsFileEntity.parentId}</td>
      <td>${hdfsFileEntity.fileSize}</td>
      <td>${hdfsFileEntity.fileType}</td>
      <td>${hdfsFileEntity.fileUrl}</td>
      <td>${hdfsFileEntity.createTime}</td>
      <td>${hdfsFileEntity.userId}</td>
    </tr>
  </c:forEach>
</table>

<hr>
<table border="1" >
  <s:iterator value="data">
    <tr>
      <td><s:property value="fileId"></s:property></td>
      <td><s:property value="fileName"></s:property></td>
      <td><s:property value="parentId"></s:property></td>
    </tr>
  </s:iterator>
</table>

<s:debug></s:debug>

</body>
</html>
