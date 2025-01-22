package View;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import Controller.DBConnect;

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
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                long startTime = System.currentTimeMillis();

                // Create a new thread for the database connection attempt
                Thread connectionThread = new Thread(() -> {
                    boolean isConnected = false;
                    while (System.currentTimeMillis() - startTime < 10000) { // Try for 10 seconds
                        try {
                            connection = DBConnect.getInstance();
                            if (connection != null) {
                                isConnected = true;
                                publish(100); // Set progress to 100 if connected
                                break;
                            }
                            // Sleep to avoid blocking the UI while trying to connect
                            Thread.sleep(500);
                            int progress = (int) ((System.currentTimeMillis() - startTime) / 100.0);
                            publish(progress); // Update progress bar based on elapsed time
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (!isConnected) {
                        publish(-1); // Indicate failure in progress bar
                    }
                });

                // Start the connection thread
                connectionThread.start();

                try {
                    connectionThread.join(); // Wait for the connection thread to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (int value : chunks) {
                    if (value == -1) {
                        JOptionPane.showMessageDialog(MainPanel, "Database Connection Timeout", "Error", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    } else {
                        jProgressBar1.setValue(value);
                    }
                }
            }

            @Override
            protected void done() {
                if (jProgressBar1.getValue() == 100) {
                    JOptionPane.showMessageDialog(MainPanel, "Initialization Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    Login.RUN();
                } else if (jProgressBar1.getValue() == -1) {
                    JOptionPane.showMessageDialog(MainPanel, "An Error Occurred.. Terminating Program", "Failed", JOptionPane.ERROR_MESSAGE);
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
