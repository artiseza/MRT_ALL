package com.example.user.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editname,editprice;
    TextView texname,texprice,texno,sum,total;
    Button add , edit , delete , query ;
    SQLiteDatabase dbrw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editname =(EditText)findViewById(R.id.editname);
        editprice =(EditText)findViewById(R.id.editprice);
        texno =(TextView) findViewById(R.id.texno);
        texname =(TextView) findViewById(R.id.texname);
        texprice =(TextView) findViewById(R.id.texprice);
        sum =(TextView) findViewById(R.id.sum);
        total =(TextView) findViewById(R.id.total);
        add =(Button) findViewById(R.id.add);
        edit =(Button) findViewById(R.id.edit);
        delete =(Button) findViewById(R.id.delete);
        query =(Button) findViewById(R.id.query);

        MyDBHelper dbhelper =new MyDBHelper(this);
        dbrw = dbhelper.getWritableDatabase();
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newBook();
            }
        });
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                renewBook();
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deleteBook();
            }
        });
        query.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                queryBook();
            }
        });
    }







    public void newBook(){
        if (editname.getText().toString().equals("")
                || editprice.getText().toString().equals(""))
            Toast.makeText(this,"輸入資料不完全",Toast.LENGTH_SHORT).show();
        else {
            double price =Double.parseDouble(editprice.getText().toString());

            ContentValues cv =new ContentValues();
            cv.put("title",editname.getText().toString());
            cv.put("price",price);

            dbrw.insert("myTable", null, cv);

            Toast.makeText(this,"新增花費:"+ editname.getText().toString()
                + "價格:" + price,Toast.LENGTH_SHORT).show();

            editname.setText("");
            editprice.setText("");
        }
    }
    public void renewBook(){
        if (editname.getText().toString().equals("")
                ||editprice.getText().toString().equals(""))
            Toast.makeText(this,"沒有輸入更新值",Toast.LENGTH_SHORT).show();
        else {
            double newprice =Double.parseDouble(editprice.getText().toString());

            ContentValues cv = new ContentValues();
            cv.put("price",newprice);

            dbrw.update("myTable", cv,"title=" + "'" + editname.getText().toString() + "'",null);

            Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();

            editname.setText("");
            editprice.setText("");
        }
    }
    public void deleteBook(){
        if (editname.getText().toString().equals(""))
            Toast.makeText(this,"請輸入要刪除之值",Toast.LENGTH_SHORT).show();
        else {
            dbrw.delete("myTable","title=" + "'" + editname.getText().toString() + "'",null);
            Toast.makeText(this,"刪除成功",Toast.LENGTH_SHORT).show();
            editname.setText("");
        }
    }
    public void queryBook(){
        String index ="順序\n",title ="花費項目\n",price="價格\n";
        String[] column ={"title", "price" };
        int tot=0,sun=0;

        Cursor c;
        if (editname.getText().toString().equals("")) {
            c = dbrw.query("myTable", column, null, null, null, null, null);
        }
        else {
            c = dbrw.query("myTable", column, "title=" + "'" +
                editname.getText().toString() + "'", null, null, null, null);
        }
        if (c.getCount()>0){
            c.moveToFirst();
            for (int i = 0; i < c.getCount();i++){
                index += (i+1) + "\n";
                title +=c.getString(0) + "\n";
                price +=c.getString(1) + "\n";
                tot += Integer.parseInt(c.getString(1));
                sun = tot;
                c.moveToNext();
            }
            String sum = String.valueOf(sun);
            total.setText(sum);
            texno.setText(index);
            texname.setText(title);
            texprice.setText(price);
            Toast.makeText(this,"共有"+c.getCount()+"筆記錄",Toast.LENGTH_SHORT).show();
        }
    }
}
