import com.google.gson.Gson;

public class TestHttp {

	public static void main(String[] args) {
		String location = "https://api.asskick.com/api/summary";
		String result = GetHttpData.fetch(location);
		System.out.println(result);

		DataDTO obj = new Gson().fromJson(result, DataDTO.class);
		String sales = obj.net_sales;
		String salesCount = obj.sales_count;
		String labor = obj.labor_pct;
		String discounts = obj.discounts;
		System.out.println("Sales: " + sales);
	}
}
class DataDTO
{
String net_sales;
String sales_count;
//String oloSales; 
//String oloCount; 
String labor_pct;
String discounts;
}
