package com.ea.ja.monopoly.server;

import com.ea.ja.monopoly.message.InvalidRequstedCode;
import com.ea.ja.monopoly.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;


class Client implements Runnable {

    private String username;
    private Socket socket;
    private Thread thread;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    Client(String username, Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.username = username;
        this.socket = socket;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
        thread = new Thread(this);
        thread.start();
    }

    private void sendMessage(int code, Object serializedObject) throws InvalidRequstedCode, IOException {
        objectOutputStream.writeObject(new Message(code,serializedObject));
    }

    @Override
    public void run() {
        // asteptare mesaje de la client
        Message resp;
        try {
            while ((resp = (Message) objectInputStream.readObject()) != null) {
                System.out.println(username + ": " + resp.getSerializableObject());
            }
        }catch (SocketException e){
            System.out.println(username + " disconnected.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
