package com.tienda.ShopServiceAPI.mapper.util;

import com.tienda.ShopServiceAPI.entity.*;
import com.tienda.ShopServiceAPI.mapper.*;
import com.tienda.ShopServiceAPI.security.entity.User;

import java.util.ArrayList;
import java.util.Collection;

public class MapperUtil {

    public static Collection<ProductMapper> convertToProductMapper(Collection<Product> products){
        Collection<ProductMapper> listProductsMapper = new ArrayList<>();
        for(Product product: products){
            listProductsMapper.add(new ProductMapper(product));
        }
        return listProductsMapper;
    }

    public static Collection<CategoryMapper> convertToCategoryMapper(Collection<Category> categories){
        Collection<CategoryMapper> listCategoriesMapper = new ArrayList<>();
        for(Category category: categories){
            listCategoriesMapper.add(new CategoryMapper(category));
        }
        return listCategoriesMapper;
    }

    public static Collection<PaymentMapper> convertToPaymentMapper(Collection<Payment> payments){
        Collection<PaymentMapper> listPaymentMapper = new ArrayList<>();
        for(Payment p: payments){
            listPaymentMapper.add(new PaymentMapper(p));
        }
        return listPaymentMapper;
    }

    public static Collection<SupplierMapper> convertToSupplierMapper(Collection<Supplier> suppliers){
        Collection<SupplierMapper> listSuppliersMapper = new ArrayList<>();
        for(Supplier s: suppliers){
            listSuppliersMapper.add(new SupplierMapper(s));
        }
        return listSuppliersMapper;
    }

    public static Collection<OrdersMapper> convertToOrdersMapper(Collection<Orders> orders){
        Collection<OrdersMapper> listOrdersMapper = new ArrayList<>();
        for(Orders o: orders){
            listOrdersMapper.add(new OrdersMapper(o));
        }
        return listOrdersMapper;
    }

    public static Collection<OrderDetailsMapper> convertToOrderDetailsMapper(Collection<OrderDetails> orderDetails){
        Collection<OrderDetailsMapper> listOrderDetailsMapper = new ArrayList<>();
        for(OrderDetails o: orderDetails){
            listOrderDetailsMapper.add(new OrderDetailsMapper(o));
        }
        return listOrderDetailsMapper;
    }

    public static Collection<UserMapper> convertToUserMapper(Collection<User> users){
        Collection<UserMapper> listUserMapper = new ArrayList<>();
        for(User u: users){
            listUserMapper.add(new UserMapper(u));
        }
        return listUserMapper;
    }
}
