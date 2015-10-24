package example.android.com.chalrahi;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Response;
import example.android.com.utils.Http;
import example.android.com.utils.OnTaskCompleted;

public class OkHttpHandler extends AsyncTask<String, Void, Boolean> {
    private Context context;
    private String responseString;
    private Http http = null;
    private OnTaskCompleted onTaskCompletedResponse ;
    Dialog dialog;
    public OkHttpHandler(OnTaskCompleted onTaskCompletedReturn,Context context, String url){
        this.context = context;
        this.onTaskCompletedResponse = onTaskCompletedReturn;
        REQUEST_URL =url;
    }
    // TODO: get it from the string.xml
    private String REQUEST_URL;

    public static final MediaType JSON
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

      @Override
      protected void onPreExecute() {
          super.onPreExecute();
          dialog=ShowError.displayProgressBar(context);
      }
    @Override
    protected Boolean doInBackground(String... params) {

        http = Http.instance();
        String parameters ="";
        if ((params[params.length-1]).equals("login"))
        {
            parameters = "mobilenumber=" + params[0] +
                            "&password=" + params[1] +
                            "&tag="+params[2];
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
        Log.d("OkHttpHandler", parameters);
        http.requestBody(JSON, parameters);
        http.requestPOSTBuilder(REQUEST_URL);
        try
        {
           Response response = http.responseExecute();
            if(response != null &&  response.isSuccessful()) {
                responseString = response.body().string();
                return true;
            }else{
                return false;
            }
        } catch (Exception e)
        {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
        dialog.hide();
        this.onTaskCompletedResponse.onTaskCompleted(responseString);
    }
}
