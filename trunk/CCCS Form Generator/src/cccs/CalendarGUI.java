/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs;

/**
 * @author Original author: Sean Bahrami, seanb6@gmail.com
 * @author Edited by George Hardigg on 09-13-09
 */

//Imports 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class CalendarGUI extends JFrame implements ActionListener{
    //Arrays
    private static String []Days = {" Sun"," Mon"," Tue"," Wed"," Thu"," Fri"," Sat"};

    private String months[] = {"January", "February", "March", "April",
         "May", "June", "July", "August", "September",
         "October", "November", "December"};
    //StartDay will tell what day for Calendar starts on
    private int startDay= 0;
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    //What month to begin with,because it is April.The Date format is month-1
    private int Month= Calendar.getInstance().get(Calendar.MONTH);
    private Container c = new Container();
    //Buttons that are part of the ActionListener
    private JButton nextMonth;
    private JButton preMonth;
    //Label that Tells what month.
    private JLabel lblMonth;
    private JLabel lblYear;
    private JFrame f =new JFrame();
    private JButton Daybuttons[];
    private Calendar calendar = null;

    public CalendarGUI()
    {
        //Form settings
        setTitle("Choose a date");
        setSize(465,445);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Container settings
        c= getContentPane();
        c.setLayout(null);
        //Call the these classes which will make the calendar
        CheckFirstDay();
        CreateCalendar();
    }

    public Calendar getCalendar(){
        return calendar;
    }

    public void getDailyLog(Calendar calendar){
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(calendar.getTime());
        dispose();
    }

    private int DaysInMonth(){
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.MONTH, Month);
        int days_in_month = temp.getActualMaximum(Calendar.DAY_OF_MONTH);

        //Calculating leap years
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                    days_in_month ++;
                }
            }
            else{
                days_in_month ++;
            }
        }

        return days_in_month;
    }

    //ActionListener event
    public void actionPerformed(ActionEvent e)
    {
        //preMonth clicked
        if (e.getSource() == preMonth)
        {
            //Month is subtracted by 1
            Month--;
            if(Month == -1){
                Month = Calendar.DECEMBER;
                year--;
            }
            //Creates a new Creative Calender form
            c.removeAll();
            c.repaint();
            CheckFirstDay();
            CreateCalendar();
        }
        else if (e.getSource() == nextMonth)
        {
            //Month is added by 1
            Month++;
            if(Month == 12){
                Month = Calendar.JANUARY;
                year ++;
            }
            //Creates a new Creative Calender form
            c.removeAll();
            c.repaint();
            CheckFirstDay();
            CreateCalendar();
        }
        else{
            for(int i = 1; i < Daybuttons.length; i ++){
                if(e.getSource().equals(Daybuttons[i])){
                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, Month + 1);
                    calendar.set(Calendar.DAY_OF_MONTH, i - startDay + 1);

                    getDailyLog(calendar);
                }
            }
        }
    }

    //Method check for what day of the week the month begins
    public void CheckFirstDay()
    {
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.MONTH, Month);
        temp.set(Calendar.DAY_OF_MONTH, 1);
        startDay = temp.get(Calendar.DAY_OF_WEEK);
    }

    //Creates all the all the buttons and sets all the values
    public void CreateCalendar()
    {
        //Previous Month
        preMonth = new JButton("<");
        preMonth.setLocation(50,25);
        preMonth.setSize(45,25);
        preMonth.addActionListener(this);
        c.add(preMonth);
        //Label Month
        lblMonth = new JLabel(months[Month], null, JLabel.CENTER);
        lblMonth.setLocation(150,15);
        lblMonth.setSize(150,25);
        lblMonth.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        lblMonth.setHorizontalTextPosition(JLabel.CENTER);
        c.add(lblMonth);
        lblYear = new JLabel(year + "", null, JLabel.CENTER);
        lblYear.setLocation(200,40);
        lblYear.setSize(50,25);
        lblYear.setHorizontalTextPosition(JLabel.CENTER);
        c.add(lblYear);
        //Next Month Button
        nextMonth = new JButton(">");
        nextMonth.setLocation(355,25);
        nextMonth.setSize(45,25);
        nextMonth.addActionListener(this);
        c.add(nextMonth);

        addDayButtons();
    }

    public void addDayButtons(){
        /*For loop Creates the Mon - Sun labels
         *Add tells the location and size
         *the math for the location is 50*(i+1)
         *that gives it space between the labels and the
         *+10 is for that looks of it.
        */
        for(int i=0;i<7;i++){
            JLabel []lblDay = new JLabel[7];
            lblDay[i] = new JLabel(Days[i]);
            lblDay[i].setLocation(50*(i+1)+10,50);
            lblDay[i].setSize(250,50);
            c.add(lblDay[i]);
        }
        //Array for the X locations for the button locations
        int [] x = {0,50,100,150,200,250,300,350};
        //Width,Height,and Y locations for the buttons
        int y=100,w=50,h=50;
        /*Xmun is a counter for the X array it starts of with
         *startDay,so that the location of the button is at the correct point
         *Example April began on a Friday so the day would be 5 so the X location
         *will be X[5], so the location begins for the X's fifth number
        */
        int Xnum=startDay;
        /*Creates how many buttons are in the month
         * it is calculated by the DaysinMonth array which holds the
         *amount of days.Month is the current month int.So April would
         *be 3(DaysinMonth[3] which in the array is 30).It is add by start day
         *to recover the locations lost by which day it is start so April is
         *starts on a Friday, it lost 4 spaces and the +startDay makes up for that
         */
        Daybuttons = new JButton[DaysInMonth()+startDay];

        for(int i=1;i<DaysInMonth()+startDay;i++)
        {
        //Creates the buttons.So there is no nullpointer error
            Daybuttons[i]= new JButton();
        }
        /*for loop loops through all the days and sets the location and text
         *it start form startDay because that is the day the month starts and
         *as you can see that the loop ends with startDay added to the number
         *of days.The text is set (i-startDay+1) Example of that is startDay =5
         *5-5+1= Which would put in 1.And Xmum is add by one and when it reach 7
         *or greater it become 0 and the y value increases by 50;so the X[]array
         *begins with the first number
         */
        for(int i = startDay;i<DaysInMonth()+startDay;i++){
            Daybuttons[i].setLocation(x[Xnum],y);
            Daybuttons[i].setSize(h,w);
            Daybuttons[i].setText(""+(i-startDay+1));
            Daybuttons[i].addActionListener(this);
            Daybuttons[i].setVisible(true);

            if(Xnum>=7) {
                    Xnum =0;
                    y+=50;}
                    Xnum++;
            c.add(Daybuttons[i]);
        }
    }
}
