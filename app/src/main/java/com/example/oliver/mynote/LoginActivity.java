package com.example.oliver.mynote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {
    Mydatabase mydb;
    SQLiteDatabase db;
    EditText name;
    EditText password;
    String Sname;
    String Spw;
    CheckBox repw;
    int isMemory ;
    SharedPreferences preferences = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText)findViewById(R.id.author);
        password = (EditText)findViewById(R.id.password);
        repw = (CheckBox)findViewById(R.id.checkbox1);
        /*
        * 获取SharedPreferences的两种方式:
1 调用Context对象的getSharedPreferences()方法
2 调用Activity对象的getPreferences()方法
两种方式的区别:
调用Context对象的getSharedPreferences()方法获得的SharedPreferences对象可以被同一应用程序下的其他 组件共享.
调用Activity对象的getPreferences()方法获得的SharedPreferences对象只能在该Activity中使用.

SharedPreferences的四种操作模式:
Context.MODE_PRIVATE
Context.MODE_APPEND
Context.MODE_WORLD_READABLE
Context.MODE_WORLD_WRITEABLE

Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件.
MODE_WORLD_READABLE：表示当前文件可以被其他应用读取.
MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入.
将数据保存至SharedPreferences:
SharedPreferences preferences=getSharedPreferences("user",Context.MODE_PRIVATE);
Editor editor=preferences.edit();
String name="xixi";
String age="22";
editor.putString("name", name);
editor.putString("age", age);
editor.commit();*/
        preferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        isMemory = preferences.getInt("isMemory",0);
        if(isMemory == 1){
            name.setText(preferences.getString("name",""));
            password.setText(preferences.getString("password",""));
            repw.setChecked(true);
        }
    }
    public void memory(){
        if(repw.isChecked()){
            if(preferences == null) {
                preferences = getSharedPreferences("userinfo",Context.MODE_PRIVATE);
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name",Sname);
            editor.putString("password",Spw);
            editor.putInt("isMemory",1);
            editor.commit();
        }
        else{
            if(preferences == null) {
                preferences = getSharedPreferences("userinfo",Context.MODE_PRIVATE);
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("isMemory",0);
            editor.commit();
        }
    }
    public void registerOnclick(View view){
//        Intent是一个将要执行的动作的抽象的描述，一般来说是作为参数来使用
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
    public void loginOnclick(View view){
        Sname = name.getText().toString();
        Spw  = password.getText().toString();
        mydb = new Mydatabase(this);
        db = mydb.getWritableDatabase();
        if(Sname.length()==0||Spw.length()==0)
            Toast.makeText(LoginActivity.this, "请输入完整信息！", Toast.LENGTH_LONG).show();
        else {
            Cursor c = db.rawQuery("select * from users where username = ? and password = ?", new String[]{Sname, Spw});
            if (c.getCount() == 0)
                Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_LONG).show();
            else{
                memory();
                Intent i = new Intent(LoginActivity.this,UserActivity.class);
                i.putExtra("username",Sname);
                startActivity(i);
                db.close();
            }
        }
    }
}