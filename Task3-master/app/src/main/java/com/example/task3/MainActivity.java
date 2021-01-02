package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<AdapterItems>    listnewsData = new ArrayList<AdapterItems>();
        MyCustomAdapter myadapter;
        //
        String jsonn = null;
        try {
            InputStream is = getAssets().open("task3.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonn = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e("questions",ex.getMessage());

        }
        try {
            JSONObject json= new JSONObject(jsonn);

            JSONArray jobs=json.getJSONArray("questions");

            for ( int i=0; i<jobs.length();i++){
                JSONObject Jobs= jobs.getJSONObject(i)  ;
                String question=Jobs.getString("question");
                JSONArray options=Jobs.getJSONArray("options");

                String question_number=Jobs.getString("question_number");


                //add data and view it
                listnewsData.add(new AdapterItems(question_number,question,options.get(0).toString(),options.get(1).toString(),options.get(2).toString()));
                myadapter=new MyCustomAdapter(listnewsData);
                ListView  lsNews=(ListView)findViewById(R.id.listview);
                lsNews.setAdapter(myadapter);//intisal with data
            }
        }
        catch (Exception ex){}
        //adapter class


    }

    //display news list
    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<AdapterItems>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_question, null);

            final   AdapterItems s = listnewsDataAdpater.get(position);

            TextView question= myView.findViewById(R.id.question);
            TextView questionnumber= myView.findViewById(R.id.Questionnumber);
            questionnumber.setText("Question Number : "+s.id);
            question.setText(s.Question);
            RadioButton option1,option2,option3;
            option1=myView.findViewById(R.id.option1);
            option2=myView.findViewById(R.id.option2);
            option3=myView.findViewById(R.id.option3);
            option1.setText(s.option1);
            option2.setText(s.option2);
            option3.setText(s.option3);

            return myView;
        }

    }




}