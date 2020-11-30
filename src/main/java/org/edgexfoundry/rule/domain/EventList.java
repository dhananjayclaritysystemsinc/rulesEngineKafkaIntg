package org.edgexfoundry.rule.domain;

import java.util.List;

public class EventList{
    public String id;
    public String isA;
    public String eventTime;
    public String eventTimeZoneOffset;
    public String parentID;
    public List<String> childEPCs;
    public String action;
    public String bizStep;
    public String disposition;
    public String readPoint;
    public String bizLocation;
    public List<ChildQuantityList> childQuantityList;
}