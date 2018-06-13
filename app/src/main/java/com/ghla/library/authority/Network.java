package com.ghla.library.authority;

public class Network {
        private static Network network = null;

        private Network() { }
        // static method to create instance of Singleton class
        public static Network getInstance() {
            if (network == null)
                network = new Network();

            return network;
        }

        void sendOverNetwork(Report report){
            //okhttp can be used to send requests to the server.

        }
}
