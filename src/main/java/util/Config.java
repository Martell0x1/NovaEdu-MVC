package util;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try {
            InputStream inp = Config.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inp);
        } catch (Exception e){
            throw new RuntimeException("-- Config File Is Missing Or Corrupted");
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
