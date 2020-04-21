package gc.information.getImageInfo;

import com.google.common.collect.Lists;
import gc.information.getImageInfo.catalog.ProcessTreeNode;
import gc.information.getImageInfo.catalog.URLNodeInfo;
import gc.information.getImageInfo.data.OriginNodeData;
import gc.information.getImageInfo.domain.NodeInfo;
import gc.information.getImageInfo.domain.SearchNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class GetImageInfoApplication  {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ProcessTreeNode processTreeNode;

    @Autowired
    URLNodeInfo urlNodeInfo;

    public static void main(String[] args) {
        SpringApplication.run(GetImageInfoApplication.class, args);
    }


    public void run(String... args) throws Exception {
        List<SearchNode> list = OriginNodeData.getCatalogList();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (SearchNode searchNode : list) {
            NodeInfo nodeInfo = urlNodeInfo.getNodeInfo(searchNode.getOid());
            executorService.execute(() -> {
                try {
                    processTreeNode.integrationNodeInfo(nodeInfo, searchNode.getCatalog(), Lists.newArrayList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
