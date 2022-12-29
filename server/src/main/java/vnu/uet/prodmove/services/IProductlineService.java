package vnu.uet.prodmove.services;

import java.util.List;

import vnu.uet.prodmove.entity.Productline;
import vnu.uet.prodmove.exception.NotFoundException;

public interface IProductlineService {
    Productline findById(int id) throws NotFoundException;

    List<Productline> getAllProductLines();
}
