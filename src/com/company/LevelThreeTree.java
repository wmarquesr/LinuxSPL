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
public class LevelThreeTree {

    public LevelThreeTree() {
        //Create and set up a frame window.
        JFrame frame = new JFrame("LinuxSPL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel linuxPanel = createPanel("Linux", 6 + DEFINES.LV1ACTIVES, 1);

        //Create Checkboxes.
        JPanel fileSystemPanel = createPanel("File System", 2, 1);
        JCheckBox fileSystemCB = createCheckBox("File System", true, false);
        fileSystemPanel.add(fileSystemCB);
        JPanel fileSystemOptPanel = createPanel(null,1,1);
        if (Objects.equals(DEFINES.CHOSEN_FILE_SYSTEM, "EXT 3")) {
            JCheckBox fsCB1 = createCheckBox("EXT 3",true,false);
            fileSystemOptPanel.add(fsCB1);
        } else if (Objects.equals(DEFINES.CHOSEN_FILE_SYSTEM, "Reiser FS")) {
            JCheckBox fsCB2 = createCheckBox("Reiser FS",true,false);
            fileSystemOptPanel.add(fsCB2);
        } else if (Objects.equals(DEFINES.CHOSEN_FILE_SYSTEM, "JSF")) {
            JCheckBox fsCB3 = createCheckBox("JSF",true,false);
            fileSystemOptPanel.add(fsCB3);
        }
        fileSystemPanel.add(fileSystemOptPanel);
        linuxPanel.add(fileSystemPanel);

        JPanel generalSetupPanel = createPanel("General Setup",2,1);
        JCheckBox generalSetupCB = createCheckBox("General Setup",true,false);
        generalSetupPanel.add(generalSetupCB);
        JPanel generalSetupOpt = createPanel(null,DEFINES.GENERALACTIVES,1);
        if (DEFINES.AUDITING_SUPPORT) {
            JCheckBox auditionSupportCB = new JCheckBox("Audition Support", true);
            auditionSupportCB.setEnabled(false);
            generalSetupOpt.add(auditionSupportCB);
        }
        if (DEFINES.SWAP) {
            JCheckBox swapCB = new JCheckBox("Swap", true);
            swapCB.setEnabled(false);
            generalSetupOpt.add(swapCB);
        }
        generalSetupPanel.add(generalSetupOpt);
        linuxPanel.add(generalSetupPanel);

        JPanel powerManPanel = createPanel("Power Management",2,1);
        JCheckBox powerManagementCB = createCheckBox("Power Management", true, false);
        powerManPanel.add(powerManagementCB);
        JPanel powerManOptPanel = createPanel(null,DEFINES.POWERMANGACTIVES,1);
        if (DEFINES.SOFTWARE_SUSPENDED) {
            JCheckBox softwareSusCB = createCheckBox("Software Suspended",true,false);
            powerManOptPanel.add(softwareSusCB);
        }
        if (DEFINES.CPU_FREQUENCY_SCALING) {
            JCheckBox cpuFreqCB = createCheckBox("CPU Frequenct Scaling", true, false);
            powerManOptPanel.add(cpuFreqCB);
        }
        if (DEFINES.APM_BIOS_SUPPORT) {
            JCheckBox apmSupCB = createCheckBox("APM BIOS Support",true,false);
            powerManOptPanel.add(apmSupCB);
        }
        powerManPanel.add(powerManOptPanel);
        linuxPanel.add(powerManPanel);

        if (DEFINES.SECURITY_OPTIONS) {
            JPanel securityPanel = createPanel("Security",2,1);
            JCheckBox securityCB = createCheckBox("Security",DEFINES.SECURITY_OPTIONS,false);
            securityPanel.add(securityCB);
            JPanel securityOptPanel = createPanel(null, DEFINES.SECURITYACTIVES,1);
            if (DEFINES.NSA_SELINUX){
                JCheckBox nsaCB = createCheckBox("NSA SELinux",true,false);
                securityOptPanel.add(nsaCB);
            }
            if (DEFINES.SOCKET_AND_NETWORKING) {
                JPanel socketPanel = createPanel("Socket and Networking",2,1);
                JCheckBox socketCB = createCheckBox("Socket and Networking",true,false);
                socketPanel.add(socketCB);
                    JPanel socketOptPanel = createPanel(null,1,1);
                        JCheckBox ipsecCB = createCheckBox("IPSec",false,true);
                        ipsecCB.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                DEFINES.IPSEC = true;
                            }
                        });
                    socketOptPanel.add(ipsecCB);
                socketPanel.add(socketOptPanel);
                securityOptPanel.add(socketPanel);
            }
            securityPanel.add(securityOptPanel);
            linuxPanel.add(securityPanel);
        }

        JPanel deviceDriversPanel = createPanel("Device Drivers",2,1);
        JCheckBox deviceDriversCB = createCheckBox("Device Drivers",true,false);
        deviceDriversPanel.add(deviceDriversCB);
        JPanel deviceDriversOptPanel = createPanel(null,DEFINES.DEVICEACTIVES,1);
        if (DEFINES.SATA) {
            JCheckBox sataCB = createCheckBox("SATA",true,false);
            deviceDriversOptPanel.add(sataCB);
        }
        if (DEFINES.WIRELESS_LAN) {
            JCheckBox wirelessCB = createCheckBox("Wireless LAN",true,false);
            deviceDriversOptPanel.add(wirelessCB);
        }
        if (DEFINES.NETWORKING_DEVICE) {
            JPanel networkDevPanel = createPanel("Networking Device",2,1);
            JCheckBox netDevCB = createCheckBox("Networking Device",true,false);
            networkDevPanel.add(netDevCB);
            JPanel networkDevOptPanel = createPanel(null,2,1);
            JCheckBox ethernet100CB = createCheckBox("Ethernet 10 to 100 MBit",false,true);
            JCheckBox ethernet1000CB = createCheckBox("Ethernet 1000 MBit",false,true);
            ethernet100CB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.ETHERNET_10_TO1_00_MBIT = true;
                    DEFINES.ETHERNETACTIVE++;
                }
            });
            ethernet1000CB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.ETHERNET_1000_MBIT = true;
                    DEFINES.ETHERNETACTIVE++;
                }
            });
            networkDevOptPanel.add(ethernet100CB);
            networkDevOptPanel.add(ethernet1000CB);
            networkDevPanel.add(networkDevOptPanel);
            deviceDriversOptPanel.add(networkDevPanel);
        }
        deviceDriversPanel.add(deviceDriversOptPanel);
        linuxPanel.add(deviceDriversPanel);

        if (DEFINES.NETWORKING_OPTIONS) {
            JPanel networkPanel = createPanel("Networking",2,1);
            JCheckBox networkCB = createCheckBox("Networking",DEFINES.NETWORKING_OPTIONS,false);
            networkPanel.add(networkCB);
            JPanel networkOptPanel = createPanel(null,DEFINES.NETACTIVES,1);
            if (DEFINES.IRDA) {
                JCheckBox irdaCB = createCheckBox("IrDA",true,false);
                networkOptPanel.add(irdaCB);
            }
            if (DEFINES.BLUETOOTH) {
                JCheckBox bluetoothCB = createCheckBox("Bluetooth",true,false);
                networkOptPanel.add(bluetoothCB);
            }
            if (DEFINES.TCP_IP) {
                JCheckBox tcpCB = createCheckBox("TCP IP",true,false);
                JPanel tcpOptPanel = createPanel("TCP IP",4,1);
                    tcpOptPanel.add(tcpCB);
                    JCheckBox multiCB = createCheckBox("Multicasting",false,true);
                    JCheckBox tunnelingCB = createCheckBox("Tunneling",false,true);
                    JCheckBox ipv6CB = createCheckBox("IPv6",false,true);
                    multiCB.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            DEFINES.MULTICASTING = true;
                            DEFINES.TCPACTIVE++;
                        }
                    });
                    tunnelingCB.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            DEFINES.TUNNELING = true;
                            DEFINES.TCPACTIVE++;
                        }
                    });
                    ipv6CB.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            DEFINES.IPV6 = true;
                            DEFINES.TCPACTIVE++;
                        }
                    });
                tcpOptPanel.add(multiCB);
                tcpOptPanel.add(tunnelingCB);
                tcpOptPanel.add(ipv6CB);
                networkOptPanel.add(tcpOptPanel);
            }
            networkPanel.add(networkOptPanel);
            linuxPanel.add(networkPanel);
        }

        if (DEFINES.CRYPTOGRAPHY_ALGORITHMS) {
            JPanel cryptographyPanel = createPanel("Cryptograph Algorithms",2,1);
            JCheckBox cryptographyCB = createCheckBox("Cryptography",DEFINES.CRYPTOGRAPHY_ALGORITHMS,false);
            cryptographyPanel.add(cryptographyCB);
            JPanel cryptographyOptPanel = createPanel(null,DEFINES.CRYPACTIVES,1);
            if (DEFINES.MD5) {
                JCheckBox md5CB = createCheckBox("MD5",true,false);
                cryptographyOptPanel.add(md5CB);
            }
            if (DEFINES.SHA256) {
                JCheckBox sha256CB = createCheckBox("SHA256",true,false);
                cryptographyOptPanel.add(sha256CB);
            }
            if (DEFINES.AES_CIPHER) {
                JCheckBox aesCB = createCheckBox("AES Cipher",true,false);
                cryptographyOptPanel.add(aesCB);
            }
            cryptographyPanel.add(cryptographyOptPanel);
            linuxPanel.add(cryptographyPanel);
        }

        JPanel processorPanel = createPanel("Processor Type and Features",2,1);
        JCheckBox processorTypeCB = createCheckBox("Processor Type",true,false);
        processorPanel.add(processorTypeCB);
        JPanel procTypeOptPanel = createPanel(null,1 + DEFINES.PROCESSORACTIVE,1);
        JPanel processorOptPanel = createPanel("Processor Family",2,1);
            JCheckBox procFamilyCB = createCheckBox("Processor Family",true,false);
            processorOptPanel.add(procFamilyCB);
            JPanel procFamimlyPanel = createPanel(null,3,1);
                JCheckBox intelCB = createCheckBox("Intel",false,true);
                JCheckBox amdCB = createCheckBox("AMD",false,true);
                JCheckBox genericCB = createCheckBox("Generic x86 64",false,true);
                intelCB.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        DEFINES.CHOSEN_PROCESS_FAMILY = "Intel";
                        amdCB.setEnabled(false);
                        genericCB.setEnabled(false);
                    }
                });
                amdCB.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        DEFINES.CHOSEN_PROCESS_FAMILY = "AMD";
                        intelCB.setEnabled(false);
                        genericCB.setEnabled(false);
                    }
                });
                genericCB.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        DEFINES.CHOSEN_PROCESS_FAMILY = "Generic x86 64";
                        intelCB.setEnabled(false);
                        amdCB.setEnabled(false);
                    }
                });
            procFamimlyPanel.add(intelCB);
            procFamimlyPanel.add(amdCB);
            procFamimlyPanel.add(genericCB);
        processorOptPanel.add(procFamimlyPanel);
        procTypeOptPanel.add(processorOptPanel);

        if (DEFINES.SYMMETRIC_MULTIPROCESSING_SUPPORT) {
            JPanel multProcPanel = createPanel("Symmetric Multiprocessing Support",2,1);
            JCheckBox multProcCB = createCheckBox("Symmetric Multiprocessing Support",true,false);
            multProcPanel.add(multProcCB);
            JPanel multProcOptPanel = createPanel(null,2,1);
            JCheckBox thredCB = createCheckBox("Hyper Threading Support",false,true);
            JCheckBox multCoreCB = createCheckBox("Multi Core Support",false,true);
            thredCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.HYPER_THREADING = true;
                    DEFINES.MULTIPROACTIVE++;
                }
            });
            multCoreCB.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    DEFINES.MULTICORE = true;
                    DEFINES.MULTIPROACTIVE++;
                }
            });
            multProcOptPanel.add(thredCB);
            multProcOptPanel.add(multCoreCB);
            multProcPanel.add(multProcOptPanel);
            procTypeOptPanel.add(multProcPanel);
        }
        processorPanel.add(procTypeOptPanel);

        linuxPanel.add(processorPanel);

        //Next Button.
        JButton productButton = new JButton("Final Product");
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new FinalProduct();
            }
        });

        linuxPanel.add(productButton);
        JScrollPane jsp = new JScrollPane(linuxPanel);
        frame.add(jsp);

        // Set the window to be visible as the default to be false
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createPanel(String name, int lin, int col) {
        JPanel p = new JPanel();
        //p.setPreferredSize(new Dimension(200,1000));
        //p.setLayout(new GridLayout(lin,col));
        p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
        //p.setMaximumSize(new Dimension(10,1));
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
