package vnu.uet.prodmove.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Factory;
import vnu.uet.prodmove.repos.FactoryRepository;
import vnu.uet.prodmove.services.IFactoryService;

@Service
public class FactoryService implements IFactoryService {

    @Autowired
    private FactoryRepository factoryRepository;

    @Override
    public Factory create(String name, String address) {
        Factory factory = new Factory();
        factory.setName(name);
        factory.setAddress(address);
        return factoryRepository.save(factory);
    }

    @Override
    public void delete(Integer id) {
        factoryRepository.deleteById(id);
    }
    
}
