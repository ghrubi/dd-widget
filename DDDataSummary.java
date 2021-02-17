import  com.google.gson.Gson;

public class DDDataSummary {
	private String location;
	private String sales;
	private String labor;
	private String salesCount;
	private String discounts;

	public DDDataSummary() {
		location = "https://api.asskick.com/api/summary";
	}

	public void fetch() {
		//System.out.println("### Test API Call ###");

		String response = GetHttpData.fetch(location);

		DataDTO obj = new Gson().fromJson(response, DataDTO.class);
		sales = obj.net_sales;
		salesCount = obj.sales_count;
		labor = obj.labor_pct;
		discounts = obj.discounts;
		//System.out.println("Sales: " + sales);
		//System.out.println("Labor: " + labor);
	}

	public String getSales() {
		return sales;
	}

	public String getLabor() {
		return labor;
	}

	public String getSalesCount() {
		return salesCount;
	}

	public String getDiscounts() {
		return discounts;
	}
}

class DataDTO
{
	String net_sales;
	String sales_count;
//	String oloSales; 
//	String oloCount; 
	String labor_pct; 
	String discounts; 
}
