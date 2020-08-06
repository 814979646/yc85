package util;

import java.io.Closeable;
import java.io.IOException;
/**
 * Closeable  
 * @author Administrator
 *
 */
public class IOhelp {
	public static void close(AutoCloseable c) {
		if(c!=null) {
			try {
				try {
					c.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}

}
