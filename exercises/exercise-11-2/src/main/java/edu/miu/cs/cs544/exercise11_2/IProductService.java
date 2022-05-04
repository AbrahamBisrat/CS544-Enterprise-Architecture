package edu.miu.cs.cs544.exercise11_2;

public interface IProductService {
	Product getProduct(int productNumber);
	int getNumberInStock(int productNumber);
}
