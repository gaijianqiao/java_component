package gc.information.getImageInfo.catalog;

import gc.information.getImageInfo.domain.ImageInfo;
import gc.information.getImageInfo.domain.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class URLNodeInfo {
    @Autowired
    private RestTemplate restTemplate;

    public NodeInfo getNodeInfo(String oid) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("oid", oid);
        String url = UriComponentsBuilder.fromUriString("https://xxx/api/catalogs/cn-zh/nodes/{oid}/children?status[]=O&status[]=L&hierParadigm=F")
                .buildAndExpand(params).toUriString();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        NodeInfo result = new NodeInfo();
        try {
            result = restTemplate.getForObject(url, NodeInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ImageInfo getImageInfo(String oid) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("oid", oid);
        String url = UriComponentsBuilder.fromUriString("https://xxx/api/catalogs/cn-zh/nodes/{oid}/bestImage?status[]=O&status[]=L&hierParadigm=F")
                .buildAndExpand(params).toUriString();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        ImageInfo result = restTemplate.getForObject(url, ImageInfo.class);

        return result;

    }

}
