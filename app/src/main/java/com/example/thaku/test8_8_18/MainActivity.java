package com.example.thaku.test8_8_18;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.Parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    ParserClass parserClass = new ParserClass();
    ArrayList<ModelClass>data=new ArrayList<>();
    ImageView click;
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parserClass.execute();
        recycler_view = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(linearLayoutManager);
        click=findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (x==0){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recycler_view.setLayoutManager(linearLayoutManager);
                    x=1;
                }

                else if (x==1){
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
                    recycler_view.setLayoutManager(linearLayoutManager);
                    x=2;
                }
                else if (x==2){
                    StaggeredGridLayoutManager gridLayoutManager =
                            new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
// Attach the layout manager to the recycler view
                    recycler_view.setLayoutManager(gridLayoutManager);
                    x=0;
                }

//                llman();

////                if (click==doubleClick)
//                StaggeredGridLayoutManager gridLayoutManager =
//                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//// Attach the layout manager to the recycler view
//                recycler_view.setLayoutManager(gridLayoutManager);

//                if (GestureDetector.OnDoubleTapListener){
//
//                }

            }
        });

//        click.setOnClickListener(new GestureDetector.OnDoubleTapListener(click) {
//            @Override
//            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
//                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
//                recycler_view.setLayoutManager(linearLayoutManager);
//
//                return true;
//            }
//
//            @Override
//            public boolean onDoubleTap(MotionEvent motionEvent) {
////                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.GridView,false);
//            return true;
//            }
//
//            @Override
//            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
//                return false;
//            }
//        });



    }
//    public void llman(){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recycler_view.setLayoutManager(linearLayoutManager);
//    }

    public class ParserClass extends AsyncTask {

        JSONObject job;
        String title,urltoimage;
        ArrayList<ModelClass> jsonobject=new ArrayList<>();
        @Override
        protected Object doInBackground(Object[] objects) {

            URL url= null;
            try {
                url = new URL("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=ca56f4dcf86a4487aa26437f54dc941d");
                HttpURLConnection con=(HttpURLConnection)url.openConnection();

                InputStream is=con.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);

                BufferedReader br=new BufferedReader(isr);
                StringBuffer sb=new StringBuffer();

                String line;
                while (!((line = br.readLine()) == null)) {
                    sb.append(line);
                }

                String json=sb.toString();
                job=new JSONObject(json);
                JSONArray jar=job.getJSONArray("articles");


                for (int i=0;i<jar.length();i++){

                    JSONObject newjob=jar.getJSONObject(i);

                    title=newjob.getString("title");
                    if(newjob.has("urlToImage"))
                    {
                        urltoimage=newjob.getString("urlToImage");
                    }

                    ModelClass modelClass=new ModelClass();
                    modelClass.setTitle (title);

                    modelClass.setUrlToImage(urltoimage);
                    data.add(modelClass);
                }

                DemoAdapter demoAdapter=new DemoAdapter(MainActivity.this,data);
                recycler_view.setAdapter(demoAdapter);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return data;

        }

    }
}
