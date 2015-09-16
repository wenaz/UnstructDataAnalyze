package com.unstruct.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by lenovo-ang on 2015/9/16.
 */
@Entity
@Table(name = "hdfs_file", schema = "", catalog = "unstructdata")
public class HdfsFileEntity {
    private int fileId;
    private String fileName;
    private int parentId;
    private int fileSize;
    private int fileType;
    private String fileUrl;
    private Timestamp createTime;
    private Integer userId;

    @Id
    @Column(name = "file_id", nullable = false, insertable = true, updatable = true)
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "file_name", nullable = false, insertable = true, updatable = true, length = 100)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "parent_id", nullable = false, insertable = true, updatable = true)
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "file_size", nullable = true, insertable = true, updatable = true)
    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Basic
    @Column(name = "file_type", nullable = false, insertable = true, updatable = true)
    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "file_url", nullable = false, insertable = true, updatable = true, length = 100)
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Basic
    @Column(name = "create_time", nullable = false, insertable = true, updatable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "user_id", nullable = true, insertable = true, updatable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HdfsFileEntity that = (HdfsFileEntity) o;

        if (fileId != that.fileId) return false;
        if (parentId != that.parentId) return false;
        if (fileSize != that.fileSize) return false;
        if (fileType != that.fileType) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (fileUrl != null ? !fileUrl.equals(that.fileUrl) : that.fileUrl != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileId;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + parentId;
        result = 31 * result + fileSize;
        result = 31 * result + fileType;
        result = 31 * result + (fileUrl != null ? fileUrl.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "["+this.fileId+","+this.fileName+","+this.parentId+","+this.fileSize+","+this.fileType
                +","+this.fileUrl+","+this.createTime+","+this.userId+"]";
    }
}
