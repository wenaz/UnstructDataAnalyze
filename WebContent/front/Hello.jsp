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
<table>
  <tr>
    <c:forEach items="=${title}" var="ti">
      <th>${ti}</th>
    </c:forEach>
  </tr>

  <c:forEach items="${data}" var="hdfsFileEntity">
    <tr>
      <s:iterator value="${hdfsFileEntity}">
        <td><s:property value="fileId"></s:property></td>
        <td><s:property value="fileName"></s:property></td>
        <td><s:property value="parentId"></s:property></td>
      </s:iterator>
    </tr>
  </c:forEach>
</table>
<s:debug></s:debug>

</body>
</html>
