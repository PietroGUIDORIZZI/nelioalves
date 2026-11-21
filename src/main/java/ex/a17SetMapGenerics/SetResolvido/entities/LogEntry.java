package ex.a17SetMapGenerics.SetResolvido.entities;

import java.util.Date;

public class LogEntry {
    private String userName;
    private Date   date;



    public LogEntry(String name, Date date) {
        this.userName = name;
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LogEntry logEntry = (LogEntry) o;
        return getUserName().equals(logEntry.getUserName());
    }

    @Override
    public int hashCode() {
        return getUserName().hashCode();
    }
}
