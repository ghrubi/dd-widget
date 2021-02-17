import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class GetHttpData {
	private static HttpClient client = HttpClient.newHttpClient();

	public static String fetch(String url) {
	//public static HttpResponse fetch(String url) {
		//System.out.println("### Test API Call ###");
	        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
		HttpResponse response = null;

		try{
			response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			//System.out.println(response.body());
		} catch(Exception e){
			e.printStackTrace();
		}

		return response.body().toString();
	}
}

