package productService.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import productService.dtos.PagedResponse;
import productService.dtos.ProductResponse;
import productService.exception.ProductNotFoundException;
import productService.mapper.ProductMapper;
import productService.model.Product;
import productService.repository.ProductRepository;
import productService.utils.PageMapper;

@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService{

    private final ProductRepository productRepository;
    protected final ProductMapper mapper;

    public PagedResponse<ProductResponse> getAllProducts(Pageable pageable){
        Page<Product> all = productRepository.findAll(pageable);
        return PageMapper.toPagedResponse(all, mapper::toResponse);//e-> mapper.toResponse(e)
    }


    public ProductResponse getProductById(Long id) throws ProductNotFoundException {
        Product productById = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found with id "+id));
        return mapper.toResponse(productById);
    }
}
