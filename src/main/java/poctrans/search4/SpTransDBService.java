package poctrans.search4;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class SpTransDBService {
	Connection con;
	
	
	public SpTransDBService()
	{
		DBConnectionDTO conDTO = new DBConnectionDTO();
		con=conDTO.getConnection();
	}
	
	public void closeConnection()
	{
		try {
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
public int getTotalPages(int limit)
	{
		String query="select count(*) from vw_trans_count";
	    int totalRecords=0;
	    int totalPages=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPages=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPages+=1;
		}
		return totalPages;
	}
	
	//pagination
	public int getTotalPages(SpTrans spTrans,int limit)
	{
		String query=getDynamicQuery2(spTrans);
		int totalRecords=0;
	    int totalPages=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPages=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPages+=1;
		}
		return totalPages;
	}
	
	
	public int getSpTransId(SpTrans spTrans)
	{
		int id=0;
		String query="select id from vw_trans_count";
String whereClause = " where "+ "transDt=? and transNos=? and feeAmt=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setDate(1, java.sql.Date.valueOf(DateService.getDTSYYYMMDDFormat(spTrans.getTransDt())));
pstmt.setString(2, spTrans.getTransNos());
pstmt.setString(3, spTrans.getFeeAmt());
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()) {
	       	id = rs.getInt("id");
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return id;
	}
	public void createSpTrans(SpTrans spTrans)
	{
		
String query="INSERT INTO vw_trans_count(transDt,transNos,feeAmt) VALUES(?,?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setDate(1, java.sql.Date.valueOf(DateService.getDTSYYYMMDDFormat(spTrans.getTransDt())));
pstmt.setString(2, spTrans.getTransNos());
pstmt.setString(3, spTrans.getFeeAmt());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getSpTransId(spTrans);
		spTrans.setId(id);
	}
	public void updateSpTrans(SpTrans spTrans)
	{
		
String query="update vw_trans_count set "+"transDt=?,transNos=?,feeAmt=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setDate(1, java.sql.Date.valueOf(DateService.getDTSYYYMMDDFormat(spTrans.getTransDt())));
pstmt.setString(2, spTrans.getTransNos());
pstmt.setString(3, spTrans.getFeeAmt());
pstmt.setInt(4, spTrans.getId());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	    	System.out.println(e);
		}
		
	}
	public String getValue(String code,String table) {
		
		String value="";
		String query="select value from "+table+" where code='"+code+"'";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	    	value=rs.getString("value");
	    }
		}
		catch (Exception e) {
			System.out.println(e);
		}
	    return value;
	}
	
	public SpTrans getSpTrans(int id)
	{
		SpTrans spTrans =new SpTrans();
		String query="select * from vw_trans_count where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
spTrans.setId(rs.getInt("id")==0?0:rs.getInt("id"));
spTrans.setTransDt(DateService.getDTDYYYYMMDDFormat(rs.getDate("transDt")==null?DateService.getDefaultDtYYYYMMDD():rs.getDate("transDt")));
spTrans.setTransNos(rs.getString("transNos")==null?"":rs.getString("transNos"));
spTrans.setFeeAmt(rs.getString("feeAmt")==null?"":rs.getString("feeAmt"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return spTrans;
	}
	
	
	public ArrayList<SpTrans> getSpTransList()
	{
		ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
		String query="select * from vw_trans_count";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	SpTrans spTrans =new SpTrans();
spTrans.setId(rs.getInt("id")==0?0:rs.getInt("id"));
spTrans.setTransDt(DateService.getDTDYYYYMMDDFormat(rs.getDate("transDt")==null?DateService.getDefaultDtYYYYMMDD():rs.getDate("transDt")));
spTrans.setTransNos(rs.getString("transNos")==null?"":rs.getString("transNos"));
spTrans.setFeeAmt(rs.getString("feeAmt")==null?"":rs.getString("feeAmt"));
	    	spTransList.add(spTrans);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return spTransList;
	}
	
	public ArrayList<SpTrans> getSpTransList(int pageNo,int limit)
	{
		ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
String query="select * from vw_trans_count limit "+limit +" offset "+limit*(pageNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	SpTrans spTrans =new SpTrans();
spTrans.setId(rs.getInt("id")==0?0:rs.getInt("id"));
spTrans.setTransDt(DateService.getDTDYYYYMMDDFormat(rs.getDate("transDt")==null?DateService.getDefaultDtYYYYMMDD():rs.getDate("transDt")));
spTrans.setTransNos(rs.getString("transNos")==null?"":rs.getString("transNos"));
spTrans.setFeeAmt(rs.getString("feeAmt")==null?"":rs.getString("feeAmt"));
	    	spTransList.add(spTrans);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return spTransList;
	}
	
	public void deleteSpTrans(int id) {
		
			String query="delete from vw_trans_count where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(SpTrans spTrans)
{
String query="select * from vw_trans_count ";
String whereClause="";
whereClause+=(null==spTrans.getTransDtFrom()||DateService.getDTSYYYMMDDFormat(spTrans.getTransDtFrom()).equals("1111-11-11"))?"":" (transDt between '"+DateService.getDTSYYYMMDDFormat(spTrans.getTransDtFrom())+"' and '"+DateService.getDTSYYYMMDDFormat(spTrans.getTransDtTo())+"')";
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery2(SpTrans spTrans)
{
String query="select count(*) from vw_trans_count ";
String whereClause="";
whereClause+=(null==spTrans.getTransDtFrom()||DateService.getDTSYYYMMDDFormat(spTrans.getTransDtFrom()).equals("1111-11-11"))?"":" (transDt between '"+DateService.getDTSYYYMMDDFormat(spTrans.getTransDtFrom())+"' and '"+DateService.getDTSYYYMMDDFormat(spTrans.getTransDtTo())+"')";
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<SpTrans> getSpTransList(SpTrans spTrans)
{
ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
String query=getDynamicQuery(spTrans);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
SpTrans spTrans2 =new SpTrans();
spTrans2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
spTrans2.setTransDt(DateService.getDTDYYYYMMDDFormat(rs.getDate("transDt")==null?DateService.getDefaultDtYYYYMMDD():rs.getDate("transDt")));
spTrans2.setTransNos(rs.getString("transNos")==null?"":rs.getString("transNos"));
spTrans2.setFeeAmt(rs.getString("feeAmt")==null?"":rs.getString("feeAmt"));
    	spTransList.add(spTrans2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return spTransList;
}
	
public ArrayList<SpTrans> getSpTransList(SpTrans spTrans,int pageNo,int limit)
{
ArrayList<SpTrans> spTransList =new ArrayList<SpTrans>();
String query=getDynamicQuery(spTrans);
query+= " limit "+limit +" offset "+limit*(pageNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
SpTrans spTrans2 =new SpTrans();
spTrans2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
spTrans2.setTransDt(DateService.getDTDYYYYMMDDFormat(rs.getDate("transDt")==null?DateService.getDefaultDtYYYYMMDD():rs.getDate("transDt")));
spTrans2.setTransNos(rs.getString("transNos")==null?"":rs.getString("transNos"));
spTrans2.setFeeAmt(rs.getString("feeAmt")==null?"":rs.getString("feeAmt"));
    	spTransList.add(spTrans2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return spTransList;
}
	
	
	public static void main(String[] args) {
		
		SpTransDBService spTransDBService =new SpTransDBService();
		
		
		
		 //Test-1 : Create SpTrans
		  
		  SpTrans spTrans = new SpTrans(); spTrans.setDefaultValues();
		  spTransDBService.createSpTrans(spTrans);
		  
		 ArrayList<SpTrans> spTransList = spTransDBService.getSpTransList();
		SpTransService spTransService =new SpTransService();
		spTransService.displayList(spTransList);
		
	}
}
