package com.example.mymissyou.model;

import com.example.mymissyou.core.enumeration.OrderStatus;
import com.example.mymissyou.dto.OrderAddressDTO;
import com.example.mymissyou.util.CommonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.example.mymissyou.util.GenericAndJson.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null")
@Table(name = "`Order`")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal totalPrice;
    private Long totalCount;
    private String snapImg;
    private String snapTitle;
    private Date expiredTime;
    private Date placedTime;

    private String snapItems;

    private String snapAddress;

    private String prepayId;
    private BigDecimal finalTotalPrice;
    private Integer status;

    @JsonIgnore
    public OrderStatus getStatusEnum() {
        return OrderStatus.toType(this.status);
    }

    public Boolean needCancel() {
        if (!this.getStatusEnum().equals(OrderStatus.UNPAID)) {
            return true;
        }
        boolean isOutOfDate = CommonUtil.isOutOfDate(this.getExpiredTime());
        if (isOutOfDate) {
            return true;
        }
        return false;
    }

    //
    public void setSnapItems(List<OrderSku> orderSkuList) {
        if (orderSkuList.isEmpty()) {
            return;
        }
        this.snapItems = objectToJson(orderSkuList);
    }

    public List<OrderSku> getSnapItems() {
        List<OrderSku> list = jsonToObject(this.snapItems,
                new TypeReference<List<OrderSku>>() {
                });
        return list;
    }


    public OrderAddressDTO getSnapAddress() {
        if (this.snapAddress == null) {
            return null;
        }
        OrderAddressDTO o = jsonToObject(this.snapAddress,
                new TypeReference<OrderAddressDTO>() {
                });
        return o;
    }

    public void setSnapAddress(OrderAddressDTO address) {
        this.snapAddress = objectToJson(address);
    }
}
