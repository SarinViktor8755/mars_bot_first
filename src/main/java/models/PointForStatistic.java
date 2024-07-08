package models;

import com.pengrad.telegrambot.model.Update;

import java.util.Objects;

public class PointForStatistic {
    private Long id;
    private Long user_id;
    private String first_name;
    private String last_name;
    private String username;

    private Long DataPoint;// дата точки
    private int dist; // дистанция

    public PointForStatistic(Update mes, int distantion) {
        this.id = Long.valueOf(mes.message().messageId());
        this.first_name = mes.message().from().firstName();
        this.last_name = mes.message().from().lastName();
        this.username = mes.message().from().username();
        DataPoint = System.currentTimeMillis();
        this.dist = distantion;
        this.user_id = mes.message().from().id();
    }

    public PointForStatistic() {

    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getDataPoint() {
        return DataPoint;
    }

    public void setDataPoint(Long dataPoint) {
        DataPoint = dataPoint;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }


    @Override
    public String toString() {
        return  id + "," + user_id + "," + first_name + "," + last_name + "," + username + "," + DataPoint + "," + dist;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointForStatistic that = (PointForStatistic) o;
        return Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user_id);
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}



