package example.android.com.chalrahi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


public class OkHttpHandler extends AsyncTask<Void, Void, String> {
    private Context context;
    public OkHttpHandler(Context context) {
        this.context = context;
        //spinner = new ProgressDialog(context); // spinner
    }

    ProgressDialog progressDialog;
    // TODO: replace with your own image url
    private final String REQUEST_URL = "http://app-login.herokuapp.com/index.php";

    OkHttpClient httpClient = new OkHttpClient();

    public static final MediaType JSON
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Wait", "Downloading...");
    }
    @Override
    protected String doInBackground(Void... params) {

        String parameters = "email=pawan@gmail.com&password=qwert1y&tag=login";
        RequestBody reqBody = RequestBody.create(JSON,parameters);
        Request.Builder builder = new Request.Builder();
        builder.url(REQUEST_URL).post(reqBody);
        Request request = builder.build();

        try
        {
            Response response = httpClient.newCall(request).execute();
            return response.body().string();
        } catch (Exception e)
        {

        }
        return null;
    }
}
