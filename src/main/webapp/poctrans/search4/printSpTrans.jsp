<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="poctrans.search4.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="tablecss1.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
  background: rgb(204,204,204); 
}
page {
  background: white;
  display: block;
  margin: 0 auto;
  margin-bottom: 0.5cm;
  
}
page[size="A4"] {  
  width: 21cm;
  height: 29.7cm; 
}
page[size="A4"][layout="landscape"] {
  width: 29.7cm;
  height: 21cm;  
}
page[size="A3"] {
  width: 29.7cm;
  height: 42cm;
}
page[size="A3"][layout="landscape"] {
  width: 42cm;
  height: 29.7cm;  
}
page[size="A5"] {
  width: 14.8cm;
  height: 21cm;
}
page[size="A5"][layout="landscape"] {
  width: 21cm;
  height: 14.8cm;  
}
@media print {
  body, page {
    margin: 0;
    box-shadow: 0;
  }
}
</style>
</head>
<body>
<page size="A4">
<div align="center">
<h1> Print Form Content </h1>
    <!--  Begin: Dynamic Codes -->

<%
SpTrans spTrans = (SpTrans)request.getAttribute("spTrans");
%>
<table cellspacing="10px">
<tr>
<td align="right">
ID:
</td>

<td>
<%=spTrans.getId()%>
</td>

</tr>
<tr>
<td align="right">
Transaction Date:
</td>

<td>
<%=DateService.getDTSDDMMYYYY(spTrans.getTransDt()).equals("11-11-1111")?"":DateService.getDTSDDMMYYYY(spTrans.getTransDt())%>
</td>

</tr>
<tr>
<td align="right">
Nos. of Transactions:
</td>

<td>
<%=spTrans.getTransNos()%>
</td>

</tr>
<tr>
<td align="right">
Fee Amount:
</td>

<td>
<%=spTrans.getFeeAmt()%>
</td>

</tr>

</table>
</div>
</page>
<!-- <page size="A4"></page>
<page size="A4" layout="landscape"></page>
<page size="A5"></page>
<page size="A5" layout="landscape"></page>
<page size="A3"></page>
<page size="A3" layout="landscape"></page>
 -->
</body>
</html>
