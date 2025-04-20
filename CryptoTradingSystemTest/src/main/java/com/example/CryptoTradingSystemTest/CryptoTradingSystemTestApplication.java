package com.example.CryptoTradingSystemTest;

import com.example.CryptoTradingSystemTest.api.GetDataFromApi;
import com.example.CryptoTradingSystemTest.model.BestPrice;
import com.example.CryptoTradingSystemTest.model.ModelCommon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.xml.transform.Source;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
public class CryptoTradingSystemTestApplication {
	private static final String apiBestAgregatedPrice= "api/latest-best-aggregated-price";
	private  static final String apiTrading= "api/trade";
	private static final String apiTradingHistory= "api/user-trading-history";
	private static final String apiWalletBalance= "api/currencies-wallet-balance";

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(CryptoTradingSystemTestApplication.class, args);
		GetDataFromApi getDataFromApi = new GetDataFromApi();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please, enter phoneNumber to login");
		String phoneNumber = scanner.nextLine();
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("localhost:8080")
				.setPath("api/login")

				.setParameter("phoneNumber",phoneNumber )
				.build();
		getDataFromApi.SendPostRequest(uri);
		String answer = "no";

		Gson gson = new Gson();
		Type listType = new TypeToken<List<ModelCommon>>() {
		}.getType();
		String response = null;
		while (!answer.equals("yes")){
			System.out.println("----------------------- OPTION MENU --------------------------");
			System.out.println("1. Retrieve the latest best aggregated price.");
			System.out.println("2. Allow user trading based on the latest best aggregated price.");
			System.out.println("3. Retrieve the user’s crypto currencies wallet balance.");
			System.out.println("4. Retrieve the user trading history.");
			System.out.println("----------------------- END --------------------------");
			System.out.println("Enter your option");

			String option = String.valueOf(scanner.nextLine());
			switch (option){
				case "1":
				{
					response = getDataFromApi.GetRequest(new URI("http://localhost:8080/" + apiBestAgregatedPrice));
					if(response!=null){
						System.out.println(" Best aggregated price : " + response);
					}else {
						System.out.println(" No data best aggregated price");
					}

				}
				break;
				case "2":
					System.out.println("Enter type of trading BUY/SALE. Please enter BUY or SALE if don't you can't continue trading ");
					String type= scanner.nextLine();
					System.out.println("Enter amount: ");
					String amount= scanner.next();
					System.out.println("Enter type coin. Please enter ETHUSDT or BTCUSDT if don't you can't continue trading : ");
					String coinType = scanner.next();
					 uri = new URIBuilder()
							 .setScheme("http")
							.setHost("localhost:8080")
							.setPath(apiTrading)
							.setParameter("phoneNumber",phoneNumber)
							 .setParameter("type",type )
							 .setParameter("amount",amount )
							 .setParameter("requiredCoinType",coinType )
							.build();
				response = getDataFromApi.SendPostRequest(uri);
					if (response!=null){
						System.out.println(response);
					}else {
						System.out.println("Trading something wrong with server");
					}
					break;
				case "3":
				{
					uri = new URIBuilder()
							.setScheme("http")
							.setHost("localhost:8080")
							.setPath(apiWalletBalance)
							.setParameter("phoneNumber",phoneNumber)
							.build();
					response = getDataFromApi.GetRequest(uri);
					if(response!=null){
						System.out.println(" Wallet Balance : " + response);
					}else {
						System.out.println(" Get Wallet Balance something wrong with server");
					}
				}
					break;
				case "4":
					uri = new URIBuilder()
							.setScheme("http")
							.setHost("localhost:8080")
							.setPath(apiTradingHistory)
							.setParameter("phoneNumber",phoneNumber)
							.build();
					response = getDataFromApi.GetRequest(uri);
					if(response!=null){
						System.out.println(" Get Trading history user : " + response);
					}else {
						System.out.println(" Get Trading history something wrong with server");
					}
					break;
				default:
				{
					System.out.print("You want exit programming...?(yes/no(different)): ");
					answer = scanner.nextLine();
					if(answer.equals("yes") ){
						System.out.println("You exited programming...... .");
						break;
					}else {
						System.out.println("Continue select your choice.....");
					}
				}
			}
		}
	}

}
