package com.example.oliver.mynote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.content.*;
import android.database.Cursor;


public class RegisterActivity extends AppCompatActivity{
    Mydatabase mydb;
    SQLiteDatabase db;
    String Sname;
    String Spw;
    String Srepw;
    EditText name;
    EditText pw;
    EditText repw;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void registerOnclick(View view){
        name = (EditText)findViewById(R.id.author);
        Sname = name.getText().toString();
        pw = (EditText)findViewById(R.id.password);
        Spw = pw.getText().toString();
        repw = (EditText)findViewById(R.id.repassword);
        Srepw = repw.getText().toString();
        mydb = new Mydatabase(this);
        /*
        * Android使用getWritableDatabase()和getReadableDatabase()方法都可以获取一个用于操作数据库的SQLiteDatabase实例。(getReadableDatabase()方法中会调用getWritableDatabase()方法)
其中getWritableDatabase() 方法以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，倘若使用的是getWritableDatabase() 方法就会出错。

getReadableDatabase()方法则是先以读写方式打开数据库，如果数据库的磁盘空间满了，就会打开失败，当打开失败后会继续尝试以只读方式打开数据库。如果该问题成功解决，则只读数据库对象就会关闭，然后返回一个可读写的数据库对象。*/
        db = mydb.getWritableDatabase();
        if(Sname.length()==0||Spw.length()==0||Srepw.length()==0)
            Toast.makeText(RegisterActivity.this, "请输入完整信息！", Toast.LENGTH_LONG).show();
        else{
            if(!Spw.equals(Srepw))
                Toast.makeText(RegisterActivity.this, "密码不一致！", Toast.LENGTH_LONG).show();
            else{
                Cursor c = db.rawQuery("select * from users where username = ?",new String[]{Sname});
                if(c.getCount()!=0){
                    Toast.makeText(RegisterActivity.this, "用户名已存在！", Toast.LENGTH_LONG).show();
                }
                else{
                    ContentValues cv = new ContentValues();
                    cv.put("username",Sname);
                    cv.put("password",Spw);
                    db.insert("users",null,cv);
                    db.close();
                    Intent i = new Intent(RegisterActivity.this,RegistersucceedActivity.class);
                    startActivity(i);
                }
            }
        }
    }
}