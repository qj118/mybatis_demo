package org.demon.domain;

public class StatusGroup {
    private Integer statusId;
    private Integer statusCount;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    @Override
    public String toString() {
        return "StatusGroup{" +
                "statusId=" + statusId +
                ", statusCount=" + statusCount +
                '}';
    }
}
