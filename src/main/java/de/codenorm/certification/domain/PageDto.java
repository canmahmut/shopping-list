package de.codenorm.certification.domain;


public class PageDto<T> {

    private long total;

    private T data;

    public PageDto(long total, T data) {
        this.total = total;
        this.data = data;
    }

    public PageDto() {
        super();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
