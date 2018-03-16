

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Initially created by Roman Gaev
 * 26.02.2018
 * Simple server
 * <p>
 * May the force be with you.
 */
public class ServerModel extends Thread {
    private Set<NewServerThread> threadPool = new HashSet<>();

    public Set<NewServerThread> getThreadPool() {
        return threadPool;
    }

@Override
    public void run() {
        //creating a server socket
        ServerSocket serverSocket=null;
        try {
            serverSocket = new ServerSocket(22001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //trying to establish postgres driver for database connection
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //establishing database connection
        String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/mumbai";
        Properties props = new Properties();
        props.setProperty("user", "mumbai");
        props.setProperty("password", "s7e5n1p3tj");
        props.setProperty("ssl", "false");
        try {
            Connection dbConnection = DriverManager.getConnection(url, props);
            Statement statement = dbConnection.createStatement();
            Socket clientSocket;

            //waiting for new clients to come and creating new threads for each of them
            while (true) {
                clientSocket = serverSocket.accept();
                NewServerThread newClient = new NewServerThread(this, clientSocket, dbConnection);
                threadPool.add(newClient);
                newClient.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                serverSocket.close();
            }
            catch (IOException io) {
                System.err.println("Couldn't close server socket" +
                        io.getMessage());
            }
        }


    }

}
