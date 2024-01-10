/*
 * Copyright @2024 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package design.iterator;


/**
 * Link.
 *
 * @author Carl, 2024-01-10 13:33
 * @version CrisisGo v1.0
 */
public class Link {

    private String from;

    private String to;

    public Link(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
