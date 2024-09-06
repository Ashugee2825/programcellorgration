package poctrans.search4;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SpTrans {
 
int id;
Date transDt;
Date transDtFrom;
Date transDtTo;
String transNos;
String feeAmt;

public int getId()
{
return id;
}
public void setId(int id)
{
this.id=id;
}
public Date getTransDt()
{
return transDt;
}
public Date getTransDtFrom()
{
return transDtFrom;
}
public Date getTransDtTo()
{
return transDtTo;
}
public void setTransDt(Date transDt)
{
this.transDt=transDt;
}
public void setTransDtFrom(Date transDtFrom)
{
this.transDtFrom=transDtFrom;
}
public void setTransDtTo(Date transDtTo)
{
this.transDtTo=transDtTo;
}
public String getTransNos()
{
return transNos;
}
public void setTransNos(String transNos)
{
this.transNos=transNos;
}
public String getFeeAmt()
{
return feeAmt;
}
public void setFeeAmt(String feeAmt)
{
this.feeAmt=feeAmt;
}


public void setRequestParam(HttpServletRequest request) {

this.setId(null!=request.getParameter("id")&&!request.getParameter("id").equals("")?Integer.parseInt((String)request.getParameter("id")):0);
this.setTransDt(null!=request.getParameter("transDt")&&!request.getParameter("transDt").equals("")?DateService.getSTDYYYYMMDDFormat(request.getParameter("transDt")):DateService.getSTDYYYYMMDDFormat("11-11-1111"));
this.setTransDtFrom(null!=request.getParameter("transDtFrom")&&!request.getParameter("transDtFrom").equals("")?DateService.getSTDYYYYMMDDFormat(request.getParameter("transDtFrom")):DateService.getSTDYYYYMMDDFormat("11-11-1111"));
this.setTransDtTo(null!=request.getParameter("transDtTo")&&!request.getParameter("transDtTo").equals("")?DateService.getSTDYYYYMMDDFormat(request.getParameter("transDtTo")):DateService.getSTDYYYYMMDDFormat("11-11-1111"));
this.setTransNos(null!=request.getParameter("transNos")?request.getParameter("transNos"):"");
this.setFeeAmt(null!=request.getParameter("feeAmt")?request.getParameter("feeAmt"):"");

}

public void displayReqParam(HttpServletRequest request) {


System.out.println("------Begin:Request Param Values---------");
System.out.println("id = "+request.getParameter("id"));
System.out.println("transDt = "+request.getParameter("transDt"));
System.out.println("transDtFrom = "+request.getParameter("transDtFrom"));
System.out.println("transDtTo = "+request.getParameter("transDtTo"));
System.out.println("transNos = "+request.getParameter("transNos"));
System.out.println("feeAmt = "+request.getParameter("feeAmt"));

System.out.println("------End:Request Param Values---------");
}

public void displayValues() {

System.out.println("Id = "+this.getId());
System.out.println("TransDt = "+DateService.getDTSYYYMMDDFormat(this.getTransDt()));
System.out.println("TransDtFrom = "+DateService.getDTSYYYMMDDFormat(this.getTransDtFrom()));
System.out.println("TransDtTo = "+DateService.getDTSYYYMMDDFormat(this.getTransDtTo()));
System.out.println("TransNos = "+this.getTransNos());
System.out.println("FeeAmt = "+this.getFeeAmt());

}

public void setDefaultValues() {

this.setTransDt(DateService.getSTDYYYYMMDDFormat("11-11-1111"));
this.setTransDtFrom(DateService.getSTDYYYYMMDDFormat("11-11-1111"));
this.setTransDtTo(DateService.getSTDYYYYMMDDFormat("11-11-1111"));
this.setTransNos("");
this.setFeeAmt("");

}
}