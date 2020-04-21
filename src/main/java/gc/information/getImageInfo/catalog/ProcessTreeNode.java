package gc.information.getImageInfo.catalog;

import gc.information.getImageInfo.domain.ImageInfo;
import gc.information.getImageInfo.domain.NodeInfo;
import gc.information.getImageInfo.util.NodeInfoUtils;
import gc.information.getImageInfo.util.Url2File;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Slf4j
@Service
public class ProcessTreeNode {
    final public static String dirInfo = "c:/pnimage/";
    final public static String path = "/pnimage/";

    @Autowired
    private URLNodeInfo urlNodeInfo;


    private AtomicLong atomicLong = new AtomicLong(1);
    public void writeSQLINFO(String catalog,List<String> sqlInfoList) throws IOException {
        File outputFile = new File("c:/work/downloadtemp/"+catalog+"pnlist.txt");
        FileUtils.touch(outputFile);

        FileUtils.writeLines(outputFile, sqlInfoList, false);
    }

    public void integrationNodeInfo(NodeInfo nodeInfo,String catalog,List<String> sqlInfoList) throws IOException {
        progressNodeInfo(nodeInfo,catalog,sqlInfoList);
        writeSQLINFO(catalog, sqlInfoList);
    }

    //
    public void progressNodeInfo(NodeInfo nodeInfo,String catalog,List<String> sqlInfoList) throws IOException {

        if (NodeInfoUtils.isLeafNode(nodeInfo)) {
            try {
                String oid = nodeInfo.getOid();
                String pn = nodeInfo.getProdnum();
                ImageInfo imageInfo = urlNodeInfo.getImageInfo(oid);
                String imageUrlHttp = imageInfo.getMetadata().getImageUrlHttp();
                String valuesTemplate = "('%s','%s','%s','%s','%s'),";
                String realValues = String.format(valuesTemplate,oid,pn,nodeInfo.getBaseProdname(),path+catalog+"/"+pn+".png",imageUrlHttp);
                sqlInfoList.add(realValues);
//            System.out.println("('"+pn+"','"+nodeInfo.getBaseProdname()+"','"+path+pn+".png'),");
//            FileUtils.writeStringToFile(outputFile,"('"+pn+"','"+nodeInfo.getBaseProdname()+"','"+path+pn+".png'),", Charset.forName("utf-8"),true);
                Url2File.transUrl2File(imageUrlHttp, dirInfo+catalog, pn + ".png");
                System.out.println(Thread.currentThread().getName()+":"+atomicLong.incrementAndGet());
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                log.error("progress exception:{}",nodeInfo.toString());
//                System.out.println(imageInfo.toString());
            }
        } else {
            //recursive method
            for (NodeInfo loopNodeInfo : nodeInfo.getChildren()) {
                //need research the nodeInfo
                NodeInfo realNodeInfo = urlNodeInfo.getNodeInfo(loopNodeInfo.getOid());
                progressNodeInfo(realNodeInfo,catalog,sqlInfoList);
            }
        }


    }

    public static void main(String[] args) {
        String valuesTemplate = "('%s','%s','%s','%s','%s'),";
        String real = String.format(valuesTemplate, "1","2","3","4","5");
        System.out.println(real);
    }

}
