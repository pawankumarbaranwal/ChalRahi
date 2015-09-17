package example.android.com.chalrahi;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import example.android.com.R;
import example.android.com.utils.OnTaskCompleted;
import example.android.com.utils.SharedPreferenceHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted, View.OnFocusChangeListener {

    private Button login;
    private Button register;
    private Button clearMobileNumber;
    private Button clearPassword;
    private EditText mobileNumber;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();
    }

    private void initializeView() {

        login = (Button) findViewById(R.id.btnLoginAsCustomer);
        register = (Button) findViewById(R.id.btnRegisterLink);
        clearMobileNumber = (Button) findViewById(R.id.btnClearPhone);
        clearPassword = (Button) findViewById(R.id.btnClearPassword);
        mobileNumber = (EditText) findViewById(R.id.etPhone);
        password = (EditText) findViewById(R.id.etPassword);

        mobileNumber.setOnFocusChangeListener(this);
        password.setOnFocusChangeListener(this);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        clearMobileNumber.setOnClickListener(this);
        clearPassword.setOnClickListener(this);

     /*   mobileNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (mobileNumber.getText().toString().length() != 0) {
                        clearMobileNumber.setVisibility(View.VISIBLE);
                    }
                    clearPassword.setVisibility(View.INVISIBLE);
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (password.getText().toString().length() != 0) {
                        clearPassword.setVisibility(View.VISIBLE);
                    }
                    clearMobileNumber.setVisibility(View.INVISIBLE);
                }
            }
        });

*/
        mobileNumber.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    clearMobileNumber.setVisibility(View.VISIBLE);
                } else {
                    clearMobileNumber.setVisibility(View.INVISIBLE);
                }
            }
        });
/*

        password.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("pppppppppp","11111111111111");
                password.setFocusable(true);
                clearMobileNumber.setVisibility(View.INVISIBLE);
                return true; // return is important...
            }
        });
*/

       /* password.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(keyCode == 66) {
                    clearMobileNumber.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });*/
        password.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                clearMobileNumber.setVisibility(View.INVISIBLE);
                if (s.length() != 0) {
                    clearPassword.setVisibility(View.VISIBLE);
                } else {
                    clearPassword.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        OkHttpHandler handler = new OkHttpHandler(this, this, "http://android-rahi.herokuapp.com/index.php");

        Validator validator = new Validator();
        if (v == clearMobileNumber) {
            mobileNumber.setText("");
        } else if (v == password) {
            clearMobileNumber.setVisibility(View.INVISIBLE);
        } else if (v == clearPassword) {
            password.setText("");
        } else if (v == login) try {
            validator.validateLoginDetails(mobileNumber.getText().toString(), password.getText().toString());
            handler.execute(mobileNumber.getText().toString(), password.getText().toString(), "login");
            //response =handler;
            /**/
        } catch (Exception e) {
            e.printStackTrace();
            ShowError.displayError(this, e.getMessage());

        }
        else if (v == register) {
            intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onTaskCompleted(String response) {
        Log.d("Response", response);
        LoginUser loginUser = new LoginUser();
        Intent intent;
        try {
            if (response != null && response != "") {

                Map<String, Object> mapObject = new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (mapObject.get("error").toString().equals("true")) {
                    throw new Exception(mapObject.get("error_msg") + "");
                } else {
                    loginUser = new Gson().fromJson(response, LoginUser.class);
                    Log.i("ResponseType", loginUser.toString());
                }
                SharedPreferenceHandler.writeValue(this, "LoginObject", response);
                Log.i("asdfghjkl", SharedPreferenceHandler.readValue(this, "LoginObject"));

                intent = new Intent(this, UserHomeActivity.class);
                startActivity(intent);
            } else if (response == null) {
                throw new Exception("Technical error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ShowError.displayError(this, e.getMessage());
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus) {
            if (v == mobileNumber) {
                if (mobileNumber.getText().toString().length() != 0) {
                    clearMobileNumber.setVisibility(View.VISIBLE);
                }
                clearPassword.setVisibility(View.INVISIBLE);
            } else if (v == password) {
                Log.i("xxxxxxxxxxx", "22222222222222");
                if (password.getText().toString().length() != 0) {
                    clearPassword.setVisibility(View.VISIBLE);
                }
                clearMobileNumber.setVisibility(View.INVISIBLE);
            }
        }
    }
}
