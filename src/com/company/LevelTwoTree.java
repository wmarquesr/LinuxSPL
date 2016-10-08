package com.company;

import params.DEFINES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

/**
 * Created by wmarques on 06/10/16.
 */
public class LevelTwoTree {

    public LevelTwoTree() {
        //Create and set up a frame window.
        JFrame frame = new JFrame("LinuxSPL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel linuxPanel = createPanel("Linux", 6 + DEFINES.LV1ACTIVES, 1);

        //Create Checkboxes.
        JPanel fileSystemPanel = createPanel("File System", 2, 1);
        JCheckBox fileSystemCB = createCheckBox("File System", true, false);
        fileSystemPanel.add(fileSystemCB);
        JPanel fileSystemOptPanel = createPanel(null,3,1);
        JCheckBox fsCB1 = new JCheckBox("EXT 3");
        JCheckBox fsCB2 = new JCheckBox("Reiser FS");
        JCheckBox fsCB3 = new JCheckBox("JSF");
        fsCB1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.CHOSEN_FILE_SYSTEM = "EXT 3";
                fsCB2.setEnabled(false);
                fsCB3.setEnabled(false);
            }
        });
        fsCB2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.CHOSEN_FILE_SYSTEM = "Reiser FS";
                fsCB1.setEnabled(false);
                fsCB3.setEnabled(false);
            }
        });
        fsCB3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.CHOSEN_FILE_SYSTEM = "JSF";
                fsCB1.setEnabled(false);
                fsCB2.setEnabled(false);
            }
        });
        fileSystemOptPanel.add(fsCB1);
        fileSystemOptPanel.add(fsCB2);
        fileSystemOptPanel.add(fsCB3);
        fileSystemPanel.add(fileSystemOptPanel);
        linuxPanel.add(fileSystemPanel);

        JPanel generalSetupPanel = createPanel("General Setup",2,1);
        JCheckBox generalSetupCB = createCheckBox("General Setup",true,false);
        generalSetupPanel.add(generalSetupCB);
        JPanel generalSetupOpt = createPanel(null,2,1);
        JCheckBox auditionSupportCB = new JCheckBox("Audition Support", false);
        auditionSupportCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.AUDITING_SUPPORT = true;
                DEFINES.GENERALACTIVES++;
            }
        });
        JCheckBox swapCB = new JCheckBox("Swap", false);
        swapCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.SWAP = true;
                DEFINES.GENERALACTIVES++;
            }
        });
        generalSetupOpt.add(auditionSupportCB);
        generalSetupOpt.add(swapCB);
        generalSetupPanel.add(generalSetupOpt);
        linuxPanel.add(generalSetupPanel);

        JPanel powerManPanel = createPanel("Power Management",2,1);
        JCheckBox powerManagementCB = createCheckBox("Power Management", true, false);
        powerManPanel.add(powerManagementCB);
        JPanel powerManOptPanel = createPanel(null,3,1);
        JCheckBox softwareSusCB = createCheckBox("Software Suspended",false,true);
        JCheckBox cpuFreqCB = createCheckBox("CPU Frequenct Scaling", false, true);
        JCheckBox apmSupCB = createCheckBox("APM BIOS Support",false,true);
        softwareSusCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.SOFTWARE_SUSPENDED = true;
                DEFINES.POWERMANGACTIVES++;
            }
        });
        cpuFreqCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.CPU_FREQUENCY_SCALING = true;
                DEFINES.POWERMANGACTIVES++;
            }
        });
        apmSupCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.APM_BIOS_SUPPORT = true;
                DEFINES.POWERMANGACTIVES++;
            }
        });
        powerManOptPanel.add(softwareSusCB);
        powerManOptPanel.add(cpuFreqCB);
        powerManOptPanel.add(apmSupCB);
        powerManPanel.add(powerManOptPanel);
        linuxPanel.add(powerManPanel);

        if (DEFINES.SECURITY_OPTIONS) {
            JPanel securityPanel = createPanel("Security",2,1);
            JCheckBox securityCB = createCheckBox("Security",DEFINES.SECURITY_OPTIONS,false);
            securityPanel.add(securityCB);
            JPanel securityOptPanel = createPanel(null,2,1);
            JCheckBox nsaCB = createCheckBox("NSA SELinux",false,true);
            JCheckBox socketCB = createCheckBox("Sockt and Networking",false,true);
            nsaCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.NSA_SELINUX = true;
                    DEFINES.SECURITYACTIVES++;
                }
            });
            socketCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.SOCKET_AND_NETWORKING = true;
                    DEFINES.SECURITYACTIVES++;
                }
            });
            securityOptPanel.add(nsaCB);
            securityOptPanel.add(socketCB);
            securityPanel.add(securityOptPanel);
            linuxPanel.add(securityPanel);
        }

        JPanel deviceDriversPanel = createPanel("Device Drivers",2,1);
        JCheckBox deviceDriversCB = createCheckBox("Device Drivers",true,false);
        deviceDriversPanel.add(deviceDriversCB);
        JPanel deviceDriversOptPanel = createPanel(null,3,1);
        JCheckBox sataCB = createCheckBox("SATA",false,true);
        JCheckBox wirelessCB = createCheckBox("Wireless LAN",false,true);
        JCheckBox netDevCB = createCheckBox("Networking Device",false,true);
        sataCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.SATA = true;
                DEFINES.DEVICEACTIVES++;
            }
        });
        wirelessCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.WIRELESS_LAN = true;
                DEFINES.DEVICEACTIVES++;
            }
        });
        netDevCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.NETWORKING_DEVICE = true;
                DEFINES.DEVICEACTIVES++;
            }
        });
        deviceDriversOptPanel.add(sataCB);
        deviceDriversOptPanel.add(wirelessCB);
        deviceDriversOptPanel.add(netDevCB);
        deviceDriversPanel.add(deviceDriversOptPanel);
        linuxPanel.add(deviceDriversPanel);

        if (DEFINES.NETWORKING_OPTIONS) {
            JPanel networkPanel = createPanel("Networking",2,1);
            JCheckBox networkCB = createCheckBox("Networking",DEFINES.NETWORKING_OPTIONS,false);
            networkPanel.add(networkCB);
            JPanel networkOptPanel = createPanel(null,3,1);
            JCheckBox irdaCB = createCheckBox("IrDA",false,true);
            JCheckBox bluetoothCB = createCheckBox("Bluetooth",false,true);
            JCheckBox tcpCB = createCheckBox("TCP IP",false,true);
            irdaCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.IRDA = true;
                    DEFINES.NETACTIVES++;
                }
            });
            bluetoothCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.BLUETOOTH = true;
                    DEFINES.NETACTIVES++;
                }
            });
            tcpCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.TCP_IP = true;
                    DEFINES.NETACTIVES++;
                }
            });
            networkOptPanel.add(irdaCB);
            networkOptPanel.add(bluetoothCB);
            networkOptPanel.add(tcpCB);
            networkPanel.add(networkOptPanel);
            linuxPanel.add(networkPanel);
        }

        if (DEFINES.CRYPTOGRAPHY_ALGORITHMS) {
            JPanel cryptographyPanel = createPanel("Cryptograph Algorithms",2,1);
            JCheckBox cryptographyCB = createCheckBox("Cryptography",DEFINES.CRYPTOGRAPHY_ALGORITHMS,false);
            cryptographyPanel.add(cryptographyCB);
            JPanel cryptographyOptPanel = createPanel(null,3,1);
            JCheckBox md5CB = createCheckBox("MD5",false,true);
            JCheckBox sha256CB = createCheckBox("SHA256",false,true);
            JCheckBox aesCB = createCheckBox("AES Cipher",false,true);
            md5CB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.MD5 = true;
                    DEFINES.CRYPACTIVES++;
                }
            });
            sha256CB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.SHA256 = true;
                    DEFINES.CRYPACTIVES++;
                }
            });
            aesCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.AES_CIPHER = true;
                    DEFINES.CRYPACTIVES++;
                }
            });
            cryptographyOptPanel.add(md5CB);
            cryptographyOptPanel.add(sha256CB);
            cryptographyOptPanel.add(aesCB);
            cryptographyPanel.add(cryptographyOptPanel);
            linuxPanel.add(cryptographyPanel);
        }

        JPanel processorPanel = createPanel("Processor Type and Features",2,1);
        JCheckBox processorTypeCB = createCheckBox("Processor Type",true,false);
        processorPanel.add(processorTypeCB);
        JPanel processorOptPanel = createPanel(null,2,1);
        JCheckBox procFamilyCB = createCheckBox("Processor Family",true,false);
        JCheckBox multProcCB = createCheckBox("Symmetric Multiprocessing Support",false,true);
        multProcCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DEFINES.SYMMETRIC_MULTIPROCESSING_SUPPORT = true;
                DEFINES.PROCESSORACTIVE++;
            }
        });
        processorOptPanel.add(procFamilyCB);
        processorOptPanel.add(multProcCB);
        processorPanel.add(processorOptPanel);
        linuxPanel.add(processorPanel);

        //Next Button.
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(DEFINES.CHOSEN_FILE_SYSTEM, "")){
                    JFrame f = new JFrame("ERROR");
                    JPanel p = new JPanel();
                    p.setBorder(BorderFactory.createEtchedBorder());
                    JLabel msg = new JLabel("SOME MANDATORY FEATURE IS NOT SELECTED.",JLabel.CENTER);
                    p.add(msg);
                    f.add(p);
                    f.pack();
                    f.setVisible(true);
                } else {
                    frame.dispose();
                    new LevelThreeTree();
                }
            }
        });

        // Set up the title for the panel
        linuxPanel.add(nextButton);

        // Add the panel into the frame
        frame.add(linuxPanel);

        // Set the window to be visible as the default to be false
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createPanel(String name, int lin, int col) {
        JPanel p = new JPanel();
        //p.setLayout(new GridLayout(lin,col));
        p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
        if (name != null) {
            p.setBorder(BorderFactory.createTitledBorder(name));
        } else {
            p.setBorder(BorderFactory.createEtchedBorder());
        }
        return p;
    }

    private JCheckBox createCheckBox(String name, boolean selected, boolean editable) {
        JCheckBox cb = new JCheckBox(name, selected);
        cb.setEnabled(editable);
        return cb;
    }
}
