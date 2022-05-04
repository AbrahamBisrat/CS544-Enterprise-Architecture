package edu.miu.cs.cs544.exercise11_2;

public class InventoryService implements IInventoryService{

    @Override
    public int getNumberInStock(int productNumber) {
        return productNumber - 200;
    }
}
