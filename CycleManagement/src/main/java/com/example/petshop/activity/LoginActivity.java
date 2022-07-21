package com.example.petshop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petshop.R;
import com.example.petshop.dao.UserDAO;
import com.example.petshop.model.User;
import com.example.petshop.util.DBContext;

public class LoginActivity extends AppCompatActivity {

    EditText editText_User, editText_Pass;
    TextView logoutTxt;
    Button button_Login, button_signup;
    DBContext dbContext;
    CheckBox checkBoxBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);

        editText_User = findViewById(R.id.userName);
        editText_Pass = findViewById(R.id.password);
        button_Login = findViewById(R.id.loginButton);
        button_signup = findViewById(R.id.sign_up);
        logoutTxt = findViewById(R.id.msgRecieve);
        checkBoxBtn = findViewById(R.id.rememberMe);

        dbContext = new DBContext(this);
        dbContext.getReadableDatabase();

        // get data from read file
        User rememberUser = readFileAccountDAT();
        editText_User.setText(rememberUser.getUserName());
        editText_Pass.setText(rememberUser.getPassword());

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editText_User.getText().toString();
                String password = editText_Pass.getText().toString();
                Boolean checkSignIn = dbContext.checkSignIn(user, password);
                Boolean check = checkBoxBtn.isChecked();
                writeFileAccountDAT(user, password, check);

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "All field required not empty", Toast.LENGTH_LONG).show();
                } else if (checkSignIn == true) {
                    Toast.makeText(LoginActivity.this, "Thanh cong", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("userKey", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong user name or password", Toast.LENGTH_LONG).show();
                }
            }


        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getExtras().containsKey("msg")) {
            String msg = data.getStringExtra("msg");
            logoutTxt.setText(msg);
        }

    }

    public void writeFileAccountDAT(String username, String password, boolean check) {
        SharedPreferences pref = getSharedPreferences("remember_account.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (check) {
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putBoolean("check", true);
        } else {
            editor.clear();
        }
        editor.commit();
    }

    public User readFileAccountDAT() {
        User user = new User();
        SharedPreferences pref = getSharedPreferences("remember_account.dat", MODE_PRIVATE);
        String userName = pref.getString("username", "");
        String pwd = pref.getString("password", "");
        user.setUserName(userName);
        user.setPassword(pwd);
        return user;
    }
}


