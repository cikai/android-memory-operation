package cn.jsutils;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void uploadFile(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "data.txt");
            if (file.exists()) {
                Toast fileExist = Toast.makeText(this, "文件存在，读取成功\n文件大小：" + file.length()/1024/1024 + "MB", Toast.LENGTH_LONG);
                fileExist.show();
            } else {
                Toast fileNotExist = Toast.makeText(this, "文件不存在，读取失败", Toast.LENGTH_SHORT);
                fileNotExist.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeMemory(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "data.txt");
            String data = getFileText(file);
            byte[] dataBytes = data.getBytes();
            long startTime = System.currentTimeMillis();
            FileOutputStream outputStream = openFileOutput("testfile", MODE_PRIVATE);
            outputStream.write(dataBytes);
            outputStream.flush();
            outputStream.close();
            long endTime = System.currentTimeMillis();
            Toast.makeText(this, "写入内存成功\n共计耗时 " + String.valueOf((endTime-startTime)/1000) + " 秒\n" + String.valueOf((endTime-startTime)) + "毫秒", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileText(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
                result.append(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
