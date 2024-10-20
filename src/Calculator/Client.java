package Calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        Console cons = System.console();
        String cmd = "a";
        Socket sock = null;
        BufferedWriter bw = null;
        BufferedReader br = null;

        try {
            // Create the socket once before the loop
            sock = new Socket("localhost", port);
            System.out.println("Connected!");

            // Set up output and input streams
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            bw = new BufferedWriter(writer);

            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            br = new BufferedReader(reader);

            while (!cmd.equals("exit")) {
                cmd = cons.readLine(">>> ");
                
                // Send command to server
                bw.write(cmd);
                bw.newLine();
                bw.flush();
                if(cmd.equals("exit")){
                    break;
                }

                // Read response from server
                String fromServer = br.readLine();
                System.out.printf(">>> Result %s\n", fromServer);
            }

            // Inform the server we are exiting
            bw.write("exit");
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sock != null && !sock.isClosed()) {
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
