package blank.trash;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class ByteTest {

	private static ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
    private static DataOutputStream datastream = new DataOutputStream(bytestream);
    
    public static byte[] toByte(short value) throws IOException
    {       
            datastream.writeShort(value);
            datastream.flush();
            byte[] bytes = bytestream.toByteArray();
            bytestream.flush();
            return bytes;
            
            
    }
    
    
    public static byte[] toByte(int value) throws IOException
    {       
            
            datastream.writeInt(value);
            datastream.flush();
            byte[] bytes = bytestream.toByteArray();        
            return bytes;
    }
 
    
    public static byte[] toByte(long value) throws IOException
    {       
            datastream.writeLong(value);
            datastream.flush();
            byte[] bytes = bytestream.toByteArray();        
            return bytes;
            
    }
 
    
    public static byte[] toByte(float value) throws IOException
    {       
            datastream.writeFloat(value);
            datastream.flush();
            byte[] bytes = bytestream.toByteArray();        
            return bytes;
    }
    
    public static byte[] toByte(double value) throws IOException
    {       
            datastream.writeDouble(value);
            datastream.flush();
            byte[] bytes = bytestream.toByteArray();        
            return bytes;
    }
 
 
 

	public static void main(String[] args) throws IOException {
		String test = "Testää";
		byte[] bytes = test.getBytes(Charset.forName("UTF8"));
		System.out.println(new String(bytes,Charset.forName("UTF8")));
		
		 ByteBuffer buf = ByteBuffer.allocate(8);
         buf.put(toByte(12324.4343f));
         System.out.println(buf.getFloat(0));
	}

}
