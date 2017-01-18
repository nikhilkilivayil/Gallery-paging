package com.example.android.effectivenavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

    }

    public void click(View v){
        Intent mainActivity=new Intent(DemoActivity.this,CollectionDemoActivity.class);
        mainActivity.putExtra("type","list");
        mainActivity.putExtra("cover","cover path");
        //mainActivity.putExtra(("type","dir"));
        //mainActivity.putExtra("value","/mnt/sdcard/");
        ArrayList<Album> list=prepareValues();
        mainActivity.putParcelableArrayListExtra("value",list);
        startActivityForResult(mainActivity,2);
    }

    ArrayList<Album> prepareValues(){
        ArrayList<Album> list=new ArrayList<Album>();
        for(int i=0;i<100;i++){
            Album album=new Album();
            album.setName("Title"+i);
            album.setImageFile("http://i.imgur.com/DvpvklR.png");
            album.setVideoPath("Video"+i);
            list.add(album);
        }

        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && requestCode==RESULT_OK){
            String video_url=data.getStringExtra("video");
            Toast.makeText(this,video_url,Toast.LENGTH_LONG).show();
        }

    }
}
