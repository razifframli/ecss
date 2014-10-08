package config;

public class Config {
    
    private static String ipServer;
    private static String dbServer;
    private static String dbUrlServer;
    private static String userServer;
    private static String passServer;
    private static String dbUrlLocal;
    private static String userLocal;
    private static String passLocal;
    
    public static String getIpServer() {
        return Config.ipServer;
    }
    public static void setIpServer(String ipServer) {
        Config.ipServer = ipServer;
    }
    public static String getDbServer() {
        return Config.dbServer;
    }
    public static void setDbServer(String dbServer) {
        Config.dbServer = dbServer;
    }
    public static String getDbUrlServer() {
        return Config.dbUrlServer;
    }
    public static void setDbUrlServer(String dbUrlServer) {
        Config.dbUrlServer = dbUrlServer;
    }
    public static String getUserServer() {
        return Config.userServer;
    }
    public static void setUserServer(String userServer) {
        Config.userServer = userServer;
    }
    public static String getPassServer() {
        return Config.passServer;
    }
    public static void setPassServer(String passServer) {
        Config.passServer = passServer;
    }
    public static String getDbUrlLocal() {
        return Config.dbUrlLocal;
    }
    public static void setDbUrlLocal(String dbUrlLocal) {
        Config.dbUrlLocal = dbUrlLocal;
    }
    public static String getUserLocal() {
        return Config.userLocal;
    }
    public static void setUserLocal(String userLocal) {
        Config.userLocal = userLocal;
    }
    public static String getPassLocal() {
        return Config.passLocal;
    }
    public static void setPassLocal(String passLocal) {
        Config.passLocal = passLocal;
    }
}
