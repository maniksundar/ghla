package com.ghla.library.authority;

import android.content.Context;
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
        switch (this.mMetric.type) {
            case Meetings:
                return ContextCompat.getColor(context, R.color.colorCardPaleGreen);
            case Extension:
                return ContextCompat.getColor(context, R.color.colorCardOrange);
            case Membership:
                return ContextCompat.getColor(context, R.color.colorCardYellow);
            case Monitoring:
                return ContextCompat.getColor(context, R.color.colorCardCyan);
            case FinanceWeekly:
                return ContextCompat.getColor(context, R.color.colorCardPink);
            case FinanceMonthly:
                return ContextCompat.getColor(context, R.color.colorCardPink);
            case ResourcesDaily:
                return ContextCompat.getColor(context, R.color.colorCardGreen);
            case ResourcesMonthly:
                return ContextCompat.getColor(context, R.color.colorCardGreen);
                default:
                    return ContextCompat.getColor(context, R.color.colorCardYellow);
        }
    }

    public int getImage(Context context){
        switch (this.mMetric.type) {
            case Meetings:
                return R.drawable.ic_meetings;
            case Extension:
                return R.drawable.ic_extension;
            case Membership:
                return R.drawable.membership;
            case Monitoring:
                return R.drawable.ic_monitoring;
            case FinanceWeekly:
                return R.drawable.ic_finance_monthly;
            case FinanceMonthly:
                return R.drawable.ic_finance_weekly;
            case ResourcesDaily:
                return R.drawable.resources_daily;
            case ResourcesMonthly:
                return R.drawable.resources_monthly;
            default:
                return R.drawable.membership;
        }
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
    int color;

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

