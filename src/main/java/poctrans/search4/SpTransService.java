package poctrans.search4;

import java.util.ArrayList;

public class SpTransService {

	public void displayList(ArrayList<SpTrans> spTransList)
	{
		spTransList.forEach((spTrans) -> print(spTrans));
	}
	
	public void print(SpTrans spTrans)
	{
		spTrans.displayValues();
	}
	
}
