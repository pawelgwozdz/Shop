package com.pawelgwozdz.shop.facade;

import com.pawelgwozdz.shop.dto.*;
import com.pawelgwozdz.shop.entity.Customer;
import com.pawelgwozdz.shop.entity.Order;
import com.pawelgwozdz.shop.entity.OrderedProduct;
import com.pawelgwozdz.shop.entity.Product;
import com.pawelgwozdz.shop.enums.ExceptionMessage;
import com.pawelgwozdz.shop.enums.OrderStatus;
import com.pawelgwozdz.shop.exceptions.NotEnoughProductsException;
import com.pawelgwozdz.shop.exceptions.OrderNotFoundException;
import com.pawelgwozdz.shop.repository.OrderedProductsRepository;
import com.pawelgwozdz.shop.repository.OrdersRepository;
import com.pawelgwozdz.shop.service.CustomerService;
import com.pawelgwozdz.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrdersFacade {

    private final OrdersRepository ordersRepository;
    private final ProductService productService;
    private final OrderedProductsRepository orderedProductsRepository;
    private final CustomerService customerService;

    @Transactional
    public AddOrderRespDTO addOrder(AddOrderDTO addOrderDTO) {
        List<Product> warehouseProducts = getProductsfromDto(addOrderDTO.getProducts());
        BigDecimal orderPrice = getPriceSum(addOrderDTO.getProducts(), warehouseProducts);
        removeFromWarehouse(addOrderDTO.getProducts(), warehouseProducts);
        Order order = createOrder(addOrderDTO, orderPrice, warehouseProducts);
        createOrderedProducts(addOrderDTO.getProducts(), order, warehouseProducts);
        return new AddOrderRespDTO(order.getId());
    }

    private List<Product> getProductsfromDto(List<AddOrderProductDTO> productDTOs) {
        return productService.findByIdsOrThrow(productDTOs.stream().map(AddOrderProductDTO::getId).collect(Collectors.toList()));
    }

    private BigDecimal getPriceSum(List<AddOrderProductDTO> productDTOs, List<Product> warehouseProducts) {

        BigDecimal orderPrice = new BigDecimal("0");
        for (AddOrderProductDTO productDTO : productDTOs) {
            Product product = warehouseProducts.stream().filter(p -> p.getId().equals(productDTO.getId())).findFirst().orElse(null) ;
            orderPrice = orderPrice.add(product.getPrice().multiply(new BigDecimal(productDTO.getAmount())));
        }
        return orderPrice;
    }

    private void removeFromWarehouse(List<AddOrderProductDTO> productDTOs, List<Product> warehouseProducts) {
        List<Product> updatedProducts = new ArrayList<>();

        for (AddOrderProductDTO productDTO : productDTOs) {
            Product product = warehouseProducts.stream().filter(p -> p.getId().equals(productDTO.getId())).findFirst().orElse(null) ;
            if (product.getAmount() < productDTO.getAmount())
                throw new NotEnoughProductsException(ExceptionMessage.NOT_ENOUGH_PRODUCTS);
            product.setAmount(product.getAmount() - productDTO.getAmount());
            updatedProducts.add(product);
        }
        productService.saveAll(updatedProducts);
    }

    private Order createOrder(AddOrderDTO addOrderDTO, BigDecimal orderPrice, List<Product> warehouseProducts) {

        Customer customer = customerService.findByIdOrThrow(addOrderDTO.getCustomerId());

        Order order = Order.builder()
                .orderDate(addOrderDTO.getOrderDate())
                .customer(customer)
                .price(orderPrice)
                .status(OrderStatus.ORDERED)
                .build();
        return ordersRepository.save(order);
    }

    private void createOrderedProducts(List<AddOrderProductDTO> products, Order order, List<Product> warehouseProducts) {

        List<OrderedProduct> orderedProducts = new ArrayList<>();

        for (AddOrderProductDTO addOrderProductDTO : products) {

            Product product = warehouseProducts.stream().filter(p -> p.getId().equals(addOrderProductDTO.getId())).findFirst().orElse(null) ;
            orderedProducts.add(OrderedProduct.builder()
                    .product(product)
                    .order(order)
                    .amount(addOrderProductDTO.getAmount())
                    .build());
        }

        orderedProductsRepository.saveAll(orderedProducts);
    }

    public void changeStatus(ChangeOrderStatusDTO changeOrderStatusDTO) {
        Order order = ordersRepository.findById(changeOrderStatusDTO.getId()).orElseThrow(() -> new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND, changeOrderStatusDTO.getId()));
        order.setStatus(changeOrderStatusDTO.getOrderStatus());
        ordersRepository.save(order);
    }

    @Transactional
    public void cancelOrder(DeleteOrderDto deleteOrderDto) {
        Order order = ordersRepository.findById(deleteOrderDto.getId()).orElseThrow(() -> new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND, deleteOrderDto.getId()));
        List<OrderedProduct> orderedProducts = orderedProductsRepository.findByOrderId(deleteOrderDto.getId());

        increaseWarehouseProductAmount(orderedProducts);
        orderedProductsRepository.deleteAll(orderedProducts);
        ordersRepository.delete(order);

    }

    private void increaseWarehouseProductAmount(List<OrderedProduct> orderedProducts) {
        List<Product> warehouseProducts = new ArrayList<>();
        for (OrderedProduct orderedProduct : orderedProducts) {
            Product product = orderedProduct.getProduct();
            product.setAmount(product.getAmount() + orderedProduct.getAmount());
            warehouseProducts.add(product);
        }
        productService.saveAll(warehouseProducts);
    }

    public OrderDTO get(Long id) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND, id));

        return createOrderResponse(order);
    }

    private OrderDTO createOrderResponse(Order order) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<OrderedProduct> orderedProducts = orderedProductsRepository.findByOrderId(order.getId());
        for (OrderedProduct orderedProduct : orderedProducts) {
            ProductDTO productDTO = ProductDTO.builder()
                    .name(orderedProduct.getProduct().getName())
                    .description(orderedProduct.getProduct().getDescription())
                    .amount(orderedProduct.getAmount())
                    .id(orderedProduct.getProduct().getId())
                    .price(orderedProduct.getProduct().getPrice())
                    .build();
            productDTOs.add(productDTO);
        }
        OrderDTO orderDTO = OrderDTO.builder()
                .customerID(order.getCustomer().getId())
                .orderedProducts(productDTOs)
                .orderDate(order.getOrderDate())
                .id(order.getId())
                .status(order.getStatus())
                .wholePrice(order.getPrice())
                .build();
        return orderDTO;
    }
}
