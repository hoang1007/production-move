package vnu.uet.prodmove.services;

import vnu.uet.prodmove.entity.Productline;
import vnu.uet.prodmove.exception.NotFoundException;

public interface IProductlineService {
    Productline findById(int id) throws NotFoundException;
}
