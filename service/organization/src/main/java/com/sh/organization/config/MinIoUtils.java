package com.sh.organization.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.api.client.util.IOUtils;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * minio工具类
 *
 *
 * @author 盛浩
 * @date 2021/1/24 18:27
 */
@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties({MinioProperties.class})
public class MinIoUtils {

    private final MinioProperties minioProperties;

    private static MinioClient minioClient;

    /**
     * 初始化minio服务器连接
     */
    @PostConstruct
    public void init() {
        try {
            minioClient = new MinioClient(this.minioProperties.getEndpoint(), this.minioProperties.getAccessKey(), this.minioProperties.getSecretKey());
            createBucket(this.minioProperties.getBucketName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化minio配置异常:：{}", e.fillInStackTrace());
        }
    }

    /**
     * 判断桶是否存在
     *
     * @param bucketName 桶名称
     * @return 桶名称是否存在
     */
    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(bucketName);
    }

    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     */
    @SneakyThrows(Exception.class)
    public static void createBucket(String bucketName) {
        if (! bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
        }
    }

    /**
     * 获取全部桶
     *
     * @return 桶列表
     */
    @SneakyThrows(Exception.class)
    public static List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    /**
     * 文件上传
     *
     * param bucket 桶名称
     * @param fileName 文件名
     * @param filePath 文件路径
     */
    @SneakyThrows(Exception.class)
    public static void fileUpload(String bucketName, String fileName, String filePath) {
        minioClient.putObject(bucketName, fileName, filePath, null);
    }

    /**
     * 拿到文件访问地址
     *
     * @param bucketName bucket名称
     * @param fileName ⽂件名称
     * @return 访问地址
     */
    @SneakyThrows(Exception.class)
    public static String getFileAccessPath(String bucketName, String fileName) {
        return minioClient.presignedGetObject(bucketName, fileName);
    }

    /**
     * 下载文件
     *
     * @param bucketName 桶名称
     * @param fileName 文件名称
     * @param response 响应
     */
    @SneakyThrows(Exception.class)
    public static void downloadFile(String bucketName, String fileName, HttpServletResponse response) {
        final ObjectStat stat = minioClient.statObject(bucketName, fileName);
        response.setContentType(stat.contentType());
        response.setCharacterEncoding(StringPool.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StringPool.UTF_8));
        InputStream is = minioClient.getObject(bucketName, fileName);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }

    /**
     * 拿到文件下载地址
     *
     * @param bucketName 桶名称
     * @param fileName 文件名称
     * @return 地址
     */
    @SneakyThrows(Exception.class)
    public static String getFileDownloadPath(String bucketName, String fileName) {
        return minioClient.presignedGetObject(bucketName, fileName);
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param fileName 文件名称
     */
    @SneakyThrows(Exception.class)
    public static void delFile(String bucketName, String fileName) {
        minioClient.removeObject(bucketName, fileName);
    }
}
