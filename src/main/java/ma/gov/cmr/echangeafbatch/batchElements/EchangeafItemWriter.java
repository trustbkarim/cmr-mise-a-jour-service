package ma.gov.cmr.echangeafbatch.batchElements;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class EchangeafItemWriter implements ItemWriter<JSONPObject> {

    private String filePath;

    @Override
    public void write(List<? extends JSONPObject> items) throws Exception {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (JSONPObject item : items) {
                fileWriter.write(item.toString());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}