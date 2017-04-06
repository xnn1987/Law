package com.cn.law.util;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class ZipUtil {

    /** 
     * 使用给定密码解压指定的ZIP压缩文件到指定目录 
     * <p> 
     * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出 
     * @param zip 指定的ZIP压缩文件 
     * @param dest 解压目录 
     * @param passwd ZIP文件的密码 
     * @return  解压后文件数组 
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出 
     */  
    public static File [] unzip(File zipFile, String dest, String passwd) throws ZipException {  
        ZipFile zFile = new ZipFile(zipFile);  
        zFile.setFileNameCharset("utf-8");  
        if (!zFile.isValidZipFile()) {  
            throw new ZipException("压缩文件不合法,可能被损坏.");  
        }  
        File destDir = new File(dest);  
        if (destDir.isDirectory() && !destDir.exists()) {  
            destDir.mkdir();  
        }  
        if (zFile.isEncrypted()) {  
            zFile.setPassword(passwd.toCharArray());  
        }  
        zFile.extractAll(dest);  
          
        @SuppressWarnings("unchecked")
		List<FileHeader> headerList = zFile.getFileHeaders();  
        List<File> extractedFileList = new ArrayList<File>();  
        for(FileHeader fileHeader : headerList) {  
            if (!fileHeader.isDirectory()) {  
                extractedFileList.add(new File(destDir,fileHeader.getFileName()));  
            }  
        }  
        File [] extractedFiles = new File[extractedFileList.size()];  
        extractedFileList.toArray(extractedFiles);  
        return extractedFiles;  
    } 
	}
