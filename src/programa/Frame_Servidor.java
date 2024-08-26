/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author David Rojas
 */
public class Frame_Servidor extends javax.swing.JFrame {
    private static final int PORT = 12345;
   ServerSocket serverSocket;
    private static HashMap<String, PrintWriter> clientMap = new HashMap<>();
    /**
     * Crea el fraem del servidor 
     */
    public Frame_Servidor() {
        initComponents();
      
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbConectar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jbDesconectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbConectar.setText("Conectar ");
        jbConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConectarActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Esto es el servidor");

        jbDesconectar.setText("Desconectar");
        jbDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDesconectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jbConectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbDesconectar)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbConectar)
                    .addComponent(jbDesconectar))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConectarActionPerformed
      txtArea.append("Iniciando servidor en el puerto " + PORT + "...\n");
    try {
        serverSocket = new ServerSocket(PORT);
        txtArea.append("Servidor iniciado exitosamente.\n");
        new Thread(() -> {
            while (!serverSocket.isClosed()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    txtArea.append("Cliente conectado desde: " + clientSocket.getInetAddress() + "\n");
                    new ClientHandler(clientSocket, this).start();
                } catch (IOException e) {
                    txtArea.append("Error al aceptar conexión de cliente: " + e.getMessage() + "\n");
                    e.printStackTrace();
                }
            }
        }).start();
    } catch (IOException e) {
        txtArea.append("Error al iniciar el servidor: " + e.getMessage() + "\n");
        e.printStackTrace();
    }
    }//GEN-LAST:event_jbConectarActionPerformed

    private void jbDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDesconectarActionPerformed
      
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close(); // Cierra el ServerSocket
                txtArea.append("Servidor desconectado.\n");
                System.exit(0);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbDesconectarActionPerformed

     private void appendMessageToTextArea(String message) {
        txtArea.append(message + "\n");
    }

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Servidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbConectar;
    private javax.swing.JButton jbDesconectar;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;
      
        private Frame_Servidor servidor;
        
       public ClientHandler(Socket socket, Frame_Servidor servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                // Cliente conectado
              
                 servidor.appendMessageToTextArea("Nuevo cliente conectado desde: " + socket.getInetAddress());
              
                out.println("");
                 name = in.readLine();
            
                //out.println("Ingresa el mensaje: ");
                //name = in.readLine();
                
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
                        servidor.appendMessageToTextArea(name + " ( privado a " + targetName + "): " + privateMessage);
                        
                    } else {
                        broadcastMessage(name + ": " + message);
                        servidor.appendMessageToTextArea(name + ": " + message);
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
                    broadcastMessage(name + " Se ha desconectado.");
                     servidor.appendMessageToTextArea(name + " se ha desconectado.");
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
