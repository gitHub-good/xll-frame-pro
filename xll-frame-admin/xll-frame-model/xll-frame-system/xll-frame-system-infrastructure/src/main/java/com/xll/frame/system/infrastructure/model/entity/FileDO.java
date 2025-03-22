package com.xll.frame.system.infrastructure.model.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.model.entity.BaseDO;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.core.util.StrUtils;
import com.xll.frame.system.infrastructure.enums.FileTypeEnum;
import com.xll.frame.system.infrastructure.enums.StorageTypeEnum;
import lombok.Data;
import lombok.SneakyThrows;
import org.dromara.x.file.storage.core.FileInfo;

import java.io.Serial;
import java.net.URL;

/**
 * 功能描述: <br>
 * <p>
 *  <文件实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:32
 * @version 1.0.0
 */
@Data
@TableName("sys_file")
public class FileDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 大小（字节）
     */
    private Long size;

    /**
     * URL
     */
    private String url;

    /**
     * 扩展名
     */
    private String extension;

    /**
     * 类型
     */
    private FileTypeEnum type;

    /**
     * 缩略图大小（字节)
     */
    private Long thumbnailSize;

    /**
     * 缩略图URL
     */
    private String thumbnailUrl;

    /**
     * 存储 ID
     */
    private Long storageId;

    /**
     * 转换为 X-File-Storage 文件信息对象
     *
     * @param storageDO 存储桶信息
     * @return X-File-Storage 文件信息对象
     */
    public FileInfo toFileInfo(StorageDO storageDO) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUrl(this.url);
        fileInfo.setSize(this.size);
        fileInfo.setFilename(StrUtil.contains(this.url, StringConstants.SLASH)
            ? StrUtil.subAfter(this.url, StringConstants.SLASH, true)
            : this.url);
        fileInfo.setOriginalFilename(StrUtils
            .blankToDefault(this.extension, this.name, ex -> this.name + StringConstants.DOT + ex));
        fileInfo.setBasePath(StringConstants.EMPTY);
        // 优化 path 处理
        fileInfo.setPath(extractRelativePath(this.url, storageDO));

        fileInfo.setExt(this.extension);
        fileInfo.setPlatform(storageDO.getCode());
        fileInfo.setThUrl(this.thumbnailUrl);
        fileInfo.setThFilename(StrUtil.contains(this.thumbnailUrl, StringConstants.SLASH)
            ? StrUtil.subAfter(this.thumbnailUrl, StringConstants.SLASH, true)
            : this.thumbnailUrl);
        fileInfo.setThSize(this.thumbnailSize);
        return fileInfo;
    }

    /**
     * 将文件路径处理成资源路径
     * 例如:
     * http://domain.cn/bucketName/2024/11/27/6746ec3b2907f0de80afdd70.png => 2024/11/27/
     * http://bucketName.domain.cn/2024/11/27/6746ec3b2907f0de80afdd70.png => 2024/11/27/
     *
     * @param url       文件路径
     * @param storageDO 存储桶信息
     * @return
     */
    @SneakyThrows
    private static String extractRelativePath(String url, StorageDO storageDO) {
        url = StrUtil.subBefore(url, StringConstants.SLASH, true) + StringConstants.SLASH;
        if (storageDO.getType().equals(StorageTypeEnum.LOCAL)) {
            return url;
        }
        // 提取 URL 中的路径部分
        String fullPath = new URL(url).getPath();
        // 移除开头的斜杠
        String relativePath = fullPath.startsWith(StringConstants.SLASH) ? fullPath.substring(1) : fullPath;
        // 如果路径以 bucketName 开头，则移除 bucketName 例如: bucketName/2024/11/27/ -> 2024/11/27/
        if (relativePath.startsWith(storageDO.getBucketName())) {
            return StrUtil.split(relativePath, storageDO.getBucketName()).get(1);
        }
        return relativePath;
    }

}
