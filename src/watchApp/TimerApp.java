package watchApp;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.sound.sampled.*;

public class TimerApp implements ActionListener, WatchComponent {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int remainingTime = 10000; //Ajuste del temporizador
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    Clip clip; 

    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            remainingTime = remainingTime - 1000;
            hours = (remainingTime / 3600000);
            minutes = (remainingTime / 60000) % 60;
            seconds = (remainingTime / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
            if (remainingTime <= 0) {
                timer.stop();
                playSound(); 
                startButton.setText("START");
                started = false;
            }
        }
    });

    public TimerApp() {
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100, 200, 100, 50);
        startButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.setBackground(new Color(128, 0, 0));
        startButton.setForeground(Color.WHITE); 

        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setBackground(new Color(128, 0, 0));
        resetButton.setForeground(Color.WHITE); 

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Temporizador");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (started == false) {
                started = true;
                startButton.setText("STOP");
                start();
            } else {
                started = false;
                startButton.setText("START");
                stop();
            }
        }
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("START");
            reset();
        }
    }

    void start() {
        timer.start();
        if (clip != null) {
            clip.stop(); 
        }
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        remainingTime = 10000; //Ajuste del temporizador
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        if (clip != null) {
            clip.stop(); 
        }
    }

    void playSound() {
        try {
            File soundFile = new File("C:\\Users\\Usuario\\Downloads\\Patrones de software\\DesignPatternsProject\\audio_files\\zelda_ocarina_of_time_kakariko_village.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
    }

    @Override
    public void display() {
        frame.setVisible(true);
    }

}


