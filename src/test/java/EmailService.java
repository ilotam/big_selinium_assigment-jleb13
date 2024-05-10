import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailService {

    private static final Gson gson = new Gson();
    public static String fetchLatestRegistrationUrl(String user) throws Exception {
        String url = "https://restmail.net/mail/" + user;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            String jsonResponse = EntityUtils.toString(httpClient.execute(request).getEntity());
            Type listType = new TypeToken<List<Email>>() {
            }.getType();
            List<Email> emails = gson.fromJson(jsonResponse, listType);

            if (!emails.isEmpty()) {
                String htmlContent = emails.get(0).text;
                return extractURL(htmlContent);
            }
        }
        return null;
    }

    public static String extractURL(String text) {
        String regex = "https://www.arukereso.hu/regisztracio/\\?operation=activation&activationCode=[^\\s]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return "No URL found";
    }

    static class Email {
        String text;
    }
}
