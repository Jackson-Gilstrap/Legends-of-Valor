package FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileReaderUtility {

    public static List<String[]> readFile(String file_path, String delimiter) {
        List<String[]> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file_path))) {

            br.readLine();


            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(delimiter);

                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                results.add(parts);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

}






