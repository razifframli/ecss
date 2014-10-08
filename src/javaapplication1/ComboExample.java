

package javaapplication1;

import java.io.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;



public class ComboExample extends JFrame {

public static void main(String[] args){
    Calendar calendar = new GregorianCalendar();
    String am_pm;
    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);
    if(calendar.get(Calendar.AM_PM) == 0)
      am_pm = "AM";
    else
      am_pm = "PM";
    System.out.println("Current Time : " + hour + ":"
+ minute + ":" + second + " " + am_pm);
  }

}
