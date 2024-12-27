package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path=".\\testData\\datadrivenexcel.xlsx";//taking xl file from testdata
		
		ExcelUtility xlutils=new ExcelUtility(path);
		//creating object of Excelutiliy file
		int totalrows=xlutils.getRowCount("Sheet1");//getting total no. of rows
		int totalcols=xlutils.getCellCount("Sheet1", totalrows);
		
		String loginData[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				loginData[i-1][j]=xlutils.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}

}
