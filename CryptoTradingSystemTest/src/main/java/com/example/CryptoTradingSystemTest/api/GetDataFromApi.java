package com.example.CryptoTradingSystemTest.api;


import com.example.CryptoTradingSystemTest.model.Huo;
import com.example.CryptoTradingSystemTest.model.HuobiModel;
import com.example.CryptoTradingSystemTest.model.ModelCommon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.aspectj.apache.bcel.classfile.annotation.NameValuePair;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetDataFromApi {
    public List<Huo> getDataApiHuobi() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.huobi.pro/market/tickers")).build();

        HttpResponse<String> response = null;
        HuobiModel target2 = null;
        List<Huo> listDataFilterHuobi;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            target2 = gson.fromJson(response.body(),
                    HuobiModel.class);

            listDataFilterHuobi = target2.getData().parallelStream()
                    .filter(item -> item.getSymbol().equalsIgnoreCase("ETHUSDT") ||
                            item.getSymbol().equalsIgnoreCase("BTCUSDT"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
        return listDataFilterHuobi;
    }

    public List<ModelCommon> getDataApiBinance() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.binance.com/api/v3/ticker/bookTicker")).build();

        HttpResponse<String> response = null;
        List<ModelCommon> listDataFilterBinance;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ModelCommon>>() {
            }.getType();

            List<ModelCommon> gsonList = gson.fromJson(response.body(), listType);
            listDataFilterBinance = gsonList.parallelStream()
                    .filter(item -> item.getSymbol().equalsIgnoreCase("ETHUSDT") ||
                            item.getSymbol().equalsIgnoreCase("BTCUSDT"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
        return listDataFilterBinance;
    }

    public String GetRequest(URI uri) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String SendPostRequest(URI uri)
    {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(uri);

            CloseableHttpResponse response = client.execute(httpPost);

            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            return null;
        }
    }

}
