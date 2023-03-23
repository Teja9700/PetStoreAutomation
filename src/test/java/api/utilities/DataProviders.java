package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// get all data from excel sheet
	// this particular method is responsible for generating data and passing the data to another test methods in project.
	// get all data method. we are exactly doing here is we r getting data file or path  under test data folder.
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{

		String path =System.getProperty("user.dir")+"//Testdata//Teja.xlsx";	
		XLUtility xl= new XLUtility(path);

		int rownum= xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1", 1);

		String apidata[][] = new String[rownum][colcount];
		//  the data is stored in 2d array. here array is apidata[][].	
		for(int i=1; i<=rownum; i++) {

			for(int j=0; j<colcount; j++) {
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}	
	@DataProvider(name="UserNames")// get only user names from excel sheet
	public String[] getUserNames() throws IOException{
		String path=System.getProperty("user.dir")+"//Testdata//Teja.xlsx";
		XLUtility xl=new XLUtility(path);

		int rownum = xl.getRowCount("Sheet1");

		String apidata[] = new String[rownum];

		for(int i=1; i<=rownum; i++) {
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);
		}
		return apidata;	
	}
}

