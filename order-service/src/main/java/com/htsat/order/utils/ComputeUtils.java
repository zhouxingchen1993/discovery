package com.htsat.order.utils;

import com.htsat.order.dto.DeliveryDTO;
import com.htsat.order.dto.OrderSKUDTO;

import java.math.BigDecimal;
import java.util.List;

public class ComputeUtils {
    public static int computeNumber(List<OrderSKUDTO> orderSKUDTOList) {
        int quantity = 0;
        for (int i = 0; i < orderSKUDTOList.size(); i++) {
            quantity += orderSKUDTOList.get(i).getQuantity();
        }
        return quantity;
    }

    public static BigDecimal computeDiscount(List<OrderSKUDTO> orderSKUDTOList) {
        BigDecimal discount = new BigDecimal(0);
        for (int i = 0; i < orderSKUDTOList.size(); i++) {
            BigDecimal quantity = new BigDecimal(orderSKUDTOList.get(i).getQuantity());
            BigDecimal sDiscount = orderSKUDTOList.get(i).getDiscount();
            if (sDiscount == null)
                sDiscount = new BigDecimal(0);
            discount = discount.add(sDiscount.multiply(quantity));
        }
        return discount;
    }

    public static BigDecimal computeTotalPrice(List<OrderSKUDTO> orderSKUDTOList, DeliveryDTO deliveryDTO) {
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal deliveryPrice = deliveryDTO.getNdeliveryprice();
        for (int i = 0; i < orderSKUDTOList.size(); i++) {
            BigDecimal displayPrice = orderSKUDTOList.get(i).getPrice();
            BigDecimal quantity = new BigDecimal(orderSKUDTOList.get(i).getQuantity());
            BigDecimal price = displayPrice.multiply(quantity);

            totalPrice = totalPrice.add(price);
        }
        totalPrice = totalPrice.add(deliveryPrice);
        return totalPrice;
    }
}
