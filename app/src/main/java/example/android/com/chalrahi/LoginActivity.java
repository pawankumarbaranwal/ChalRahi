package example.android.com.chalrahi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;
    private EditText mobileNumber;
    private EditText password;
    String response;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();
    }

    private void initializeView()
    {
        login=(Button)findViewById(R.id.btnLoginAsCustomer);
        register=(Button)findViewById(R.id.btnRegisterLink);
        mobileNumber=(EditText)findViewById(R.id.etPhone);
        password=(EditText)findViewById(R.id.etPassword);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        OkHttpHandler handler = new OkHttpHandler(this);
        Intent intent;
        Validator validator =new Validator();
        if (v==login){
            try
            {
                validator.validateLoginDetails(mobileNumber.getText().toString(),password.getText().toString());
                    response = handler.execute().get();
                    if (response != null && response != "")
                    {
                        Log.i("reponse", response);
                        Gson gson = new GsonBuilder().create();
                        Map<String, Object> mapObject = new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {}.getType());
                        Log.i("ResponseType",mapObject.get("error")+"");
                        if (mapObject.get("error").toString().equals("true"))
                        {
                            throw new Exception(mapObject.get("error_msg")+"");
                        }
                    }
                intent=new Intent(this,UserHomeActivity.class);
                startActivity(intent);
                //return pnr;
            } catch (Exception e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        else if (v==register){
            intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}