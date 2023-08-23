package com.example.mymissyou.api.v1;

import com.example.mymissyou.bo.PageCounter;
import com.example.mymissyou.core.LocalUser;
import com.example.mymissyou.dto.OrderDTO;
import com.example.mymissyou.exception.http.NotFoundException;
import com.example.mymissyou.logic.OrderChecker;
import com.example.mymissyou.model.Order;
import com.example.mymissyou.service.OrderService;
import com.example.mymissyou.util.CommonUtil;
import com.example.mymissyou.vo.OrderIdVO;
import com.example.mymissyou.vo.OrderPureVO;
import com.example.mymissyou.vo.OrderSimplifyVO;
import com.example.mymissyou.vo.PagingDozer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${missyou.order.pay-time-limit}")
    private Long payTimeLimit;

    @PostMapping("")
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO) {
        Long uid = LocalUser.getUser().getId();
        System.out.println(orderDTO);
        System.out.println(orderDTO.getSkuInfoList());
        System.out.println(orderDTO.getTotalPrice());
        OrderChecker orderChecker = this.orderService.isOk(uid, orderDTO);

        Long oid = this.orderService.placeOrder(uid, orderDTO, orderChecker);
        return new OrderIdVO(oid);
    }

    @GetMapping("/detail/{id}")
    public OrderPureVO getOrderDetail(@PathVariable(name = "id") Long oid) {
        Optional<Order> orderOptional = this.orderService.getOrderDetail(oid);
        return orderOptional.map((o) -> new OrderPureVO(o, payTimeLimit))
                .orElseThrow(() -> new NotFoundException(50009));
    }


    @GetMapping("/status/unpaid")
    @SuppressWarnings("unchecked")
    public PagingDozer getUnpaid(@RequestParam(defaultValue = "0")
                                 Integer start,
                                 @RequestParam(defaultValue = "10")
                                 Integer count) {
        PageCounter page = CommonUtil.convertToPageParameter(start, count);
        Page<Order> orderPage = this.orderService.getUnpaid(page.getPage(), page.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(orderPage, OrderSimplifyVO.class);
        pagingDozer.getItems().forEach((o) -> ((OrderSimplifyVO) o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }

    @GetMapping("/by/status/{status}")
    public PagingDozer getByStatus(@PathVariable int status,
                                   @RequestParam(name = "start", defaultValue = "0")
                                   Integer start,
                                   @RequestParam(name = "count", defaultValue = "10")
                                   Integer count) {
        PageCounter page = CommonUtil.convertToPageParameter(start, count);
        Page<Order> paging = this.orderService.getByStatus(status, page.getPage(), page.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(paging, OrderSimplifyVO.class);
        pagingDozer.getItems().forEach(o -> ((OrderSimplifyVO) o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }
}
