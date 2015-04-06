package code.data.operator;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Operator {
    private int ID;
    private String name;
    private double standardSubscriptionCost;
    private double premiumSubscriptionCost;
    private Map <String,Double> foreignCall;
    private Map <String,Double> foreignSms;

    public int getID(){return ID;}
    public String getName(){return name;}

    public void setStandardSubscriptionCost(double standardSubscription) {
        this.standardSubscriptionCost = standardSubscription ;
    }
    public double getStandardSubscriptionCost() {
        return standardSubscriptionCost ;
    }

    public void setPremiumSubscriptionCost(double premiumSubscription) {
        this.premiumSubscriptionCost = premiumSubscription ;
    }
    public double getPremiumSubscriptionCost() {
        return premiumSubscriptionCost ;
    }

    public void setCallCost(String opName, double cost) {
        foreignCall.put(opName, cost) ;
    }
    public Double getCallCost(String opName) {
        return foreignCall.get(opName) ;
    }


    public void setSmsCost(String opName, double cost) {
        foreignSms.put(opName, cost) ;
    }
    public Double getSmsCost(String opName) {
        return foreignSms.get(opName) ;
    }

    public Operator(String name, int ID, double standardSubscriptionCost, double premiumSubscriptionCost) {
        this.name = name ;
        this.ID = ID ;
        this.standardSubscriptionCost = standardSubscriptionCost;
        this.premiumSubscriptionCost = premiumSubscriptionCost;
        foreignCall = new TreeMap<String, Double>() ;
        foreignSms = new TreeMap<String, Double>() ;
    }
}
