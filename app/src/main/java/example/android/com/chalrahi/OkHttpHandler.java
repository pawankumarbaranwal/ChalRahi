package example.android.com.chalrahi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


public class OkHttpHandler extends AsyncTask<String, Void, String> {
    private Context context;
    private ProgressBar progressBar;
    public OkHttpHandler(Context context, ProgressBar pBar, String url){
        this.context = context;
        progressBar=pBar;
        REQUEST_URL =url;
        //spinner = new ProgressDialog(context); // spinner
    }
    // TODO: replace with your own image url
    private String REQUEST_URL;

    OkHttpClient httpClient = new OkHttpClient();

    public static final MediaType JSON
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
  /*  @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // progressBar = new ProgressBar(context,null,android.R.attr.progressBarStyleLarge);
        progressBar.setVisibility(View.VISIBLE);
    }*/
    @Override
    protected String doInBackground(String... params) {


        String parameters ="";
        Log.i("qwertyuiop",params[0]+params[1]);
        if ((params[params.length-1]).equals("login"))
        {
            parameters = "mobilenumber=" + params[0] + "&password=" + params[1] + "&tag="+params[2];
        }else if ((params[params.length-1]).equals("register"))
        {
            parameters = "name="+params[0]+
                    "&email="+params[1]+
                    "&mobilenumber="+params[2]+
                    "&dateofbirth="+params[3]+
                    "&password=" + params[4]+
                    "&sex="+params[5]+
                    "&eContactName="+params[6]+
                    "&eContact="+params[7]+
                    "&tag="+params[8];
        }
        Log.i("OkHttpHandler",parameters);
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
            return null;
        }
    }
/*
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.INVISIBLE);
    }*/
}
