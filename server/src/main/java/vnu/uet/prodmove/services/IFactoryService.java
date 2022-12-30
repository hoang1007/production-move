package vnu.uet.prodmove.services;

import vnu.uet.prodmove.entity.Factory;

public interface IFactoryService {
    Factory create(String name, String address);

    void delete(Integer id);
}
