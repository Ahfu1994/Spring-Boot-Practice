package com.chiradet.training.backend.exception;

public class ProductExction extends BaseException{
    public ProductExction(String code) {
        super("product."+code);
    }

    public static ProductExction notFound(){
        return new ProductExction("not.found");
    }


}
