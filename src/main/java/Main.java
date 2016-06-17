import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.InputStream;
import java.io.StringWriter;


public class Main {
    public static void main(String[] args) throws UnirestException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("issue.json");
        String body= convertStreamToString(inputStream);
        System.out.println("Body="+body);
        HttpResponse<JsonNode> response = Unirest.get("https://XXX.atlassian.net/rest/api/2/issue")
                .basicAuth("username", "password")
               .header("content-type", "application/json")
               .body(body)
                .asJson();
        System.out.println(response.getStatus()+"\t"+response.getStatusText()+"\nBody="+response.getBody().toString());
    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
