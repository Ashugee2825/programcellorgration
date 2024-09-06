<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="poctrans.search4.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<!-- begin: submit buttons -->
<style>
.form-submit-button {
background: #0066A2;
color: white;
border-style: outset;
border-color: #0066A2;
height: 30px;
width: 70px;
font: bold17px arial,sans-serif;
text-shadow: none;
}
</style>
<!-- end: submit buttons -->
<!--  Begin: Date Picker -->
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
<script>
    $(document).ready(function () {
    	$('input[id$=transDt]').datepicker({
    	    dateFormat: 'dd-mm-yy'
    	});
    });
</script>
<!-- End: Date Picker -->
 <link rel="stylesheet" href="topMenu.css">
 <link rel="stylesheet" href="leftMenu.css">
<style>
div#banner {
background-color: white;
height: 120px;
}
div#main {
border: solid 2px blue;
margin-left: 100px;
min-height: 300px;
background-color: yellow;
}
div#leftMenu {
background-color: green;
width: 80px;
position: relative;
top: 0px;
min-height: 300px;
}
div#topMenu {
border: 0px solid red;
background-color: orange;
}
div#main {
border: 2px solid red;
background-color: red;
}
div#content {
border: 2px solid red;
background-color: red;
}
.container {
    background-color:white;
    display:flex;
}
.fixed-left-menu {
    background-color:white;
    width: 200px;
}
.flex-item {
    background-color:white;
    flex-grow: 1;
}
</style>
</head>
<meta charset="ISO-8859-1">
<title>Dashboard Template</title>
</head>
<body>
<div id="outer">
<!-- begin: banner -->
<div id="banner" align="center">
<img alt="nitttr-bpl" src="banner.jpg" width="70%" height="100%" >
</div>
<!-- begin: banner -->
<br/>
<!--  begin: top menu -->
<div id="topMenu" class="topMenu">
<%@include file="topMenu.jsp" %>
</div>
<!--  end: top menu -->
<!--  begin: container -->
<div class="container">
    
    <!-- begin: side navigation -->
    <!-- end: side navigation -->
    <%@include file="leftMenu.jsp" %>
    <!--  begin: main content -->
    <div class="flex-item" align="center">
    
    <h1>Main Content</h1>
    

<%
DropDownDBService dropDownDBService =new DropDownDBService();
SpTrans spTrans = (SpTrans)request.getAttribute("spTrans");
String homeURL=(String)request.getSession().getAttribute("homeURL");
ArrayList<DropDownDTO> list = new ArrayList<DropDownDTO>();
DropDownDTO ddDto=new DropDownDTO();
Iterator<DropDownDTO> it = list.iterator();
%>
<form action="" id="myform" name="myform" accept-charset="UTF-8" >

<%if(null!=request.getSession().getAttribute("msg")){ %>
<%String msg = (String)request.getSession().getAttribute("msg");%>
<%if(!msg.equals("")){ %>
<br/><font color="red"><%=msg%></font><br/>
<%}%>
<%request.getSession().removeAttribute("msg");%>
<%}%>
<table cellspacing="10px">

<tr>

<td>ID:</td>
<td><%=spTrans.getId()%></td>
</tr>

<tr>

<td>
Transaction Date:
</td>

<td>
<input type="text" id="transDt" name="transDt" value="<%=DateService.getDTSDDMMYYYY(spTrans.getTransDt()).equals("11-11-1111")?"":DateService.getDTSDDMMYYYY(spTrans.getTransDt())%>">
</td>

</tr>

<tr>

<td>
Nos. of Transactions:
</td>

<td>
<input type="text" name="transNos" value="<%=spTrans.getTransNos()%>" size="10">
</td>

</tr>

<tr>

<td>
Fee Amount:
</td>

<td>
<textarea col="50" row="10" name="feeAmt" id="feeAmt"><%=spTrans.getFeeAmt()%></textarea>
</td>

</tr>

</table>

<input type="hidden" name="page" value= "updateNextSpTrans">
<input type="hidden" name="id" value= "<%=spTrans.getId()%>">
</form>

<button type="submit" form="myform"  name="opr" value= "update" class="form-submit-button">Save</button>
<a href="<%=homeURL%>"><button type="button" class="form-submit-button">Close</button></a>
<a href=""><button type="button" class="form-submit-button">Next</button></a>
<% dropDownDBService.closeConnection(); %>
    
    </div>
    <!--  end: main content -->
</div>
<!--  end: container -->
</div>
</body>
</html>
