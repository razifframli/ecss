package library;

import Bean.ConnectCSS;
import Helper.J;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Func {
    
    /**
     * Hide password
     */
    public static String getPwd1(String pwd) {
        String str = "";
        try {
            for (int i = 0; i < pwd.length(); i++) {
                str += "*";
            }
        } catch (Exception e) {
            str = "";
        }
        return str;
    }
    
    /**
     * Distinct ArrayList<String>
     */
    public static ArrayList<String> getDistinctList(ArrayList<String> data) {
        String te = "";
        ArrayList<String> lst = new ArrayList<String>();
        ArrayList<String> lst2 = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++) {
            te += data.get(i) + ", ";
            lst.add(data.get(i));
            lst2.add(data.get(i));
        }
        System.out.println("Duplicates List " + lst);
        Object[] st = lst.toArray();
        for (Object s : st) {
            if (lst.indexOf(s) != lst.lastIndexOf(s)) {
                lst.remove(lst.lastIndexOf(s));
            }
        }
        System.out.println("Distinct List - Original " + lst);
        lst.removeAll(Collections.singleton(null));
        System.out.println("Distinct List - Remove null " + lst);
        lst.removeAll(Collections.singleton(""));
        System.out.println("Distinct List - Remove blank " + lst);
        lst.removeAll(Collections.singleton("-"));
        System.out.println("Distinct List - Remove dash " + lst);
        Collections.sort(lst);
        System.out.println("Distinct List " + lst);
        return lst;
    }
    
    /**
     * Beza antara tarikh format dd/MM/yyyy
     * @return 
     */
    public static int getDiffDate1(String d1, String d2) {
        int a = 0;
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(d1);
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(d2);
            long diffDate = date2.getTime() - date1.getTime();
            long diffDay = (diffDate / 1000) / 86400;
            System.out.println("date: "+diffDay);
            a = (int) diffDay;
        } catch (Exception e) {
            a = 0;
            e.printStackTrace();
        }
        return a;
    }
    
    public static String getTimeNow() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    
    public static Timestamp getTimestampNow() {
        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        return timestamp;
    }
    
    public static String getFormatZero(int type, int num) {
        String str = "";
        try {
            switch(type) {
                case 1: //2 digits
                    if (num < 10) {
                        str = "0"+num;
                    } else if (num < 100) {
                        str = ""+num;
                    } else {
                        str = "01";
                    }
                    break;
            }
        } catch (Exception e) {
            str = "";
            e.printStackTrace();
        }
        return str;
    }
    
    public static void cmbSelectInput(JComboBox combo, String input) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if(String.valueOf(combo.getItemAt(i)).equals(input)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public static boolean isMatch1(ArrayList<String> data1, ArrayList<String> data2) {
        boolean stat = true;
        try {
            int size1 = data1.size();
            int size2 = data2.size();
            if(size1 == size2) {
                for (int i = 0; i < size1; i++) {
                    if (!data1.get(i).equals(data2.get(i))) {
                        stat = false;
                        break;
                    }
                }
            } else {
                stat= false;
            }
        } catch (Exception e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }
    
    public static String datetosql(String date) {
        String tarikh = "0000-00-00";
        try {
            String d[] = date.split("/");
            tarikh = d[2] + "-" + d[1] + "-" + d[0];
        } catch (Exception e) {
            tarikh = "0000-00-00";
            e.printStackTrace();
        }
        return tarikh;
    }
    
    public static String sqltodate(String date) {
        String tarikh = "00/00/0000";
        try {
            String d[] = date.split("-");
            tarikh = d[2] + "/" + d[1] + "/" + d[0];
        } catch (Exception e) {
            tarikh = "00/00/0000";
            e.printStackTrace();
        }
        return tarikh;
    }
    
    public static String sqltotime(String date) {
        String masa = "00:00";
        try {
            String d1[] = date.split(" ");
            String d2[] = d1[1].split(":");
            masa = d2[0] + ":" + d2[1];
        } catch (Exception e) {
            masa = "00:00";
            e.printStackTrace();
        }
        return masa;
    }
    
    public static String timestampToDate(String date) {
        String tarikh = "00/00/0000";
        try {
            String d1[] = date.split(" ");
            String d2[] = d1[0].split("-");
            tarikh = d2[2] + "/" + d2[1] + "/" + d2[0];
        } catch (Exception e) {
            tarikh = "00/00/0000";
            e.printStackTrace();
        }
        return tarikh;
    }
    
    public static String trim(String data) {
        try {
            data = data.equals("") ? "-" : data;
            if (data.contains("Select")) {
                data = "-";
            }
        } catch (Exception e) {
            //e.printStackTrace();
            data = "-";
        }
        return data;
    }
    
    public static double trim2(double data) {
        try { 
            return data; 
        } catch(Exception ex) {
            ex.printStackTrace();
            return 0; 
        }
    }
    
    public static ArrayList<String> readXML(String stat) {
        ArrayList<String> data = new ArrayList<String>();
        try {
//            File fXmlFile = new File("connect.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(fXmlFile);
//            doc.getDocumentElement().normalize();
//            
//            NodeList nList = doc.getElementsByTagName(stat);
//            Node nNode = nList.item(0);
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) nNode;
//                data.add(getTagValue("ip", eElement).equals("-")?"":getTagValue("ip", eElement));
//                data.add(getTagValue("db", eElement).equals("-")?"":getTagValue("db", eElement));
//                data.add(getTagValue("user", eElement).equals("-")?"":getTagValue("user", eElement));
//                data.add(getTagValue("pass", eElement).equals("-")?"":getTagValue("pass", eElement));
//                data.add(getTagValue("url", eElement).equals("-")?"":getTagValue("url", eElement));
//                data.add(getTagValue("on", eElement).equals("-")?"":getTagValue("on", eElement));
//            }
            
            if(stat.equals("online")) {
                ConnectCSS.online();
            } else {
                ConnectCSS.offline();
            }
            data.add(ConnectCSS.getIp().equals("-") ? "" : ConnectCSS.getIp());
            data.add(ConnectCSS.getDb().equals("-") ? "" : ConnectCSS.getDb());
            data.add(ConnectCSS.getUser().equals("-") ? "" : ConnectCSS.getUser());
            data.add(ConnectCSS.getPass().equals("-") ? "" : ConnectCSS.getPass());
            data.add(ConnectCSS.getUrl().equals("-") ? "" : ConnectCSS.getUrl());
            data.add(ConnectCSS.getOn().equals("-") ? "" : ConnectCSS.getOn());
            
        } catch (Exception ex) {
            //Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
            J.o("File Network Problem", ex.getMessage(), 0);
            System.exit(1);
        }
        return data;
    }
    
    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }
}
