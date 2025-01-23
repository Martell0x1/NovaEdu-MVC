package View;

import Controller.LoginController;
import Model.DatabaseConnect;
import Model.LoginModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author martell
 */
public class Loading extends javax.swing.JFrame {
    private Connection connection = null;
    public Loading() {
        initComponents();
    }
    public void RUN() {
        SwingUtilities.invokeLater(() -> this.setVisible(true));
        startProgress();
    }

    public void startProgress() {
        AtomicBoolean isConnected = new AtomicBoolean(false);
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                long startTime = System.currentTimeMillis();
                boolean isConnected = false;

                // Create a new thread for the database connection attempt
                while (System.currentTimeMillis() - startTime < 10000 && !isConnected) {
                    try {
                        // Attempt the database connection
                        connection = DatabaseConnect.getConnection();

                        if (connection != null) {
                            isConnected = true;
                            publish(100); // Set progress to 100 if connected
                        } else {
                            // Update progress as long as connection is being attempted
                            int progress = (int) ((System.currentTimeMillis() - startTime) / 100.0);
                            publish(progress); // Update progress bar
                        }

                        // Sleep to avoid blocking the UI while trying to connect
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Indicate failure if connection couldn't be established
                if (!isConnected) {
                    publish(-1);
                }

                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (int value : chunks) {
                    if (value == -1) {
                        // Timeout or error case
                        JOptionPane.showMessageDialog(MainPanel, "Database Connection Timeout", "Error", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    } else {
                        // Update the progress bar on the UI
                        jProgressBar1.setValue(value);
                    }
                }
            }


            @Override
            protected void done() {
                // Once done, check if connected
                try {
                    if (connection != null && jProgressBar1.getValue() == 100) {
                        JOptionPane.showMessageDialog(MainPanel, "Initialization Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                        Login login = new Login();
                        LoginModel modle = new LoginModel();
                        new LoginController(login,modle);
                        login.setVisible(true);

                    } else {
                        // Connection failed
                        JOptionPane.showMessageDialog(MainPanel, "Cannot Connect to database ..", "Failed", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(MainPanel, "Error during database connection.", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            }
        };

        worker.execute();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        MainPanel = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar(0,100);
        jProgressBar1.setForeground(Color.orange);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        MainPanel.setLayout(null);

        img.setIcon(new javax.swing.ImageIcon("/home/martell/IdeaProjects/DataBase2/src/main/resources/images/DALL·E 2025-01-19 22.11.19 - A sleek and modern logo design for a university education system called NovaEdu. The logo features a stylized, glowing globe symbolizing global lear.png")); // NOI18N
        MainPanel.add(img);
        img.setBounds(0, 0, 400, 360);
        MainPanel.add(jProgressBar1);
        jProgressBar1.setBounds(0, 360, 400, 10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
        );

        pack();
    }


    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel img;
    private javax.swing.JProgressBar jProgressBar1;
}
