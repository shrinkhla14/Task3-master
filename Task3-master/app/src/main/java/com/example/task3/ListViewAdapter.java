package com.example.task3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// adapter class
class AdapterItems
 {
    public  String id,Question;
    public  String option1,option2,option3;
//for news details

     public AdapterItems(String id, String question, String option1, String option2, String option3) {
         this.id=id;
         this.Question = question;
         this.option1 = option1;
         this.option2 = option2;
         this.option3 = option3;
     }
 }

