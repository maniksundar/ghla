package com.ghla.library.authority;

public class DataModel {

    // TODO: This class can be used to communicate with the Network and the Disk classes and can provide the necessary abstraction for the app.
    //This class communicates with the Network and the Disk classes.
    // It's implemented as singleton for convenience. This can be changed if necessary
    
    private static Network mNetwork;
    private static Disk mDisk;
    private static final String MEMBERSHIP = "membership";

    private static DataModel DataModel = null;

    private DataModel() {
        mNetwork = Network.getInstance();
        mDisk = Disk.getInstance();
    }

    // static method to create instance of Singleton class
    public static DataModel getInstance() {
        if (DataModel == null)
            DataModel = new DataModel();
        return DataModel;
    }

    void submitMembershipReport(Report report){
        //Convert to JSON
        String reportJSON = new JSONHelper().convertModeltoJson(report);
        //Save to the disk or send over the network.
        mDisk.saveReportAs(reportJSON, MEMBERSHIP);
//        mNetwork.postJSON(reportJSON);
        //Change the state to submitted once sent
        report.setStatus(Report.Status.Submitted);
    }

    Report getMembershipReport(){
        return (Report) new JSONHelper().deserializeJSON(mDisk.getReport(MEMBERSHIP), Membership.class);
    }

    void sendOverDataModel(Report report){
        //okhttp can be used to send requests to the server.

    }
}
