package com.example.android.socketconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Gowtham on 12/09/16.
 */
public class ClientActivity extends AppCompatActivity {

    TextView status;
    EditText serverIP;

    String IpAddress = "";
    boolean connected = false;
    public Socket socket;

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
                if (!connected) {
                    if (serverIP.equals("")) {
                        IpAddress = serverIP.getText().toString();
                    } if (!IpAddress.equals("")) {
                        //Thread cThread = new Thread(new clientThread());
                        clientThread();
                    }

                }

            }
        });

    }

    public void clientThread () {
        new Thread() {
            @Override
            public void run() {
                InetAddress serverAddr = null;
                try {
                    serverAddr = InetAddress.getByName(IpAddress);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                try {
                    socket = new Socket(serverAddr, 2345);
                    //Socket client = new Socket("localhost", 1234);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                connected = true;
                while (connected) {
                    try {
                        status.setText("Connection established");

                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                        out.println("Hey Server!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        };
    }

}
