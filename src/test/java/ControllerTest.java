import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ControllerTest {

    private final static String API_URL = "https://api-testing-conference.herokuapp.com/v1.0/";

    @Test
    public void getUsers() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL+"users")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    @Test
    public void createUser() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"name\": \"carla\",\r\n  \"username\": \"carlac\",\r\n  \"password\": \"1234\",\r\n  \"role\": \"QA\",\r\n  \"email\": \"hassanecharif@gmail.com\"\r\n}");

        Request request = new Request.Builder()
                .url(API_URL+"users")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    @Test
    public void createMessage() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"theme\": \"Testing\",\r\n  \"subject\": \"Pruebaaaaaa Lidl\",\r\n  \"message\": \"Las API's molan, yuhu!\"\r\n}");

        Request request = new Request.Builder()
                .url(API_URL+"forum")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    @Test
    public void getMessages() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL+"forum")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    @Test
    public void sendPrivateMessage() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"message\": \"mensaje nuevo en el mail\"\r\n}");

        Request request = new Request.Builder()
                .url(API_URL+"users/inbox/cc")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    @Test
    public void getPrivateMessages() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String credentials = Credentials.basic("cc", "1234");

        Request request = new Request.Builder()
                .url(API_URL+"users/inbox/cc")
                .get()
                .addHeader("Authorization", credentials)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
        assertNotNull(response.body());
    }
}

