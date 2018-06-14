package com.ghla.library.authority;

import java.util.ArrayList;

public class DummyDataGenerator {
    static int COUNT_REPORT = 1;
    static int COUNT_QUESTION = 10;
    private static final String MEMBERSHIP_JSON = "membership.json";

    ArrayList<Report> getReports(){
        ArrayList<Report> reports = new ArrayList<>();
        for (int i = 0; i < COUNT_REPORT; i++ ) {

            //Daily
            reports.add(new ReportHeader(ReportHeader.Header.Daily));
            reports.add(generateMemberShipReport());
            reports.add(generateResourcesDailyReport());

            //Weekly
            reports.add(new ReportHeader(ReportHeader.Header.Weekly));
            reports.add(generateMonitoringReport());
            reports.add(generateFinanceWeeklyReport());
            reports.add(generateExtensionReport());

            //Monthly
            reports.add(new ReportHeader(ReportHeader.Header.Monthly));
            reports.add(generateResourcesMonthlyReport());
            reports.add(generateFinanceMonthlyReport());
        }
        return reports;
    }

    Report generateMemberShipReport(){
        Metric metric =  new Metric(Metric.MetricType.Membership, generateRandomQuestions());
        Report report = new Report(metric);
        // The following line shouldn't be placed inside membership constructor. It'll cause a stackoverflow.
        Membership membership = (Membership) new JSONHelper().deserializeJSONFromFile(MEMBERSHIP_JSON,Membership.class);
        System.out.println(membership);
        report.setReportContent(membership.reportContent);
        return report;
    }

    Report generateMonitoringReport(){
        Metric metric =  new Metric(Metric.MetricType.Monitoring, generateRandomQuestions());
        Report report = new Report(metric);
        return report;
    }

    Report generateExtensionReport(){
        Metric metric =  new Metric(Metric.MetricType.Extension, generateRandomQuestions());
        Report report = new Report(metric);
        return report;
    }

    Report generateResourcesMonthlyReport(){
        Metric metric =  new Metric(Metric.MetricType.ResourcesMonthly, generateRandomQuestions());
        Report report = new Report(metric);
        return report;
    }

    Report generateResourcesDailyReport(){
        Metric metric =  new Metric(Metric.MetricType.ResourcesDaily, generateRandomQuestions());
        Report report = new Report(metric);
        return report;
    }

    Report generateFinanceMonthlyReport(){
        Metric metric =  new Metric(Metric.MetricType.FinanceMonthly, generateRandomQuestions());
        Report report = new Report(metric);
        return report;
    }

    Report generateFinanceWeeklyReport(){
        Metric metric =  new Metric(Metric.MetricType.FinanceWeekly, generateRandomQuestions());
        Report report = new Report(metric);
        return report;
    }

    ArrayList<Question> generateRandomQuestions(){
        ArrayList<Question> questions = new ArrayList<>();
        for (int i=0 ; i < COUNT_QUESTION; i++) {
            Question question = new Question("Question text " + i);
            questions.add(question);
        }
        return questions;
    }

    ArrayList<Library> getLibraries(){
        ArrayList<Library> libraries = new ArrayList<>();
        for (int i=0; i < 12; i++) {
            libraries.add(new Library(User.getCurrentUser(), "Keta Library"));
        }
        return libraries;
    }
}
