package gc.information.getImageInfo.util;

import gc.information.getImageInfo.domain.NodeInfo;

import java.util.Objects;

public class NodeInfoUtils {
    public static boolean isLeafNode(NodeInfo nodeInfo) {
        boolean flag = false;
        if (Objects.isNull(nodeInfo.getChildren()) || nodeInfo.getChildren().size() == 0) {
            flag = true;
        }
        return flag;
    }

}
