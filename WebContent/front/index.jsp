<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo-ang
  Date: 2015/9/18
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页-非结构化数据分析系统</title>
    <link href="../css/layout.css" type="text/css" rel="stylesheet">
    <link href="../css/jstree-themes/default/style.min.css" rel="stylesheet">

    <script src="../js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="../js/jstree.min.js" type="text/javascript"></script>
    <script src="../js/IndexJs.js"></script>
</head>
<body>
<div id="container">

  <div id="header">
    <h1>Logo</h1>
  </div>

  <div id="navbar">首页</div>

  <div id="menu">
    <h2>文件目录</h2>
    <div><input type="text" id="jstree_search"></div>
    <div id="jstree"></div>
    <!--<button>demo button</button>-->
  </div>

  <div id="content">

    <ul id="WDtoolBar">
      <li id="reBack"><a href="#" parentId="0">返回上一层</a></li>
      <li><a href="#" command="makeDir" id="newDir">新建文件夹</a></li>
      <li><a href="#" command="upload" id="upload">上传</a></li>
      <li><a href="#" command="makeDir" id="searchFile">搜索</a></li>
      <li><input type="button" value="submit"></li>
    </ul>

    <p id="event_result"> Content goes here</p>

    <table>
      <tr>
        <th>文件ID</th>
        <th>文件名</th>
        <th>父节点</th>
        <th>文件大小</th>
        <th>文件类型</th>
        <th>文件URL</th>
        <th>创建时间</th>
        <th>所属用户</th>
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
  <%--  <s:debug></s:debug>--%>

  </div>

  <div id="footer">&copy;<i>2015-</i><a href="index.html">www.scut.edu.cn</a>, All Copyright Reserved</div>


</div>
</body>
</html>
