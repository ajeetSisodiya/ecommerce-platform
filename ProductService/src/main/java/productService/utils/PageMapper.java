package productService.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import productService.dtos.PagedResponse;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class PageMapper {

    public static <E,D> PagedResponse<D> toPagedResponse(Page<E> page ,Function<E,D> mapper){
        List<D> content = page.getContent().stream().map(mapper).toList();
        return new PagedResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
