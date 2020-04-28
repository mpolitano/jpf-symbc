package issta2006;
import java.io.FileInputStream;
import java.util.Properties;

public class Utils {
	public final static String funAbsFile = "abstractionFunction.txt";
	public static String scope;
	
	public static void readScope() {

		String propFile = System.getProperty("properties", funAbsFile);
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(propFile));
			scope = props.getProperty("scope");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
}