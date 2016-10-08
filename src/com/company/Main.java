package com.company;

import params.DEFINES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {

    public static void main(String[] args) {

        //Create and set up a frame window.
        JFrame frame = new JFrame("LinuxSPL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel linuxPanel = new JPanel();
        linuxPanel.setLayout(new GridLayout(9,1));

        //Create Checkboxes.
        JCheckBox fileSystemCB = new JCheckBox("File System",true);
        fileSystemCB.setEnabled(false);

        JCheckBox generalSetupCB = new JCheckBox("General Setup",true);
        generalSetupCB.setEnabled(false);

        JCheckBox powerManagementCB = new JCheckBox("Power Management",true);
        powerManagementCB.setEnabled(false);

        JCheckBox securityCB = new JCheckBox("Security",false);
        securityCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DEFINES.SECURITY_OPTIONS = true;
                    DEFINES.LV1ACTIVES++;
                }
            }
        });

        JCheckBox deviceDriversCB = new JCheckBox("Device Drivers",true);
        deviceDriversCB.setEnabled(false);

        JCheckBox networkCB = new JCheckBox("Network",false);
        networkCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DEFINES.NETWORKING_OPTIONS = true;
                    DEFINES.LV1ACTIVES++;
                }
            }
        });

        JCheckBox cryptographyCB = new JCheckBox("Cryptography",false);
        cryptographyCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DEFINES.CRYPTOGRAPHY_ALGORITHMS = true;
                    DEFINES.LV1ACTIVES++;
                }
            }
        });

        JCheckBox processorTypeCB = new JCheckBox("Processor Type",true);
        processorTypeCB.setEnabled(false);

        //Next Button.
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LevelTwoTree();
            }
        });

        // Set up the title for the panel
        linuxPanel.setBorder(BorderFactory.createTitledBorder("Linux"));
        linuxPanel.add(fileSystemCB);
        linuxPanel.add(generalSetupCB);
        linuxPanel.add(powerManagementCB);
        linuxPanel.add(securityCB);
        linuxPanel.add(deviceDriversCB);
        linuxPanel.add(networkCB);
        linuxPanel.add(cryptographyCB);
        linuxPanel.add(processorTypeCB);
        linuxPanel.add(nextButton);

        // Add the panel into the frame
        frame.add(linuxPanel);

        // Set the window to be visible as the default to be false
        frame.pack();
        frame.setVisible(true);
    }
}
