package kucoin;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class StreamApiExamples {

    public List<TickerData> getTickers(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker", TickerData.class);
    }

    @Test
    public void checkCrypto(){
        List<TickerData> usdTickers = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
        System.out.println(getTickers().size());
        System.out.println(usdTickers.size());
    }

    @Test
    public void map(){
//        Map<String, Float> usd = new HashMap<>();
//        List<String> lowerCases = getTickers().stream().map(x->x.getSymbol().toLowerCase()).collect(Collectors.toList());
//        getTickers().forEach(x->usd.put(x.getSymbol(),Float.parseFloat(x.getChangeRate())));
        List<TickerShort> shortList = new ArrayList<>();
        getTickers().forEach(x->shortList.add(new TickerShort(x.getSymbol(),Float.parseFloat(x.getChangeRate()))));
        System.out.println(shortList);
    }
}
