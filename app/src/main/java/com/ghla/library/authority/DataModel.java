package com.ghla.library.authority;

public class DataModel {

    // TODO: This class can be used to communicate with the Network and the Disk classes and can provide the necessary abstraction for the app.
    //This class communicates with the Network and the Disk classes.
    // It's implemented as singleton for convenience. This can be changed if necessary
    
    private static Network mNetwork;
    private static Disk mDisk;

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

    static void submitMembershipReport(Report report){
        //Save to the disk or send over the DataModel.
        mDisk.saveToDisk(report);
        mNetwork.sendOverNetwork(report);
        //Either the model can be sent or the JSON can be sent over the network
        String reportJSON = new JSONHelper().convertModeltoJson(report);
        mNetwork.sendOverNetwork(reportJSON);
    }

    void sendOverDataModel(Report report){
        //okhttp can be used to send requests to the server.

    }
}
