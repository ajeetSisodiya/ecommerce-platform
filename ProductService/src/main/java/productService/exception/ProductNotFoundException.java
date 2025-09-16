package productService.exception;

public class ProductNotFoundException extends Exception{
  public ProductNotFoundException(String message){
      super(message);
  }
}
