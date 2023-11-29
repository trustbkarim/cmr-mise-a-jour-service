package ma.gov.cmr.echangeafbatch.batchElements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import ma.gov.cmr.echangeafbatch.dao.models.WsPasSuiviEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EchangeafItemProcessor implements ItemProcessor<WsPasSuiviEntity, JSONPObject> {

    private ObjectMapper objectMapper;

    @Override
    public JSONPObject process(WsPasSuiviEntity item) throws Exception {

        String jsonAsString = objectMapper.writeValueAsString(item);
        JSONPObject jsonObject = objectMapper.readValue(jsonAsString, JSONPObject.class);

        return jsonObject;
    }
}
