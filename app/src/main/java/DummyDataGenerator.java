import java.util.ArrayList;

public class DummyDataGenerator {
    static int COUNT_REPORT = 10;
    static int COUNT_QUESTION = 10;

    ArrayList<Report> getReports(){
        ArrayList<Report> reports = new ArrayList<>();
        for (int i = 0; i < COUNT_REPORT; i++ ) {
            reports.add(generateMemberShipReport());
        }
        return reports;
    }

    Report generateMemberShipReport(){
        Metric metric =  new Metric(Metric.MetricType.Membership, generateRandomQuestions());
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
