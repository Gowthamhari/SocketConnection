package com.example.android.socketconnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends Activity {


    EditText serverIP;
    TextView status;
    //String IP;
    ServerSocket serverSocket;
    final int port = 2345;

    String serverAddress = "192.168.0.251";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView) findViewById(R.id.status);
        serverIP = (EditText) findViewById(R.id.serverIP);
        Button connect = (Button) findViewById(R.id.Connection);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setText("first run the client side to have a connection");


            }
        });
        try {
            ServerThread();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public  void  ServerThread() throws IOException {

        serverSocket = new ServerSocket(port);

        Socket client = serverSocket.accept();

        status.setText("Connection Established...");

    }
}








