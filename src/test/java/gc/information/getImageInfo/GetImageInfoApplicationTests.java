package gc.information.getImageInfo;


import gc.information.getImageInfo.catalog.ProcessTreeNode;
import gc.information.getImageInfo.catalog.URLNodeInfo;
import gc.information.getImageInfo.domain.NodeInfo;
import gc.information.getImageInfo.util.Url2File;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GetImageInfoApplicationTests {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ProcessTreeNode processTreeNode;

    @Autowired
    URLNodeInfo urlNodeInfo;

    @Test
    public void getTreeInfo() {
//		String paras = "data";
//		String url="https://pcb.inc.xxx.com/api/catalogs/cn-zh/nodes/18162739/bestImage?status[]=O&status[]=L&hierParadigm=F";
        String url = "https://pcb.inc.xxx.com/api/catalogs/cn-zh/nodes/7398647/children?status[]=O&status[]=L&hierParadigm=F";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//		JSONObject jsonObj = JSONObject.parseObject(paras);
//		HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
//		String result = restTemplate.postForObject(url, formEntity, String.class);
//		ImageInfo result = restTemplate.getForObject(url, ImageInfo.class);
        String result = restTemplate.getForObject(url, String.class);

        System.out.println(result);
        NodeInfo result1 = restTemplate.getForObject(url, NodeInfo.class);
        System.out.println(result1);
    }

    @Test
    public void testURL2File() throws IOException {
        String url = "http://product-images.www8-xxx.com/digmedialib/prodimg/lowres/c05372744.png";
        Url2File.transUrl2File(url, "c:/test/", "test.png");
    }

    @Test
    public void testURIOperator() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("oid", "7398647");
        String struri = UriComponentsBuilder.fromUriString("https://pcb.inc.xxx.com/api/catalogs/cn-zh/nodes/{oid}/children?status[]=O&status[]=L&hierParadigm=F")
                .buildAndExpand(params).toUriString();
        System.out.println(struri);
    }

    @Test
    public void testNodeGetInfo() throws IOException {
        String url = "https://pcb.inc.xxx.com/api/catalogs/cn-zh/nodes/469044/children?status[]=O&status[]=L&hierParadigm=F";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        NodeInfo result1 = restTemplate.getForObject(url, NodeInfo.class);
//        processTreeNode.integrationNodeInfo(result1, "software");

    }

   /* @Test
    public void testNodeAllInfo() {
        List<SearchNode> list = OriginNodeData.getCatalogList();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (SearchNode searchNode : list) {
            NodeInfo nodeInfo = urlNodeInfo.getNodeInfo(searchNode.getOid());
            executorService.execute(() -> {
                try {
//                    processTreeNode.integrationNodeInfo(nodeInfo, searchNode.getCatalog());
                    System.out.println();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }*/

   @Test
    public void testReadAllFile2OneFile() throws IOException {
       File distFile = new File("c:/work/downloadtemp/all.txt");
       FileUtils.touch(distFile);
       File file = new File("C:\\work\\downloadtemp");
       File[] files = file.listFiles();
       for(File tempFile:files){
           List<String> tempList = FileUtils.readLines(tempFile);
           FileUtils.writeLines(distFile,tempList,true);
       }

   }

}
