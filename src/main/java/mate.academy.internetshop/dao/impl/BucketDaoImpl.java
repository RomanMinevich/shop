package mate.academy.internetshop.dao.impl;

import static java.util.stream.IntStream.range;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        if (Storage.buckets
                .stream()
                .anyMatch(element -> element.getId().equals(bucket.getId()))) {
            throw new IllegalArgumentException("Can't add bucket with id " + bucket.getId());
        }
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        return Storage.buckets
                .stream()
                .filter(element -> element.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + id));
    }

    @Override
    public Bucket update(Bucket bucket) {
        int index = range(0, Storage.buckets.size())
                .filter(element -> Storage.buckets.get(element).getId().equals(bucket.getId()))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Cant't find bucket with id " + bucket.getId()));
        Storage.buckets.set(index, bucket);
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        Bucket bucket = get(id);
        Storage.buckets.removeIf(element -> element.equals(bucket));
        return bucket;
    }
}
