package ChatHistory;


import java.io.*;

public class StreamDemoApp {
    public static void main(String[] args) {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream ("demo.txt"))) {
            for (int i = 0; i < 100; i++) {
                out.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream in = new BufferedInputStream (new FileInputStream ("demo.txt"))) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char)x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
