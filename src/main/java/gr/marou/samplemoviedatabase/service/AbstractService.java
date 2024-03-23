package gr.marou.samplemoviedatabase.service;

import gr.marou.samplemoviedatabase.mapper.SMDBMapper;
import gr.marou.samplemoviedatabase.model.BaseEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractService<T extends BaseEntity, D> implements BaseService<T, D, Long> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);
    public abstract JpaRepository<T, Long> getRepository();
    public abstract SMDBMapper<T, D> getMapper();

    @Override
    @Transactional
    public T create(final D dto) {
        T entity = getMapper().toEntity(dto);
        LOG.info("Creating entity: {}", entity.getClass().getName());
        return getRepository().save(entity);
    }

    @Override
    @Transactional
    public List<T> createAll(final List<D> dtos){
        List<T> entities = dtos.stream()
                .map(dto -> {
                    T entity = getMapper().toEntity(dto);
                    LOG.info("Creating entity: {}", entity.getClass().getName());
                    return entity;
                })
                .collect(Collectors.toList());
        return getRepository().saveAll(entities);
    }


    @Override
    public List<D> read() {
        LOG.info("Finding all entities");
        List<T> entities = getRepository().findAll();
        List<D> dtos = new ArrayList<>();
        for (T entity : entities) {
            dtos.add(getMapper().toDto(entity));
        }
        return dtos;
    }

    @Override
    public D read(final Long id) {
        LOG.info("Finding entity by id: {}", id);
        D returnEntity = null;
        Optional<T> repositoryValue = getRepository().findById(id);
        if(repositoryValue.isPresent()){
            T entity = repositoryValue.get();
            returnEntity = getMapper().toDto(entity);
        }
        return returnEntity;
    }

    @Override
    @Transactional
    public void update(D dto) {
        T entity = getMapper().toEntity(dto);
        LOG.info("Updating entity: {}", entity.getClass().getName());
        getRepository().save(entity);
    }


    @Override
    @Transactional
    public void deleteById(final Long id) {
        LOG.info("Soft deleting entity with id: {}", id);
        getRepository().deleteById(id);
    }

    @Override
    public boolean exists(final T entity) {
        LOG.info("Searching if entity {} exists", entity.getClass().getName() );
        return getRepository().existsById(entity.getId());
    }


}
