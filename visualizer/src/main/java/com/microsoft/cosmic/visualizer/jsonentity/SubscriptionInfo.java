package com.microsoft.cosmic.visualizer.jsonentity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SubscriptionInfo {

    private String id;
    private String name;
    private String purpose;
    private String location;
    private String instance;

}
