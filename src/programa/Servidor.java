/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Servidor extends javax.swing.JFrame {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private static HashMap<String, PrintWriter> clientMap = new HashMap<>();

    public Servidor() {
        initComponents();
    }

    private void initComponents() {
        jbConectar = new javax.swing.JButton();
        jbDesconectar = new javax.swing.JButton();
        jbSubirArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbConectar.setText("Conectar ");
        jbConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbConectarActionPerformed(evt);
            }
        });

        jbDesconectar.setText("Desconectar");
        jbDesconectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbDesconectarActionPerformed(evt);
            }
        });

        jbSubirArchivo.setText("Subir Archivo");
        jbSubirArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbSubirArchivoActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel1.setText("Esto es el servidor");

        // Layout code omitted for brevity

        pack();
    }

    private void jbConectarActionPerformed(ActionEvent evt) {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jbDesconectarActionPerformed(ActionEvent evt) {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
                txtArea.append("Servidor desconectado.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void jbSubirArchivoActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Aquí puedes manejar el contenido del archivo
                    // Por ejemplo, enviarlo a todos los clientes conectados
                    broadcastMessage("Archivo: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcastMessage(String message) {
        synchronized (clientMap) {
            for (PrintWriter writer : clientMap.values()) {
                writer.println(message);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Servidor().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jbConectar;
    private javax.swing.JButton jbDesconectar;
    private javax.swing.JButton jbSubirArchivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Ingresa el mensaje publico: ");
                name = in.readLine();
                synchronized (clientMap) {
                    clientMap.put(name, out);
                    updateUserList();
                }

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("@")) {
                        String[] splitMessage = message.split(" ", 2);
                        String targetName = splitMessage[0].substring(1);
                        String privateMessage = splitMessage[1];
                        sendMessage(targetName, privateMessage);
                    } else {
                        broadcastMessage(name + ": " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (name != null) {
                    synchronized (clientMap) {
                        clientMap.remove(name);
                        updateUserList();
                    }
                    broadcastMessage(name + " se ha desconectado.");
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcastMessage(String message) {
            synchronized (clientMap) {
                for (PrintWriter writer : clientMap.values()) {
                    writer.println(message);
                }
            }
        }

        private void sendMessage(String targetName, String message) {
            synchronized (clientMap) {
                PrintWriter targetOut = clientMap.get(targetName);
                if (targetOut != null) {
                    targetOut.println(name + " (private): " + message);
                }
            }
        }

        private void updateUserList() {
            StringBuilder userList = new StringBuilder("@userlist ");
            synchronized (clientMap) {
                for (String user : clientMap.keySet()) {
                    userList.append(user).append(";");
                }
            }
            broadcastMessage(userList.toString());
        }
    }
}
