//
//Not used anymore after refactoring.
//
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import  com.google.gson.Gson;

public class GetData {
	private String location = "https://api.asskick.com/api/summary";
	private HttpClient client;
	private HttpRequest httpRequest;
	private String sales;
	private String labor;
	private String salesCount;
	private String discounts;

	public GetData() {
		location = "https://api.asskick.com/api/summary";
		client = HttpClient.newHttpClient();
		httpRequest = HttpRequest.newBuilder().uri(URI.create(location)).GET().build();
		//fetch();
	}

	public void fetch() {
		//System.out.println("### Test API Call ###");

		try{
			var response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			//System.out.println(response.body());

			DataDTO obj = new Gson().fromJson(response.body(), DataDTO.class);
			sales = obj.net_sales;
			salesCount = obj.sales_count;
			labor = obj.labor_pct;
			discounts = obj.discounts;
			//System.out.println("Sales: " + sales);
			//System.out.println("Labor: " + labor);
		}catch(Exception e){
			e.printStackTrace();
		}
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
