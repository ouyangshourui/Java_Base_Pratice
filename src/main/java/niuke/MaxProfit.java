/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;


/**
 * @author yougu
 * @version : MaxProfit.java, v 0.1 2021年06月07日 11:12 上午 yougu Exp $
 */
public class MaxProfit {

    public int maxProfit(int[] prices){
        if(prices==null || prices.length==0){
            return 0;
        }
        int max =  0;
        int min= prices[0];
        for(int i =0 ;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            max  = Math.max(max,prices[i]-min);
        }
        return max;
    }
}