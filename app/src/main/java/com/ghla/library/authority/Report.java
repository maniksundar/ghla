package com.ghla.library.authority;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Report implements ReportType, Parcelable{
    enum Status{
        NotStarted, Submitted, Approved, InReview
    }
    Metric mMetric;
    Status mStatus;
    Metric.MetricType mType;
    private List<Title> reportContent;

    //Default constructor for the subclasses
    Report(){}

    // Public methods
    public String getTitle(){
        return mMetric.getMetricTypeString();
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

    public int getType(){
        return TYPE_REPORT;
    }

    Report(Metric mMetric, Status status) {
        this.mMetric = mMetric;
        this.mStatus = status;
        this.mType = mMetric.type;
    }

    protected void setReportContent(List<Title> reportContent) {
        this.reportContent = reportContent;
    }

    public List<Title> getReportContent() {
        return reportContent;
    }

    public void setStatus(Status mStatus) {
        this.mStatus = mStatus;
    }

    public Status getmStatus() {
        return mStatus;
    }
// Private methods

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mType == null ? -1 : this.mType.ordinal());
        dest.writeList(this.reportContent);
    }

    protected Report(Parcel in) {
        int tmpMType = in.readInt();
        this.mType = tmpMType == -1 ? null : Metric.MetricType.values()[tmpMType];
        this.reportContent = new ArrayList<Title>();
        in.readList(this.reportContent, Title.class.getClassLoader());
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel source) {
            return new Report(source);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
}

class ReportHeader extends Report implements ReportType {

    Header mValue;

    enum Header {
        Daily, Weekly, Monthly
    }

    ReportHeader(Header header){
        this.mValue = header;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    @Override
    public String toString() {
        switch (mValue) {
            case Daily:
                return "due today";
            case Weekly:
                return "due this week";
            case Monthly:
                return "due this month";
            default:
                return "due soon";
        }
    }
}

class Metric{
    MetricType type;
    ArrayList<Question> questions;
    public static final String RESOURCESMONTHLY = "Monthly Resources";
    public static final String RESOURCESDAILY = "Daily Resources";
    public static final String FINANCEMONTHLY = "Monthly Finance";
    public static final String FINANCEWEEKLY = "Weekly Finance";
    public static final String MONITORING = "Monitoring";
    public static final String MEMBERSHIP = "Membership";
    public static final String EXTENSION = "Extension";
    public static final String MEETINGS = "Meetings";
    public static final String HEADER = "Header";

    int color;

    enum MetricType{
        Membership, ResourcesDaily, ResourcesMonthly, Extension, FinanceMonthly, FinanceWeekly, Monitoring, Meetings, Header
    }

    public String getMetricTypeString(){

        switch (type){
            case ResourcesMonthly:
                return RESOURCESMONTHLY;
            case ResourcesDaily:
                return RESOURCESDAILY;
            case FinanceMonthly:
                return FINANCEMONTHLY;
            case FinanceWeekly:
                return FINANCEWEEKLY;
            case Monitoring:
                return MONITORING;
            case Membership:
                return MEMBERSHIP;
            case Extension:
                return EXTENSION;
            case Meetings:
                return MEETINGS;
            default:
                return HEADER;
        }
    }

    Metric (MetricType type, ArrayList<Question> questions) {
        this.type = type;
        this.questions = questions;
    }
}


class Person{
    String name;
    String phone = "";
    String email = "";

    Person(){}

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
    String name;

    //Public methods
    String getName (){
        return name;
    }

    Library (Person manager, String name) {
        this.manager = manager;
        this.name = name;
    }
}

class Region{
    ArrayList<Library> libraries;
    Person regionalManager;
}

// Every title has an array of subtitles
class Title{
    String text;
    int type;
    int answer;
    boolean answerable = false;
    List<Subtitle> subtitles;
    Title (String text){
        this.text = text;
    }
    Title (String text, boolean answerable){
        this.text = text;
        this.answerable = answerable;
    }
    void add(Subtitle subtitle) {
        if(this.subtitles == null){
            this.subtitles = new ArrayList<>();
        }
        this.subtitles.add(subtitle);
    }
}
class Subtitle{
    List<Question> questions;
    String text;
    int answer;
    boolean answerable = false;
    Subtitle(String text) {
        this.text = text;
    }
    Subtitle (String text, boolean answerable){
        this.text = text;
        this.answerable = answerable;
    }
    void add (Question question){
        if (this.questions == null){
            this.questions = new ArrayList<>();
        }
        questions.add(question);
    }
}
class Question {
    String text;
    int answer;
    boolean answerable = false;
    Question(String questionText) {
        this.text = questionText;
    }
    Question (String text, boolean answerable){
        this.text = text;
        this.answerable = answerable;
    }
}