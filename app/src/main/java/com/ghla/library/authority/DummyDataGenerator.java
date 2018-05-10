package com.ghla.library.authority;

import java.util.ArrayList;

public class DummyDataGenerator {
    static int COUNT_REPORT = 2;
    static int COUNT_QUESTION = 10;

    ArrayList<Report> getReports(){
        ArrayList<Report> reports = new ArrayList<>();
        for (int i = 0; i < COUNT_REPORT; i++ ) {
            reports.add(generateMemberShipReport());
            reports.add(generateMonitoringReport());
            reports.add(generateFinanceMonthlyReport()); reports.add(generateFinanceWeeklyReport());
            reports.add(generateResourcesDailyReport()); reports.add(generateResourcesMonthlyReport());
            reports.add(generateExtensionReport());
        }
        return reports;
    }

    Report generateMemberShipReport(){
        Metric metric =  new Metric(Metric.MetricType.Membership, generateRandomQuestions());
        Status status = new Status(Status.Value.NotStarted);
        Report report = new Report(metric, status);
        return report;
    }

    Report generateMonitoringReport(){
        Metric metric =  new Metric(Metric.MetricType.Monitoring, generateRandomQuestions());
        Status status = new Status(Status.Value.NotStarted);
        Report report = new Report(metric, status);
        return report;
    }

    Report generateExtensionReport(){
        Metric metric =  new Metric(Metric.MetricType.Extension, generateRandomQuestions());
        Status status = new Status(Status.Value.NotStarted);
        Report report = new Report(metric, status);
        return report;
    }

    Report generateResourcesMonthlyReport(){
        Metric metric =  new Metric(Metric.MetricType.ResourcesMonthly, generateRandomQuestions());
        Status status = new Status(Status.Value.NotStarted);
        Report report = new Report(metric, status);
        return report;
    }

    Report generateResourcesDailyReport(){
        Metric metric =  new Metric(Metric.MetricType.ResourcesDaily, generateRandomQuestions());
        Status status = new Status(Status.Value.NotStarted);
        Report report = new Report(metric, status);
        return report;
    }

    Report generateFinanceMonthlyReport(){
        Metric metric =  new Metric(Metric.MetricType.FinanceMonthly, generateRandomQuestions());
        Status status = new Status(Status.Value.NotStarted);
        Report report = new Report(metric, status);
        return report;
    }

    Report generateFinanceWeeklyReport(){
        Metric metric =  new Metric(Metric.MetricType.FinanceWeekly, generateRandomQuestions());
        Status status = new Status(Status.Value.NotStarted);
        Report report = new Report(metric, status);
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
}
