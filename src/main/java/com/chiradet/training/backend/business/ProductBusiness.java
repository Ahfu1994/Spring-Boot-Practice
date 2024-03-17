package com.chiradet.training.backend.business;

import com.chiradet.training.backend.exception.BaseException;
import com.chiradet.training.backend.exception.ProductExction;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ProductBusiness {

    public String getProductById(String id) throws BaseException {

        //TODO get data from database
        if (Objects.equals("1234", id)){
            throw ProductExction.notFound();
        }
        return id;
    }
}
