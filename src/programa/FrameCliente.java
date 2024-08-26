
package programa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author David Rojas
 */
public class FrameCliente extends javax.swing.JFrame {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    // ventana  para colocar el nombre
    private JFrame frame = new JFrame("Como se llama usted");
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    String archivo;
    private int i = 1;
    /**
     * Crea el fram de cliente
     */
    public FrameCliente() {
         initComponents();
        TxtMensaje.setText("");
        AreaContactos.setModel(listModel);
        AreaConversacion.setText("Si no selecciona el destinatario \n el mensaje se compartira con todos \n");
}   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaConversacion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jbEnviar = new javax.swing.JButton();
        TxtMensaje = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AreaContactos = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btSeleccionar = new javax.swing.JButton();
        txtSeleccionArchivo = new javax.swing.JTextField();
        jbenviarrrr = new javax.swing.JButton();
        jbActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuarios conectados: ");

        AreaConversacion.setColumns(20);
        AreaConversacion.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        AreaConversacion.setRows(5);
        jScrollPane2.setViewportView(AreaConversacion);

        jLabel2.setText("Conversacion: ");

        jbEnviar.setText("Enviar");
        jbEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarActionPerformed(evt);
            }
        });

        TxtMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtMensajeActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(204, 0, 51));
        jLabel3.setText("Usted es: ");

        txtUsuario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(255, 0, 51));
        txtUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        AreaContactos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(AreaContactos);

        jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Desconectar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btSeleccionar.setText("Seleccionar arachivo");
        btSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSeleccionarActionPerformed(evt);
            }
        });

        jbenviarrrr.setText("Enviar archivo");
        jbenviarrrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbenviarrrrActionPerformed(evt);
            }
        });

        jbActualizar.setText("actualizar");
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(373, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSeleccionArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TxtMensaje)
                                            .addComponent(btSeleccionar))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jbenviarrrr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jbEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jbActualizar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 9, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbEnviar)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSeleccionar)
                            .addComponent(jbenviarrrr)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSeleccionArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbActualizar)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarActionPerformed
   
    String message = TxtMensaje.getText();
    String selectedUser = AreaContactos.getSelectedValue(); 

    if (selectedUser != null && !selectedUser.isEmpty()) {
        out.println("@" + selectedUser + " " + message); 
    } else {
        out.println(message);
    }
    //AreaConversacion.append("mensaje: "  + message + "\n");
        Limpiar();
    }//GEN-LAST:event_jbEnviarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//Se conecta con el servidor
        try {
        connectToServer();
      jButton1.setEnabled(false);
    } catch (IOException ex) {
        Logger.getLogger(FrameCliente.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // se desconecta del servidor
        if (socket != null && !socket.isClosed()) {
            try {
                String nombre= txtUsuario.getText();
                listModel.removeElement(nombre);
                socket.close(); // Cierra el ServerSocket
                AreaConversacion.append("Servidor desconectado.\n");
                jButton1.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TxtMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtMensajeActionPerformed

    }//GEN-LAST:event_TxtMensajeActionPerformed

    private void btSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSeleccionarActionPerformed
// se toma de https://es.stackoverflow.com/questions/254478/abrir-file-chooser-o-gestor-de-archivos-en-una-ruta-especifica
//Ubicacion del archivo
JFileChooser jf = new JFileChooser("C:\\Users\\david\\Desktop");
jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
jf.setMultiSelectionEnabled(false);
//cargue de archivos
FileNameExtensionFilter filter=new FileNameExtensionFilter("Todos los Archivos", "txt", "html", "exe", "bad");
jf.setFileFilter(filter);
//se selecciona un archivo
int returnValue = jf.showOpenDialog(null);
if(returnValue==JFileChooser.APPROVE_OPTION){
    File selecinarArchivo = jf.getSelectedFile();
    if(selecinarArchivo != null){
        String rutaArchivo = selecinarArchivo.getAbsolutePath();
        txtSeleccionArchivo.setText(rutaArchivo);
}
}
    }//GEN-LAST:event_btSeleccionarActionPerformed

    private void jbenviarrrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbenviarrrrActionPerformed
     String rutaArchivo = txtSeleccionArchivo.getText();
    String selectedUser = AreaContactos.getSelectedValue();
    String destino="C:\\Users\\david\\Desktop\\destino";
    if (rutaArchivo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un archivo antes de enviarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (selectedUser == null || selectedUser.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario antes de enviar un archivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    // Obtener el nombre del archivo original
    Path sourcePath = Paths.get(rutaArchivo);
    String nombreArchivoOriginal = sourcePath.getFileName().toString();
    // Crear un nuevo nombre de archivo con un identificador único
    // se puede utilizar uuid o hacer un numero random 
    String nuevoNombreArchivo = null;
   // int numero =(int)(Math.random()*100+1);
    String nombre = txtUsuario.getText();	
    nuevoNombreArchivo = nombre +"_" + i + "_" + nombreArchivoOriginal;
    i++;
    Path destinoPath = Paths.get(destino, nuevoNombreArchivo);
    // Copia el archivo seleccionado a la carpeta de destino
    try { 
        Files.copy(sourcePath, destinoPath);
        AreaConversacion.append("Archivo copiado exitosamente a: " + destinoPath.toString() + "\n");
        out.println("@" + selectedUser + " FILE " + rutaArchivo);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al copiar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Enviar la ruta del archivo al servidor
    
//    AreaConversacion.append("Archivo enviado a " + selectedUser + ": " + rutaArchivo + "\n");
    // Limpiar el campo de selección de archivo
    txtSeleccionArchivo.setText("");

    }//GEN-LAST:event_jbenviarrrrActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
   
    }//GEN-LAST:event_jbActualizarActionPerformed

  
   public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        new FrameCliente().setVisible(true);
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> AreaContactos;
    private javax.swing.JTextArea AreaConversacion;
    private javax.swing.JTextField TxtMensaje;
    private javax.swing.JButton btSeleccionar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbEnviar;
    private javax.swing.JButton jbenviarrrr;
    private javax.swing.JTextField txtSeleccionArchivo;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
 
     public void updateUserList(String[] users) {
        listModel.clear();
        for (String user : users) {
            listModel.addElement(user);
        }
    }
     
    
     
     
    private void connectToServer() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        String name = JOptionPane.showInputDialog(frame, "Ingresa el nombre", "Nombre requerido", JOptionPane.PLAIN_MESSAGE);
        out.println(name);
        txtUsuario.setText(name);
        new Thread(new FrameCliente.IncomingReader()).start();
        AreaContactos.setModel(listModel);
    }
    
    private class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("@userlist ")) {
                        // Actualizar lista de usuarios conectados
                        String[] userListArray = message.substring(10).split(";");
                        listModel.clear();
                        for (String user : userListArray) {
                            listModel.addElement(user);
                        }
                    } else {
                        // Mostrar mensaje en el área de texto
                        AreaConversacion.append(message + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   
 private void Limpiar(){
     TxtMensaje.setText("");
     AreaContactos.clearSelection();
 }
         


}
