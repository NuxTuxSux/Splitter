package com.ntsys;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Button;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;


public class Splitter extends Frame implements WindowListener, ItemListener, ActionListener {
    public static int N = 3;
    public String imgPath = "";
    Button btnSplit;
    public Splitter() {
        super("Splitter");
        setLayout(new FlowLayout());
        setTitle("Splitter");
        // setSize(300,300);
        CheckboxGroup splitnumber = new CheckboxGroup();
        Checkbox two = new Checkbox("2", false, splitnumber);
        Checkbox three = new Checkbox("3", false, splitnumber);
        Checkbox four = new Checkbox("4", false, splitnumber);
        btnSplit = new Button("Split!");
        two.addItemListener(this);
        three.addItemListener(this);
        four.addItemListener(this);
        btnSplit.addActionListener(this);
        Label label = new Label("Please select the number of splits");
        add(label);
        add(two);
        add(three);
        add(four);
        add(btnSplit);
        addWindowListener(this);
        pack();
        setVisible(true);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        this.dispose();
    }
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void itemStateChanged(ItemEvent e) {
        Checkbox selected = (Checkbox) e.getItemSelectable();
        N = Integer.parseInt(selected.getLabel());
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSplit) {
            try {
                splitImage();
            }
            catch (Exception ex) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // String imagePath = args[0];
        // String imagePath = "prova.png";
        Splitter app = new Splitter();

        FileDialog fd = new FileDialog(app, "Choose an image", FileDialog.LOAD);
        fd.setDirectory(".");
        fd.setVisible(true);
        app.imgPath = fd.getDirectory() + fd.getFile();
        if (args.length > 1) N = Integer.parseInt(args[1]);
    }
    public void splitImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(imgPath));
        int width = image.getWidth();
        int height = image.getHeight();
        int deltaH = height / N;
        BufferedImage subImg;
        File subImgFile;
        for (int i = 0; i < N; i++) {
            subImg = image.getSubimage(0, i * deltaH, width, deltaH);
            subImgFile = new File(String.format("%d.png", i));
            ImageIO.write(subImg, "png", subImgFile);
        }
    }
}

