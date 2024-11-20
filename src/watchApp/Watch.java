package watchApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

public class Watch extends JFrame implements Runnable, WatchComponent {

    String hour, minute, second, ampm;
    Calendar calendar;
    Thread thread1;
    private static final long serialVersionUID = 1L;
    private JPanel lblWatch;
    private JLabel jLabel1;

    public Watch() {
        initComponents();
        thread1 = new Thread(this);
        thread1.start();
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        while (currentThread == thread1) {
            calculate();
            jLabel1.setText(hour + ":" + minute + ":" + second + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException error) {
            }
        }
    }

    private void calculate() {
        Calendar calendar = new GregorianCalendar();
        Date currentDateTime = new Date();
        calendar.setTime(currentDateTime);
        ampm = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendar.get(Calendar.HOUR_OF_DAY) - 12;
            hour = h > 9 ? "" + h : "0" + h;
            if (h == 0) {
                hour = "12";
            } else {
                hour = h > 9 ? "" + h : "0" + h;
            }
        } else {
            hour = calendar.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendar.get(Calendar.HOUR_OF_DAY) : "0" + calendar.get(Calendar.HOUR_OF_DAY);
        }
        minute = calendar.get(Calendar.MINUTE) > 9 ? "" + calendar.get(Calendar.MINUTE) : "0" + calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND) > 9 ? "" + calendar.get(Calendar.SECOND) : "0" + calendar.get(Calendar.SECOND);
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel1.setBackground(new Color(0, 0, 0));
        jLabel1.setForeground(new Color(128, 0, 0));
        jLabel1.setFont(new Font("Tw Cen MT", Font.BOLD, 45));
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setVerticalAlignment(JLabel.CENTER);

        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        lblWatch = new JPanel();
        lblWatch.setBorder(new EmptyBorder(5, 5, 5, 5));
        lblWatch.setLayout(new BorderLayout(0, 0));

        setContentPane(lblWatch);
        lblWatch.add(jLabel1, BorderLayout.CENTER);
        
        setTitle("Reloj digital");
    }

    @Override
    public void display() {
        setVisible(true);
    }
}
