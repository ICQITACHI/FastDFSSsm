package com.jin.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * 高山仰之可及，深渊度之可测
 */
public class FastDFSUtils {

    //声明
    TrackerClient trackerClient = null;
    TrackerServer trackerServer = null;
    StorageClient storageClient = null;
    StorageServer storageServer = null;

   public FastDFSUtils(){

       try {
           //1.加载配置文件
           ClientGlobal.init("client.conf");
           System.out.println(ClientGlobal.configInfo());

           //2.创建trackerClient对象
           trackerClient = new TrackerClient();

           //3.得到TrackerServer对象
           trackerServer = trackerClient.getConnection();

           //4.构建storageClient对象
           storageClient = new StorageClient(trackerServer,storageServer);

       } catch (IOException e) {
           e.printStackTrace();
       } catch (MyException e) {
           e.printStackTrace();
       }
   }

   public String[] upload(String local_name,String suffix){
       try {
           storageClient.upload_file(local_name,suffix,null);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }

   public String[] upload(byte[] b,String ext){
       try {
           return storageClient.upload_file(b,ext,null);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }





}
