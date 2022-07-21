package com.example.petshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petshop.R;
import com.example.petshop.dao.UserDAO;
import com.example.petshop.model.User;
import com.example.petshop.util.DBContext;

public class SignUpActivity extends AppCompatActivity {
    EditText editText_User, editText_Pass, editText_ConfirmPwd, editText_Email;
    Button button_Create;
    DBContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editText_User = findViewById(R.id.signup_username);
        editText_Pass = findViewById(R.id.signup_pwd);
        editText_ConfirmPwd = findViewById(R.id.signup_confirmpwd);
        editText_Email = findViewById(R.id.signup_email);
        button_Create = findViewById(R.id.signup_create);

        dbContext = new DBContext(this);
        dbContext.getWritableDatabase();
//        dbContext.getReadableDatabase();
        button_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editText_User.getText().toString();
                String email = editText_Email.getText().toString();
                String password = editText_Pass.getText().toString();
                String rePwd = editText_ConfirmPwd.getText().toString();

                UserDAO dao = new UserDAO(SignUpActivity.this);
                User user = new User();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(SignUpActivity.this, "Empty user name", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUpActivity.this, "Empty email", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Empty password", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Empty confirm password", Toast.LENGTH_LONG).show();
                }

                Boolean checkExist = dao.checkExistAcc(userName);
                Boolean checkTheSame = dao.confirmPassword(password, rePwd);

                if (checkExist == true) {
                    Toast.makeText(SignUpActivity.this, "Account existed", Toast.LENGTH_LONG).show();
                }
                else {
                    user.setUserName(userName);
                    user.setPassword(password);
                    if (checkTheSame == true){

                        boolean insert = dao.createUser(user);
                        if (insert) {
                            Toast.makeText(SignUpActivity.this, "Create susccsessfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    } else{
                        Toast.makeText(SignUpActivity.this, "Failed to create", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}