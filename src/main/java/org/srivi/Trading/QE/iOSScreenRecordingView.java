package org.srivi.Trading.QE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class iOSScreenRecordingView extends JFrame {
    private JFrame mainAppFrame;
    private static Process screenRecordProcess;
    private HelpOptionsPanel helpOptionsPanel;
    private JTextField fileNameField;
    private JTextField fileLocationField;
    public JLabel savedLocationLabel;
    String desktopPath = System.getProperty("user.home") + "/Desktop";

    public iOSScreenRecordingView(JFrame mainAppFrame) {
        this.mainAppFrame = mainAppFrame;
        setupUI();
    }

    private void setupUI(){
        JFrame recordingFrame = new JFrame("Screen Recording");
        recordingFrame.setSize(500, 400);
        recordingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        recordingFrame.setLayout(null);
        recordingFrame.setResizable(false);
        // Set the custom font
        Font interFont = FontUtil.getInterFont(12f);

        JLabel backLabel = BackButtonUtil.createBackLabel(recordingFrame);
        recordingFrame.add(backLabel);

        // File Name Label and Field
        JLabel fileNameLabel = new JLabel("File Name:");
        fileNameLabel.setBounds(50, 70, 100, 30);
        fileNameLabel.setFont(interFont);
        recordingFrame.add(fileNameLabel);

        fileNameField = new JTextField("ios_screen_record");
        fileNameField.setFont(interFont);
        fileNameField.setBounds(150, 70, 150, 30);
        recordingFrame.add(fileNameField);

        // File Location Label and Field
        JLabel fileLocationLabel = new JLabel("File Location:");
        fileLocationLabel.setFont(interFont);
        fileLocationLabel.setBounds(50, 120, 100, 30);
        recordingFrame.add(fileLocationLabel);

        fileLocationField = new JTextField(desktopPath);
        fileLocationField.setBounds(150, 120, 150, 30);
        fileLocationField.setFont(interFont);
        recordingFrame.add(fileLocationField);

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(360, 120, 80, 30);
        recordingFrame.add(browseButton);
        browseButton.setFont(interFont);
        browseButton.addActionListener(e -> FileChooserUtil.selectFileLocation(fileLocationField));

        // Start and Stop Recording Buttons
        JButton startRecordButton = new JButton("Start Recording");
        startRecordButton.setBounds(50, 170, 150, 30);
        startRecordButton.setFont(interFont);
        recordingFrame.add(startRecordButton);

        JButton stopRecordButton = new JButton("Stop Recording");
        stopRecordButton.setBounds(220, 170, 150, 30);
        stopRecordButton.setFont(interFont);
        stopRecordButton.setEnabled(false);
        recordingFrame.add(stopRecordButton);

        savedLocationLabel = new JLabel("");
        savedLocationLabel.setBounds(20, 220, 460, 30);
        savedLocationLabel.setFont(interFont);
        recordingFrame.add(savedLocationLabel);

        startRecordButton.addActionListener(e -> {
            XcrunHelper.startiOSScreenRecording(fileNameField.getText(), fileLocationField.getText());
            startRecordButton.setEnabled(false);
            stopRecordButton.setEnabled(true);
        });

        stopRecordButton.addActionListener(e -> {
            XcrunHelper.stopiOSScreenRecording(fileNameField.getText(),fileLocationField.getText(),savedLocationLabel);
            startRecordButton.setEnabled(true);
            stopRecordButton.setEnabled(false);
        });

        recordingFrame.setVisible(true);
        helpOptionsPanel = new HelpOptionsPanel(recordingFrame.getWidth(), recordingFrame.getHeight());
        recordingFrame.add(helpOptionsPanel);

        // Add ComponentListener to handle resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Reposition HelpOptionsPanel
                helpOptionsPanel.setBounds(0,  recordingFrame.getWidth() - 100, recordingFrame.getHeight(), 100);
            }
        });
    }
}
