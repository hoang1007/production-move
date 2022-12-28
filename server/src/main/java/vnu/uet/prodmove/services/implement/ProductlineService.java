package vnu.uet.prodmove.services.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Productline;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.ProductlineRepository;
import vnu.uet.prodmove.services.IProductlineService;

@Service
public class ProductlineService implements IProductlineService {

    @Autowired
    private ProductlineRepository productlineRepository;

    @Override
    public Productline findById(int id) throws NotFoundException {
        Optional<Productline> wrapperProductline = productlineRepository.findById(id);
        if (wrapperProductline.isPresent())
            return wrapperProductline.get();
        throw new NotFoundException("This productline is not found");
    }
}
