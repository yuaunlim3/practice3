package Calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Handler implements Runnable{
        private final Socket sock;

    public Handler(Socket s){
        sock = s;
    }

    @Override
    public void run(){
        String threadName = Thread.currentThread().getName();
        try{
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            // Get the output stream
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // Read from the client
            String fromClient;
            int result = 0;

            while ((fromClient = br.readLine()) != null) {
                if ("exit".equalsIgnoreCase(fromClient.trim())) {
                    System.out.println("Exit command received. Shutting down server...");
                    Server.stopServer(); // Call the method to stop the server
                    break;
                }
                System.out.printf("[%s]>>> CLIENT: %s\n",threadName, fromClient);

                Calculator calculator = new Calculator(fromClient);
                result = calculator.calculate();
                bw.write(Integer.toString(result));
                bw.newLine();
                bw.flush();
    
            }

        }
        
        catch(IOException ex){
            //Exception Handler
            ex.printStackTrace();
        }finally{
            try{
                System.out.println("Socket close");
                sock.close();}
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}
