package com.item.javaee.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @ClassName: FastDfsUtils
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-07 16:51
 * @Version: 1.0
 **/
public class FastDfsUtils {
    private static StorageClient storageClient ;

    /**
     * 静态代码块加载StorageClient
     */
    static {

        try {
            ClientGlobal.init("fastdfs_client.properties") ;

            TrackerClient trackerClient = new TrackerClient() ;

            TrackerServer trackerServer = trackerClient.getConnection() ;
            StorageServer storageServer = null ;

            storageClient = new StorageClient(trackerServer, storageServer) ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件绝对路径名上传本地文件,
     * 返回一个 group/...
     */
    public static String upload(String fileName, String ext) throws IOException,MyException {
        String[] strings = storageClient.upload_file(fileName, ext, null);

        return strings[0].concat("/").concat(strings[1]) ;
    }

    /**
     * 根据文件的字节流上传
     * 返回一个 group[0-9]/...等的串
     */
    public static String upload(byte[] fileBytes, String ext) throws IOException, MyException {
        String[] strings = storageClient.upload_file(fileBytes, ext, null);
        return strings[0].concat("/").concat(strings[1]) ;
    }
}
