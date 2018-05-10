package com.ghla.library.authority;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class Report {
    Metric mMetric;
    Status mStatus;

    // Public methods
    public String getTitle(){
        return mMetric.type.toString();
    }
    public int getColor(Context context) {
        return ContextCompat.getColor(context, R.color.colorCardYellow);
//        return Resources.getSystem().getColor(R.color.colorCardYellow);
    }

    Report(Metric mMetric, Status status) {
        this.mMetric = mMetric;
        this.mStatus = status;
    }

    // Private methods

}

class Metric{
    MetricType type;
    ArrayList<Question> questions;
    enum MetricType{
        Membership, ResourcesDaily, ResourcesMonthly, Extension, FinanceMonthly, FinanceWeekly, Monitoring, Meetings
    }

    Metric (MetricType type, ArrayList<Question> questions) {
        this.type = type;
        this.questions = questions;
    }
}

class Status{
    Value value;

    Status(Value value) {
        this.value = value;
    }
    enum Value{
        NotStarted, Submitted, Approved
    }
}

class Question {
    String title;
    String subTitle;
    String questionText;

    Question(String questionText) {
        this.questionText = questionText;
    }

    Question(String title, String subTitle, String questionText) {
        this.title = title;
        this.subTitle = subTitle;
        this.questionText = questionText;
    }
    Question(String title, String questionText) {
        this.title = title;
        this.questionText = questionText;
    }

}

class Person{
    String name;
    String phone = "";
    String email = "";

    Person (String name) {
        this.name = name;
    }

    Person(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

class Library{
    Person manager;
    Library (Person manager) {
        this.manager = manager;
    }
}

class Region{
    ArrayList<Library> libraries;
    Person regionalManager;
}

